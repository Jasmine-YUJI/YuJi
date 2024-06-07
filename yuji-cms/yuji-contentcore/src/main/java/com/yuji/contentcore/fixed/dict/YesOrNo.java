package com.yuji.contentcore.fixed.dict;

import com.yuji.common.core.fixed.FixedDictType;
import org.springframework.stereotype.Component;


/**
 * 是/否
 */
@Component(FixedDictType.BEAN_PREFIX + YesOrNo.TYPE)
public class YesOrNo extends FixedDictType {

	public static final String TYPE = "YesOrNo";
	
	public static final String YES = "Y";
	
	public static final String NO = "N";



	
	public static boolean isYes(String v) {
		return YES.equals(v);
	}
	
	public static boolean isNo(String v) {
		return !isYes(v);
	}


}
