package com.yuji.contentcore.core.impl;

import com.yuji.common.core.exception.CommonErrorCode;
import com.yuji.common.core.utils.Assert;
import com.yuji.contentcore.core.IInternalDataType;
import com.yuji.contentcore.core.InternalURL;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.service.ICmsContentService;
import com.yuji.contentcore.service.ICmsPublishpipeService;
import com.yuji.contentcore.service.IPublishService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 内部数据类型：内容
 *
 * @author Liguoqiang
 */
@RequiredArgsConstructor
@Component(IInternalDataType.BEAN_NAME_PREFIX + InternalDataType_Content.ID)
public class InternalDataType_Content implements IInternalDataType {

    public final static String ID = "content";

    private final ICmsContentService cmsContentService;

    private final IPublishService publishService;
    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getPageData(RequestData requestData) throws IOException, TemplateException {
        CmsContent content = cmsContentService.selectCmsContentByContentId(requestData.getDataId());
        Assert.notNull(content, () -> CommonErrorCode.DATA_NOT_FOUND_BY_ID.exception("contentId", requestData.getDataId()));

        return publishService.getContentPageData(content, requestData.getPageIndex(), requestData.getPublishPipeCode(), requestData.isPreview());
    }

    @Override
    public String getLink(InternalURL internalUrl, int pageIndex, String publishPipeCode, boolean isPreview) {
        CmsContent content = cmsContentService.selectCmsContentByContentId(internalUrl.getId());
        Assert.notNull(content, () -> CommonErrorCode.DATA_NOT_FOUND_BY_ID.exception("contentId", internalUrl.getId()));

        return cmsContentService.getContentLink(content, 1, publishPipeCode, isPreview);
    }
}
