package com.yuji.contentcore.fixed.dict;

import com.yuji.common.core.fixed.FixedDictType;
import org.springframework.stereotype.Component;

/**
 * 静态化文件名后缀
 */
@Component(FixedDictType.BEAN_PREFIX + StaticSuffix.TYPE)
public class StaticSuffix extends FixedDictType {

	public static final String TYPE = "CMSStaticSuffix";

	public static final String SHTML = "shtml";

	public static final String HTML = "html";

	public static final String XML = "xml";

	public static final String JSON = "json";

}
