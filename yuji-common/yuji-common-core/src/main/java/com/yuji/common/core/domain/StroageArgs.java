package com.yuji.common.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
public class StroageArgs {


    /**
     * 存储类型
     */
    private String fileType;

    /**
     * 访问API接口地址
     */
    private String endpoint;

    /**
     * 用户名
     */
    private String accessKey;

    /**
     * 访问密码
     */
    private String accessSecret;

    /**
     * 存储空间名
     */
    private String bucket;

    /**
     * 路径
     */
    private String path;

    /**
     * 源路径
     */
    private String sourcePath;

    /**
     * 目标路径
     */
    private String destPath;

    /**
     * 写入对象输入流
     */
    private InputStream inputStream;

    /**
     * 文件大小
     */
    private Long length;

}
