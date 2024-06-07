package com.yuji.contentcore.domain.vo;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.domain.dto.PublishPipeProp;
import com.yuji.contentcore.fixed.dict.ContentAttribute;
import com.yuji.contentcore.utils.InternalUrlUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter 
@Setter
public class ContentVO {

	/**
	 * 内容ID
	 */
	private Long contentId;

	/**
	 * 栏目ID
	 */
	private Long catalogId;
    
	/**
	 * 栏目名称
	 */
    private String catalogName;

    /**
     * 内容类型
     */
	private String contentType;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 副标题
	 */
    private String subTitle;

    /**
     * 短标题
     */
    private String shortTitle;

    /**
     * 标题样式
     */
    private String titleStyle;
	
    /**
     * 是否显示副标题，用户偏好设置(Y/N)
     */
	private String showSubTitle;

    /**
     * 引导图
     */
    private String logo;

    /**
     * 引导图预览路径
     */
    private String logoSrc;
    
    /**
     * 发布链接
     */
    private String link;

    /**
     * 来源名称
     */
    private String source;

    /**
     * 来源URL
     */
    private String sourceUrl;

    /**
     * 是否原创
     */
    private String original;

    /**
     * 作者
     */
    private String author;

    /**
     * 编辑
     */
    private String editor;
	
    /**
	 * 摘要
	 */
    private String summary;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 是否链接内容
     */
    private String linkFlag;

    /**
     * 链接
     */
    private String redirectUrl;

    /**
     * 内容属性值数组
     */
    private String[] attributes;

    /**
     * 置顶标识
     */
    private Long topFlag;

    /**
     * 置顶时间
     */
    private Date topDate;

    /**
     * 关键词
     */
    private String[] keywords;

    /**
     * TAG
     */
    private String[] tags;

    /**
     * 是否锁定
     */
    private String isLock;

    /**
     * 锁定人
     */
    private String lockUser;

    /**
     * 复制类型
     */
    private Integer copyType;

    /**
     * 复制源ID
     */
    private Long copyId;

    /**
     * 发布时间
     */
    private LocalDateTime publishDate;

    /**
     * 下线时间
     */
    private LocalDateTime offlineDate;

    /**
     * 发布通道
     */
    private String[] publishPipe;

    /**
     * 独立路径
     */
    private String staticPath;
    
    /**
     * 发布通道配置
     */
    private List<PublishPipeProp> publishPipeProps;

    /**
     * SEO标题
     */
    private String seoTitle;

    /**
     * SEO关键词
     */
    private String seoKeywords;

    /**
     * SEO描述
     */
    private String seoDescription;

    /**
     * 备用字段1
     */
    private String prop1;

    /**
     * 备用字段2
     */
    private String prop2;

    /**
     * 备用字段3
     */
    private String prop3;

    /**
     * 备用字段4
     */
    private String prop4;
    
    /**
     * 备注
     */
	private String remark;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	
	/**
	 * 栏目扩展配置
	 */
	private Map<String, Object> catalogConfigProps = new HashMap<>(); 
	
	/**
	 * 自定义参数
	 */
	private Map<String, Object> params;
	
	public static ContentVO newInstance(CmsContent cmsContent) {
		ContentVO dto = new ContentVO();
		BeanUtils.copyProperties(cmsContent, dto);
		dto.setAttributes(ContentAttribute.convertStr(cmsContent.getAttributes()));
		if (StringUtils.isNotEmpty(dto.getLogo())) {
			dto.setLogoSrc(InternalUrlUtils.getActualPreviewUrl(dto.getLogo()));
		}
		return dto;
	}
}
