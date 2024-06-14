package com.yuji.contentcore.properties;

import com.yuji.common.core.utils.JacksonUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.core.IProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 资源存储策略参数
 */
@Component(IProperty.BEAN_NAME_PREFIX + FileStorageArgsProperty.ID)
public class FileStorageArgsProperty implements IProperty {

	public final static String ID = "FileStorageArgs";
	
	static UseType[] UseTypes = new UseType[] { UseType.Site };

	@Override
	public UseType[] getUseTypes() {
		return UseTypes;
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return "资源存储策略参数";
	}
	
	@Override
	public FileStorageArgs defaultValue() {
		return new FileStorageArgs();
	}
	
	@Override
	public FileStorageArgs getPropValue(Map<String, String> configProps) {
		String v = MapUtils.getString(configProps, getId());
		if (StringUtils.isNotEmpty(v)) {
			return JacksonUtils.from(v, FileStorageArgs.class);
		}
		return defaultValue();
	}	
	
	public static FileStorageArgs getValue(Map<String, String> props) {
		String v = MapUtils.getString(props, ID);
		if (StringUtils.isNotEmpty(v)) {
			return JacksonUtils.from(v, FileStorageArgs.class);
		}
		return new FileStorageArgs();
	}
	
	@Getter
	@Setter
	public static class FileStorageArgs {
		
		private String accessKey;
		
		private String accessSecret;
		
		private String endpoint;
		
		private String bucket;
		
		private String pipeline;
		
		private String domain;
	}
}
