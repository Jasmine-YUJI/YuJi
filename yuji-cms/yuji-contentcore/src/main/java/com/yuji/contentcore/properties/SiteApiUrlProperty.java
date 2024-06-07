package com.yuji.contentcore.properties;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.core.IProperty;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.utils.ConfigPropertyUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 站点API域名地址
 *
 * @author Liguoqiang
 */
@Component(IProperty.BEAN_NAME_PREFIX + SiteApiUrlProperty.ID)
public class SiteApiUrlProperty implements IProperty {

	public final static String ID = "SiteApiUrl";
	
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
		return "站点API域名地址";
	}

	//TODO 待解决
	@Override
	public String defaultValue() {
		return "http://localhost:8080/";
	}
	
	@Override
	public String getPropValue(Map<String, String> configProps) {
		String propValue = MapUtils.getString(configProps, getId());
		if (StringUtils.isNotEmpty(propValue)) {
			return propValue;
		}
		return defaultValue();
	}
	
	public static String getValue(CmsSite site, String publishPipeCode) {
		String apiUrl = ConfigPropertyUtils.getStringValue(ID, site.getConfigProps());
		if (StringUtils.isEmpty(apiUrl)) {
			apiUrl = "http://localhost:8080/";
		}
		if (StringUtils.isEmpty(apiUrl)) {
			apiUrl = site.getUrl(publishPipeCode);
		}
		if (StringUtils.isNotEmpty(apiUrl) && !apiUrl.endsWith("/")) {
			apiUrl += "/";
		}
		return apiUrl;
	}
}
