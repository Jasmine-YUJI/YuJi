package com.yuji.contentcore.fixed.dict;

import com.yuji.common.core.fixed.FixedDictType;
import com.yuji.common.core.utils.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 启用/禁用
 */
@Component(FixedDictType.BEAN_PREFIX + ContentAttribute.TYPE)
public class ContentAttribute extends FixedDictType {

	public static final String TYPE = "CMSContentAttribute";

	public static final String IMAGE = "image"; // 图片

	public static final String VIDEO = "video"; // 视频

	public static final String ATTACH = "attach"; // 附件

	public static final String HOT = "hot"; // 热点

	public static final String RECOMMEND = "recommend"; // 推荐


	/**
	 * 附加指定属性
	 * 
	 * @param sourceAttr
	 * @param appcenAttr
	 * @return
	 */
	public static int append(int sourceAttr, int appcenAttr) {
		if (sourceAttr == 0) {
			return appcenAttr;
		} else {
			if ((sourceAttr & appcenAttr) == appcenAttr) {
				return sourceAttr; // 已经包含
			}
			return (sourceAttr & ~appcenAttr) + appcenAttr;
		}
	}

	/**
	 * 移除指定属性
	 * 
	 * @param sourceAttr
	 * @param removeAttr
	 * @return
	 */
	public static int remove(int sourceAttr, int removeAttr) {
		if (sourceAttr != 0) {
			if ((sourceAttr & removeAttr) == 0) {
				return sourceAttr; // 并不包含
			}
			return sourceAttr & ~removeAttr;
		}
		return sourceAttr;
	}

	/**
	 * 字符串数组转数字
	 *
	 * @param attributes
	 * @return
	 */
	public static int convertInt(String... attributes) {
		if (attributes == null || attributes.length == 0) {
			return 0;
		}
		int v = 0;
		for (String attr : attributes) {
			if (NumberUtils.isDigits(attr)) {
				v += Integer.parseInt(attr);
			}
		}
		return v;
	}

	/**
	 * 转属性数组
	 *
	 * @param attributes
	 * @return
	 */
	public static String[] convertStr(Integer attributes) {
		if (attributes == null || attributes == 0) {
			return ArrayUtils.EMPTY_STRING_ARRAY;
		}
		ArrayList<Integer> binaryList = NumberUtils.getBinaryList(attributes);
		return binaryList.stream().toArray(String[]::new);
	}


}
