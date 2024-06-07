package com.yuji.contentcore.template.tag;

import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.StaticizeConstants;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.enums.TagAttrDataType;
import com.yuji.common.staticize.tag.AbstractTag;
import com.yuji.common.staticize.tag.TagAttr;
import com.yuji.contentcore.core.IPageWidgetType;
import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.contentcore.service.ICmsPageWidgetService;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CmsPageWidgetDataTag extends AbstractTag {

	public final static String TAG_NAME = "cms_pagewidget_data";
	public final static String NAME = "{FREEMARKER.TAG.NAME." + TAG_NAME + "}";
	public final static String DESC = "{FREEMARKER.TAG.DESC." + TAG_NAME + "}";
	
	final static String TagAttr_Code = "code";
	
	private final ICmsPageWidgetService cmsPageWidgetService;

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
	@Override
	public List<TagAttr> getTagAttrs() {
		List<TagAttr> tagAttrs = new ArrayList<>();
		tagAttrs.add(new TagAttr(TagAttr_Code, true, TagAttrDataType.STRING, "页面部件编码"));
		return tagAttrs;
	}

	@Override
	public Map<String, TemplateModel> execute0(Environment env, Map<String, String> attrs)
			throws TemplateException, IOException {
		String code = attrs.get(TagAttr_Code);
		if (StringUtils.isEmpty(code)) {
			throw new TemplateException("参数[code]不能为空", env);
		}
		Long siteId = FreeMarkerUtils.evalLongVariable(env, "Site.siteId");
		
		/*LambdaQueryWrapper<CmsPageWidget> q = new LambdaQueryWrapper<CmsPageWidget>()
				.eq(CmsPageWidget::getSiteId, siteId)
				.eq(CmsPageWidget::getCode, code);
		CmsPageWidget pageWidget = cmsPageWidgetService.getOne(q);
		Assert.notNull(pageWidget, () -> new TemplateException(StringUtils.messageFormat("Tag attr[code={0}] data not found.", code), env));

		IPageWidgetType pwt = cmsPageWidgetService.getPageWidgetType(pageWidget.getType());
		Assert.notNull(pwt, () -> new TemplateException(StringUtils.messageFormat("Unknow page widget type：{0}", pageWidget.getType()), env));
		
		TemplateContext context = FreeMarkerUtils.getTemplateContext(env);
		Object contentObj = pwt.parseContent(pageWidget, context.getPublishPipeCode(), context.isPreview());
		pageWidget.setContentObj(contentObj);
		return Map.of(StaticizeConstants.TemplateVariable_Data, this.wrap(env, pageWidget));*/
		return null;
	}
}
