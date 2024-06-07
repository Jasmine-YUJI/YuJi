package com.yuji.contentcore.template.func;

import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.func.AbstractFunc;
import com.yuji.contentcore.core.IDynamicPageType;
import com.yuji.contentcore.exception.ContentCoreErrorCode;
import com.yuji.contentcore.utils.TemplateUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Freemarker模板自定义函数：处理内部链接
 */
@Component
@RequiredArgsConstructor
public class DynamicPageLinkFunction extends AbstractFunc {

	static final String FUNC_NAME = "dynamicPageLink";

	private static final String DESC = "{FREEMARKER.FUNC.DESC." + FUNC_NAME + "}";

	private final Map<String, IDynamicPageType> dynamicPageTypeMap;

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
		boolean ignoreBaseArg = true;
		if (args.length == 2) {
			ignoreBaseArg = ((TemplateBooleanModel) args[1]).getAsBoolean();
		}
		TemplateContext context = FreeMarkerUtils.getTemplateContext(Environment.getCurrentEnvironment());
		IDynamicPageType dynamicPageType = this.dynamicPageTypeMap.get(IDynamicPageType.BEAN_PREFIX + args[0].toString());
		Assert.notNull(dynamicPageType, () -> ContentCoreErrorCode.UNSUPPORTED_DYNAMIC_PAGE_TYPE.exception(args[0].toString()));

		String path = dynamicPageType.getRequestPath();
		if (context.isPreview()) {
			path += "?preview=true";
			path = TemplateUtils.appendTokenParameter(path, Environment.getCurrentEnvironment());
		}
		if (!ignoreBaseArg) {
			path += (path.contains("?") ? "&" : "?") + "sid=" + FreeMarkerUtils.evalLongVariable(Environment.getCurrentEnvironment(), "Site.siteId")
					+ "&pp=" + context.getPublishPipeCode();
		}
		return path;
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of(
				new FuncArg("动态页面类型", FuncArgType.String, true, null),
				new FuncArg("忽略sid/pp参数", FuncArgType.String, false, "默认：true")
		);
	}
}
