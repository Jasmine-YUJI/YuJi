package com.yuji.contentcore.domain.vo;

import com.yuji.common.staticize.tag.TagAttr;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TemplateTagVO {
	
	private String name;
	
	private String tagName;
	
	private String description;
	
	private List<TagAttr> tagAttrs;
}
