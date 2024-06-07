package com.yuji.contentcore.domain;

import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

/**
 * 页面部件对象 cms_page_widget
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsPageWidget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long pageWidgetId;

    /** 所属站点ID */
    @Excel(name = "所属站点ID")
    private Long siteId;

    /** 所属栏目ID */
    @Excel(name = "所属栏目ID")
    private Long catalogId;

    /** 所属栏目祖级IDs */
    @Excel(name = "所属栏目祖级IDs")
    private String catalogAncestors;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 编码，站内唯一标识 */
    @Excel(name = "编码，站内唯一标识")
    private String code;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 发布通道编码 */
    @Excel(name = "发布通道编码")
    private String publishPipeCode;

    /** 模板 */
    @Excel(name = "模板")
    private String template;

    /** 发布目录 */
    @Excel(name = "发布目录")
    private String path;

    /** 页面部件内容 */
    @Excel(name = "页面部件内容")
    private String content;


}
