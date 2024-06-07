package com.yuji.contentcore.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PageWidgetEditDTO {

	/**
	 * 页面部件ID
	 */
	private Long pageWidgetId;

	/**
	 * 名称
	 */
    @NotBlank
    private String name;

    /**
     * 编码
     */
    @NotBlank
    private String code;

    /**
     * 发布通道编码
     */
    @NotEmpty
    private String publishPipeCode;
    
    /**
     * 模板路径
     */
    private String template;
    
    /**
     * 静态化目录
     */
    @NotEmpty
    private String path;
    
    /**
     * 页面部件内容
     */
    private String contentStr;
    
    /**
     * 备注
     */
    private String remark;
}
