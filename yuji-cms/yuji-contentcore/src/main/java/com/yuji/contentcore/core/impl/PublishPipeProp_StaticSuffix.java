package com.yuji.contentcore.core.impl;

import com.yuji.contentcore.core.IPublishPipeProp;
import com.yuji.contentcore.fixed.dict.StaticSuffix;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 发布通道属性：静态文件类型
 *
 * @author Liguoqiang
 */
@Component(IPublishPipeProp.BEAN_PREFIX + PublishPipeProp_StaticSuffix.KEY)
public class PublishPipeProp_StaticSuffix implements IPublishPipeProp {

	public static final String KEY = "staticSuffix";
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName() {
		return "静态文件类型";
	}
	
	@Override
	public String getDefaultValue() {
		return StaticSuffix.SHTML;
	}

	@Override
	public List<PublishPipePropUseType> getUseTypes() {
		return List.of(PublishPipePropUseType.Site, PublishPipePropUseType.Catalog);
	}

	public static String getValue(String publishPipeCode, Map<String, Map<String, Object>> publishPipeProps) {
		if (Objects.nonNull(publishPipeProps)) {
			return MapUtils.getString(publishPipeProps.get(publishPipeCode), KEY);
		}
		return StaticSuffix.SHTML;
	}
}
