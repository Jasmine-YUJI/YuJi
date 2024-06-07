package com.yuji.contentcore.template.impl;

import com.yuji.contentcore.template.ITemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component(ITemplateType.BEAN_NAME_PREFIX + SiteTemplateType.TypeId)
public class SiteTemplateType implements ITemplateType {
	
	public final static String TypeId = "SiteIndex";

	@Override
	public String getId() {
		return TypeId;
	}
}
