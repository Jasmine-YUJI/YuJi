package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsContent;

/**
 * 内容管理Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsContentMapper 
{
    /**
     * 查询内容管理
     * 
     * @param contentId 内容管理主键
     * @return 内容管理
     */
    public CmsContent selectCmsContentByContentId(Long contentId);

    public List<CmsContent>  selectCmsContentByContentIdInList(Long[] contentIds);

    /**
     * 查询内容管理列表
     * 
     * @param cmsContent 内容管理
     * @return 内容管理集合
     */
    public List<CmsContent> selectCmsContentList(CmsContent cmsContent);

    /**
     * 新增内容管理
     * 
     * @param cmsContent 内容管理
     * @return 结果
     */
    public int insertCmsContent(CmsContent cmsContent);

    /**
     * 修改内容管理
     * 
     * @param cmsContent 内容管理
     * @return 结果
     */
    public int updateCmsContent(CmsContent cmsContent);

    /**
     * 删除内容管理
     * 
     * @param contentId 内容管理主键
     * @return 结果
     */
    public int deleteCmsContentByContentId(Long contentId);

    /**
     * 批量删除内容管理
     * 
     * @param contentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsContentByContentIds(Long[] contentIds);
}
