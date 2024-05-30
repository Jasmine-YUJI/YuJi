package com.yuji.system.api.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件信息
 * 
 * @author Liguoqiang
 */
@Getter
@Setter
public class SysFile
{
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

}
