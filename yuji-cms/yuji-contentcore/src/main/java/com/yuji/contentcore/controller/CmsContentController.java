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
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.service.ICmsContentService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 内容管理Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/content")
public class CmsContentController extends BaseController
{
    @Autowired
    private ICmsContentService cmsContentService;

    /**
     * 查询内容管理列表
     */
    @RequiresPermissions("cms:content:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsContent cmsContent)
    {
        startPage();
        List<CmsContent> list = cmsContentService.selectCmsContentList(cmsContent);
        return getDataTable(list);
    }

    /**
     * 导出内容管理列表
     */
    @RequiresPermissions("cms:content:export")
    @Log(title = "内容管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsContent cmsContent)
    {
        List<CmsContent> list = cmsContentService.selectCmsContentList(cmsContent);
        ExcelUtil<CmsContent> util = new ExcelUtil<CmsContent>(CmsContent.class);
        util.exportExcel(response, list, "内容管理数据");
    }

    /**
     * 获取内容管理详细信息
     */
    @RequiresPermissions("cms:content:query")
    @GetMapping(value = "/{contentId}")
    public AjaxResult getInfo(@PathVariable("contentId") Long contentId)
    {
        return success(cmsContentService.selectCmsContentByContentId(contentId));
    }

    /**
     * 新增内容管理
     */
    @RequiresPermissions("cms:content:add")
    @Log(title = "内容管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsContent cmsContent)
    {
        return toAjax(cmsContentService.insertCmsContent(cmsContent));
    }

    /**
     * 修改内容管理
     */
    @RequiresPermissions("cms:content:edit")
    @Log(title = "内容管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsContent cmsContent)
    {
        return toAjax(cmsContentService.updateCmsContent(cmsContent));
    }

    /**
     * 删除内容管理
     */
    @RequiresPermissions("cms:content:remove")
    @Log(title = "内容管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contentIds}")
    public AjaxResult remove(@PathVariable Long[] contentIds)
    {
        return toAjax(cmsContentService.deleteCmsContentByContentIds(contentIds));
    }
}
