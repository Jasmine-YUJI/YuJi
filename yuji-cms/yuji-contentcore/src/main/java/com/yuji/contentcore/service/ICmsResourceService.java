package com.yuji.contentcore.service;

import java.io.IOException;
import java.util.List;
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.domain.dto.ResourceUploadDTO;

/**
 * 资源Service接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface ICmsResourceService 
{
    /**
     * 查询资源
     * 
     * @param resourceId 资源主键
     * @return 资源
     */
    public CmsResource selectCmsResourceByResourceId(Long resourceId);

    /**
     * 查询资源列表
     * 
     * @param cmsResource 资源
     * @return 资源集合
     */
    public List<CmsResource> selectCmsResourceList(CmsResource cmsResource);

    /**
     * 新增资源
     * 
     * @param dto 资源
     * @return 结果
     */
    public CmsResource insertCmsResource(ResourceUploadDTO dto) throws IOException;

    /**
     * 修改资源
     * 
     * @param dto 资源
     * @return 结果
     */
    public CmsResource updateCmsResource(ResourceUploadDTO dto) throws IOException;

    /**
     * 批量删除资源
     * 
     * @param resourceIds 需要删除的资源主键集合
     * @return 结果
     */
    public int deleteCmsResourceByResourceIds(Long[] resourceIds);

    /**
     * 删除资源信息
     * 
     * @param resourceId 资源主键
     * @return 结果
     */
    public int deleteCmsResourceByResourceId(Long resourceId);

    /**
     * 获取资源访问路径
     *
     * @param resource 素材信息
     * @param preview 是否预览模式
     */
    String getResourceLink(CmsResource resource, boolean preview);

}
