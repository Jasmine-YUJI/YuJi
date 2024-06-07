package com.yuji.contentcore.domain;

import com.yuji.contentcore.fixed.dict.EnableOrDisable;
import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 发布通道对象 cms_publishpipe
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsPublishpipe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long publishpipeId;

    /** 站点ID */
    @Excel(name = "站点ID")
    private Long siteId;

    /** 发布点名称 */
    @Excel(name = "发布点名称")
    @NotBlank
    private String name;

    /** 发布点编码（同站点唯一标识） */
    @Excel(name = "发布点编码", readConverterExp = "同=站点唯一标识")
    @Pattern(regexp = "[A-Za-z0-9_]+")
    private String code;

    /** 发布通道状态（0 = 禁用，1 = 启用） */
    @Excel(name = "发布通道状态", readConverterExp = "0=,==,禁=用，1,==,启=用")
    private String state;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    public boolean isEnable() {
        return EnableOrDisable.isEnable(this.state);
    }

}
