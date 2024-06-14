package com.yuji.contentcore.core.impl;

import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.contentcore.core.IResourceType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 资源类型：视频
 *
 * @author Liguoqiang
 */
@RequiredArgsConstructor
@Component(IResourceType.BEAN_NAME_PREFIX + ResourceType_Video.ID)
public class ResourceType_Video implements IResourceType {

	public final static String ID = "video";
	
	public static final  String NAME = "{CMS.CONTENTCORE.RESOURCE_TYPE." + ID + "}";

	public final static String[] SuffixArray = { "mp4", "mpg", "mpeg", "rmvb", "rm", "avi", "wmv", "mov", "flv" };

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String[] getUsableSuffix() {
		return SuffixArray;
	}

	public static boolean isVideo(String path) {
		String ext = FileTypeUtils.getExtension(path);
		return Objects.nonNull(path) && ArrayUtils.contains(SuffixArray, ext);
	}
}
