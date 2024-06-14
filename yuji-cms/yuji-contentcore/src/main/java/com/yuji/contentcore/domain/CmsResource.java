package com.yuji.contentcore.domain;

import com.yuji.contentcore.fixed.dict.EnableOrDisable;
import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

/**
 * 资源对象 cms_resource
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long resourceId;

    /** 站点id */
    @Excel(name = "站点id")
    private Long siteId;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String resourceType;

    /** 存储类型（本地=local，Minio=minio） */
    @Excel(name = "存储类型", readConverterExp = "本地=local，Minio=minio")
    private String storageType;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String name;

    /** 文件保存相对路径 */
    @Excel(name = "文件保存相对路径")
    private String path;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 后缀名，不带. */
    @Excel(name = "后缀名，不带.")
    private String suffix;

    /** 宽 */
    @Excel(name = "宽")
    private Integer width;

    /** 高 */
    @Excel(name = "高")
    private Integer height;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long fileSize;

    /** 来源地址 */
    @Excel(name = "来源地址")
    private String sourceUrl;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 引用关系 */
    @Excel(name = "引用关系")
    private String usageInfo;

    /**
     * 资源类型名称
     */
    private String resourceTypeName;


    private String fileSizeName;

    private String src;

    private String internalUrl;

    public boolean isEnable() {
        return EnableOrDisable.isEnable(this.status);
    }

}
