package com.yuji.contentcore.template.tag;

import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.enums.TagAttrDataType;
import com.yuji.common.staticize.tag.AbstractTag;
import com.yuji.common.staticize.tag.TagAttr;
import com.yuji.contentcore.core.IPageWidgetType;
import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.properties.EnableSSIProperty;
import com.yuji.contentcore.service.ICmsPageWidgetService;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.service.ICmsTemplateService;
import com.yuji.contentcore.utils.PageWidgetUtils;
import com.yuji.contentcore.utils.SiteUtils;
import com.yuji.contentcore.utils.TemplateUtils;
import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CmsPageWidgetTag extends AbstractTag {

	public final static String TAG_NAME = "cms_pagewidget";
	public final static String NAME = "{FREEMARKER.TAG.NAME." + TAG_NAME + "}";
	public final static String DESC = "{FREEMARKER.TAG.DESC." + TAG_NAME + "}";

	@Override
	public String getTagName() {
		return TAG_NAME;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public String getDescription() {
		return DESC;
	}

	final static String TagAttr_Code = "code";

	final static String TagAttr_SSI = "ssi";
	
	private final ICmsSiteService cmsSiteService;

	private final ICmsPageWidgetService cmsPageWidgetService;

	private final ICmsTemplateService cmsTemplateService;

	@Override
	public List<TagAttr> getTagAttrs() {
		List<TagAttr> tagAttrs = new ArrayList<>();
		tagAttrs.add(new TagAttr(TagAttr_Code, true, TagAttrDataType.STRING, "页面部件编码"));
		tagAttrs.add(new TagAttr(TagAttr_SSI, false, TagAttrDataType.BOOLEAN, "是否启用SSI"));
		return tagAttrs;
	}

	@Override
	public Map<String, TemplateModel> execute0(Environment env, Map<String, String> attrs)
			throws TemplateException, IOException {
		TemplateContext context = FreeMarkerUtils.getTemplateContext(env);

		String code = attrs.get(TagAttr_Code);
		Assert.notEmpty(code, () -> new TemplateException("参数[code]不能为空", env));

		long siteId = FreeMarkerUtils.evalLongVariable(env, "Site.siteId");
		CmsPageWidget cmsPageWidget = new CmsPageWidget();
		cmsPageWidget.setSiteId(siteId);
		cmsPageWidget.setCode(code);
		CmsPageWidget pw = cmsPageWidgetService.selectCmsPageWidgetList(cmsPageWidget).get(0);
		Assert.notNull(pw, () -> new TemplateException(StringUtils.messageFormat("页面部件[{0}]不存在", code), env));

		IPageWidgetType pwt = cmsPageWidgetService.getPageWidgetType(pw.getType());
		Assert.notNull(pwt, () -> new TemplateException(StringUtils.messageFormat("页面部件类型错误：{0}", pw.getType()), env));
		
		CmsSite site = cmsSiteService.selectCmsSiteBySiteId(siteId);
		File templateFile = cmsTemplateService.findTemplateFile(site, pw.getTemplate(), context.getPublishPipeCode());
		Assert.notNull(templateFile, () -> new TemplateException(StringUtils.messageFormat("页面部件[{0}]指定模板[{1}]不存在", code, pw.getTemplate()), env));

		boolean ssi = MapUtils.getBoolean(attrs, TagAttr_SSI, EnableSSIProperty.getValue(site.getConfigProps()));
		String templateKey = SiteUtils.getTemplateKey(site, pw.getPublishPipeCode(), pw.getTemplate());
		if (context.isPreview()) {
			env.getOut().write(this.processTemplate(env, pw, templateKey));
		} else {
			String siteRoot = SiteUtils.getSiteRoot(site, context.getPublishPipeCode());
			String staticFileName = PageWidgetUtils.getStaticFileName(pw, site.getStaticSuffix(pw.getPublishPipeCode()));
			String staticFilePath = pw.getPath() + staticFileName;
			if (ssi) {
				// 读取页面部件静态化内容
				String staticContent = cmsTemplateService.getTemplateStaticContentCache(templateKey);
				if (Objects.isNull(staticContent) || !new File(siteRoot + staticFilePath).exists()) {
					staticContent = this.processTemplate(env, pw, templateKey);
					FileUtils.writeStringToFile(new File(siteRoot + staticFilePath), staticContent, StandardCharsets.UTF_8);
					cmsTemplateService.setTemplateStaticContentCache(templateKey, staticContent);
				}
				env.getOut().write(StringUtils.messageFormat(CmsIncludeTag.SSI_INCLUDE_TAG, "/" + staticFilePath));
			} else {
				// 非ssi模式无法使用缓存
				String staticContent = this.processTemplate(env, pw, templateKey);
				env.getOut().write(staticContent);
			}
		}
		return null;
	}
	
	private String processTemplate(Environment env, CmsPageWidget pageWidget, String templateName)
			throws TemplateException, IOException {
		Writer out = env.getOut();
		try (StringWriter writer = new StringWriter()) {
			env.setOut(writer);
			Template includeTemplate = env.getTemplateForInclusion(templateName,
					StandardCharsets.UTF_8.displayName(), true);
			env.setVariable(TemplateUtils.TemplateVariable_PageWidget, wrap(env, pageWidget));
			env.include(includeTemplate);
			return writer.getBuffer().toString();
		} finally {
			env.setOut(out);
		}
	}
}
