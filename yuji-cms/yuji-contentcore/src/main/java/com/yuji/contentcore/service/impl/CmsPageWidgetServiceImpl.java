package com.yuji.contentcore.service.impl;

import java.util.List;
import java.util.Map;

import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.DateUtils;
import com.yuji.contentcore.core.IPageWidgetType;
import com.yuji.contentcore.exception.ContentCoreErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsPageWidgetMapper;
import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.contentcore.service.ICmsPageWidgetService;

/**
 * 页面部件Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsPageWidgetServiceImpl implements ICmsPageWidgetService 
{
    @Autowired
    private CmsPageWidgetMapper cmsPageWidgetMapper;
    /*@Autowired
    private Map<String, IPageWidgetType> pageWidgetTypes;*/



    /**
     * 查询页面部件
     * 
     * @param pageWidgetId 页面部件主键
     * @return 页面部件
     */
    @Override
    public CmsPageWidget selectCmsPageWidgetByPageWidgetId(Long pageWidgetId)
    {
        return cmsPageWidgetMapper.selectCmsPageWidgetByPageWidgetId(pageWidgetId);
    }

    /**
     * 查询页面部件列表
     * 
     * @param cmsPageWidget 页面部件
     * @return 页面部件
     */
    @Override
    public List<CmsPageWidget> selectCmsPageWidgetList(CmsPageWidget cmsPageWidget)
    {
        return cmsPageWidgetMapper.selectCmsPageWidgetList(cmsPageWidget);
    }

    /**
     * 新增页面部件
     * 
     * @param cmsPageWidget 页面部件
     * @return 结果
     */
    @Override
    public int insertCmsPageWidget(CmsPageWidget cmsPageWidget)
    {
        cmsPageWidget.setCreateTime(DateUtils.getNowDate());
        return cmsPageWidgetMapper.insertCmsPageWidget(cmsPageWidget);
    }

    /**
     * 修改页面部件
     * 
     * @param cmsPageWidget 页面部件
     * @return 结果
     */
    @Override
    public int updateCmsPageWidget(CmsPageWidget cmsPageWidget)
    {
        cmsPageWidget.setUpdateTime(DateUtils.getNowDate());
        return cmsPageWidgetMapper.updateCmsPageWidget(cmsPageWidget);
    }

    /**
     * 批量删除页面部件
     * 
     * @param pageWidgetIds 需要删除的页面部件主键
     * @return 结果
     */
    @Override
    public int deleteCmsPageWidgetByPageWidgetIds(Long[] pageWidgetIds)
    {
        return cmsPageWidgetMapper.deleteCmsPageWidgetByPageWidgetIds(pageWidgetIds);
    }

    /**
     * 删除页面部件信息
     * 
     * @param pageWidgetId 页面部件主键
     * @return 结果
     */
    @Override
    public int deleteCmsPageWidgetByPageWidgetId(Long pageWidgetId)
    {
        return cmsPageWidgetMapper.deleteCmsPageWidgetByPageWidgetId(pageWidgetId);
    }

    @Override
    public IPageWidgetType getPageWidgetType(String type) {
       /* IPageWidgetType pwt = this.pageWidgetTypes.get(IPageWidgetType.BEAN_NAME_PREFIX + type);
        Assert.notNull(pwt, () -> ContentCoreErrorCode.UNSUPPORTED_PAGE_WIDGET_TYPE.exception());
        return pwt;*/return null;
    }
}
