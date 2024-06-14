package com.yuji.contentcore.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.domain.CmsPublishpipe;
import com.yuji.contentcore.domain.CmsSite;
import freemarker.template.TemplateException;

/**
 * 发布通道Service接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface ICmsPublishpipeService 
{
    /**
     * 查询发布通道
     * 
     * @param publishpipeId 发布通道主键
     * @return 发布通道
     */
    public CmsPublishpipe selectCmsPublishpipeByPublishpipeId(Long publishpipeId);

    /**
     * 查询发布通道列表
     * 
     * @param cmsPublishpipe 发布通道
     * @return 发布通道集合
     */
    public List<CmsPublishpipe> selectCmsPublishpipeList(CmsPublishpipe cmsPublishpipe);

    /**
     * 获取指定站点可用发布通道
     *
     * @param siteId 站点ID
     * @return 结果列表
     */
    public List<CmsPublishpipe> getPublishPipes(Long siteId);

    /**
     * 获取指定站点所有发布通道
     *
     * @param siteId 站点ID
     * @return 结果列表
     */
    public List<CmsPublishpipe> getAllPublishPipes(Long siteId);

    /**
     * 新增发布通道
     * 
     * @param cmsPublishpipe 发布通道
     * @return 结果
     */
    public int insertCmsPublishpipe(CmsPublishpipe cmsPublishpipe);

    /**
     * 修改发布通道
     * 
     * @param cmsPublishpipe 发布通道
     * @return 结果
     */
    public int updateCmsPublishpipe(CmsPublishpipe cmsPublishpipe);

    /**
     * 批量删除发布通道
     * 
     * @param publishpipeIds 需要删除的发布通道主键集合
     * @return 结果
     */
    public int deleteCmsPublishpipeByPublishpipeIds(Long[] publishpipeIds);

    /**
     * 删除发布通道信息
     * 
     * @param publishpipeId 发布通道主键
     * @return 结果
     */
    public int deleteCmsPublishpipeByPublishpipeId(Long publishpipeId);

    /**
     * 获取发布通道属性值
     *
     * @param propKey 发布通道属性唯一标识
     * @param publishPipeCode 发布通道编码
     * @param props 数据集合
     * @return 属性值
     */
    String getPublishPipePropValue(String propKey, String publishPipeCode, Map<String, Map<String, Object>> props);
}
