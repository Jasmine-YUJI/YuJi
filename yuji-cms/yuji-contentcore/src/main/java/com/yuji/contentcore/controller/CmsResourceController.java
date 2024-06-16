package com.yuji.contentcore.controller;

import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.yuji.common.core.utils.ServletUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.i18n.I18nUtils;
import com.yuji.common.security.utils.SecurityUtils;
import com.yuji.contentcore.core.IResourceType;
import com.yuji.contentcore.core.impl.InternalDataType_Resource;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.domain.dto.ResourceUploadDTO;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.utils.ContentCoreUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yuji.common.log.annotation.Log;
import com.yuji.common.log.enums.BusinessType;
import com.yuji.common.security.annotation.RequiresPermissions;
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.service.ICmsResourceService;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.web.domain.AjaxResult;
import com.yuji.common.core.utils.poi.ExcelUtil;
import com.yuji.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private ICmsSiteService cmsSiteService;

    @GetMapping("/types")
    public AjaxResult getResourceTypes() {
        List<Map<String, String>> list = ContentCoreUtils.getResourceTypes().stream()
                .map(rt -> Map.of(
                        "id", rt.getId(),
                        "name", I18nUtils.get(rt.getName()),
                        "accepts", StringUtils.join(rt.getUsableSuffix(), ","))
                ).toList();
        return success(list);
    }

    /**
     * 查询资源列表
     */
    @RequiresPermissions("cms:resource:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsResource cmsResource)
    {
        CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        cmsResource.setSiteId(site.getSiteId());
        cmsResource.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<CmsResource> list = cmsResourceService.selectCmsResourceList(cmsResource);
        if (!list.isEmpty()){
            list.forEach(res ->{
                IResourceType rt = ContentCoreUtils.getResourceType(res.getResourceType());
                res.setResourceTypeName(I18nUtils.get(rt.getName()));
                if (res.getPath().startsWith("http://") || res.getPath().startsWith("https://")) {
                    res.setSrc(res.getPath());
                } else {
                    String resourceLink = cmsResourceService.getResourceLink(res, true);
                    res.setSrc(resourceLink);
                }
                res.setInternalUrl(InternalDataType_Resource.getInternalUrl(res));
                res.setFileSizeName(FileUtils.byteCountToDisplaySize(res.getFileSize()));
            });
        }
        return getDataTable(list);
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
    @Log(title = "新增资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestParam("file") MultipartFile resourceFile,
                          String name,
                          String remark) throws IOException
    {
        CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        ResourceUploadDTO dto = ResourceUploadDTO.builder()
                .site(site)
                .file(resourceFile)
                .name(name)
                .remark(remark)
                .build();
        dto.setOperator(SecurityUtils.getUsername());
        return success(cmsResourceService.insertCmsResource(dto));
    }

    /**
     * 修改资源
     */
    @RequiresPermissions("cms:resource:edit")
    @Log(title = "编辑资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestParam("file") MultipartFile resourceFile,
                           Long resourceId,
                           String name,
                           String remark) throws IOException
    {
        CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        ResourceUploadDTO dto = ResourceUploadDTO.builder()
                .resourceId(resourceId)
                .site(site)
                .file(resourceFile)
                .name(name)
                .remark(remark)
                .build();
        dto.setOperator(SecurityUtils.getUsername());
        return success(cmsResourceService.updateCmsResource(dto));
    }

    /**
     * 删除资源
     */
    @RequiresPermissions("cms:resource:remove")
    @Log(title = "删除资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resourceIds}")
    public AjaxResult remove(@PathVariable Long[] resourceIds)
    {
        return toAjax(cmsResourceService.deleteCmsResourceByResourceIds(resourceIds));
    }
}
