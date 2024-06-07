package com.yuji.common.staticize.func;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public interface IFunction {
	
	/**
	 * 函数使用名<br/>
	 * 例如：${replace(txt, 'findStr', 'repStr')}
	 * 
	 * @return
	 */
	String getFuncName();
	
	/**
	 * 描述
	 * 
	 * @return
	 */
	String getDesc();
	
	/**
	 * 获取函数参数定义列表
	 * 
	 * @return
	 */
	List<FuncArg> getFuncArgs();

	/**
	 * 函数别名
	 */
	default List<String> getAliases() {
		return List.of();
	}

	/**
	 * 模板函数参数
	 */
	@Getter
	@Setter
	class FuncArg {
		
		private String name;
		
		private FuncArgType type;
		
		private boolean required;
		
		private String desc;
		
		public FuncArg(String name, FuncArgType type, boolean required, String desc) {
			this.name = name;
			this.type = type;
			this.required = required;
			this.desc = desc;
		}
	}
	
	enum FuncArgType {
		String, Int, Long, Float, Double, DateTime, Boolean
	}
}
