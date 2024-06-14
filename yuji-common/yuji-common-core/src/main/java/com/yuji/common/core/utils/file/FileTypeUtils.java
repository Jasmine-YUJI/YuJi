package com.yuji.common.core.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import com.yuji.common.core.utils.DateUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.uuid.Seq;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

/**
 * 文件类型工具类
 *
 * @author Liguoqiang
 */
public class FileTypeUtils
{
    /**
     * 获取文件类型
     * <p>
     * 例如: yuji.txt, 返回: txt
     * 
     * @param file 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(File file)
    {
        if (null == file)
        {
            return StringUtils.EMPTY;
        }
        return getFileType(file.getName());
    }

    /**
     * 获取文件类型
     * <p>
     * 例如: yuji.txt, 返回: txt
     *
     * @param fileName 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(String fileName)
    {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0)
        {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }

    /**
     * 获取文件名的后缀
     * 
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file)
    {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension))
        {
            extension = MimeTypeUtils.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }

    /**
     * 读取指定字符串的后缀名
     *
     * @param path 文路径
     * @return 后缀名
     */
    public static String getExtension(String path) {
        if (path.contains("://")) {
            path = HtmlUtils.htmlUnescape(path);
            String queryString = StringUtils.substringAfter(path, "?");
            if (queryString.contains("wx_fmt=")) {
                // 微信图片路径处理
                String ext = StringUtils.substringAfter(queryString, "wx_fmt=");
                if (ext.contains("&")) {
                    ext = StringUtils.substringBefore(ext, "&");
                }
                if (StringUtils.isNotEmpty(ext)) {
                    return ext;
                }
            }
        }
        if (path.contains("?")) {
            path = StringUtils.substringBefore(path, "?");
        }
        return FilenameUtils.getExtension(path);
    }

    /**
     * 获取文件类型
     * 
     * @param photoByte 文件字节码
     * @return 后缀（不含".")
     */
    public static String getFileExtendName(byte[] photoByte)
    {
        String strFileExtendName = "JPG";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97))
        {
            strFileExtendName = "GIF";
        }
        else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70))
        {
            strFileExtendName = "JPG";
        }
        else if ((photoByte[0] == 66) && (photoByte[1] == 77))
        {
            strFileExtendName = "BMP";
        }
        else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71))
        {
            strFileExtendName = "PNG";
        }
        return strFileExtendName;
    }

    /**
     * 格式化路径，去掉`../`、`./`等类似路径避免文件泄露
     *
     * @param path 路径
     * @return 格式化路径
     */
    public static String normalizePath(String path) {
        path = path.replace('\\', '/');

        path = StringUtils.replaceEx(path, "../", "/");
        path = StringUtils.replaceEx(path, "./", "/");
        path = StringUtils.replaceEx(path, "~/", "/");
        if (path.endsWith("..")) {
            path = path.substring(0, path.length() - 2);
        }
        path = path.replaceAll("/+", "/");
        return path;
    }

    /**
     * 创建指定路径的目录，包括所有上级目录
     */
    public static void mkdirs(String... paths) {
        if (StringUtils.isEmpty(paths)) {
            return;
        }
        for (String path : paths) {
            try {
                Files.createDirectories(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException("Create directory failed: " + path, e);
            }
        }
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file)
    {
        return StringUtils.format("{}/{}_{}.{}", DateUtils.datePath(),
                FilenameUtils.getBaseName(file.getOriginalFilename()), Seq.getId(Seq.uploadSeqType), FileTypeUtils.getExtension(file));
    }
}