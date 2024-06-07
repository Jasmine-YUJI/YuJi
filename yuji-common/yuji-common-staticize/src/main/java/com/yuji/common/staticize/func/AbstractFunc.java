package com.yuji.common.staticize.func;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public abstract class AbstractFunc implements IFunction, TemplateMethodModelEx {
	
	@Override
    public Object exec(List args) throws TemplateModelException {
		return exec0(args.toArray());
	}
	
	public abstract Object exec0(Object... args) throws TemplateModelException;
}
