package com.yuji.contentcore.service.impl;

import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.StaticizeService;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.contentcore.core.IContentType;
import com.yuji.contentcore.core.IPublishPipeProp;
import com.yuji.contentcore.core.impl.CatalogType_Link;
import com.yuji.contentcore.core.impl.PublishPipeProp_ContentTemplate;
import com.yuji.contentcore.core.impl.PublishPipeProp_DefaultListTemplate;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.exception.ContentCoreErrorCode;
import com.yuji.contentcore.mapper.CmsSiteMapper;
import com.yuji.contentcore.service.*;
import com.yuji.contentcore.template.ITemplateType;
import com.yuji.contentcore.template.impl.CatalogTemplateType;
import com.yuji.contentcore.template.impl.ContentTemplateType;
import com.yuji.contentcore.template.impl.SiteTemplateType;
import com.yuji.contentcore.utils.ContentCoreUtils;
import com.yuji.contentcore.utils.SiteUtils;
import com.yuji.contentcore.utils.TemplateUtils;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PublishServiceImpl implements IPublishService {

	private static final Logger logger = LoggerFactory.getLogger(PublishServiceImpl.class);

	@Autowired
	private CmsSiteMapper cmsSiteMapper;

	@Autowired
	private ICmsTemplateService cmsTemplateService;

	@Autowired
	private ICmsCatalogService cmsCatalogService;

	@Autowired
	private ICmsContentService cmsContentService;

	@Autowired
	private ICmsPublishpipeService cmsPublishpipeService;
	@Autowired
	private final StaticizeService staticizeService;



	/**
	 * 站点首页页面内容
	 *
	 * @param site
	 * @param publishPipeCode
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	@Override
	public String getSitePageData(CmsSite site, String publishPipeCode, boolean isPreview)
			throws IOException, TemplateException {
		String indexTemplate = site.getIndexTemplate(publishPipeCode);
		File templateFile = cmsTemplateService.findTemplateFile(site, indexTemplate, publishPipeCode);
		if (Objects.isNull(templateFile)) {
			throw ContentCoreErrorCode.TEMPLATE_EMPTY.exception(publishPipeCode, indexTemplate);
		}
		// 模板ID = 通道:站点目录:模板文件名
		String templateKey = SiteUtils.getTemplateKey(site, publishPipeCode, indexTemplate);
		TemplateContext context = new TemplateContext(templateKey, isPreview, publishPipeCode);
		// init template datamode
		TemplateUtils.initGlobalVariables(site, context);
		// init templateType data to datamode
		ITemplateType templateType = cmsTemplateService.getTemplateType(SiteTemplateType.TypeId);
		templateType.initTemplateData(site.getSiteId(), context);

		long s = System.currentTimeMillis();
		try (StringWriter writer = new StringWriter()) {
			this.staticizeService.process(context, writer);
			return writer.toString();
		} finally {
			logger.debug("[{}]首页模板解析：{}\t耗时：{}ms", publishPipeCode, site.getName(), System.currentTimeMillis() - s);
		}
	}

	/**
	 * 获取栏目模板页面内容
	 *
	 * @param catalog
	 * @param listFlag
	 * @param publishPipeCode
	 * @param isPreview
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	@Override
	public String getCatalogPageData(CmsCatalog catalog, int pageIndex, boolean listFlag, String publishPipeCode, boolean isPreview)
			throws IOException, TemplateException {
		if (CatalogType_Link.ID.equals(catalog.getCatalogType())) {
			throw new RuntimeException("链接类型栏目无独立页面：" + catalog.getName());
		}
		String templateFilename = catalog.getListTemplate(publishPipeCode);
		if (!listFlag && pageIndex == 1) {
			// 获取首页模板
			String indexTemplate = catalog.getIndexTemplate(publishPipeCode);
			if (StringUtils.isNotEmpty(indexTemplate)) {
				templateFilename = indexTemplate;
			} else {
				listFlag = true;
			}
		}
		CmsSite site = this.cmsSiteMapper.selectCmsSiteBySiteId(catalog.getSiteId());
		if (StringUtils.isEmpty(templateFilename)) {
			// 站点默认模板
			templateFilename = PublishPipeProp_DefaultListTemplate.getValue(publishPipeCode, site.getPublishPipeProps());
		}
		final String template = templateFilename;
		File templateFile = cmsTemplateService.findTemplateFile(site, template, publishPipeCode);
		Assert.notNull(templateFile, () -> ContentCoreErrorCode.TEMPLATE_EMPTY.exception(publishPipeCode, template));

		long s = System.currentTimeMillis();
		// 生成静态页面
		String templateKey = SiteUtils.getTemplateKey(site, publishPipeCode, template);
		TemplateContext templateContext = new TemplateContext(templateKey, isPreview, publishPipeCode);
		templateContext.setPageIndex(pageIndex);
		// init template variables
		TemplateUtils.initGlobalVariables(site, templateContext);
		// init templateType variables
		ITemplateType templateType = cmsTemplateService.getTemplateType(CatalogTemplateType.TypeId);
		templateType.initTemplateData(catalog.getCatalogId(), templateContext);
		// 分页链接
		if (listFlag) {
			String catalogLink = cmsCatalogService.getCatalogListLink(catalog, 1, publishPipeCode, isPreview);
			templateContext.setFirstFileName(catalogLink);
			templateContext.setOtherFileName(catalogLink + "&pi=" + TemplateContext.PlaceHolder_PageNo);
		}
		try (StringWriter writer = new StringWriter()) {
			this.staticizeService.process(templateContext, writer);
			return writer.toString();
		} finally {
			logger.debug("[{}]栏目页模板解析：{}，耗时：{}ms", publishPipeCode, catalog.getName(),
					(System.currentTimeMillis() - s));
		}
	}

	/**
	 * 获取内容模板页面结果
	 *
	 * @param content
	 * @param pageIndex
	 * @param publishPipeCode
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	@Override
	public String getContentPageData(CmsContent content, int pageIndex, String publishPipeCode, boolean isPreview)
			throws IOException, TemplateException {
		CmsSite site = cmsSiteMapper.selectCmsSiteBySiteId(content.getSiteId());
		CmsCatalog catalog = cmsCatalogService.selectCmsCatalogByCatalogId(content.getCatalogId());
		if (content.isLinkContent()) {
			throw new RuntimeException("标题内容：" + content.getTitle() + "，跳转链接：" + content.getRedirectUrl());
		}
		// 查找模板
		final String detailTemplate = getDetailTemplate(site, catalog, content, publishPipeCode);
		File templateFile = cmsTemplateService.findTemplateFile(site, detailTemplate, publishPipeCode);
		Assert.notNull(templateFile,
				() -> ContentCoreErrorCode.TEMPLATE_EMPTY.exception(publishPipeCode, detailTemplate));

		long s = System.currentTimeMillis();
		// 生成静态页面
		try (StringWriter writer = new StringWriter()) {
			IContentType contentType = ContentCoreUtils.getContentType(content.getContentType());
			// 模板ID = 通道:站点目录:模板文件名
			String templateKey = SiteUtils.getTemplateKey(site, publishPipeCode, detailTemplate);
			TemplateContext templateContext = new TemplateContext(templateKey, isPreview, publishPipeCode);
			templateContext.setPageIndex(pageIndex);
			// init template datamode
			TemplateUtils.initGlobalVariables(site, templateContext);
			// init templateType data to datamode
			ITemplateType templateType = cmsTemplateService.getTemplateType(ContentTemplateType.TypeId);
			templateType.initTemplateData(content.getContentId(), templateContext);
			// 分页链接
			String contentLink = cmsContentService.getContentLink(content, 1, publishPipeCode, isPreview);
			templateContext.setFirstFileName(contentLink);
			templateContext.setOtherFileName(contentLink + "&pi=" + TemplateContext.PlaceHolder_PageNo);
			// staticize
			this.staticizeService.process(templateContext, writer);
			logger.debug("[{}][{}]内容模板解析：{}，耗时：{}", publishPipeCode, contentType.getName(), content.getTitle(),
					System.currentTimeMillis() - s);
			return writer.toString();
		}
	}


	private String getDetailTemplate(CmsSite site, CmsCatalog catalog, CmsContent content, String publishPipeCode) {
		String detailTemplate = PublishPipeProp_ContentTemplate.getValue(publishPipeCode,
				content.getPublishPipeProps());
		if (StringUtils.isEmpty(detailTemplate)) {
			// 无内容独立模板取栏目配置
			detailTemplate = cmsPublishpipeService.getPublishPipePropValue(
					IPublishPipeProp.DetailTemplatePropPrefix + content.getContentType(), publishPipeCode,
					catalog.getPublishPipeProps());
			if (StringUtils.isEmpty(detailTemplate)) {
				// 无栏目配置去站点默认模板配置
				detailTemplate = cmsPublishpipeService.getPublishPipePropValue(
						IPublishPipeProp.DefaultDetailTemplatePropPrefix + content.getContentType(), publishPipeCode,
						site.getPublishPipeProps());
			}
		}
		return detailTemplate;
	}

	@Override
	public String getPageWidgetPageData(CmsPageWidget pageWidget, boolean isPreview)
			throws IOException, TemplateException {
		CmsSite site = cmsSiteMapper.selectCmsSiteBySiteId(pageWidget.getSiteId());
		File templateFile = cmsTemplateService.findTemplateFile(site, pageWidget.getTemplate(),
				pageWidget.getPublishPipeCode());
		Assert.notNull(templateFile,
				() -> ContentCoreErrorCode.TEMPLATE_EMPTY.exception(pageWidget.getName(), pageWidget.getCode()));

		// 生成静态页面
		try (StringWriter writer = new StringWriter()) {
			long s = System.currentTimeMillis();
			// 模板ID = 通道:站点目录:模板文件名
			String templateKey = SiteUtils.getTemplateKey(site, pageWidget.getPublishPipeCode(),
					pageWidget.getTemplate());
			TemplateContext templateContext = new TemplateContext(templateKey, isPreview,
					pageWidget.getPublishPipeCode());
			// init template global variables
			TemplateUtils.initGlobalVariables(site, templateContext);
			templateContext.getVariables().put(TemplateUtils.TemplateVariable_PageWidget, pageWidget);
			// init templateType data to datamode
			ITemplateType templateType = cmsTemplateService.getTemplateType(SiteTemplateType.TypeId);
			templateType.initTemplateData(site.getSiteId(), templateContext);
			// staticize
			this.staticizeService.process(templateContext, writer);
			logger.debug("[{}]页面部件【{}#{}】模板解析耗时：{}ms", pageWidget.getPublishPipeCode(), pageWidget.getName(),
					pageWidget.getCode(), System.currentTimeMillis() - s);
			return writer.toString();
		}
	}
}
