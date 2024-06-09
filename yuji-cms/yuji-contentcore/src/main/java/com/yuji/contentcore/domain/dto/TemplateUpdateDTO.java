package com.yuji.contentcore.domain.dto;


import com.yuji.common.core.domain.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TemplateUpdateDTO extends BaseDTO {

    @NotNull
    @Min(1)
    private Long templateId;

    @NotEmpty
    private String content;
    
    private String remark;
}
