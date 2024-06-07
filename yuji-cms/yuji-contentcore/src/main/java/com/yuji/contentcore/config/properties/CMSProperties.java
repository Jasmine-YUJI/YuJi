package com.yuji.contentcore.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CMS配置属性
 *
 * @author Liguoqiang
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "yuji.cms")
public class CMSProperties {
	
	/**
	 *	资源文件根目录 
	 */
	private String resourceRoot;
	
	/**
	 * 缓存名统一前缀
	 */
	private String cacheName = "cms:";
	
	/**
	 * 系统启动时是否清空cacheName前缀的所有缓存
	 */
	private Boolean resetCache = true;
}
