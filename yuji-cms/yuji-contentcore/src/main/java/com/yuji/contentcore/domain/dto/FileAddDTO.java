package com.yuji.contentcore.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FileAddDTO {
	
	/*
	 * 目所在录
	 */
	@NotEmpty
	private String dir;

	/*
	 * 文件/目录名
	 */
	@NotEmpty
	private String fileName;

	/*
	 * 是否目录
	 */
	@NotNull
	private Boolean isDirectory;
	
}
