package com.yuji.contentcore.domain;

import lombok.Getter;
import lombok.Setter;
import com.yuji.common.core.annotation.Excel;
import com.yuji.common.core.web.domain.BaseEntity;

/**
 * 关联内容对象 cms_content_rela
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Getter
@Setter
public class CmsContentRela extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long relaId;

    /** 内容ID */
    @Excel(name = "内容ID")
    private Long contentId;

    /** 关联内容ID */
    @Excel(name = "关联内容ID")
    private Long relaContentId;

    /** 站点 */
    @Excel(name = "站点")
    private Long siteId;


}
