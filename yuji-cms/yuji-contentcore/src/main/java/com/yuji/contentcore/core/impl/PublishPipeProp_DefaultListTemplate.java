package com.yuji.contentcore.core.impl;

import com.yuji.contentcore.core.IPublishPipeProp;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 发布通道属性：栏目列表页默认模板
 *
 * @author Liguoqiang
 */
@Component(IPublishPipeProp.BEAN_PREFIX + PublishPipeProp_DefaultListTemplate.KEY)
public class PublishPipeProp_DefaultListTemplate implements IPublishPipeProp {

	public static final String KEY = "defaultListTemplate";
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName() {
		return "列表页默认模板";
	}

	@Override
	public List<PublishPipePropUseType> getUseTypes() {
		return List.of(PublishPipePropUseType.Site);
	}

	public static String getValue(String publishPipeCode, Map<String, Map<String, Object>> publishPipeProps) {
		if (Objects.nonNull(publishPipeProps)) {
			return MapUtils.getString(publishPipeProps.get(publishPipeCode), KEY);
		}
		return null;
	}
}
