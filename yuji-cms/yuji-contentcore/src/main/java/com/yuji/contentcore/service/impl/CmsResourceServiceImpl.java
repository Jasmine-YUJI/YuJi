package com.yuji.contentcore.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.yuji.common.core.exception.CommonErrorCode;
import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.DateUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.common.core.utils.uuid.IdUtils;
import com.yuji.common.file.IFileStorageType;
import com.yuji.common.file.StorageWriteArgs;
import com.yuji.common.file.StorageWriteArgs.StorageWriteArgsBuilder;
import com.yuji.common.file.exception.StorageErrorCode;
import com.yuji.common.file.local.LocalFileStorageType;
import com.yuji.contentcore.core.IResourceType;
import com.yuji.contentcore.core.impl.InternalDataType_Resource;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.domain.dto.ResourceUploadDTO;
import com.yuji.contentcore.exception.ContentCoreErrorCode;
import com.yuji.contentcore.fixed.dict.EnableOrDisable;
import com.yuji.contentcore.mapper.CmsSiteMapper;
import com.yuji.contentcore.properties.FileStorageArgsProperty;
import com.yuji.contentcore.properties.FileStorageArgsProperty.FileStorageArgs;
import com.yuji.contentcore.properties.FileStorageTypeProperty;
import com.yuji.contentcore.utils.ResourceUtils;
import com.yuji.contentcore.utils.SiteUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsResourceMapper;
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.service.ICmsResourceService;

