package com.yuji.contentcore.domain;

import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 站点自定义属性对象 cms_site_property
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsSiteProperty extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 属性ID */
    private Long propertyId;

    /** 站点ID */
    @Excel(name = "站点ID")
    private Long siteId;

    /** 属性名称 */
    @Excel(name = "属性名称")
    @NotBlank
    private String propName;

    /** 属性编码 */
    @Excel(name = "属性编码")
    @Pattern(regexp = "[A-Za-z0-9_]+")
    private String propCode;

    /** 属性值 */
    @Excel(name = "属性值")
    private String propValue;


}
