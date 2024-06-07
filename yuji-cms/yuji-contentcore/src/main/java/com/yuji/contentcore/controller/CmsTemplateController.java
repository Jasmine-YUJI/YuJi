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
import com.yuji.contentcore.domain.CmsTemplate;
import com.yuji.contentcore.service.ICmsTemplateService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 模板管理Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/template")
public class CmsTemplateController extends BaseController
{
    @Autowired
    private ICmsTemplateService cmsTemplateService;

    /**
     * 查询模板管理列表
     */
    @RequiresPermissions("cms:template:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsTemplate cmsTemplate)
    {
        startPage();
        List<CmsTemplate> list = cmsTemplateService.selectCmsTemplateList(cmsTemplate);
        return getDataTable(list);
    }

    /**
     * 导出模板管理列表
     */
    @RequiresPermissions("cms:template:export")
    @Log(title = "模板管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsTemplate cmsTemplate)
    {
        List<CmsTemplate> list = cmsTemplateService.selectCmsTemplateList(cmsTemplate);
        ExcelUtil<CmsTemplate> util = new ExcelUtil<CmsTemplate>(CmsTemplate.class);
        util.exportExcel(response, list, "模板管理数据");
    }

    /**
     * 获取模板管理详细信息
     */
    @RequiresPermissions("cms:template:query")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId)
    {
        return success(cmsTemplateService.selectCmsTemplateByTemplateId(templateId));
    }

    /**
     * 新增模板管理
     */
    @RequiresPermissions("cms:template:add")
    @Log(title = "模板管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsTemplate cmsTemplate)
    {
        return toAjax(cmsTemplateService.insertCmsTemplate(cmsTemplate));
    }

    /**
     * 修改模板管理
     */
    @RequiresPermissions("cms:template:edit")
    @Log(title = "模板管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsTemplate cmsTemplate)
    {
        return toAjax(cmsTemplateService.updateCmsTemplate(cmsTemplate));
    }

    /**
     * 删除模板管理
     */
    @RequiresPermissions("cms:template:remove")
    @Log(title = "模板管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable Long[] templateIds)
    {
        return toAjax(cmsTemplateService.deleteCmsTemplateByTemplateIds(templateIds));
    }
}
