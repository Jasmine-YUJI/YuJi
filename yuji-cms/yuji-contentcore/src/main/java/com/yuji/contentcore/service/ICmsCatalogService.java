package com.yuji.contentcore.service;

import java.util.List;
import com.yuji.contentcore.domain.CmsCatalog;

/**
 * 栏目管理Service接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface ICmsCatalogService 
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
     * 批量删除栏目管理
     * 
     * @param catalogIds 需要删除的栏目管理主键集合
     * @return 结果
     */
    public int deleteCmsCatalogByCatalogIds(Long[] catalogIds);

    /**
     * 删除栏目管理信息
     * 
     * @param catalogId 栏目管理主键
     * @return 结果
     */
    public int deleteCmsCatalogByCatalogId(Long catalogId);

    /**
     * 根据栏目别名查找栏目数据
     *
     * @param siteId
     * @param catalogAlias
     * @return
     */
    CmsCatalog getCatalogByAlias(Long siteId, String catalogAlias);

    /**
     * 获取栏目链接
     *
     * @param catalog
     * @param publishPipeCode
     * @param isPreview
     * @return
     */
    String getCatalogLink(CmsCatalog catalog, int pageIndex, String publishPipeCode, boolean isPreview);

    String getCatalogListLink(CmsCatalog catalog, int pageIndex, String publishPipeCode, boolean isPreview);

}
