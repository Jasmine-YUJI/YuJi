package com.yuji.contentcore.service.impl;

import java.util.List;
import com.yuji.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsResourceMapper;
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.service.ICmsResourceService;

/**
 * 资源Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsResourceServiceImpl implements ICmsResourceService 
{
    @Autowired
    private CmsResourceMapper cmsResourceMapper;

    /**
     * 查询资源
     * 
     * @param resourceId 资源主键
     * @return 资源
     */
    @Override
    public CmsResource selectCmsResourceByResourceId(Long resourceId)
    {
        return cmsResourceMapper.selectCmsResourceByResourceId(resourceId);
    }

    /**
     * 查询资源列表
     * 
     * @param cmsResource 资源
     * @return 资源
     */
    @Override
    public List<CmsResource> selectCmsResourceList(CmsResource cmsResource)
    {
        return cmsResourceMapper.selectCmsResourceList(cmsResource);
    }

    /**
     * 新增资源
     * 
     * @param cmsResource 资源
     * @return 结果
     */
    @Override
    public int insertCmsResource(CmsResource cmsResource)
    {
        cmsResource.setCreateTime(DateUtils.getNowDate());
        return cmsResourceMapper.insertCmsResource(cmsResource);
    }

    /**
     * 修改资源
     * 
     * @param cmsResource 资源
     * @return 结果
     */
    @Override
    public int updateCmsResource(CmsResource cmsResource)
    {
        cmsResource.setUpdateTime(DateUtils.getNowDate());
        return cmsResourceMapper.updateCmsResource(cmsResource);
    }

    /**
     * 批量删除资源
     * 
     * @param resourceIds 需要删除的资源主键
     * @return 结果
     */
    @Override
    public int deleteCmsResourceByResourceIds(Long[] resourceIds)
    {
        return cmsResourceMapper.deleteCmsResourceByResourceIds(resourceIds);
    }

    /**
     * 删除资源信息
     * 
     * @param resourceId 资源主键
     * @return 结果
     */
    @Override
    public int deleteCmsResourceByResourceId(Long resourceId)
    {
        return cmsResourceMapper.deleteCmsResourceByResourceId(resourceId);
    }
}
