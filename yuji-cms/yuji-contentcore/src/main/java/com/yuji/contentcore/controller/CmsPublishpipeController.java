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
import com.yuji.contentcore.domain.CmsPublishpipe;
import com.yuji.contentcore.service.ICmsPublishpipeService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 发布通道Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/publishpipe")
public class CmsPublishpipeController extends BaseController
{
    @Autowired
    private ICmsPublishpipeService cmsPublishpipeService;

    /**
     * 查询发布通道列表
     */
    @RequiresPermissions("cms:publishpipe:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsPublishpipe cmsPublishpipe)
    {
        startPage();
        List<CmsPublishpipe> list = cmsPublishpipeService.selectCmsPublishpipeList(cmsPublishpipe);
        return getDataTable(list);
    }

    /**
     * 导出发布通道列表
     */
    @RequiresPermissions("cms:publishpipe:export")
    @Log(title = "发布通道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsPublishpipe cmsPublishpipe)
    {
        List<CmsPublishpipe> list = cmsPublishpipeService.selectCmsPublishpipeList(cmsPublishpipe);
        ExcelUtil<CmsPublishpipe> util = new ExcelUtil<CmsPublishpipe>(CmsPublishpipe.class);
        util.exportExcel(response, list, "发布通道数据");
    }

    /**
     * 获取发布通道详细信息
     */
    @RequiresPermissions("cms:publishpipe:query")
    @GetMapping(value = "/{publishpipeId}")
    public AjaxResult getInfo(@PathVariable("publishpipeId") Long publishpipeId)
    {
        return success(cmsPublishpipeService.selectCmsPublishpipeByPublishpipeId(publishpipeId));
    }

    /**
     * 新增发布通道
     */
    @RequiresPermissions("cms:publishpipe:add")
    @Log(title = "发布通道", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsPublishpipe cmsPublishpipe)
    {
        return toAjax(cmsPublishpipeService.insertCmsPublishpipe(cmsPublishpipe));
    }

    /**
     * 修改发布通道
     */
    @RequiresPermissions("cms:publishpipe:edit")
    @Log(title = "发布通道", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsPublishpipe cmsPublishpipe)
    {
        return toAjax(cmsPublishpipeService.updateCmsPublishpipe(cmsPublishpipe));
    }

    /**
     * 删除发布通道
     */
    @RequiresPermissions("cms:publishpipe:remove")
    @Log(title = "发布通道", businessType = BusinessType.DELETE)
	@DeleteMapping("/{publishpipeIds}")
    public AjaxResult remove(@PathVariable Long[] publishpipeIds)
    {
        return toAjax(cmsPublishpipeService.deleteCmsPublishpipeByPublishpipeIds(publishpipeIds));
    }
}
