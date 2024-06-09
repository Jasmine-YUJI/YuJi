package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsTemplate;

/**
 * 模板管理Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsTemplateMapper 
{
    /**
     * 查询模板管理
     * 
     * @param templateId 模板管理主键
     * @return 模板管理
     */
    public CmsTemplate selectCmsTemplateByTemplateId(Long templateId);

    /**
     * 查询模板管理列表
     * 
     * @param cmsTemplate 模板管理
     * @return 模板管理集合
     */
    public List<CmsTemplate> selectCmsTemplateList(CmsTemplate cmsTemplate);

    public List<CmsTemplate>  selectCmsTemplateByTemplateIdInList(Long[] templateIds);


    /**
     * 新增模板管理
     * 
     * @param cmsTemplate 模板管理
     * @return 结果
     */
    public int insertCmsTemplate(CmsTemplate cmsTemplate);

    /**
     * 修改模板管理
     * 
     * @param cmsTemplate 模板管理
     * @return 结果
     */
    public int updateCmsTemplate(CmsTemplate cmsTemplate);

    /**
     * 删除模板管理
     * 
     * @param templateId 模板管理主键
     * @return 结果
     */
    public int deleteCmsTemplateByTemplateId(Long templateId);

    /**
     * 批量删除模板管理
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsTemplateByTemplateIds(Long[] templateIds);
}
