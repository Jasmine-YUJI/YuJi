package com.yuji.contentcore.fixed.config;

import com.yuji.common.core.constant.SecurityConstants;
import com.yuji.common.core.fixed.FixedConfig;
import com.yuji.common.core.utils.SpringUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.system.api.RemoteConfigService;
import org.springframework.stereotype.Component;

/**
 * 后台访问地址
 */
@Component(FixedConfig.BEAN_PREFIX + BackendContext.ID)
public class BackendContext extends FixedConfig {

	public static final String ID = "cms.backend.context";

	private static final RemoteConfigService remoteConfigService = SpringUtils.getBean(RemoteConfigService.class);
	
	private static final String DEFAULT_VALUE = "http://localhost/dev-api/";
	
	public BackendContext() {
		super(ID, "{CONFIG." + ID + "}", DEFAULT_VALUE, null);
	}
	
	public static String getValue() {
		String configValue = remoteConfigService.getConfigKey(ID, SecurityConstants.INNER).getData();
		if (StringUtils.isBlank(configValue)) {
			configValue = DEFAULT_VALUE;
		}
		if (!configValue.endsWith("/")) {
			configValue += "/";
		}
		return configValue;
	}
}
