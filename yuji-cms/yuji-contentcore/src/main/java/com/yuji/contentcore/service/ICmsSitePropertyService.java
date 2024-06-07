package com.yuji.contentcore.service;

import java.util.List;
import com.yuji.contentcore.domain.CmsSiteProperty;

/**
 * 站点自定义属性Service接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface ICmsSitePropertyService 
{
    /**
     * 查询站点自定义属性
     * 
     * @param propertyId 站点自定义属性主键
     * @return 站点自定义属性
     */
    public CmsSiteProperty selectCmsSitePropertyByPropertyId(Long propertyId);

    /**
     * 查询站点自定义属性列表
     * 
     * @param cmsSiteProperty 站点自定义属性
     * @return 站点自定义属性集合
     */
    public List<CmsSiteProperty> selectCmsSitePropertyList(CmsSiteProperty cmsSiteProperty);

    /**
     * 新增站点自定义属性
     * 
     * @param cmsSiteProperty 站点自定义属性
     * @return 结果
     */
    public int insertCmsSiteProperty(CmsSiteProperty cmsSiteProperty);

    /**
     * 修改站点自定义属性
     * 
     * @param cmsSiteProperty 站点自定义属性
     * @return 结果
     */
    public int updateCmsSiteProperty(CmsSiteProperty cmsSiteProperty);

    /**
     * 批量删除站点自定义属性
     * 
     * @param propertyIds 需要删除的站点自定义属性主键集合
     * @return 结果
     */
    public int deleteCmsSitePropertyByPropertyIds(Long[] propertyIds);

    /**
     * 删除站点自定义属性信息
     * 
     * @param propertyId 站点自定义属性主键
     * @return 结果
     */
    public int deleteCmsSitePropertyByPropertyId(Long propertyId);
}
