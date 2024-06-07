package com.yuji.contentcore.domain;

import com.yuji.contentcore.core.impl.PublishPipeProp_IndexTemplate;
import com.yuji.contentcore.core.impl.PublishPipeProp_ListTemplate;
import com.yuji.contentcore.fixed.dict.EnableOrDisable;
import com.yuji.contentcore.fixed.dict.YesOrNo;
import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 栏目管理对象 cms_catalog
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsCatalog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long catalogId;

    /** 站点ID */
    @Excel(name = "站点ID")
    private Long siteId;

    /** 父级栏目ID */
    @Excel(name = "父级栏目ID")
    private Long parentId;

    /** 栏目名称 */
    @Excel(name = "栏目名称")
    private String name;

    /**
     * 父级栏目名称（非表字段）
     */
    private String parentName;

    /** 栏目引导图 */
    @Excel(name = "栏目引导图")
    private String logo;

    /** 栏目别名 */
    @Excel(name = "栏目别名")
    private String alias;

    /** 栏目简介 */
    @Excel(name = "栏目简介")
    private String description;

    /** 栏目祖级IDs */
    @Excel(name = "栏目祖级IDs")
    private String ancestors;

    /** 所属部门编码 */
    @Excel(name = "所属部门编码")
    private String deptCode;

    /** 栏目类型 */
    @Excel(name = "栏目类型")
    private String catalogType;

    /** 站点路径 */
    @Excel(name = "站点路径")
    private String path;

    /** 标题栏目跳转地址 */
    @Excel(name = "标题栏目跳转地址")
    private String redirectUrl;

    /** 是否生成静态页面 */
    @Excel(name = "是否生成静态页面")
    private String staticFlag;

    /** 栏目是否可见 */
    @Excel(name = "栏目是否可见")
    private String visibleFlag;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long sortFlag;

    /** 栏目首页模板 */
    @Excel(name = "栏目首页模板")
    private String indexTemplate;

    /** 栏目首页命名 */
    @Excel(name = "栏目首页命名")
    private String indexFileName;

    /** 列表页模板 */
    @Excel(name = "列表页模板")
    private String listTemplate;

    /** 列表页命名规则 */
    @Excel(name = "列表页命名规则")
    private String listNameRule;

    /** 详情页模板 */
    @Excel(name = "详情页模板")
    private String detailTemplate;

    /** 详情页命名规则 */
    @Excel(name = "详情页命名规则")
    private String detailNameRule;

    /** 栏目层级 */
    @Excel(name = "栏目层级")
    private Long treeLevel;

    /** 子栏目数 */
    @Excel(name = "子栏目数")
    private Long childCount;

    /** 内容数量 */
    @Excel(name = "内容数量")
    private Long contentCount;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 点击量 */
    @Excel(name = "点击量")
    private Long hitCount;

    /** SEO关键词 */
    @Excel(name = "SEO关键词")
    private String seoKeywords;

    /** SEO描述 */
    @Excel(name = "SEO描述")
    private String seoDescription;

    /** SEO标题 */
    @Excel(name = "SEO标题")
    private String seoTitle;

    /** 发布平台属性 */
    @Excel(name = "发布平台属性")
    private Map<String, Map<String, Object>> publishPipeProps;

    /** 扩展配置 */
    @Excel(name = "扩展配置")
    private Map<String, String> configProps;

    /**
     * logo预览地址（非表字段）
     */
    private String logoSrc;
    /**
     * 链接（非表字段）
     */
    private String link;

    /**
     * 列表页链接（无首页模板时与link一致）
     */
    private String listLink;

    public Map<String, String> getConfigProps() {
        if (this.configProps == null) {
            this.configProps = new HashMap<>();
        }
        return configProps;
    }

    public Map<String, Object> getPublishPipeProps(String publishPipeCode) {
        if (this.publishPipeProps == null) {
            this.publishPipeProps = new HashMap<>();
        }
        Map<String, Object> map = this.publishPipeProps.get(publishPipeCode);
        if (map == null) {
            map = new HashMap<>();
            this.publishPipeProps.put(publishPipeCode, map);
        }
        return map;
    }

    public String getIndexTemplate(String publishPipeCode) {
        return PublishPipeProp_IndexTemplate.getValue(publishPipeCode, this.publishPipeProps);
    }

    public String getListTemplate(String publishPipeCode) {
        return PublishPipeProp_ListTemplate.getValue(publishPipeCode, this.publishPipeProps);
    }

    public boolean isStaticize() {
        return YesOrNo.isYes(this.staticFlag);
    }

    public boolean isVisible() {
        return YesOrNo.isYes(this.visibleFlag);
    }

    public boolean isEnable() {
        return EnableOrDisable.isEnable(this.status);
    }
}
