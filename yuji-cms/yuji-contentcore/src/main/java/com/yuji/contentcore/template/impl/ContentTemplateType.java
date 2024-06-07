package com.yuji.contentcore.template.impl;

import com.yuji.common.core.utils.ConvertUtils;
import com.yuji.common.core.utils.ReflectASMUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.fixed.dict.ContentAttribute;
import com.yuji.contentcore.service.ICmsCatalogService;
import com.yuji.contentcore.service.ICmsContentService;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.template.ITemplateType;
import com.yuji.contentcore.utils.TemplateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component(ITemplateType.BEAN_NAME_PREFIX + ContentTemplateType.TypeId)
public class ContentTemplateType implements ITemplateType {

	public final static String TypeId = "Content";

	private final ICmsContentService contentService;

	private final ICmsSiteService siteService;

	private final ICmsCatalogService catalogService;

	@Override
	public String getId() {
		return TypeId;
	}

	@Override
	public void initTemplateData(Object dataId, TemplateContext context) {
		CmsContent content = this.contentService.selectCmsContentByContentId(ConvertUtils.toLong(dataId));
		Map<String, Object> contentMap = ReflectASMUtils.beanToMap(content);
		String link = this.contentService.getContentLink(content, 1,
				context.getPublishPipeCode(), context.isPreview());
		contentMap.put(TemplateUtils.TemplateVariable_OBJ_Link, link);
		contentMap.put("attributes", ContentAttribute.convertStr(content.getAttributes()));
		context.getVariables().put(TemplateUtils.TemplateVariable_Content, contentMap);

		CmsSite site = this.siteService.selectCmsSiteBySiteId(content.getSiteId());
		CmsCatalog catalog = this.catalogService.selectCmsCatalogByCatalogId(content.getCatalogId());
		TemplateUtils.addCatalogVariables(site, catalog, context);
	}
}
