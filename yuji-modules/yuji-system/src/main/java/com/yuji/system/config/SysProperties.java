package com.yuji.system.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "yuji.system")
public class SysProperties {

	/**
	 * 资源文件上传根目录
	 */
	private String uploadPath;
}
