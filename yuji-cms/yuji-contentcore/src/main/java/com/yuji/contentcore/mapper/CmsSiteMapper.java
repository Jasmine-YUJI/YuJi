package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsSite;

/**
 * 站点管理Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsSiteMapper 
{
    /**
     * 查询站点管理
     * 
     * @param siteId 站点管理主键
     * @return 站点管理
     */
    public CmsSite selectCmsSiteBySiteId(Long siteId);

    /**
     * 查询站点管理列表
     * 
     * @param cmsSite 站点管理
     * @return 站点管理集合
     */
    public List<CmsSite> selectCmsSiteList(CmsSite cmsSite);

    /**
     * 新增站点管理
     * 
     * @param cmsSite 站点管理
     * @return 结果
     */
    public int insertCmsSite(CmsSite cmsSite);

    /**
     * 修改站点管理
     * 
     * @param cmsSite 站点管理
     * @return 结果
     */
    public int updateCmsSite(CmsSite cmsSite);

    /**
     * 删除站点管理
     * 
     * @param siteId 站点管理主键
     * @return 结果
     */
    public int deleteCmsSiteBySiteId(Long siteId);

    /**
     * 批量删除站点管理
     * 
     * @param siteIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsSiteBySiteIds(Long[] siteIds);
}