/**
 * 资源Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Slf4j
@Service
public class CmsResourceServiceImpl implements ICmsResourceService 
{
    @Autowired
    private CmsResourceMapper cmsResourceMapper;

    @Autowired
    private CmsSiteMapper cmsSiteMapper;

    @Autowired
    private Map<String, IFileStorageType> fileStorageTypes;


    /**
     * 查询资源
     * 
     * @param resourceId 资源主键
     * @return 资源
     */
    @Override
    public CmsResource selectCmsResourceByResourceId(Long resourceId)
    {
        return cmsResourceMapper.selectCmsResourceByResourceId(resourceId);
    }

    /**
     * 查询资源列表
     * 
     * @param cmsResource 资源
     * @return 资源
     */
    @Override
    public List<CmsResource> selectCmsResourceList(CmsResource cmsResource)
    {
        return cmsResourceMapper.selectCmsResourceList(cmsResource);
    }

    /**
     * 新增资源
     * 
     * @param dto 资源
     * @return 结果
     */
    @Override
    public CmsResource insertCmsResource(ResourceUploadDTO dto) throws IOException
    {
        String suffix = FileTypeUtils.getExtension(Objects.requireNonNull(dto.getFile().getOriginalFilename()));
        IResourceType resourceType = ResourceUtils.getResourceTypeBySuffix(suffix);
        Assert.notNull(resourceType, () -> ContentCoreErrorCode.UNSUPPORTED_RESOURCE_TYPE.exception(suffix));

        CmsResource cmsResource = new CmsResource();
        cmsResource.setResourceId(IdUtils.getSnowflakeId());
        cmsResource.setSiteId(dto.getSite().getSiteId());
        cmsResource.setResourceType(resourceType.getId());
        cmsResource.setFileName(dto.getFile().getOriginalFilename());
        cmsResource.setName(StringUtils.isEmpty(dto.getName()) ? dto.getFile().getOriginalFilename() : dto.getName());
        cmsResource.setSuffix(suffix);

        String siteResourceRoot = SiteUtils.getSiteResourceRoot(dto.getSite());
        String fileName = cmsResource.getResourceId() + StringUtils.DOT + suffix;
        String dir = resourceType.getUploadPath()
                + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + StringUtils.SLASH;
        FileTypeUtils.mkdirs(siteResourceRoot + dir);

        cmsResource.setPath(dir + fileName);
        cmsResource.setStatus(EnableOrDisable.ENABLE);
        cmsResource.setCreateBy(dto.getOperator());
        cmsResource.setRemark(dto.getRemark());
        cmsResource.setCreateTime(DateUtils.getNowDate());
        // 处理资源
        this.processResource(cmsResource, resourceType, dto.getSite(), dto.getFile().getBytes());
        cmsResourceMapper.insertCmsResource(cmsResource);
        return cmsResource;
    }

    /**
     * 修改资源
     * 
     * @param dto 资源
     * @return 结果
     */
    @Override
    public CmsResource updateCmsResource(ResourceUploadDTO dto) throws IOException
    {
        String suffix = FileTypeUtils.getExtension(Objects.requireNonNull(dto.getFile().getOriginalFilename()));
        IResourceType resourceType = ResourceUtils.getResourceTypeBySuffix(suffix);
        Assert.notNull(resourceType, () -> ContentCoreErrorCode.UNSUPPORTED_RESOURCE_TYPE.exception(suffix));

        CmsResource cmsResource = cmsResourceMapper.selectCmsResourceByResourceId(dto.getResourceId());
        Assert.notNull(cmsResource, () -> CommonErrorCode.DATA_NOT_FOUND_BY_ID.exception("resourceId", dto.getResourceId()));

        cmsResource.setResourceType(resourceType.getId());
        cmsResource.setFileName(dto.getFile().getOriginalFilename());
        cmsResource.setName(StringUtils.isEmpty(dto.getName()) ? dto.getFile().getOriginalFilename() : dto.getName());
        cmsResource.setSuffix(suffix);

        String fileName = cmsResource.getResourceId() + StringUtils.DOT + suffix;
        String path = StringUtils.substringBeforeLast(cmsResource.getPath(), "/") + fileName;

        cmsResource.setPath(path);
        cmsResource.setUpdateBy(dto.getOperator());
        cmsResource.setRemark(dto.getRemark());
        cmsResource.setUpdateTime(DateUtils.getNowDate());

        // 处理资源
        this.processResource(cmsResource, resourceType, dto.getSite(), dto.getFile().getBytes());
        cmsResourceMapper.updateCmsResource(cmsResource);
        return cmsResource;
    }

    /**
     * 批量删除资源
     * 
     * @param resourceIds 需要删除的资源主键
     * @return 结果
     */
    @Override
    public int deleteCmsResourceByResourceIds(Long[] resourceIds)
    {
        List<CmsResource> resources = cmsResourceMapper.selectListByIds(resourceIds);
        if (!resources.isEmpty()) {
            CmsSite site = cmsSiteMapper.selectCmsSiteBySiteId(resources.get(0).getSiteId());
            String siteRoot = SiteUtils.getSiteResourceRoot(site);
            resources.forEach(r -> {
                // 删除资源文件
                try {
                    File file = new File(siteRoot + r.getPath());
                    FileUtils.delete(file);
                    final String fileNamePrefix = StringUtils.substringBeforeLast(file.getName(), ".");
                    // 删除图片缩略图
                    File[] others = file.getParentFile().listFiles(
                            (dir, name) -> name.startsWith(fileNamePrefix)
                    );
                    if (Objects.nonNull(others)) {
                        for (File f : others) {
                            FileUtils.delete(f);
                        }
                    }
                } catch (IOException e) {
                    log.error("Delete resource file failed: " + r.getPath());
                }
            });
        }
        return cmsResourceMapper.deleteCmsResourceByResourceIds(resourceIds);
    }

    /**
     * 删除资源信息
     * 
     * @param resourceId 资源主键
     * @return 结果
     */
    @Override
    public int deleteCmsResourceByResourceId(Long resourceId)
    {
        CmsResource resource = cmsResourceMapper.selectCmsResourceByResourceId(resourceId);
        CmsSite site = cmsSiteMapper.selectCmsSiteBySiteId(resource.getSiteId());
        String siteRoot = SiteUtils.getSiteResourceRoot(site);
        // 删除资源文件
        try {
            File file = new File(siteRoot + resource.getPath());
            FileUtils.delete(file);
            final String fileNamePrefix = StringUtils.substringBeforeLast(file.getName(), ".");
            // 删除图片缩略图
            File[] others = file.getParentFile().listFiles(
                    (dir, name) -> name.startsWith(fileNamePrefix)
            );
            if (Objects.nonNull(others)) {
                for (File f : others) {
                    FileUtils.delete(f);
                }
            }
        } catch (IOException e) {
            log.error("Delete resource file failed: " + resource.getPath());
        }
        return cmsResourceMapper.deleteCmsResourceByResourceId(resourceId);
    }

    @Override
    public String getResourceLink(CmsResource resource, boolean isPreview) {
        CmsSite site = cmsSiteMapper.selectCmsSiteBySiteId(resource.getSiteId());
        String prefix = ResourceUtils.getResourcePrefix(resource.getStorageType(), site, isPreview);
        return prefix + resource.getPath();
    }

    private void processResource(CmsResource cmsResource, IResourceType resourceType, CmsSite site, byte[] bytes) throws IOException {
        // 处理资源，图片属性读取、水印等
        bytes = resourceType.process(cmsResource, bytes);
        // 写入磁盘/OSS
        String fileStorageType = FileStorageTypeProperty.getValue(site.getConfigProps());
        IFileStorageType fst = this.getFileStorageType(fileStorageType);
        FileStorageArgs fileStorageArgs = FileStorageArgsProperty.getValue(site.getConfigProps());
        // 写入参数设置
        StorageWriteArgsBuilder builder = StorageWriteArgs.builder();
        builder.bucket(fileStorageArgs.getBucket());
        if (LocalFileStorageType.TYPE.equals(fst.getType())) {
            builder.bucket(SiteUtils.getSiteResourceRoot(site));
        } else {
            builder.accessKey(fileStorageArgs.getAccessKey());
            builder.accessSecret(fileStorageArgs.getAccessSecret());
            builder.endpoint(fileStorageArgs.getEndpoint());
        }
        builder.path(cmsResource.getPath());
        builder.inputStream(new ByteArrayInputStream(bytes));
        fst.write(builder.build());
        cmsResource.setStorageType(fst.getType());
        // 内部链接
        cmsResource.setInternalUrl(InternalDataType_Resource.getInternalUrl(cmsResource));
    }

    private IFileStorageType getFileStorageType(String type) {
        IFileStorageType fileStorageType = fileStorageTypes.get(IFileStorageType.BEAN_NAME_PREIFX + type);
        Assert.notNull(fileStorageType, () -> StorageErrorCode.UNSUPPORTED_STORAGE_TYPE.exception(type));
        return fileStorageType;
    }
}
