package com.yuji.contentcore.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yuji.common.log.annotation.Log;
import com.yuji.common.log.enums.BusinessType;
import com.yuji.common.security.annotation.RequiresPermissions;
import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.contentcore.service.ICmsPageWidgetService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 页面部件Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/pageWidget")
public class CmsPageWidgetController extends BaseController
{
    @Autowired
    private ICmsPageWidgetService cmsPageWidgetService;

    /**
     * 查询页面部件列表
     */
    @RequiresPermissions("cms:pageWidget:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsPageWidget cmsPageWidget)
    {
        startPage();
        List<CmsPageWidget> list = cmsPageWidgetService.selectCmsPageWidgetList(cmsPageWidget);
        return getDataTable(list);
    }

    /**
     * 导出页面部件列表
     */
    @RequiresPermissions("cms:pageWidget:export")
    @Log(title = "页面部件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsPageWidget cmsPageWidget)
    {
        List<CmsPageWidget> list = cmsPageWidgetService.selectCmsPageWidgetList(cmsPageWidget);
        ExcelUtil<CmsPageWidget> util = new ExcelUtil<CmsPageWidget>(CmsPageWidget.class);
        util.exportExcel(response, list, "页面部件数据");
    }

    /**
     * 获取页面部件详细信息
     */
    @RequiresPermissions("cms:pageWidget:query")
    @GetMapping(value = "/{pageWidgetId}")
    public AjaxResult getInfo(@PathVariable("pageWidgetId") Long pageWidgetId)
    {
        return success(cmsPageWidgetService.selectCmsPageWidgetByPageWidgetId(pageWidgetId));
    }

    /**
     * 新增页面部件
     */
    @RequiresPermissions("cms:pageWidget:add")
    @Log(title = "页面部件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsPageWidget cmsPageWidget)
    {
        return toAjax(cmsPageWidgetService.insertCmsPageWidget(cmsPageWidget));
    }

    /**
     * 修改页面部件
     */
    @RequiresPermissions("cms:pageWidget:edit")
    @Log(title = "页面部件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsPageWidget cmsPageWidget)
    {
        return toAjax(cmsPageWidgetService.updateCmsPageWidget(cmsPageWidget));
    }

    /**
     * 删除页面部件
     */
    @RequiresPermissions("cms:pageWidget:remove")
    @Log(title = "页面部件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pageWidgetIds}")
    public AjaxResult remove(@PathVariable Long[] pageWidgetIds)
    {
        return toAjax(cmsPageWidgetService.deleteCmsPageWidgetByPageWidgetIds(pageWidgetIds));
    }
}
