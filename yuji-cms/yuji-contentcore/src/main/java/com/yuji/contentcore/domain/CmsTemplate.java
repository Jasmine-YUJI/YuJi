package com.yuji.contentcore.domain;

import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

/**
 * 模板管理对象 cms_template
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long templateId;

    /** 站点ID */
    @Excel(name = "站点ID")
    private Long siteId;

    /** 发布通道编码 */
    @Excel(name = "发布通道编码")
    private String publishPipeCode;

    /** 模板路径 */
    @Excel(name = "模板路径")
    private String path;

    /** 模板内容 */
    @Excel(name = "模板内容")
    private String content;

    /** 模板文件大小 */
    @Excel(name = "模板文件大小")
    private Long filesize;

    /** 模板文件更新时间戳 */
    @Excel(name = "模板文件更新时间戳")
    private Long modifyTime;


}
