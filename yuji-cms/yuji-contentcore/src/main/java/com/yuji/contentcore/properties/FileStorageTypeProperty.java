package com.yuji.contentcore.properties;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.file.local.LocalFileStorageType;
import com.yuji.contentcore.core.IProperty;
import com.yuji.contentcore.utils.ConfigPropertyUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 资源存储策略
 */
@Component(IProperty.BEAN_NAME_PREFIX + FileStorageTypeProperty.ID)
public class FileStorageTypeProperty implements IProperty {

	public final static String ID = "FileStorageType";
	
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
		return "资源存储策略";
	}
	
	@Override
	public String defaultValue() {
		return LocalFileStorageType.TYPE;
	}
	
	public static String getValue(Map<String, String> props) {
		String value = ConfigPropertyUtils.getStringValue(ID, props);
		return StringUtils.isEmpty(value) ? LocalFileStorageType.TYPE : value;
	}
}
