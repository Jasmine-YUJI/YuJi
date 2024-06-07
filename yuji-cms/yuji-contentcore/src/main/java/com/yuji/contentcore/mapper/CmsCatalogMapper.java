package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsCatalog;

/**
 * 栏目管理Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsCatalogMapper 
{
    /**
     * 查询栏目管理
     * 
     * @param catalogId 栏目管理主键
     * @return 栏目管理
     */
    public CmsCatalog selectCmsCatalogByCatalogId(Long catalogId);

    /**
     * 查询栏目管理列表
     * 
     * @param cmsCatalog 栏目管理
     * @return 栏目管理集合
     */
    public List<CmsCatalog> selectCmsCatalogList(CmsCatalog cmsCatalog);

    /**
     * 新增栏目管理
     * 
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    public int insertCmsCatalog(CmsCatalog cmsCatalog);

    /**
     * 修改栏目管理
     * 
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    public int updateCmsCatalog(CmsCatalog cmsCatalog);

    /**
     * 删除栏目管理
     * 
     * @param catalogId 栏目管理主键
     * @return 结果
     */
    public int deleteCmsCatalogByCatalogId(Long catalogId);

    /**
     * 批量删除栏目管理
     * 
     * @param catalogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsCatalogByCatalogIds(Long[] catalogIds);
}
