package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsResource;

/**
 * 资源Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsResourceMapper 
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
     * @param cmsResource 资源
     * @return 结果
     */
    public int insertCmsResource(CmsResource cmsResource);

    /**
     * 修改资源
     * 
     * @param cmsResource 资源
     * @return 结果
     */
    public int updateCmsResource(CmsResource cmsResource);

    /**
     * 删除资源
     * 
     * @param resourceId 资源主键
     * @return 结果
     */
    public int deleteCmsResourceByResourceId(Long resourceId);

    /**
     * 批量删除资源
     * 
     * @param resourceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsResourceByResourceIds(Long[] resourceIds);
}
