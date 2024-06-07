package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsSiteProperty;

/**
 * 站点自定义属性Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsSitePropertyMapper 
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
     * 删除站点自定义属性
     * 
     * @param propertyId 站点自定义属性主键
     * @return 结果
     */
    public int deleteCmsSitePropertyByPropertyId(Long propertyId);

    /**
     * 批量删除站点自定义属性
     * 
     * @param propertyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsSitePropertyByPropertyIds(Long[] propertyIds);
}
