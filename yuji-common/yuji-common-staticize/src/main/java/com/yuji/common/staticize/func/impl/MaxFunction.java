package com.yuji.common.staticize.func.impl;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.func.AbstractFunc;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Freemarker模板自定义函数：最大值
 */
@Component
@RequiredArgsConstructor
public class MaxFunction extends AbstractFunc {

	static final String FUNC_NAME = "max";

	private static final String DESC = "获取多个数字中最大的数字";

	@Override
	public String getFuncName() {
		return FUNC_NAME;
	}

	@Override
	public String getDesc() {
		return DESC;
	}

	@Override
	public Object exec0(Object... args) throws TemplateModelException {
		if (StringUtils.isEmpty(args)) {
			return StringUtils.EMPTY;
		}
		List<Number> numbers = MinFunction.readNumbers(args);
		return Collections.max(numbers, Comparator.comparing(Number::doubleValue));
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of();
	}
}
