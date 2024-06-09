package com.yuji.contentcore.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.domain.CmsTemplate;
import com.yuji.contentcore.domain.dto.TemplateRenameDTO;
import com.yuji.contentcore.domain.dto.TemplateUpdateDTO;
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

    /**
     * 保存模板内容
     */
    public int saveTemplate(CmsTemplate template, TemplateUpdateDTO dto) throws IOException;

    /**
     * 模板文件重命名
     *
     * @param template 模板信息
     * @param dto 修改信息
     * @return 结果
     */
    public int renameTemplate(CmsTemplate template, TemplateRenameDTO dto)  throws IOException;

    /**
     * 清理站点指定模板静态化缓存
     *
     * @param templateKey 唯一标识：相对resourceRoot路径
     */
    public void clearTemplateStaticContentCache(String templateKey);

    /**
     * 获取模板文件
     *
     * @param template 模板信息
     */
    File getTemplateFile(CmsTemplate template);

}
