package com.yuji.contentcore.core.impl;

import com.yuji.contentcore.core.IPublishPipeProp;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 发布通道属性：栏目列表页模板
 *
 * @author Liguoqiang
 */
@Component(IPublishPipeProp.BEAN_PREFIX + PublishPipeProp_ListTemplate.KEY)
public class PublishPipeProp_ListTemplate implements IPublishPipeProp {

	public static final String KEY = "listTemplate";
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName() {
		return "列表页模板";
	}

	@Override
	public List<PublishPipePropUseType> getUseTypes() {
		return List.of(PublishPipePropUseType.Catalog);
	}

	public static String getValue(String publishPipeCode, Map<String, Map<String, Object>> publishPipeProps) {
		if (Objects.nonNull(publishPipeProps)) {
			return MapUtils.getString(publishPipeProps.get(publishPipeCode), KEY);
		}
		return null;
	}
}
