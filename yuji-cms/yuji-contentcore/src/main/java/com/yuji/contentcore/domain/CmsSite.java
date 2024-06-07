package com.yuji.contentcore.domain;

import com.yuji.contentcore.core.impl.PublishPipeProp_IndexTemplate;
import com.yuji.contentcore.core.impl.PublishPipeProp_SiteUrl;
import com.yuji.contentcore.core.impl.PublishPipeProp_StaticSuffix;
import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 站点管理对象 cms_site
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 站点ID */
    private Long siteId;

    /** 父级站点ID */
    @Excel(name = "父级站点ID")
    private Long parentId;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String description;

    /** 站点LOGO */
    @Excel(name = "站点LOGO")
    private String logo;

    /** 站点目录 */
    @Excel(name = "站点目录")
    private String path;

    /** 站点资源访问地址 */
    @Excel(name = "站点资源访问地址")
    private String resourceUrl;

    /** 顶级栏目编码最大值 */
    @Excel(name = "顶级栏目编码最大值")
    private Long catalogMaxCode;

    /** 所属机构编码 */
    @Excel(name = "所属机构编码")
    private String deptCode;

    /** 首页模板 */
    @Excel(name = "首页模板")
    private String indexTemplate;

    /** 静态文件类型 */
    @Excel(name = "静态文件类型")
    private String staticSuffix;

    /** 排序标识 */
    @Excel(name = "排序标识")
    private Long sortFlag;

    /** 发布通道属性 */
    @Excel(name = "发布通道属性")
    private Map<String, Map<String, Object>> publishPipeProps;

    /** 站点扩展属性 */
    @Excel(name = "站点扩展属性")
    private Map<String, String> configProps;

    /** SEO关键词 */
    @Excel(name = "SEO关键词")
    private String seoKeywords;

    /** SEO描述 */
    @Excel(name = "SEO描述")
    private String seoDescription;

    /** SEO标题 */
    @Excel(name = "SEO标题")
    private String seoTitle;

    /**
     * logo预览地址
     */
    private String logoSrc;

    private String link;

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

    public String getStaticSuffix(String publishPipeCode) {
        return PublishPipeProp_StaticSuffix.getValue(publishPipeCode, this.publishPipeProps);
    }

    public String getUrl(String publishPipeCode) {
        String ppUrl = PublishPipeProp_SiteUrl.getValue(publishPipeCode, this.publishPipeProps);
        if (ppUrl != null && !ppUrl.endsWith("/")) {
            ppUrl += "/";
        }
        return ppUrl;
    }

}
