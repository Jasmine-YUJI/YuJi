package com.yuji.contentcore.fixed.config;

import com.yuji.common.core.constant.SecurityConstants;
import com.yuji.common.core.fixed.FixedConfig;
import com.yuji.common.core.utils.SpringUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.system.api.RemoteConfigService;
import org.springframework.stereotype.Component;

/**
 * 模板文件后缀名，默认：.template.html
 */
@Component(FixedConfig.BEAN_PREFIX + TemplateSuffix.ID)
public class TemplateSuffix extends FixedConfig {

	public static final String ID = "cms.template.suffix";

	private static final RemoteConfigService remoteConfigService = SpringUtils.getBean(RemoteConfigService.class);
	
	private static final String DEFAULT_VALUE = ".template.html";
	
	public TemplateSuffix() {
		super(ID, "{CONFIG." + ID + "}", DEFAULT_VALUE, null);
	}
	
	public static String getValue() {
		String configValue = remoteConfigService.getConfigKey(ID, SecurityConstants.INNER).getData();
		if (StringUtils.isBlank(configValue)) {
			configValue = DEFAULT_VALUE;
		}
		return configValue;
	}
}
