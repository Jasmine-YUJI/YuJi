package com.yuji.system.config;

import com.yuji.common.core.utils.SpringUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.file.FileTypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;

@Slf4j
@Configuration
@EnableConfigurationProperties(SysProperties.class)
public class SystemConfig implements WebMvcConfigurer {
	
	/**
	 * 通用资源文件上传目录
	 */
	private static String UPLOAD_DIRECTORY;
	
	private final SysProperties properties;
	
	public SystemConfig(SysProperties properties) throws FileNotFoundException {
		UPLOAD_DIRECTORY = properties.getUploadPath();
		if (StringUtils.isEmpty(UPLOAD_DIRECTORY)) {
			UPLOAD_DIRECTORY = SpringUtils.getAppParentDirectory() + "/system/profile/";
		}
		UPLOAD_DIRECTORY = FileTypeUtils.normalizePath(UPLOAD_DIRECTORY);
		if (!UPLOAD_DIRECTORY.endsWith("/")) {
			UPLOAD_DIRECTORY += "/";
		}
		FileTypeUtils.mkdirs(UPLOAD_DIRECTORY);
		properties.setUploadPath(UPLOAD_DIRECTORY);
		log.info("System upload directory: " + UPLOAD_DIRECTORY);
		this.properties = properties;
	}
	
	/**
	 * 获取资源文件上传根目录
	 */
	public static String getUploadDir() {
		return UPLOAD_DIRECTORY;
	}
	
	/**
	 * 获取资源文件预览地址前缀
	 */
	public static String getResourcePrefix() {
		return "/system/profile/";
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/** 本地文件上传路径 */
		registry.addResourceHandler("/profile/**")
				.addResourceLocations("file:" + this.properties.getUploadPath());
	}

	/**
	 * 开启跨域
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路由
		registry.addMapping("/profile/**")
				// 设置允许跨域请求的域名
				.allowedOrigins("*")
				// 设置允许的方法
				.allowedMethods("GET");
	}
}
