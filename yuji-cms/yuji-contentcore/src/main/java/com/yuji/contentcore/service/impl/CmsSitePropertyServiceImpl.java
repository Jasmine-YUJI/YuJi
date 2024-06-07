package com.yuji.contentcore.service.impl;

import java.util.List;
import com.yuji.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsSitePropertyMapper;
import com.yuji.contentcore.domain.CmsSiteProperty;
import com.yuji.contentcore.service.ICmsSitePropertyService;

/**
 * 站点自定义属性Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsSitePropertyServiceImpl implements ICmsSitePropertyService 
{
    @Autowired
    private CmsSitePropertyMapper cmsSitePropertyMapper;

    /**
     * 查询站点自定义属性
     * 
     * @param propertyId 站点自定义属性主键
     * @return 站点自定义属性
     */
    @Override
    public CmsSiteProperty selectCmsSitePropertyByPropertyId(Long propertyId)
    {
        return cmsSitePropertyMapper.selectCmsSitePropertyByPropertyId(propertyId);
    }

    /**
     * 查询站点自定义属性列表
     * 
     * @param cmsSiteProperty 站点自定义属性
     * @return 站点自定义属性
     */
    @Override
    public List<CmsSiteProperty> selectCmsSitePropertyList(CmsSiteProperty cmsSiteProperty)
    {
        return cmsSitePropertyMapper.selectCmsSitePropertyList(cmsSiteProperty);
    }

    /**
     * 新增站点自定义属性
     * 
     * @param cmsSiteProperty 站点自定义属性
     * @return 结果
     */
    @Override
    public int insertCmsSiteProperty(CmsSiteProperty cmsSiteProperty)
    {
        cmsSiteProperty.setCreateTime(DateUtils.getNowDate());
        return cmsSitePropertyMapper.insertCmsSiteProperty(cmsSiteProperty);
    }

    /**
     * 修改站点自定义属性
     * 
     * @param cmsSiteProperty 站点自定义属性
     * @return 结果
     */
    @Override
    public int updateCmsSiteProperty(CmsSiteProperty cmsSiteProperty)
    {
        cmsSiteProperty.setUpdateTime(DateUtils.getNowDate());
        return cmsSitePropertyMapper.updateCmsSiteProperty(cmsSiteProperty);
    }

    /**
     * 批量删除站点自定义属性
     * 
     * @param propertyIds 需要删除的站点自定义属性主键
     * @return 结果
     */
    @Override
    public int deleteCmsSitePropertyByPropertyIds(Long[] propertyIds)
    {
        return cmsSitePropertyMapper.deleteCmsSitePropertyByPropertyIds(propertyIds);
    }

    /**
     * 删除站点自定义属性信息
     * 
     * @param propertyId 站点自定义属性主键
     * @return 结果
     */
    @Override
    public int deleteCmsSitePropertyByPropertyId(Long propertyId)
    {
        return cmsSitePropertyMapper.deleteCmsSitePropertyByPropertyId(propertyId);
    }
}
