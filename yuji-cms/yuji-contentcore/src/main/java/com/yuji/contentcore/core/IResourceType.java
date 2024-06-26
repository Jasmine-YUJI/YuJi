package com.yuji.contentcore.core;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.domain.CmsResource;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;

/**
 * 资源类型
 *
 * @author Liguoqiang
 */
public interface IResourceType {
	
	/**
	 * Bean名称前缀
	 */
	String BEAN_NAME_PREFIX = "ResourceType_";
    
    /**
     * 站点上传资源文件目录
     */
    String UploadResourceDirectory = "resources/";

    /**
     * 唯一标识
     */
	String getId();

	/**
	 * 名称
	 */
	String getName();

	/**
	 * 资源类型所用后缀
	 */
	String[] getUsableSuffix();
	
	/**
	 * 校验文件后缀是否符合当前资源类型
	 * 
	 * @param suffix
	 * @return
	 */
	default boolean check(String suffix) {
		return ArrayUtils.contains(this.getUsableSuffix(), suffix.toLowerCase());
	}
	
	default String getUploadPath() {
		return UploadResourceDirectory + this.getId() + StringUtils.SLASH;
	}
	
	/**
	 * 处理资源：提取资源属性、添加水印等
	 * 
	 * @param resource
	 * @throws IOException 
	 */
	default byte[] process(CmsResource resource, byte[] bytes) throws IOException {
		resource.setFileSize((long) bytes.length);
		return bytes;
	}
}
