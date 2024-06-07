package com.yuji.contentcore.template.func;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.func.AbstractFunc;
import com.yuji.contentcore.utils.InternalUrlUtils;
import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Freemarker模板自定义函数：处理Html文本内容中的内部链接
 */
@Component
@RequiredArgsConstructor
public class HtmlInternalUrlFunction extends AbstractFunc {

	static final String FUNC_NAME = "htmlInternalUrl";
	
	private static final String DESC = "{FREEMARKER.FUNC.DESC." + FUNC_NAME + "}";

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
		if (args.length < 1) {
			return StringUtils.EMPTY;
		}
		TemplateContext context = FreeMarkerUtils.getTemplateContext(Environment.getCurrentEnvironment());
		SimpleScalar simpleScalar = (SimpleScalar) args[0];
		return InternalUrlUtils.dealInternalUrl(simpleScalar.getAsString(), context.getPublishPipeCode(), context.isPreview());
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of(new FuncArg("HTML文本内容", FuncArgType.String, true, null));
	}
}
