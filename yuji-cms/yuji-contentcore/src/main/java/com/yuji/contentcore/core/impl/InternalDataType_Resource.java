package com.yuji.contentcore.core.impl;

import com.yuji.common.core.enums.FileType;
import com.yuji.contentcore.core.IInternalDataType;
import com.yuji.contentcore.core.InternalURL;
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.utils.ResourceUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

/**
 * 内部数据类型：资源
 *
 * @author Liguqoiang
 */
@RequiredArgsConstructor
@Component(IInternalDataType.BEAN_NAME_PREFIX + InternalDataType_Resource.ID)
public class InternalDataType_Resource implements IInternalDataType {

	public final static String ID = "resource";

	private static final String InternalUrl_Param_SiteId = "sid"; // 内部链接参数：站点ID

	private static final String InternalUrl_Param_StorageType = "st"; // 内部链接参数：存储方式

	private final ICmsSiteService cmsSiteService;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getLink(InternalURL internalUrl, int pageIndex, String publishPipeCode, boolean isPreview) {
		long siteId = MapUtils.getLongValue(internalUrl.getParams(), InternalUrl_Param_SiteId);
		CmsSite site = cmsSiteService.selectCmsSiteBySiteId(siteId);
		String fileType = MapUtils.getString(internalUrl.getParams(), InternalUrl_Param_StorageType, FileType.LOCAL.getCode());

		String prefix = ResourceUtils.getResourcePrefix(fileType, site, isPreview);
		return prefix + internalUrl.getPath();
	}

	/**
	 * 资源文件内部链接比较特殊，很多地方使用，路径不会变化且不缓存，不适合每次解析都从数据库读取资源信息，因此直接将路径放到内部链接上，后续解析仅需添加上站点资源地址前缀即可。
	 *
	 * @param resource
	 * @return
	 */
	public static String getInternalUrl(CmsResource resource) {
		InternalURL internalURL = new InternalURL(ID, resource.getResourceId(), resource.getPath());
		internalURL.addParam(InternalUrl_Param_SiteId, resource.getSiteId().toString());
		internalURL.addParam(InternalUrl_Param_StorageType, resource.getStorageType());
		return internalURL.toIUrl();
	}
}
