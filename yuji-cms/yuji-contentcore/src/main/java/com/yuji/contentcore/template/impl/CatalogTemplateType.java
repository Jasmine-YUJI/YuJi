package com.yuji.contentcore.template.impl;

import com.yuji.common.core.utils.ConvertUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.properties.CatalogPageSizeProperty;
import com.yuji.contentcore.service.ICmsCatalogService;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.template.ITemplateType;
import com.yuji.contentcore.utils.TemplateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component(ITemplateType.BEAN_NAME_PREFIX + CatalogTemplateType.TypeId)
public class CatalogTemplateType implements ITemplateType {
	
	public final static String TypeId = "CatalogIndex";

	private final ICmsSiteService siteService;

	private final ICmsCatalogService catalogService;

	@Override
	public String getId() {
		return TypeId;
	}

	@Override
	public void initTemplateData(Object dataId, TemplateContext context) {
		CmsCatalog catalog = this.catalogService.selectCmsCatalogByCatalogId(ConvertUtils.toLong(dataId));
		CmsSite site = this.siteService.selectCmsSiteBySiteId(catalog.getSiteId());
		TemplateUtils.addCatalogVariables(site, catalog, context);

		int pageSize = CatalogPageSizeProperty.getValue(catalog.getConfigProps(), site.getConfigProps());
		context.setPageSize(pageSize);
	}
}
