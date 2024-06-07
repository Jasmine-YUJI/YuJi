package com.yuji.contentcore.fixed.dict;

import com.yuji.common.core.fixed.FixedDictType;
import org.springframework.stereotype.Component;


/**
 * 启用/禁用
 */
@Component(FixedDictType.BEAN_PREFIX + EnableOrDisable.TYPE)
public class EnableOrDisable extends FixedDictType {

	public static final String TYPE = "EnableOrDisable";

	public static final String ENABLE = "0";

	public static final String DISABLE = "1";




	public static boolean isEnable(String v) {
		return ENABLE.equals(v);
	}

	public static boolean isDisable(String v) {
		return !isEnable(v);
	}


}
