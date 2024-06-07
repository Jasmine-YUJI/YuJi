package com.yuji.contentcore.mapper;

import java.util.List;
import com.yuji.contentcore.domain.CmsPageWidget;

/**
 * 页面部件Mapper接口
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
public interface CmsPageWidgetMapper 
{
    /**
     * 查询页面部件
     * 
     * @param pageWidgetId 页面部件主键
     * @return 页面部件
     */
    public CmsPageWidget selectCmsPageWidgetByPageWidgetId(Long pageWidgetId);

    /**
     * 查询页面部件列表
     * 
     * @param cmsPageWidget 页面部件
     * @return 页面部件集合
     */
    public List<CmsPageWidget> selectCmsPageWidgetList(CmsPageWidget cmsPageWidget);

    /**
     * 新增页面部件
     * 
     * @param cmsPageWidget 页面部件
     * @return 结果
     */
    public int insertCmsPageWidget(CmsPageWidget cmsPageWidget);

    /**
     * 修改页面部件
     * 
     * @param cmsPageWidget 页面部件
     * @return 结果
     */
    public int updateCmsPageWidget(CmsPageWidget cmsPageWidget);

    /**
     * 删除页面部件
     * 
     * @param pageWidgetId 页面部件主键
     * @return 结果
     */
    public int deleteCmsPageWidgetByPageWidgetId(Long pageWidgetId);

    /**
     * 批量删除页面部件
     * 
     * @param pageWidgetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsPageWidgetByPageWidgetIds(Long[] pageWidgetIds);
}
