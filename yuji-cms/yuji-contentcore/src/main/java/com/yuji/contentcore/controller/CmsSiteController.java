package com.yuji.contentcore.controller;

import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.yuji.common.core.domain.R;
import com.yuji.common.core.utils.ServletUtils;
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
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 站点管理Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/site")
public class CmsSiteController extends BaseController
{
    @Autowired
    private ICmsSiteService cmsSiteService;

    /**
     * 查询站点管理列表
     */
    @RequiresPermissions("cms:site:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsSite cmsSite)
    {
        startPage();
        List<CmsSite> list = cmsSiteService.selectCmsSiteList(cmsSite);
        return getDataTable(list);
    }

    /**
     * 导出站点管理列表
     */
    @RequiresPermissions("cms:site:export")
    @Log(title = "站点管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsSite cmsSite)
    {
        List<CmsSite> list = cmsSiteService.selectCmsSiteList(cmsSite);
        ExcelUtil<CmsSite> util = new ExcelUtil<CmsSite>(CmsSite.class);
        util.exportExcel(response, list, "站点管理数据");
    }

    /**
     * 获取站点管理详细信息
     */
    @RequiresPermissions("cms:site:query")
    @GetMapping(value = "/{siteId}")
    public AjaxResult getInfo(@PathVariable("siteId") Long siteId)
    {
        return success(cmsSiteService.selectCmsSiteBySiteId(siteId));
    }

    /**
     * 新增站点管理
     */
    @RequiresPermissions("cms:site:add")
    @Log(title = "站点管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsSite cmsSite)
    {
        return toAjax(cmsSiteService.insertCmsSite(cmsSite));
    }

    /**
     * 修改站点管理
     */
    @RequiresPermissions("cms:site:edit")
    @Log(title = "站点管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsSite cmsSite)
    {
        return toAjax(cmsSiteService.updateCmsSite(cmsSite));
    }

    /**
     * 删除站点管理
     */
    @RequiresPermissions("cms:site:remove")
    @Log(title = "站点管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{siteIds}")
    public AjaxResult remove(@PathVariable Long[] siteIds)
    {
        return toAjax(cmsSiteService.deleteCmsSiteBySiteIds(siteIds));
    }

    /**
     * 获取当前站点数据
     *
     * @apiNote 读取request.header['CurrentSite']中的siteId，如果无header或无站点则取数据库第一条站点数据
     */
    @GetMapping("/getCurrentSite")
    public R<Map<String, Object>> getCurrentSite() {
        CmsSite site = this.cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        return R.ok(Map.of("siteId", site.getSiteId(), "siteName", site.getName()));
    }

    /**
     * 设置当前站点
     *
     * @param siteId 站点ID
     */
    @Log(title = "切换站点", businessType = BusinessType.UPDATE)
    @PostMapping("/setCurrentSite/{siteId}")
    public R<Map<String, Object>> setCurrentSite(@PathVariable("siteId")  Long siteId) {
        CmsSite site = this.cmsSiteService.selectCmsSiteBySiteId(siteId);
        return R.ok(Map.of("siteId", site.getSiteId(), "siteName", site.getName()));
    }
}
