package com.yuji.contentcore.utils;

import com.yuji.common.core.enums.FileType;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.contentcore.core.IResourceType;
import com.yuji.contentcore.core.impl.ResourceType_File;
import com.yuji.contentcore.domain.CmsSite;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ResourceUtils {

    public static final Pattern ImgHtmlTagPattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);

    private static final Pattern IUrlTagPattern = Pattern.compile("<[^>]+iurl\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);

    private static final Pattern TagAttrSrcPattern = Pattern.compile("src\\s*=\\s*['\"]([^'\"]+)['\"]",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);

    private static final Pattern TagAttrHrefPattern = Pattern.compile("href\\s*=\\s*['\"]([^'\"]+)['\"]",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);

    private static final Pattern TagAttrIUrlPattern = Pattern.compile("iurl\\s*=\\s*['\"]([^'\"]+)['\"]",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);

    /**
     * 通过文件后缀名获取对应资源类型，由于文件资源包含所有文件类型，再获取到超过1个资源类型时将文件资源类型移除再获取。
     */
    public static IResourceType getResourceTypeBySuffix(String suffix) {
        List<IResourceType> list = ContentCoreUtils.getResourceTypes().stream().filter(rt -> rt.check(suffix)).toList();
        if (list.size() > 1) {
            for (IResourceType rt : list) {
                if (!ResourceType_File.ID.equals(rt.getId())) {
                    return rt;
                }
            }
        }
        return list.get(0);
    }

    public static String getResourcePrefix(String storageType, CmsSite site, boolean isPreview) {
        if (FileType.LOCAL.getCode().equals(storageType)) {
            return SiteUtils.getResourcePrefix(site, isPreview);
        }
        //TODO 待解决
        /*FileStorageArgsProperty.FileStorageArgs fileStorageArgs = FileStorageArgsProperty.getValue(site.getConfigProps());
        if (fileStorageArgs != null && StringUtils.isNotEmpty(fileStorageArgs.getDomain())) {
            String domain = fileStorageArgs.getDomain();
            if (!domain.endsWith("/")) {
                domain += "/";
            }
            return domain;
        }*/
        // 无法获取到对象存储访问地址时默认使用站点资源域名
        return site.getResourceUrl();
    }

    /**
     * 处理html中包含iurl属性的标签，移除iurl属性，如果标签中包含src或者href属性则替换成iurl属性值
     */
    public static String dealHtmlInternalUrl(String content) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        Matcher matcher = IUrlTagPattern.matcher(content);
        int lastEndIndex = 0;
        StringBuilder sb = new StringBuilder();
        while (matcher.find(lastEndIndex)) {
            int s = matcher.start();
            sb.append(content, lastEndIndex, s);

            String tagStr = matcher.group();
            String iurl = matcher.group(1);
            // begin
            try {
                // 移除iurl属性
                tagStr = TagAttrIUrlPattern.matcher(tagStr).replaceAll("");
                // 查找src属性，替换成iurl
                Matcher matcherSrc = TagAttrSrcPattern.matcher(tagStr);
                if (matcherSrc.find()) {
                    tagStr = tagStr.substring(0, matcherSrc.start()) + "src=\"" + iurl + "\""
                            + tagStr.substring(matcherSrc.end());
                } else {
                    // 无src属性则继续查找href属性
                    Matcher matcherHref = TagAttrHrefPattern.matcher(tagStr);
                    if (matcherHref.find()) {
                        tagStr = tagStr.substring(0, matcherHref.start()) + "href=\"" + iurl + "\""
                                + tagStr.substring(matcherHref.end());
                    }
                }
            } catch (Exception e) {
                log.warn("InternalUrl parse failed: " + iurl, e);
            }
            // end
            sb.append(tagStr.replace("\\s+", " "));

            lastEndIndex = matcher.end();
        }
        sb.append(content.substring(lastEndIndex));
        return sb.toString();
    }
}
