package com.yuji.contentcore.fixed.dict;

import com.yuji.common.core.fixed.FixedDictType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 启用/禁用
 */
@Component(FixedDictType.BEAN_PREFIX + ContentStatus.TYPE)
public class ContentStatus extends FixedDictType {

	public static final String TYPE = "CMSContentStatus";

	public static final String DRAFT = "0"; // 初稿

	public static final String TO_PUBLISHED = "20"; // 待发布

	public static final String PUBLISHED = "30"; // 已发布

	public static final String OFFLINE = "40"; // 已下线

	public static final String EDITING = "60"; // 重新编辑



	public static boolean isDraft(String status) {
		return DRAFT.equals(status);
	}

	public static boolean isPublished(String v) {
		return PUBLISHED.equals(v);
	}

	public static boolean isToPublish(String v) {
		return TO_PUBLISHED.equals(v);
	}

	public static boolean isToPublishOrPublished(String v) {
		return PUBLISHED.equals(v) || TO_PUBLISHED.equals(v);
	}

	public static boolean isOffline(String v) {
		return OFFLINE.equals(v);
	}


}
