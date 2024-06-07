package com.yuji.contentcore.template.func;

import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.func.AbstractFunc;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.service.ICmsCatalogService;
import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Freemarker模板自定义函数：处理发布通道栏目URL
 */
@Component
@RequiredArgsConstructor
public class CatalogUrlFunction extends AbstractFunc {

	private static final String FUNC_NAME = "catalogUrl";
	
	private static final String DESC = "{FREEMARKER.FUNC.DESC." + FUNC_NAME + "}";

	private final ICmsCatalogService cmsCatalogService;

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
		long catalogId = ((SimpleNumber) args[0]).getAsNumber().longValue();
		CmsCatalog catalog = cmsCatalogService.selectCmsCatalogByCatalogId(catalogId);
		if (Objects.isNull(catalog)) {
			return StringUtils.EMPTY;
		}
		String publishPipeCode = ((SimpleScalar) args[1]).getAsString();
		TemplateContext context = FreeMarkerUtils.getTemplateContext(Environment.getCurrentEnvironment());
		return cmsCatalogService.getCatalogLink(catalog, 1, publishPipeCode, context.isPreview());
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of(new FuncArg("栏目ID", FuncArgType.Long, true, null),
				new FuncArg("发布通道编码", FuncArgType.String, true, null));
	}
}
