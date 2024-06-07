package com.yuji.contentcore.utils;

import com.yuji.common.core.utils.ReflectASMUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.security.utils.SecurityUtils;
import com.yuji.common.staticize.FreeMarkerUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.contentcore.ContentCoreConsts;
import com.yuji.contentcore.core.IProperty.UseType;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.fixed.config.TemplateSuffix;
import com.yuji.contentcore.properties.SiteApiUrlProperty;
import freemarker.core.Environment;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateUtils {

	/**
	 * 模板变量：预览模式登录用户token键名
	 */
	public final static String TemplateVariable_TokenName = "TokenName";

	/**
	 * 模板变量：预览模式登录用户token
	 */
	public final static String TemplateVariable_Token = "Token";

	/**
	 * 模板变量：是否预览模式
	 */
	public final static String TemplateVariable_IsPreview = "IsPreview";

	/**
	 * 模板变量：发布通道编码
	 */
	public final static String TemplateVariable_PublishPipeCode = "PublishPipeCode";

	/**
	 * 模板变量：发布通道静态化文件访问前缀
	 */
	public final static String TemplateVariable_Prefix = "Prefix";

	/**
	 * 模板变量：资源文件访问前缀
	 */
	public final static String TemplateVariable_ResourcePrefix = "ResourcePrefix";

	/**
	 * 模板变量：站点API访问前缀
	 */
	public final static String TemplateVariable_ApiPrefix = "ApiPrefix";

	/**
	 * 模板变量：站点信息
	 */
	public final static String TemplateVariable_Site = "Site";

	/**
	 * 模板变量：栏目信息
	 */
	public final static String TemplateVariable_Catalog = "Catalog";

	/**
	 * 模板变量：内容信息
	 */
	public final static String TemplateVariable_Content = "Content";

	/**
	 * 模板变量：页面组件信息
	 */
	public final static String TemplateVariable_PageWidget = "PageWidget";

	/**
	 * 模板变量：logo链接
	 */
	public final static String TemplateVariable_OBJ_LogoSrc = "logoSrc";

	/**
	 * 模板变量：访问地址
	 */
	public final static String TemplateVariable_OBJ_Link = "link";

	/**
	 * 添加站点数据到模板上线文变量中
	 *
	 * @param site 站点
	 * @param context 模板上下文
	 */
	public static void addSiteVariables(CmsSite site, TemplateContext context) {
		// 站点属性
		Map<String, Object> mapSite = ReflectASMUtils.beanToMap(site);
		// 扩展属性
		Map<String, Object> configProps = ConfigPropertyUtils.parseConfigProps(site.getConfigProps(), UseType.Site);
		configProps.forEach((key, value) -> mapSite.put(ContentCoreConsts.ConfigPropFieldPrefix + key, value));
		// 站点logo
		String siteLogo = InternalUrlUtils.getActualUrl(site.getLogo(), context.getPublishPipeCode(), context.isPreview());
		if (StringUtils.isNotEmpty(siteLogo)) {
			mapSite.put(TemplateVariable_OBJ_LogoSrc, siteLogo);
		}
		// 站点链接
		String siteLink = SiteUtils.getSiteLink(site, context.getPublishPipeCode(), context.isPreview());
		mapSite.put(TemplateVariable_OBJ_Link, siteLink);
		context.getVariables().put(TemplateVariable_Site, mapSite);
	}

	/**
	 * 添加栏目数据到模板上下文变量中
	 *
	 * @param site 站点
	 * @param catalog 栏目
	 * @param context 模板上下文
	 */
	public static void addCatalogVariables(CmsSite site, CmsCatalog catalog, TemplateContext context) {
		Map<String, Object> mapCatalog = ReflectASMUtils.beanToMap(catalog);
		// 扩展属性
		Map<String, Object> configProps = ConfigPropertyUtils.parseConfigProps(catalog.getConfigProps(), UseType.Catalog);
		configProps.forEach((key, value) -> mapCatalog.put(ContentCoreConsts.ConfigPropFieldPrefix + key, value));
		// 栏目logo
		String catalogLogo = InternalUrlUtils.getActualUrl(catalog.getLogo(), context.getPublishPipeCode(), context.isPreview());
		if (StringUtils.isNotEmpty(catalogLogo)) {
			mapCatalog.put(TemplateVariable_OBJ_LogoSrc, catalogLogo);
		}
		// 栏目链接
		String catalogLink = CatalogUtils.getCatalogLink(site, catalog, 1, context.getPublishPipeCode(), context.isPreview());
		mapCatalog.put(TemplateVariable_OBJ_Link, catalogLink);
		String catalogListLink = CatalogUtils.getCatalogListLink(site, catalog, 1, context.getPublishPipeCode(), context.isPreview());
		mapCatalog.put("listLink", catalogListLink);
		context.getVariables().put(TemplateVariable_Catalog, mapCatalog);
	}

	/**
	 * 创建模板初始变量，包括全局变量和站点信息
	 *
	 * @param site 站点
	 * @param context 模板上下文
	 */
	public static void initGlobalVariables(CmsSite site, TemplateContext context) {
		context.getVariables().put(TemplateVariable_IsPreview, context.isPreview());
		context.getVariables().put(TemplateVariable_PublishPipeCode, context.getPublishPipeCode());
		// 发布通道静态化文件访问前缀
		context.getVariables().put(TemplateVariable_Prefix, SiteUtils.getPublishPipePrefix(site, context.getPublishPipeCode(), context.isPreview()));
		// 资源文件访问前缀
		context.getVariables().put(TemplateVariable_ResourcePrefix, SiteUtils.getResourcePrefix(site, context.isPreview()));
		// 站点API访问前缀
		context.getVariables().put(TemplateVariable_ApiPrefix, SiteApiUrlProperty.getValue(site, context.getPublishPipeCode()));
		// 添加站点数据
		addSiteVariables(site, context);
		if (context.isPreview()) {
			String token = SecurityUtils.getToken();
			context.getVariables().put(TemplateVariable_Token, token);
		}
	}

	public static String appendTokenParameter(String url, Environment env) throws TemplateModelException {
		if (StringUtils.isEmpty(url)) {
			return url;
		}
		String tokenName = FreeMarkerUtils.getStringVariable(Environment.getCurrentEnvironment(), TemplateUtils.TemplateVariable_TokenName);
		String token = FreeMarkerUtils.getStringVariable(Environment.getCurrentEnvironment(), TemplateUtils.TemplateVariable_Token);
		if (url.contains("?")) {
			return url + "&" + tokenName + "=" + token;
		}
		return url + "?" + token + "=" + token;
	}

	public static String appendTokenParameter(String url) {
		if (StringUtils.isEmpty(url)) {
			return url;
		}
		String token = SecurityUtils.getToken();
		if (url.contains("?")) {
			return url + "&" + token;
		}
		return url + "?" + token;
	}


	/**
	 * 页面区块静态文件相对路径
	 *
	 * @param site 站点
	 * @param publishPipeCode 发布通道编码
	 * @param includeTemplateName 相对resourceRoot的模板路径
	 */
	public static String getIncludeRelativeStaticPath(CmsSite site, String publishPipeCode, String includeTemplateName) {
		String siteTemplatePath = SiteUtils.getSiteTemplatePath(site.getPath(), publishPipeCode);
		return "include/" + includeTemplateName.substring(siteTemplatePath.length(),
				includeTemplateName.length() - TemplateSuffix.getValue().length())
				+ "." + site.getStaticSuffix(publishPipeCode);
	}
}
