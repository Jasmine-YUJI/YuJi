package com.yuji.contentcore.core.impl;

import com.yuji.common.core.enums.WatermarkerPosition;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.common.core.utils.file.ImageUtils;
import com.yuji.contentcore.core.IResourceType;
import com.yuji.contentcore.domain.CmsResource;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.properties.ImageWatermarkArgsProperty;
import com.yuji.contentcore.properties.ImageWatermarkArgsProperty.ImageWatermarkArgs;
import com.yuji.contentcore.properties.ImageWatermarkProperty;
import com.yuji.contentcore.properties.ThumbnailHeightProperty;
import com.yuji.contentcore.properties.ThumbnailWidthProperty;
import com.yuji.contentcore.service.ICmsSiteService;
import com.yuji.contentcore.utils.SiteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 资源类型：图片
 *
 * @author Liguoqiang
 */
@Slf4j
@RequiredArgsConstructor
@Component(IResourceType.BEAN_NAME_PREFIX + ResourceType_Image.ID)
public class ResourceType_Image implements IResourceType {

	public final static String ID = "image";
	
	public static final  String NAME = "{CMS.CONTENTCORE.RESOURCE_TYPE." + ID + "}";

	public final static String[] SuffixArray = { "jpg", "jpeg", "gif", "png", "ico", "webp" };

	private final ICmsSiteService siteService;

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
	
	public static boolean isImage(String path) {
		String ext = FileTypeUtils.getExtension(path);
		return Objects.nonNull(path) && ArrayUtils.contains(SuffixArray, ext);
	}

	@Override
	public byte[] process(CmsResource resource, byte[] bytes) throws IOException {
		CmsSite site = siteService.selectCmsSiteBySiteId(resource.getSiteId());
		// 提取图片宽高属性
		try (ByteArrayInputStream is = new ByteArrayInputStream(bytes)) {
			BufferedImage bi = ImageIO.read(is);
			resource.setWidth(bi.getWidth());
			resource.setHeight(bi.getHeight());
			// 默认缩略图处理
			int w = ThumbnailWidthProperty.getValue(site.getConfigProps());
			int h = ThumbnailHeightProperty.getValue(site.getConfigProps());
			if (w > 0 && h > 0) {
				String siteResourceRoot = SiteUtils.getSiteResourceRoot(site);
				Thumbnails.of(bi).size(w, h).toFile(siteResourceRoot + ImageUtils.getThumbnailFileName(resource.getPath(), w, h));
			}
			// 添加水印
			if (ImageWatermarkProperty.getValue(site.getConfigProps())
					&& !"webp".equalsIgnoreCase(resource.getSuffix())) {
				// TODO webp水印支持
				ImageWatermarkArgs args = ImageWatermarkArgsProperty.getValue(site.getConfigProps());
				if (StringUtils.isNotEmpty(args.getImage())) {
					// 水印图片占比大小调整
					String siteResourceRoot = SiteUtils.getSiteResourceRoot(site);
					File file = new File(siteResourceRoot + args.getImage());
					if (file.exists()) {
						float waterremakImageWidth = bi.getWidth() * args.getRatio() * 0.01f;
						BufferedImage biWatermarkImage = ImageIO.read(file);
						biWatermarkImage = Thumbnails.of(biWatermarkImage)
								.scale(waterremakImageWidth / biWatermarkImage.getWidth()).asBufferedImage();
						try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
							// 添加水印
							Thumbnails.of(bi)
									.watermark(WatermarkerPosition.valueOf(args.getPosition()).position(),
											biWatermarkImage, args.getOpacity())
									.scale(1f).outputFormat(resource.getSuffix()).toOutputStream(os);
							bytes = os.toByteArray();
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("图片处理失败：", e);
			resource.setWidth(0);
			resource.setHeight(0);
		}
		resource.setFileSize((long) bytes.length);
		return bytes;
	}
}
