package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsPublishpipe;

/**
 * 发布通道Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsPublishpipeMapper 
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
     * 删除发布通道
     * 
     * @param publishpipeId 发布通道主键
     * @return 结果
     */
    public int deleteCmsPublishpipeByPublishpipeId(Long publishpipeId);

    /**
     * 批量删除发布通道
     * 
     * @param publishpipeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsPublishpipeByPublishpipeIds(Long[] publishpipeIds);
}
