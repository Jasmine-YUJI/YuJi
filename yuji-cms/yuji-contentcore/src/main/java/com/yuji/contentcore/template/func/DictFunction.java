package com.yuji.contentcore.template.func;

import com.yuji.common.core.constant.SecurityConstants;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.func.AbstractFunc;
import com.yuji.system.api.domain.SysDictData;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Freemarker模板自定义函数：获取字典指定值数据
 */
@Component
@RequiredArgsConstructor
public class DictFunction extends AbstractFunc {

	private static final String FUNC_NAME = "dict";

	private static final String DESC = "{FREEMARKER.FUNC.DESC." + FUNC_NAME + "}";

	//private final RemoteDictService remoteDictService;

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
		/*if (args.length < 1) {
			return List.of();
		}
		String dictType = args[0].toString();
		if (StringUtils.isBlank(dictType)) {
			return List.of();
		}
		String dictValue = args.length > 1 ? args[1].toString() : null;
		List<SysDictData> datas = remoteDictService.dictType(dictType, SecurityConstants.INNER).getData();
		if (StringUtils.isNotBlank(dictValue)) {
			Optional<SysDictData> first = datas.stream().filter(data -> StringUtils.equals(data.getDictValue(), dictValue)).findFirst();
			if (first.isEmpty()) {
				return List.of();
			}
			return List.of(first.get());
		}
		return datas;*/
		return null;
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of(new FuncArg("字典类型", FuncArgType.String, true, null),
				new FuncArg("字典数据值", FuncArgType.String, false, null));
	}
}
