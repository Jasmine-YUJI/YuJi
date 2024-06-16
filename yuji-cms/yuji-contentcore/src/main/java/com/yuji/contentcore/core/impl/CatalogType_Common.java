package com.yuji.contentcore.core.impl;

import com.yuji.contentcore.core.ICatalogType;
import org.springframework.stereotype.Component;

/**
 * 栏目类型：普通栏目
 *
 * @author Liguoqiang
 */
@Component(ICatalogType.BEAN_NAME_PREFIX + CatalogType_Common.ID)
public class CatalogType_Common implements ICatalogType {

	public final static String ID = "common";
    
    private final static String NAME = "{CMS.CONTENTCORE.CATALOG_TYPE." + ID + "}";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
