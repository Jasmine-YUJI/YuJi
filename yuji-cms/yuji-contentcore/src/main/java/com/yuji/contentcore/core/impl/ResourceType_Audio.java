package com.yuji.contentcore.core.impl;

import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.contentcore.core.IResourceType;
import com.yuji.contentcore.domain.CmsResource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * 资源类型：音频
 *
 * @author Liguoqiang
 */
@RequiredArgsConstructor
@Component(IResourceType.BEAN_NAME_PREFIX + ResourceType_Audio.ID)
public class ResourceType_Audio implements IResourceType {

	public final static String ID = "audio";
	
	public static final  String NAME = "{CMS.CONTENTCORE.RESOURCE_TYPE." + ID + "}";

	public final static String[] SuffixArray = { "mp3", "wav", "wma", "ogg", "aiff", "aac", "flac", "mid" };

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
	
	public static boolean isAudio(String path) {
		String ext = FileTypeUtils.getExtension(path);
		return Objects.nonNull(path) && ArrayUtils.contains(SuffixArray, ext);
	}
	
	@Override
	public byte[] process(CmsResource resource, byte[] bytes) throws IOException {
		return IResourceType.super.process(resource, bytes);
	}
}
