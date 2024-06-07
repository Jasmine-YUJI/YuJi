package com.yuji.contentcore.template.tag;

import com.github.pagehelper.Page;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.enums.TagAttrDataType;
import com.yuji.common.staticize.tag.AbstractListTag;
import com.yuji.common.staticize.tag.TagAttr;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.domain.CmsContentRela;
import com.yuji.contentcore.domain.dto.ContentDTO;
import com.yuji.contentcore.mapper.CmsContentRelaMapper;
import com.yuji.contentcore.service.ICmsContentService;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CmsContentRelaTag extends AbstractListTag {

	public final static String TAG_NAME = "cms_content_rela";
	public final static String NAME = "{FREEMARKER.TAG.NAME." + TAG_NAME + "}";
	public final static String DESC = "{FREEMARKER.TAG.DESC." + TAG_NAME + "}";

	public final static String TagAttr_ContentId = "contentid";

	private final CmsContentRelaMapper cmsContentRelaMapper;

	private final ICmsContentService cmsContentService;

	@Override
	public List<TagAttr> getTagAttrs() {
		List<TagAttr> tagAttrs = super.getTagAttrs();
		tagAttrs.add(new TagAttr(TagAttr_ContentId, true, TagAttrDataType.INTEGER, "内容ID"));
		return tagAttrs;
	}

	@Override
	public TagPageData prepareData(Environment env, Map<String, String> attrs, boolean page, int size, int pageIndex)
			throws TemplateException {
		long contentId = MapUtils.getLongValue(attrs, TagAttr_ContentId);
		if (contentId <= 0) {
			throw new TemplateException("内容ID错误：" + contentId, env);
		}
		TemplateContext context = FreeMarkerUtils.getTemplateContext(env);
		CmsContentRela cmsContentRela = new CmsContentRela();
		cmsContentRela.setContentId(contentId);
		List<CmsContentRela> cmsContentRelaList = cmsContentRelaMapper.selectCmsContentRelaList(cmsContentRela);
		if (!cmsContentRelaList.isEmpty()) {
			Long[] contentIds = (Long[]) cmsContentRelaList.stream().map(CmsContentRela::getRelaContentId).toArray();
			List<CmsContent> contents = cmsContentService.selectCmsContentByContentIdInList(contentIds);
			List<ContentDTO> result = contents.stream().map(c -> {
				ContentDTO dto = ContentDTO.newInstance(c);
				dto.setLink(cmsContentService.getContentLink(c, 1,
						context.getPublishPipeCode(), context.isPreview()));
				return dto;
			}).toList();
			return TagPageData.of(result, page ? cmsContentRelaList.size() : result.size());
		} else {
			return TagPageData.of(List.of(), 0);
		}
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
}
