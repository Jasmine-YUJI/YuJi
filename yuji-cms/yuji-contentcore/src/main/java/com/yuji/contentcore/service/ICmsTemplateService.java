package com.yuji.contentcore.service;

import java.io.File;
import java.util.List;

import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.domain.CmsTemplate;
import com.yuji.contentcore.template.ITemplateType;

/**
 * 模板管理Service接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface ICmsTemplateService 
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
     * 批量删除模板管理
     * 
     * @param templateIds 需要删除的模板管理主键集合
     * @return 结果
     */
    public int deleteCmsTemplateByTemplateIds(Long[] templateIds);

    /**
     * 删除模板管理信息
     * 
     * @param templateId 模板管理主键
     * @return 结果
     */
    public int deleteCmsTemplateByTemplateId(Long templateId);

    /**
     * 查找模板文件
     *
     * @param site 站点信息
     * @param templatePath 模板路径
     * @param publishPipeCode 发布通道编码
     */
    File findTemplateFile(CmsSite site, String templatePath, String publishPipeCode);

    /**
     * 获取模板静态化内容缓存，主要区块模板使用
     *
     * @param templateKey 相对resourceRoot路径
     */
    String getTemplateStaticContentCache(String templateKey);

    /**
     * 缓存模板静态化内容
     *
     * @param templateKey 相对resourceRoot路径
     */
    void setTemplateStaticContentCache(String templateKey, String staticContent);

    /**
     * 获取模板类型
     *
     * @param typeid 模板类型唯一标识
     */
    ITemplateType getTemplateType(String typeid);

}
