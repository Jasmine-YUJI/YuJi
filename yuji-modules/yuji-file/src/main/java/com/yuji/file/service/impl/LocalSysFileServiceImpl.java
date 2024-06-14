package com.yuji.file.service.impl;

import com.yuji.common.core.domain.StroageArgs;
import com.yuji.common.core.enums.FileType;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.common.core.utils.i18n.I18nUtils;
import com.yuji.file.exception.FileStorageException;
import com.yuji.file.service.ISysFileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.yuji.file.utils.FileUploadUtils;

import java.io.*;

/**
 * 本地文件存储
 * 
 * @author Liguoqiang
 */
@Component(ISysFileService.BEAN_NAME_PREIFX + LocalSysFileServiceImpl.TYPE)
public class LocalSysFileServiceImpl implements ISysFileService
{
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    public final static String TYPE = "Local";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getName() {
        return I18nUtils.get("{STORAGE.TYPE." + TYPE + "}");
    }

    @Override
    public boolean exists(StroageArgs args) {
        String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
        if (!bucket.endsWith("/")) {
            bucket += "/";
        }
        String filePath = bucket + args.getPath();
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    @Override
    public void copy(StroageArgs args) {
        try {
            String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
            if (!bucket.endsWith("/")) {
                bucket += "/";
            }
            FileUtils.copyDirectory(new File(bucket + args.getSourcePath()), new File(bucket + args.getDestPath()));
        } catch (IOException e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void move(StroageArgs args) {
        try {
            String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
            if (!bucket.endsWith("/")) {
                bucket += "/";
            }
            FileUtils.moveDirectory(new File(bucket + args.getSourcePath()), new File(bucket + args.getDestPath()));
        } catch (IOException e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public InputStream read(StroageArgs args) {
        try {
            String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
            if (!bucket.endsWith("/")) {
                bucket += "/";
            }
            String filePath = bucket + args.getPath();
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            return new FileInputStream(new File(filePath));
        } catch (IOException e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void write(StroageArgs args) {
        try {
            String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
            if (!bucket.endsWith("/")) {
                bucket += "/";
            }
            String filePath = bucket + args.getPath();
            File file = new File(filePath);
            FileTypeUtils.mkdirs(file.getParentFile().getAbsolutePath());
            FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(args.getInputStream()));
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void remove(StroageArgs args) {
        try {
            String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
            if (!bucket.endsWith("/")) {
                bucket += "/";
            }
            String filePath = bucket + args.getPath();
            FileUtils.delete(new File(filePath));
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }
    /**
     * 本地文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        String name = FileUploadUtils.upload(localFilePath, file);
        String url = domain + localFilePrefix + name;
        return url;
    }
}
