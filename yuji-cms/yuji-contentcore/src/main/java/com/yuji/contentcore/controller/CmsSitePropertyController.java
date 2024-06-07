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
import com.yuji.contentcore.domain.CmsSiteProperty;
import com.yuji.contentcore.service.ICmsSitePropertyService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;

/**
 * 站点自定义属性Controller
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/property")
public class CmsSitePropertyController extends BaseController
{
    @Autowired
    private ICmsSitePropertyService cmsSitePropertyService;

    /**
     * 查询站点自定义属性列表
     */
    @RequiresPermissions("cms:property:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsSiteProperty cmsSiteProperty)
    {
        startPage();
        List<CmsSiteProperty> list = cmsSitePropertyService.selectCmsSitePropertyList(cmsSiteProperty);
        return getDataTable(list);
    }

    /**
     * 导出站点自定义属性列表
     */
    @RequiresPermissions("cms:property:export")
    @Log(title = "站点自定义属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsSiteProperty cmsSiteProperty)
    {
        List<CmsSiteProperty> list = cmsSitePropertyService.selectCmsSitePropertyList(cmsSiteProperty);
        ExcelUtil<CmsSiteProperty> util = new ExcelUtil<CmsSiteProperty>(CmsSiteProperty.class);
        util.exportExcel(response, list, "站点自定义属性数据");
    }

    /**
     * 获取站点自定义属性详细信息
     */
    @RequiresPermissions("cms:property:query")
    @GetMapping(value = "/{propertyId}")
    public AjaxResult getInfo(@PathVariable("propertyId") Long propertyId)
    {
        return success(cmsSitePropertyService.selectCmsSitePropertyByPropertyId(propertyId));
    }

    /**
     * 新增站点自定义属性
     */
    @RequiresPermissions("cms:property:add")
    @Log(title = "站点自定义属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsSiteProperty cmsSiteProperty)
    {
        return toAjax(cmsSitePropertyService.insertCmsSiteProperty(cmsSiteProperty));
    }

    /**
     * 修改站点自定义属性
     */
    @RequiresPermissions("cms:property:edit")
    @Log(title = "站点自定义属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsSiteProperty cmsSiteProperty)
    {
        return toAjax(cmsSitePropertyService.updateCmsSiteProperty(cmsSiteProperty));
    }

    /**
     * 删除站点自定义属性
     */
    @RequiresPermissions("cms:property:remove")
    @Log(title = "站点自定义属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{propertyIds}")
    public AjaxResult remove(@PathVariable Long[] propertyIds)
    {
        return toAjax(cmsSitePropertyService.deleteCmsSitePropertyByPropertyIds(propertyIds));
    }
}
