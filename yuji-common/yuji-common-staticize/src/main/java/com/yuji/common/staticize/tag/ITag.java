package com.yuji.common.staticize.tag;

import java.util.List;

public interface ITag {
	
    /**
     * 标签名
     * 
     * <@value></@value>
     */
    String getTagName();

    /**
     * 标签名称
     */
    String getName();

    /**
     * 标签描述
     */
    default String getDescription() {
        return "";
    }
    
    /**
     * 标签属性定义
     */
    default List<TagAttr> getTagAttrs() {
    	return null;
    }
}
