package com.yuji.contentcore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

import com.yuji.common.core.domain.TreeNode;
import com.yuji.common.core.exception.CommonErrorCode;
import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.DateUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.uuid.IdUtils;
import com.yuji.contentcore.core.impl.InternalDataType_Catalog;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.mapper.CmsSiteMapper;
import com.yuji.contentcore.utils.CatalogUtils;
import com.yuji.contentcore.utils.InternalUrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsCatalogMapper;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.service.ICmsCatalogService;

/**
 * 栏目管理Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsCatalogServiceImpl implements ICmsCatalogService 
{
    @Autowired
    private CmsCatalogMapper cmsCatalogMapper;
    @Autowired
    private CmsSiteMapper cmsSiteMapper;

    /**
     * 查询栏目管理
     * 
     * @param catalogId 栏目管理主键
     * @return 栏目管理
     */
    @Override
    public CmsCatalog selectCmsCatalogByCatalogId(Long catalogId)
    {
        return cmsCatalogMapper.selectCmsCatalogByCatalogId(catalogId);
    }

    /**
     * 查询栏目管理列表
     * 
     * @param cmsCatalog 栏目管理
     * @return 栏目管理
     */
    @Override
    public List<CmsCatalog> selectCmsCatalogList(CmsCatalog cmsCatalog)
    {
        return cmsCatalogMapper.selectCmsCatalogList(cmsCatalog);
    }

    /**
     * 新增栏目管理
     * 
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    @Override
    public int insertCmsCatalog(CmsCatalog cmsCatalog)
    {
        cmsCatalog.setCreateTime(DateUtils.getNowDate());
        return cmsCatalogMapper.insertCmsCatalog(cmsCatalog);
    }

    /**
     * 修改栏目管理
     * 
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    @Override
    public int updateCmsCatalog(CmsCatalog cmsCatalog)
    {
        cmsCatalog.setUpdateTime(DateUtils.getNowDate());
        return cmsCatalogMapper.updateCmsCatalog(cmsCatalog);
    }

    /**
     * 批量删除栏目管理
     * 
     * @param catalogIds 需要删除的栏目管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsCatalogByCatalogIds(Long[] catalogIds)
    {
        return cmsCatalogMapper.deleteCmsCatalogByCatalogIds(catalogIds);
    }

    /**
     * 删除栏目管理信息
     * 
     * @param catalogId 栏目管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsCatalogByCatalogId(Long catalogId)
    {
        return cmsCatalogMapper.deleteCmsCatalogByCatalogId(catalogId);
    }

    /**
     * 根据栏目别名查找栏目数据
     *
     * @param siteId
     * @param catalogAlias
     * @return
     */
    @Override
    public CmsCatalog getCatalogByAlias(Long siteId, String catalogAlias) {
        if (!IdUtils.validate(siteId) || StringUtils.isEmpty(catalogAlias)) {
            return null;
        }
        Assert.notNull(catalogAlias, () -> CommonErrorCode.NOT_EMPTY.exception("CatalogAlias: " + catalogAlias));
        CmsCatalog catalog = new CmsCatalog();
        catalog.setSiteId(siteId);
        catalog.setAlias(catalogAlias);
        catalog = cmsCatalogMapper.selectCmsCatalogList(catalog).get(0);
        return catalog;
    }

    /**
     * 获取栏目链接
     *
     * @param catalog
     * @param publishPipeCode
     * @param isPreview
     * @return
     */
    @Override
    public String getCatalogLink(CmsCatalog catalog, int pageIndex, String publishPipeCode, boolean isPreview) {
        CmsSite site = this.cmsSiteMapper.selectCmsSiteBySiteId(catalog.getSiteId());
        return CatalogUtils.getCatalogLink(site, catalog, pageIndex, publishPipeCode, isPreview);
    }

    @Override
    public String getCatalogListLink(CmsCatalog catalog, int pageIndex, String publishPipeCode, boolean isPreview) {
        CmsSite site = this.cmsSiteMapper.selectCmsSiteBySiteId(catalog.getSiteId());
        return CatalogUtils.getCatalogListLink(site, catalog, pageIndex, publishPipeCode, isPreview);
    }

    @Override
    public List<TreeNode<String>> buildCatalogTreeData(List<CmsCatalog> catalogs, BiConsumer<CmsCatalog, TreeNode<String>> consumer) {
        if (Objects.isNull(catalogs)) {
            return List.of();
        }
        List<TreeNode<String>> list = catalogs.stream().map(c -> {
            TreeNode<String> treeNode = new TreeNode<>(String.valueOf(c.getCatalogId()),
                    String.valueOf(c.getParentId()), c.getName(), c.getParentId() == 0);
            String internalUrl = InternalUrlUtils.getInternalUrl(InternalDataType_Catalog.ID, c.getCatalogId());
            String logoSrc = InternalUrlUtils.getActualPreviewUrl(c.getLogo());
            Map<String, Object> props = Map.of("path", c.getPath(), "internalUrl", internalUrl, "logo",
                    c.getLogo() == null ? "" : c.getLogo(), "logoSrc", logoSrc == null ? "" : logoSrc, "description",
                    c.getDescription() == null ? "" : c.getDescription());
            treeNode.setProps(props);
            consumer.accept(c, treeNode);
            return treeNode;
        }).toList();
        return TreeNode.build(list);
    }
}
