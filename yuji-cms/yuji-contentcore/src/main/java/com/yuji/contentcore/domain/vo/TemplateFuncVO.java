package com.yuji.contentcore.domain.vo;

import com.yuji.common.staticize.func.IFunction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TemplateFuncVO {
	
	private String funcName;

	private List<String> aliases;
	
	private String desc;
	
	private List<IFunction.FuncArg> funcArgs;
}
