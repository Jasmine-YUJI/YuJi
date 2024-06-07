package com.yuji.contentcore.core.impl;

import com.yuji.contentcore.core.IInternalDataType;
import com.yuji.contentcore.core.InternalURL;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.service.IPublishService;
import com.yuji.contentcore.utils.SiteUtils;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 内部数据类型：站点
 *
 * @author 兮玥
 * @email 190785909@qq.com
 */
@RequiredArgsConstructor
@Component(IInternalDataType.BEAN_NAME_PREFIX + InternalDataType_Site.ID)
public class InternalDataType_Site implements IInternalDataType {

	public final static String ID = "site";
	
	private final ICmsSiteService cmsSiteService;
	
	private final IPublishService publishService;
	
	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getPageData(RequestData requestData) throws IOException, TemplateException {
		CmsSite site = cmsSiteService.selectCmsSiteBySiteId(requestData.getDataId());
		return publishService.getSitePageData(site, requestData.getPublishPipeCode(), requestData.isPreview());
	}

	@Override
	public String getLink(InternalURL internalUrl, int pageIndex, String publishPipeCode, boolean isPreview) {
		CmsSite site = cmsSiteService.selectCmsSiteBySiteId(internalUrl.getId());
		return SiteUtils.getSiteLink(site, publishPipeCode, isPreview);
	}
}
