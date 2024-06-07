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
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.service.ICmsResourceService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 资源Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/resource")
public class CmsResourceController extends BaseController
{
    @Autowired
    private ICmsResourceService cmsResourceService;

    /**
     * 查询资源列表
     */
    @RequiresPermissions("cms:resource:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsResource cmsResource)
    {
        startPage();
        List<CmsResource> list = cmsResourceService.selectCmsResourceList(cmsResource);
        return getDataTable(list);
    }

    /**
     * 导出资源列表
     */
    @RequiresPermissions("cms:resource:export")
    @Log(title = "资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsResource cmsResource)
    {
        List<CmsResource> list = cmsResourceService.selectCmsResourceList(cmsResource);
        ExcelUtil<CmsResource> util = new ExcelUtil<CmsResource>(CmsResource.class);
        util.exportExcel(response, list, "资源数据");
    }

    /**
     * 获取资源详细信息
     */
    @RequiresPermissions("cms:resource:query")
    @GetMapping(value = "/{resourceId}")
    public AjaxResult getInfo(@PathVariable("resourceId") Long resourceId)
    {
        return success(cmsResourceService.selectCmsResourceByResourceId(resourceId));
    }

    /**
     * 新增资源
     */
    @RequiresPermissions("cms:resource:add")
    @Log(title = "资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsResource cmsResource)
    {
        return toAjax(cmsResourceService.insertCmsResource(cmsResource));
    }

    /**
     * 修改资源
     */
    @RequiresPermissions("cms:resource:edit")
    @Log(title = "资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsResource cmsResource)
    {
        return toAjax(cmsResourceService.updateCmsResource(cmsResource));
    }

    /**
     * 删除资源
     */
    @RequiresPermissions("cms:resource:remove")
    @Log(title = "资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resourceIds}")
    public AjaxResult remove(@PathVariable Long[] resourceIds)
    {
        return toAjax(cmsResourceService.deleteCmsResourceByResourceIds(resourceIds));
    }
}
