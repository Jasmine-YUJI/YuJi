package com.yuji.common.core.enums;

public enum FileType {

    LOCAL("Local", "本地存储"), MINIO("Minio", "Minio存储"), FASTDFS("FastDFS", "FastDFS存储");


    private final String code;
    private final String info;

    FileType(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
