package com.yuji.contentcore.controller;

import java.util.List;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import com.yuji.common.core.domain.R;
import com.yuji.common.core.exception.CommonErrorCode;
import com.yuji.common.core.exception.auth.NotPermissionException;
import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.ServletUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.common.security.utils.SecurityUtils;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.domain.dto.TemplateRenameDTO;
import com.yuji.contentcore.domain.dto.TemplateUpdateDTO;
import com.yuji.contentcore.exception.ContentCoreErrorCode;
import com.yuji.contentcore.perm.SitePermissionType;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.utils.SiteUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private ICmsSiteService cmsSiteService;

    /**
     * 查询模板管理列表
     */
    @RequiresPermissions("cms:template:list")
    @GetMapping("/list")
    public TableDataInfo list(CmsTemplate cmsTemplate)
    {
        CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        cmsTemplate.setSiteId(site.getSiteId());
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
        CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        cmsTemplate.setSiteId(site.getSiteId());
        cmsTemplate.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        return toAjax(cmsTemplateService.insertCmsTemplate(cmsTemplate));
    }

    /**
     * 修改模板管理
     */
    @RequiresPermissions("cms:template:edit")
    @Log(title = "模板管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated TemplateUpdateDTO dto)throws IOException
    {
        CmsTemplate template = this.cmsTemplateService.selectCmsTemplateByTemplateId(dto.getTemplateId());
        Assert.notNull(template,
                () -> CommonErrorCode.DATA_NOT_FOUND_BY_ID.exception("templateId", dto.getTemplateId()));

        CmsSite site = this.cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        Assert.isTrue(Objects.equals(template.getSiteId(), site.getSiteId()),() -> new NotPermissionException(SitePermissionType.SitePrivItem.View.getPermissionKey(site.getSiteId())));
        dto.setOperator(SecurityUtils.getUsername());
        return toAjax(this.cmsTemplateService.saveTemplate(template, dto));
    }

    @Log(title = "重命名模板", businessType = BusinessType.UPDATE)
    @PostMapping("/rename")
    public AjaxResult rename(@RequestBody @Validated TemplateRenameDTO dto) throws IOException {
        Assert.isTrue(validTemplateName(dto.getPath()), ContentCoreErrorCode.INVALID_TEMPLATE_NAME::exception);
        CmsTemplate template = this.cmsTemplateService.selectCmsTemplateByTemplateId(dto.getTemplateId());
        Assert.notNull(template,
                () -> CommonErrorCode.DATA_NOT_FOUND_BY_ID.exception("templateId", dto.getTemplateId()));

        CmsSite site = this.cmsSiteService.getCurrentSite(ServletUtils.getRequest());
        Assert.isTrue(Objects.equals(template.getSiteId(), site.getSiteId()),() -> new NotPermissionException(SitePermissionType.SitePrivItem.View.getPermissionKey(site.getSiteId())));
        dto.setOperator(SecurityUtils.getUsername());
        return toAjax(this.cmsTemplateService.renameTemplate(template, dto));
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

    private static boolean validTemplateName(String fileName) {
        String suffix = ".template.html";
        if (StringUtils.isEmpty(fileName) || !fileName.endsWith(suffix)) {
            return false;
        }
        fileName = FileTypeUtils.normalizePath(fileName);
        String[] split = fileName.substring(0, fileName.indexOf(suffix)).split("/");
        for (String item : split) {
            System.out.println(item);
            System.out.println(Pattern.matches("[a-zA-Z0-9_]+", item));
            if (StringUtils.isEmpty(item) || !Pattern.matches("^[a-zA-Z0-9_]+$", item)) {
                return false;
            }
        }
        return true;
    }

    @Log(title = "清理区块缓存", businessType = BusinessType.OTHER)
    @DeleteMapping("/clearIncludeCache")
    public R<?> clearIncludeCache(@RequestBody @NotEmpty Long[] templateIds) {
        cmsTemplateService.selectCmsTemplateByTemplateIdInList(templateIds).forEach(template -> {
            CmsSite site = cmsSiteService.selectCmsSiteBySiteId(template.getSiteId());
            String templateKey = SiteUtils.getTemplateKey(site, template.getPublishPipeCode(), template.getPath());
            cmsTemplateService.clearTemplateStaticContentCache(templateKey);
        });
        return R.ok();
    }
}
