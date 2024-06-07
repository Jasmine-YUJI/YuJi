package com.yuji.common.staticize.func.impl;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.func.AbstractFunc;
import freemarker.template.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Freemarker模板自定义函数：最小值
 */
@Component
@RequiredArgsConstructor
public class MinFunction extends AbstractFunc {

	static final String FUNC_NAME = "min";

	private static final String DESC = "获取多个数字中最小的数字";

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
		List<Number> numbers = readNumbers(args);
		return Collections.min(numbers, Comparator.comparing(Number::doubleValue));
	}

	static List<Number> readNumbers(Object... args) throws TemplateModelException {
		ArrayList<Number> numbers = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof SimpleNumber simpleNumber) {
                numbers.add(simpleNumber.getAsNumber());
            } else if (arg instanceof SimpleSequence simpleSequence) {
                for (int j = 0; j < simpleSequence.size(); j++) {
                    if (simpleSequence.get(j) instanceof SimpleNumber simpleNumber) {
                        numbers.add(simpleNumber.getAsNumber());
                    }
                }
            } else if (arg instanceof SimpleCollection simpleCollection) {
                TemplateModelIterator iterator = simpleCollection.iterator();
                while (iterator.hasNext()) {
                    TemplateModel next = iterator.next();
                    if (next instanceof SimpleNumber simpleNumber) {
                        numbers.add(simpleNumber.getAsNumber());
                    }
                }
            }
        }
		return numbers;
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of();
	}
}
