package com.yuji.contentcore.controller;

import com.yuji.common.core.utils.i18n.I18nUtils;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.core.domain.R;
import com.yuji.common.staticize.func.IFunction;
import com.yuji.common.staticize.tag.ITag;
import com.yuji.contentcore.domain.vo.TemplateFuncVO;
import com.yuji.contentcore.domain.vo.TemplateTagVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 静态化管理
 * 
 * @author Liguoqiang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/staticize")
public class StaticizeController extends BaseController {

	private final List<ITag> tags;

	private final List<IFunction> functions;

	@GetMapping("/aa")
	public R<?> aa() {

		return R.ok(I18nUtils.get("{FREEMARKER.TAG.DESC.cms_catalog}"));
	}


	/**
	 * 获取静态化自定义模板标签列表
	 */
	@GetMapping("/tags")
	public R<?> getTemplateTags() {
		List<TemplateTagVO> list = this.tags.stream().map(tag -> {
			TemplateTagVO vo = TemplateTagVO.builder().name(I18nUtils.get(tag.getName())).tagName(tag.getTagName())
					.description(I18nUtils.get(tag.getDescription())).tagAttrs(tag.getTagAttrs()).build();
			vo.getTagAttrs().forEach(attr -> {
				attr.setName(I18nUtils.get(attr.getName()));
				attr.setUsage(I18nUtils.get(attr.getUsage()));
			});
			return vo;
		}).toList();
		return R.ok(list);
	}

	/**
	 * 获取静态化自定义模板函数列表
	 */
	@GetMapping("/functions")
	public R<?> getTemplateFunctions() {
		List<TemplateFuncVO> list = this.functions.stream().map(func -> {
			TemplateFuncVO vo = TemplateFuncVO.builder().funcName(func.getFuncName())
					.desc(I18nUtils.get(func.getDesc())).funcArgs(func.getFuncArgs()).build();
			vo.getFuncArgs().forEach(arg -> {
				arg.setName(I18nUtils.get(arg.getName()));
				arg.setDesc(I18nUtils.get(arg.getDesc()));
			});
			vo.setAliases(func.getAliases());
			return vo;
		}).toList();
		return R.ok(list);
	}
}
