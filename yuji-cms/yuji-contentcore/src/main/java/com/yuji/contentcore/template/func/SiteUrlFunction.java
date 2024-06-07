package com.yuji.contentcore.template.func;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.func.AbstractFunc;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.utils.SiteUtils;
import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Freemarker模板自定义函数：处理发布通道站点URL
 */
@Component
@RequiredArgsConstructor
public class SiteUrlFunction extends AbstractFunc {

	private static final String FUNC_NAME = "siteUrl";
	
	private static final String DESC = "{FREEMARKER.FUNC.DESC." + FUNC_NAME + "}";

	private final ICmsSiteService cmsSiteService;

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
		if (args.length != 2) {
			return StringUtils.EMPTY;
		}
		long siteId = ((SimpleNumber) args[0]).getAsNumber().longValue();
		String publishPipeCode = ((SimpleScalar) args[1]).getAsString();
		TemplateContext context = FreeMarkerUtils.getTemplateContext(Environment.getCurrentEnvironment());
		return SiteUtils.getSiteLink(cmsSiteService.selectCmsSiteBySiteId(siteId), publishPipeCode, context.isPreview());
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of(new FuncArg("站点ID", FuncArgType.Long, true, null),
				new FuncArg("发布通道编码", FuncArgType.String, true, null));
	}
}
