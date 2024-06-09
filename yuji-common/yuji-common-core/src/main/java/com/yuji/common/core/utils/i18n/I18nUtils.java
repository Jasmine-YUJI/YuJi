package com.yuji.common.core.utils.i18n;

import com.yuji.common.core.utils.SpringUtils;
import com.yuji.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.PropertyPlaceholderHelper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
public class I18nUtils {

	private final static MessageSource messageSource = SpringUtils
			.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME);

	private static final PropertyPlaceholderHelper FieldPlaceholderHelper = new PropertyPlaceholderHelper("#{", "}");

	private static final I18nPlaceholderHelper PlaceholderHelper = new I18nPlaceholderHelper("{", "}", ":",
			true);


	/**
	 * 获取国际化键名对应的当前默认语言值
	 *
	 * @param str
	 * @return
	 */
	public static String get(String str) {
		return get(str, LocaleContextHolder.getLocale());
	}


	public static String get(String str, Object... args) {
		return get(str, LocaleContextHolder.getLocale(), args);
	}

	/**
	 * 获取国际化键名指定的语言值
	 *
	 * @param str
	 * @param locale
	 * @param args
	 * @return
	 */
	public static String get(String str, Locale locale, Object... args) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		return PlaceholderHelper.replacePlaceholders(str, langKey -> messageSource.getMessage(langKey, args, locale));
	}
}
