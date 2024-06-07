package com.yuji.contentcore.core;


import com.yuji.common.core.utils.StringUtils;

import java.util.List;

/**
 * 发布通道属性
 *
 * @author Liguoqiang
 */
public interface IPublishPipeProp {

	String BEAN_PREFIX = "PublishPipeProp_";
	
	String DetailTemplatePropPrefix = "detailTemplate_";
	
	String DefaultDetailTemplatePropPrefix = "defaultDetailTemplate_";

	/**
	 * 属性唯一标识键名
	 */
	String getKey();
	
	/**
	 * 属性名称
	 */
	String getName();
	
	/**
	 * 属性应用类型
	 */
	List<PublishPipePropUseType> getUseTypes();
	
	/**
	 * 默认值
	 */
	default String getDefaultValue() {
		return StringUtils.EMPTY;
	}
	
	/**
	 * 应用类型
	 */
	enum PublishPipePropUseType {
		Site, Catalog, Content
	}
}
