package com.yuji.contentcore.properties;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.core.IProperty;
import com.yuji.contentcore.fixed.dict.YesOrNo;
import com.yuji.contentcore.utils.ConfigPropertyUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 是否支持SSI
 */
@Component(IProperty.BEAN_NAME_PREFIX + EnableSSIProperty.ID)
public class EnableSSIProperty implements IProperty {

	public final static String ID = "SSIEnabled";
	
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
		return "是否支持SSI";
	}

	@Override
	public String defaultValue() {
		return YesOrNo.YES;
	}

	public static boolean getValue(Map<String, String> props) {
		String v = ConfigPropertyUtils.getStringValue(ID, props);
		if (StringUtils.isEmpty(v)) {
			v = YesOrNo.YES;
		}
		return YesOrNo.isYes(v);
	}
}
