package com.yuji.contentcore.service.impl;

import java.util.List;
import com.yuji.common.core.utils.DateUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.core.IInternalDataType;
import com.yuji.contentcore.core.impl.InternalDataType_Content;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.fixed.config.BackendContext;
import com.yuji.contentcore.service.ICmsCatalogService;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.utils.InternalUrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsContentMapper;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.service.ICmsContentService;

/**
 * 内容管理Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsContentServiceImpl implements ICmsContentService 
{
    @Autowired
    private CmsContentMapper cmsContentMapper;

    @Autowired
    private ICmsSiteService cmsSiteService;

    @Autowired
    private ICmsCatalogService cmsCatalogService;

    /**
     * 查询内容管理
     * 
     * @param contentId 内容管理主键
     * @return 内容管理
     */
    @Override
    public CmsContent selectCmsContentByContentId(Long contentId)
    {
        return cmsContentMapper.selectCmsContentByContentId(contentId);
    }

    @Override
    public List<CmsContent>  selectCmsContentByContentIdInList(Long[] contentIds){
        return cmsContentMapper.selectCmsContentByContentIdInList(contentIds);
    }


    /**
     * 查询内容管理列表
     * 
     * @param cmsContent 内容管理
     * @return 内容管理
     */
    @Override
    public List<CmsContent> selectCmsContentList(CmsContent cmsContent)
    {
        return cmsContentMapper.selectCmsContentList(cmsContent);
    }

    /**
     * 新增内容管理
     * 
     * @param cmsContent 内容管理
     * @return 结果
     */
    @Override
    public int insertCmsContent(CmsContent cmsContent)
    {
        cmsContent.setCreateTime(DateUtils.getNowDate());
        return cmsContentMapper.insertCmsContent(cmsContent);
    }

    /**
     * 修改内容管理
     * 
     * @param cmsContent 内容管理
     * @return 结果
     */
    @Override
    public int updateCmsContent(CmsContent cmsContent)
    {
        cmsContent.setUpdateTime(DateUtils.getNowDate());
        return cmsContentMapper.updateCmsContent(cmsContent);
    }

    /**
     * 批量删除内容管理
     * 
     * @param contentIds 需要删除的内容管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsContentByContentIds(Long[] contentIds)
    {
        return cmsContentMapper.deleteCmsContentByContentIds(contentIds);
    }

    /**
     * 删除内容管理信息
     * 
     * @param contentId 内容管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsContentByContentId(Long contentId)
    {
        return cmsContentMapper.deleteCmsContentByContentId(contentId);
    }

    /**
     * 获取内容链接
     *
     * @param content
     * @param publishPipeCode
     * @param isPreview
     * @return
     */
    @Override
    public String getContentLink(CmsContent content, int pageIndex, String publishPipeCode, boolean isPreview) {
        if (content.isLinkContent()) {
            return InternalUrlUtils.getActualUrl(content.getRedirectUrl(), publishPipeCode, isPreview);
        }
        if (isPreview) {
            String previewPath = IInternalDataType.getPreviewPath(InternalDataType_Content.ID, content.getContentId(),
                    publishPipeCode, pageIndex);
            return BackendContext.getValue() + previewPath;
        }
        CmsSite site = cmsSiteService.selectCmsSiteBySiteId(content.getSiteId());
        CmsCatalog catalog = cmsCatalogService.selectCmsCatalogByCatalogId(content.getCatalogId());
        if (catalog.isStaticize()) {
            String contentPath = content.getStaticPath();
            if (StringUtils.isEmpty(contentPath)) {
                contentPath = catalog.getPath() + content.getContentId() + "." + site.getStaticSuffix(publishPipeCode);
            }
            return site.getUrl(publishPipeCode) + contentPath;
        } else {
            String viewPath = IInternalDataType.getViewPath(InternalDataType_Content.ID, content.getContentId(),
                    publishPipeCode, pageIndex);
            return site.getUrl(publishPipeCode) + viewPath;
        }
    }
}
