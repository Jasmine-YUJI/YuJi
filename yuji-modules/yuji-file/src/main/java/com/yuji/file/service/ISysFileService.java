package com.yuji.file.service;

import com.yuji.common.core.domain.StroageArgs;
import com.yuji.common.core.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件上传接口
 * 
 * @author Liguoqiang
 */
public interface ISysFileService
{

    public static final String BEAN_NAME_PREIFX = "SysFileType_";

    /**
     * 文件存储方式唯一标识
     */
    public String getType();

    /**
     * 存储方式名称
     */
    public String getName();

    /**
     * 测试连接
     */
    default public boolean testConnection(String endpoint, String accessKey, String accessSecret) {
        return true;
    }

    /**
     * 重置连接客户端
     *
     * @param clientKey
     * @param args
     */
    default void reloadClient(String endpoint, String accessKey, String accessSecret) {

    }

    /**
     * 创建存储桶
     *
     * @param minioClientKey
     * @param bucketName
     */
    default void createBucket(StroageArgs args) {

    }

    default boolean exists(StroageArgs args) {
        return true;
    }

    /**
     * 读取文件
     */
    public InputStream read(StroageArgs args);

    /**
     * 写入文件
     */
    public void write(StroageArgs args);

    /**
     * 删除文件
     */
    public void remove(StroageArgs args);

    /**
     * 复制文件
     */
    void copy(StroageArgs args);

    /**
     * 删除文件
     */
    void move(StroageArgs args);

    /**
     * 文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;



}
