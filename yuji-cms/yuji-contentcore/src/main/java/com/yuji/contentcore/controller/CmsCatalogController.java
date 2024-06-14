package com.yuji.contentcore.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.yuji.common.core.utils.ServletUtils;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.service.ICmsSiteService;
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
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.service.ICmsCatalogService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 栏目管理Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/catalog")
public class CmsCatalogController extends BaseController
{
    @Autowired
    private ICmsCatalogService cmsCatalogService;
    @Autowired
    private ICmsSiteService cmsSiteService;

    /**
     * 查询栏目管理列表
     */
    @RequiresPermissions("cms:catalog:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsCatalog cmsCatalog)
    {
        CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        cmsCatalog.setSiteId(site.getSiteId());
        startPage();
        List<CmsCatalog> list = cmsCatalogService.selectCmsCatalogList(cmsCatalog);
        return getDataTable(list);
    }

    /**
     * 导出栏目管理列表
     */
    @RequiresPermissions("cms:catalog:export")
    @Log(title = "栏目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsCatalog cmsCatalog)
    {
        List<CmsCatalog> list = cmsCatalogService.selectCmsCatalogList(cmsCatalog);
        ExcelUtil<CmsCatalog> util = new ExcelUtil<CmsCatalog>(CmsCatalog.class);
        util.exportExcel(response, list, "栏目管理数据");
    }

    /**
     * 获取栏目管理详细信息
     */
    @RequiresPermissions("cms:catalog:query")
    @GetMapping(value = "/{catalogId}")
    public AjaxResult getInfo(@PathVariable("catalogId") Long catalogId)
    {
        return success(cmsCatalogService.selectCmsCatalogByCatalogId(catalogId));
    }

    /**
     * 新增栏目管理
     */
    @RequiresPermissions("cms:catalog:add")
    @Log(title = "栏目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsCatalog cmsCatalog)
    {
        return toAjax(cmsCatalogService.insertCmsCatalog(cmsCatalog));
    }

    /**
     * 修改栏目管理
     */
    @RequiresPermissions("cms:catalog:edit")
    @Log(title = "栏目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsCatalog cmsCatalog)
    {
        return toAjax(cmsCatalogService.updateCmsCatalog(cmsCatalog));
    }

    /**
     * 删除栏目管理
     */
    @RequiresPermissions("cms:catalog:remove")
    @Log(title = "栏目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{catalogIds}")
    public AjaxResult remove(@PathVariable Long[] catalogIds)
    {
        return toAjax(cmsCatalogService.deleteCmsCatalogByCatalogIds(catalogIds));
    }
}
