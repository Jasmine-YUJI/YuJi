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
import com.yuji.contentcore.domain.CmsContentRela;
import com.yuji.contentcore.service.ICmsContentRelaService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 关联内容Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/rela")
public class CmsContentRelaController extends BaseController
{
    @Autowired
    private ICmsContentRelaService cmsContentRelaService;

    /**
     * 查询关联内容列表
     */
    @RequiresPermissions("cms:rela:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsContentRela cmsContentRela)
    {
        startPage();
        List<CmsContentRela> list = cmsContentRelaService.selectCmsContentRelaList(cmsContentRela);
        return getDataTable(list);
    }

    /**
     * 导出关联内容列表
     */
    @RequiresPermissions("cms:rela:export")
    @Log(title = "关联内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsContentRela cmsContentRela)
    {
        List<CmsContentRela> list = cmsContentRelaService.selectCmsContentRelaList(cmsContentRela);
        ExcelUtil<CmsContentRela> util = new ExcelUtil<CmsContentRela>(CmsContentRela.class);
        util.exportExcel(response, list, "关联内容数据");
    }

    /**
     * 获取关联内容详细信息
     */
    @RequiresPermissions("cms:rela:query")
    @GetMapping(value = "/{relaId}")
    public AjaxResult getInfo(@PathVariable("relaId") Long relaId)
    {
        return success(cmsContentRelaService.selectCmsContentRelaByRelaId(relaId));
    }

    /**
     * 新增关联内容
     */
    @RequiresPermissions("cms:rela:add")
    @Log(title = "关联内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsContentRela cmsContentRela)
    {
        return toAjax(cmsContentRelaService.insertCmsContentRela(cmsContentRela));
    }

    /**
     * 修改关联内容
     */
    @RequiresPermissions("cms:rela:edit")
    @Log(title = "关联内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsContentRela cmsContentRela)
    {
        return toAjax(cmsContentRelaService.updateCmsContentRela(cmsContentRela));
    }

    /**
     * 删除关联内容
     */
    @RequiresPermissions("cms:rela:remove")
    @Log(title = "关联内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relaIds}")
    public AjaxResult remove(@PathVariable Long[] relaIds)
    {
        return toAjax(cmsContentRelaService.deleteCmsContentRelaByRelaIds(relaIds));
    }
}
