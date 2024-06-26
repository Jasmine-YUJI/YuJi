package com.yuji.contentcore.template.exception;

import com.yuji.common.core.utils.StringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateException;

public class SiteNotFoundException extends TemplateException {

	private static final long serialVersionUID = 1L;

	public SiteNotFoundException(String tag, long siteId, Environment env) {
		super(StringUtils.messageFormat("<@{0}>[id: {1}]", tag, siteId), env);
	}
}
