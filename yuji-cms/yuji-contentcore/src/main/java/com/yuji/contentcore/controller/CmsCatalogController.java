package com.yuji.contentcore.controller;

import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.yuji.common.core.domain.TreeNode;
import com.yuji.common.core.utils.ServletUtils;
import com.yuji.common.core.utils.i18n.I18nUtils;
import com.yuji.common.security.utils.SecurityUtils;
import com.yuji.contentcore.core.ICatalogType;
import com.yuji.contentcore.core.IContentType;
import com.yuji.contentcore.core.impl.CatalogType_Link;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.system.api.model.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CmsCatalogController extends BaseController
{
    @Autowired
    private ICmsCatalogService cmsCatalogService;
    @Autowired
    private ICmsSiteService cmsSiteService;

    private final List<ICatalogType> catalogTypes;

    private final List<IContentType> contentTypes;


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
    @GetMapping(value = "/getInfo/{catalogId}")
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

    /**
     * 内容类型数据
     */
    @GetMapping("/getContentTypes")
    public AjaxResult getContentTypes() {
        List<Map<String, String>> list = contentTypes.stream().sorted(Comparator.comparingInt(IContentType::getOrder))
                .map(ct -> Map.of("id", ct.getId(), "name", I18nUtils.get(ct.getName()))).toList();
        return success(list);
    }

    /**
     * 栏目类型数据
     */
    @GetMapping("/getCatalogTypes")
    public AjaxResult getCatalogTypes() {
        List<Map<String, String>> list = this.catalogTypes.stream()
                .map(ct -> Map.of("id", ct.getId(), "name", I18nUtils.get(ct.getName()))).toList();
        return success(list);
    }

    /**
     * 栏目树结构数据
     */
    @GetMapping("/treeData")
    public AjaxResult treeData(@RequestParam(required = false, defaultValue = "false") Boolean disableLink) {
        CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        LoginUser loginUser = SecurityUtils.getLoginUser();
        CmsCatalog cmsCatalog = new CmsCatalog();
        cmsCatalog.setSiteId(site.getSiteId());
        List<CmsCatalog> catalogs = cmsCatalogService.selectCmsCatalogList(cmsCatalog);

        List<TreeNode<String>> treeData = cmsCatalogService.buildCatalogTreeData(catalogs, (catalog, node) -> {
            if (disableLink) {
                node.setDisabled(CatalogType_Link.ID.equals(catalog.getCatalogType()));
            }
        });
        return success(Map.of("rows", treeData, "siteName", site.getName()));
    }
}
