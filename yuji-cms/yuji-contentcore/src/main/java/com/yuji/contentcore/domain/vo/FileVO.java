package com.yuji.contentcore.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileVO {
	
	private String filePath;

	private String fileName;
	
	private Boolean isDirectory;
	
	private Boolean canEdit = false;
	
	private Long fileSize;
	
	private LocalDateTime modifyTime;
}
