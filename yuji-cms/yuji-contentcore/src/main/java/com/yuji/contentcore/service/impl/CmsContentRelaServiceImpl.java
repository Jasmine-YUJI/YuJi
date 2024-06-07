package com.yuji.contentcore.service.impl;

import java.util.List;
import com.yuji.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsContentRelaMapper;
import com.yuji.contentcore.domain.CmsContentRela;
import com.yuji.contentcore.service.ICmsContentRelaService;

/**
 * 关联内容Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsContentRelaServiceImpl implements ICmsContentRelaService 
{
    @Autowired
    private CmsContentRelaMapper cmsContentRelaMapper;

    /**
     * 查询关联内容
     * 
     * @param relaId 关联内容主键
     * @return 关联内容
     */
    @Override
    public CmsContentRela selectCmsContentRelaByRelaId(Long relaId)
    {
        return cmsContentRelaMapper.selectCmsContentRelaByRelaId(relaId);
    }

    /**
     * 查询关联内容列表
     * 
     * @param cmsContentRela 关联内容
     * @return 关联内容
     */
    @Override
    public List<CmsContentRela> selectCmsContentRelaList(CmsContentRela cmsContentRela)
    {
        return cmsContentRelaMapper.selectCmsContentRelaList(cmsContentRela);
    }

    /**
     * 新增关联内容
     * 
     * @param cmsContentRela 关联内容
     * @return 结果
     */
    @Override
    public int insertCmsContentRela(CmsContentRela cmsContentRela)
    {
        cmsContentRela.setCreateTime(DateUtils.getNowDate());
        return cmsContentRelaMapper.insertCmsContentRela(cmsContentRela);
    }

    /**
     * 修改关联内容
     * 
     * @param cmsContentRela 关联内容
     * @return 结果
     */
    @Override
    public int updateCmsContentRela(CmsContentRela cmsContentRela)
    {
        cmsContentRela.setUpdateTime(DateUtils.getNowDate());
        return cmsContentRelaMapper.updateCmsContentRela(cmsContentRela);
    }

    /**
     * 批量删除关联内容
     * 
     * @param relaIds 需要删除的关联内容主键
     * @return 结果
     */
    @Override
    public int deleteCmsContentRelaByRelaIds(Long[] relaIds)
    {
        return cmsContentRelaMapper.deleteCmsContentRelaByRelaIds(relaIds);
    }

    /**
     * 删除关联内容信息
     * 
     * @param relaId 关联内容主键
     * @return 结果
     */
    @Override
    public int deleteCmsContentRelaByRelaId(Long relaId)
    {
        return cmsContentRelaMapper.deleteCmsContentRelaByRelaId(relaId);
    }
}
