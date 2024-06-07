package com.yuji.contentcore.template.func;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.func.AbstractFunc;
import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Freemarker模板自定义函数：获取内容分页链接
 */
@Component
@RequiredArgsConstructor
public class ContentPageLinkFunction extends AbstractFunc {

	private static final String FUNC_NAME = "contentPageLink";
	
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
		if (args.length == 0) {
			return StringUtils.EMPTY;
		}
		String link = args[0].toString();
		if (args.length == 1) {
			return link;
		}
		int pageNumber = ((SimpleNumber) args[1]).getAsNumber().intValue();
		if(pageNumber <= 1) {
			return link;
		}
		TemplateContext context = FreeMarkerUtils.getTemplateContext(Environment.getCurrentEnvironment());
		if (context.isPreview()) {
			link += "&pi=" + pageNumber;
		} else {
			int dotIndex = link.lastIndexOf(StringUtils.DOT);
			link = link.substring(0, dotIndex) + "_" + pageNumber + link.substring(dotIndex);
		}
		return link;
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of(new FuncArg("内容链接", FuncArgType.String, true, null),
				new FuncArg("页码", FuncArgType.Int, true, null));
	}
}
