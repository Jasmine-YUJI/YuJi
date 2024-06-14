package com.yuji.contentcore.properties;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.core.IProperty;
import com.yuji.contentcore.utils.ConfigPropertyUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 素材库图片默认缩略图宽度配置
 *
 * @author Liguoqiang
 */
@Component(IProperty.BEAN_NAME_PREFIX + ThumbnailWidthProperty.ID)
public class ThumbnailWidthProperty implements IProperty {

	public final static String ID = "ThumbnailWidth";

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
		return "素材库图片默认缩略图宽度配置";
	}
	
	@Override
	public boolean validate(String value) {
		return StringUtils.isEmpty(value) || NumberUtils.isCreatable(value);
	}
	
	@Override
	public Integer defaultValue() {
		return 0;
	}
	
	@Override
	public Integer getPropValue(Map<String, String> configProps) {
		String string = MapUtils.getString(configProps, getId());
		if (this.validate(string)) {
			return NumberUtils.toInt(string);
		}
		return defaultValue();
	}
	
	public static int getValue(Map<String, String> siteProps) {
		return ConfigPropertyUtils.getIntValue(ID, siteProps);
	}
}