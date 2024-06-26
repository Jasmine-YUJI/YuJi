package com.yuji.contentcore.template.tag;

import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.enums.TagAttrDataType;
import com.yuji.common.staticize.tag.AbstractListTag;
import com.yuji.common.staticize.tag.TagAttr;
import com.yuji.common.staticize.tag.TagAttrOption;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.template.exception.SiteNotFoundException;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CmsSiteTag extends AbstractListTag {

	public final static String TAG_NAME = "cms_site";
	public final static String NAME = "{FREEMARKER.TAG.NAME." + TAG_NAME + "}";
	public final static String DESC = "{FREEMARKER.TAG.DESC." + TAG_NAME + "}";

	public final static String TAG_ATTR_ID = "id";

	public final static String TAG_ATTR_LEVEL = "level";

	private final ICmsSiteService cmsSiteService;

	@Override
	public List<TagAttr> getTagAttrs() {
		List<TagAttr> tagAttrs = super.getTagAttrs();
		tagAttrs.add(new TagAttr(TAG_ATTR_ID, false, TagAttrDataType.INTEGER, "站点ID"));
		tagAttrs.add(new TagAttr(TAG_ATTR_LEVEL, false, TagAttrDataType.STRING, "数据获取范围，值为`Root`时忽略属性id",
				SiteTagLevel.toTagAttrOptions(), "Current"));
		return tagAttrs;
	}

	@Override
	public TagPageData prepareData(Environment env, Map<String, String> attrs, boolean page, int size, int pageIndex)
			throws TemplateException {
		Long siteId = MapUtils.getLong(attrs, TAG_ATTR_ID);
		String level = MapUtils.getString(attrs, TAG_ATTR_LEVEL);

		CmsSite site = cmsSiteService.selectCmsSiteBySiteId(siteId);
		if (!SiteTagLevel.isRoot(level) && Objects.isNull(site)) {
			throw new SiteNotFoundException(getTagName(), siteId, env);
		}

		/*LambdaQueryWrapper<CmsSite> q = new LambdaQueryWrapper<>();
		if (SiteTagLevel.isCurrent(level)) {
			q.eq(CmsSite::getSiteId, site.getSiteId());
		} else if (SiteTagLevel.isChild(level)) {
			q.eq(CmsSite::getParentId, site.getSiteId());
		}
		String condition = MapUtils.getString(attrs, TagAttr.AttrName_Condition);
		q.apply(StringUtils.isNotEmpty(condition), condition);
		q.orderByAsc(CmsSite::getSortFlag);

		TemplateContext context = FreeMarkerUtils.getTemplateContext(env);
		Page<CmsSite> pageResult = this.siteService.page(new Page<>(pageIndex, size, page), q);
		pageResult.getRecords().forEach(c -> {
			c.setLink(SiteUtils.getSiteLink(c, context.getPublishPipeCode(), context.isPreview()));
		});
		return TagPageData.of(pageResult.getRecords(), pageResult.getTotal());*/
		return null;
	}

	@Override
	public String getTagName() {
		return TAG_NAME;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESC;
	}

	private enum SiteTagLevel {
		// 所有站点
		Root("所有站点"),
		// 当前站点
		Current("当前站点"),
		// 子站点
		Child("子站点");

		private final String desc;

		SiteTagLevel(String desc) {
			this.desc = desc;
		}

		static boolean isRoot(String level) {
			return Root.name().equalsIgnoreCase(level);
		}

		static boolean isCurrent(String level) {
			return Current.name().equalsIgnoreCase(level);
		}

		static boolean isChild(String level) {
			return Child.name().equalsIgnoreCase(level);
		}

		static List<TagAttrOption> toTagAttrOptions() {
			return List.of(
					new TagAttrOption(Root.name(), Root.desc),
					new TagAttrOption(Current.name(), Current.desc),
					new TagAttrOption(Child.name(), Child.desc)
			);
		}
	}
}
