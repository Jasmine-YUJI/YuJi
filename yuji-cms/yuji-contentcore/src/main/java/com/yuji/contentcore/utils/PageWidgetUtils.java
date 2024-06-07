package com.yuji.contentcore.utils;


import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.domain.CmsPageWidget;

public class PageWidgetUtils {

	public static String getStaticFileName(CmsPageWidget pw, String staticSuffix) {
		return pw.getCode() + StringUtils.DOT + staticSuffix;
	}
}
