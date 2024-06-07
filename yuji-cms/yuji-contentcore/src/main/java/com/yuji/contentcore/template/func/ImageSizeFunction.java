package com.yuji.contentcore.template.func;

import com.yuji.common.core.enums.FileType;
import com.yuji.common.core.utils.ObjectUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.common.staticize.func.AbstractFunc;
import com.yuji.contentcore.core.InternalURL;
import com.yuji.contentcore.core.impl.InternalDataType_Resource;
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.service.ICmsResourceService;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.utils.InternalUrlUtils;
import com.yuji.contentcore.utils.SiteUtils;
import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Freemarker模板自定义函数：生成图片缩略图
 */
@Component
@RequiredArgsConstructor
public class ImageSizeFunction extends AbstractFunc {

	private static final String FUNC_NAME = "imageSize";

	private static final String DESC = "{FREEMARKER.FUNC.DESC." + FUNC_NAME + "}";

	private final ICmsSiteService cmsSiteService;

	private final ICmsResourceService cmsResourceService;

	@Override
	public String getFuncName() {
		return FUNC_NAME;
	}

	@Override
	public String getDesc() {
		return DESC;
	}

	@Override
	public Object exec0(Object... args) throws TemplateModelException {
		if (args.length < 3 || ObjectUtils.isAnyNull(args)) {
			return StringUtils.EMPTY;
		}
		String iurl = ((SimpleScalar) args[0]).getAsString();
		int width = ((SimpleNumber) args[1]).getAsNumber().intValue();
		int height = ((SimpleNumber) args[2]).getAsNumber().intValue();
		if (width <= 0 || width >= 6144 || height <= 0 || height >= 6144) {
			throw new TemplateModelException("Function[imageSize]: make sure the width/height is between 0 - 6144.");
		}
		if (!InternalUrlUtils.isInternalUrl(iurl)) {
			return iurl; // 非内部链接直接返回
		}
		boolean crop = false; // 是否在缩放后进行居中裁剪，默认：false
		if (args.length == 4) {
			crop = ((TemplateBooleanModel) args[3]).getAsBoolean();
		}
		TemplateContext context = FreeMarkerUtils.getTemplateContext(Environment.getCurrentEnvironment());
		InternalURL internalUrl = InternalUrlUtils.parseInternalUrl(iurl);
		if (Objects.isNull(internalUrl) || !InternalDataType_Resource.ID.equals(internalUrl.getType())) {
			return iurl;
		}
		String actualUrl = InternalUrlUtils.getActualUrl(internalUrl, context.getPublishPipeCode(),
				context.isPreview());
		String siteResourceRoot = SiteUtils.getSiteResourceRoot(cmsSiteService.selectCmsSiteBySiteId(Long.valueOf(internalUrl.getParams().get("sid"))));
		String destPath = StringUtils.substringBeforeLast(internalUrl.getPath(), ".") + "_" + width + "x" + height
				+ "." + StringUtils.substringAfterLast(internalUrl.getPath(), ".");
		if (Files.exists(Path.of(siteResourceRoot + destPath))) {
			return StringUtils.substringBeforeLast(actualUrl, ".") + "_" + width + "x" + height + "."
					+ StringUtils.substringAfterLast(actualUrl, ".");
		}
		try {
			CmsResource resource = cmsResourceService.selectCmsResourceByResourceId(internalUrl.getId());
			if (Objects.nonNull(resource) && FileType.LOCAL.getCode().equals(resource.getStorageType())) {
				Thumbnails.Builder<File> builder = Thumbnails.of(siteResourceRoot + resource.getPath())
						.size(width, height);
				if (crop) {
					builder.crop(Positions.CENTER);
				}
				builder.toFile(siteResourceRoot + destPath);
				actualUrl = StringUtils.substringBeforeLast(actualUrl, ".") + "_" + width + "x" + height + "."
						+ StringUtils.substringAfterLast(actualUrl, ".");
			}
		} catch (IOException e) {
			throw new TemplateModelException("Generate thumbnail failed!", e);
		}
		return actualUrl;
	}

	@Override
	public List<FuncArg> getFuncArgs() {
		return List.of(
				new FuncArg("图片资源内部路径", FuncArgType.String, true, "仅支持处理内部资源图片(iurl://)"),
				new FuncArg("宽度", FuncArgType.Int, true, null),
				new FuncArg("高度", FuncArgType.Int, true, null),
				new FuncArg("是否居中裁剪", FuncArgType.Boolean, false, null)
		);
	}
}
