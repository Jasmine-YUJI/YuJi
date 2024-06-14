package com.yuji.contentcore.domain.dto;

import com.yuji.common.core.domain.BaseDTO;
import com.yuji.contentcore.domain.CmsSite;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class ResourceUploadDTO extends BaseDTO {

	private CmsSite site;

	private MultipartFile file;

	private Long resourceId;
	
	private String name;
	
	private String remark;
	
}
