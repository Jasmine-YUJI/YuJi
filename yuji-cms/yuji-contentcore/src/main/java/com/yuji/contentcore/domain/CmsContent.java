package com.yuji.contentcore.domain;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yuji.contentcore.fixed.dict.YesOrNo;
import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

/**
 * 内容管理对象 cms_content
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long contentId;

    /** 所属站点ID */
    @Excel(name = "所属站点ID")
    private Long siteId;

    /** 所属栏目ID */
    @Excel(name = "所属栏目ID")
    private Long catalogId;

    /** 所属栏目祖级IDs */
    @Excel(name = "所属栏目祖级IDs")
    private String catalogAncestors;

    /** 所属顶级栏目ID */
    @Excel(name = "所属顶级栏目ID")
    private Long topCatalog;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 部门编码 */
    @Excel(name = "部门编码")
    private String deptCode;

    /** 内容类型 */
    @Excel(name = "内容类型")
    private String contentType;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 副标题 */
    @Excel(name = "副标题")
    private String subTitle;

    /** 短标题 */
    @Excel(name = "短标题")
    private String shortTitle;

    /** 标题样式 */
    @Excel(name = "标题样式")
    private String titleStyle;

    /** 引导图 */
    @Excel(name = "引导图")
    private String logo;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 来源URL */
    @Excel(name = "来源URL")
    private String sourceUrl;

    /** 是否原创（Y=是，N=否） */
    @Excel(name = "是否原创", readConverterExp = "Y==是，N=否")
    private String original;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 编辑 */
    @Excel(name = "编辑")
    private String editor;

    /** 摘要 */
    @Excel(name = "摘要")
    private String summary;

    /** 自定义内容静态化文件路径 */
    @Excel(name = "自定义内容静态化文件路径")
    private String staticPath;

    /** 内容状态 */
    @Excel(name = "内容状态")
    private String status;

    /** 内容属性 */
    @Excel(name = "内容属性")
    private Integer attributes;

    /** 置顶标识 */
    @Excel(name = "置顶标识")
    private Long topFlag;

    /** 置顶时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "置顶时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date topDate;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long sortFlag;

    /** 关键词 */
    @Excel(name = "关键词")
    private String[] keywords;

    /** TAGs */
    @Excel(name = "TAGs")
    private String[] tags;

    /** 复制类型 */
    @Excel(name = "复制类型")
    private Long copyType;

    /** 复制源ID */
    @Excel(name = "复制源ID")
    private Long copyId;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishDate;

    /** 下线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date offlineDate;

    /** 发布通道 */
    @Excel(name = "发布通道")
    private String[] publishPipe;

    /** 发布通道属性，配置独立模板用 */
    @Excel(name = "发布通道属性，配置独立模板用")
    private Map<String, Map<String, Object>> publishPipeProps;

    /** 是否链接内容（Y=是，N=否） */
    @Excel(name = "是否链接内容", readConverterExp = "Y==是，N=否")
    private String linkFlag;

    /** 跳转链接 */
    @Excel(name = "跳转链接")
    private String redirectUrl;

    /** 是否已锁定（Y=是，N=否） */
    @Excel(name = "是否已锁定", readConverterExp = "Y==是，N=否")
    private String isLock;

    /** 锁定用户名 */
    @Excel(name = "锁定用户名")
    private String lockUser;

    /** 扩展属性 */
    @Excel(name = "扩展属性")
    private Map<String, Object> configProps;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long likeCount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long commentCount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long favoriteCount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long viewCount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long contributorId;

    /** SEO标题 */
    @Excel(name = "SEO标题")
    private String seoTitle;

    /** SEO关键词 */
    @Excel(name = "SEO关键词")
    private String seoKeywords;

    /** SEO描述 */
    @Excel(name = "SEO描述")
    private String seoDescription;

    public boolean isLock() {
        return YesOrNo.isYes(this.isLock);
    }

    public boolean isLinkContent() {
        return YesOrNo.isYes(this.linkFlag);
    }

}
