package com.yuji.contentcore.service;

import java.util.List;
import com.yuji.contentcore.domain.CmsContentRela;

/**
 * 关联内容Service接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface ICmsContentRelaService 
{
    /**
     * 查询关联内容
     * 
     * @param relaId 关联内容主键
     * @return 关联内容
     */
    public CmsContentRela selectCmsContentRelaByRelaId(Long relaId);

    /**
     * 查询关联内容列表
     * 
     * @param cmsContentRela 关联内容
     * @return 关联内容集合
     */
    public List<CmsContentRela> selectCmsContentRelaList(CmsContentRela cmsContentRela);

    /**
     * 新增关联内容
     * 
     * @param cmsContentRela 关联内容
     * @return 结果
     */
    public int insertCmsContentRela(CmsContentRela cmsContentRela);

    /**
     * 修改关联内容
     * 
     * @param cmsContentRela 关联内容
     * @return 结果
     */
    public int updateCmsContentRela(CmsContentRela cmsContentRela);

    /**
     * 批量删除关联内容
     * 
     * @param relaIds 需要删除的关联内容主键集合
     * @return 结果
     */
    public int deleteCmsContentRelaByRelaIds(Long[] relaIds);

    /**
     * 删除关联内容信息
     * 
     * @param relaId 关联内容主键
     * @return 结果
     */
    public int deleteCmsContentRelaByRelaId(Long relaId);
}
