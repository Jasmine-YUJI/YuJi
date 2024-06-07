package com.yuji.contentcore.core.impl;

import com.yuji.contentcore.core.IInternalDataType;
import com.yuji.contentcore.core.InternalURL;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.service.ICmsCatalogService;
import com.yuji.contentcore.service.ICmsPublishpipeService;
import com.yuji.contentcore.service.IPublishService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 内部数据类型：栏目
 *
 * @author Liguoqiang
 */
@RequiredArgsConstructor
@Component(IInternalDataType.BEAN_NAME_PREFIX + InternalDataType_Catalog.ID)
public class InternalDataType_Catalog implements IInternalDataType {

	public final static String ID = "catalog";
	
	private final ICmsCatalogService cmsCatalogService;
	
	private final IPublishService publishService;
	
	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public String getPageData(RequestData requestData) throws IOException, TemplateException {
		CmsCatalog catalog = cmsCatalogService.selectCmsCatalogByCatalogId(requestData.getDataId());
		//TODO 待解决
		boolean listFlag = "Y".equals(requestData.getParams().get("list"));
		return this.publishService.getCatalogPageData(catalog, requestData.getPageIndex(), listFlag, requestData.getPublishPipeCode(), requestData.isPreview());
	}

	@Override
	public String getLink(InternalURL internalUrl, int pageIndex, String publishPipeCode, boolean isPreview) {
		CmsCatalog catalog = cmsCatalogService.selectCmsCatalogByCatalogId(internalUrl.getId());
		return this.cmsCatalogService.getCatalogLink(catalog, pageIndex, publishPipeCode, isPreview);
	}
}
