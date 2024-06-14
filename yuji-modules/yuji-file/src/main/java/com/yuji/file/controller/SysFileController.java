package com.yuji.file.controller;

import com.yuji.common.core.domain.StroageArgs;
import com.yuji.file.service.StrategyFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.yuji.common.core.domain.R;
import com.yuji.common.core.utils.file.FileUtils;
import com.yuji.system.api.domain.SysFile;

/**
 * 文件请求处理
 * 
 * @author Liguoqiang
 */
@RestController
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);


    @Autowired
    private StrategyFileService strategyFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(@RequestPart(name = "file") MultipartFile file, StroageArgs stroageArgs)
    {
        try
        {
            // 上传并返回访问地址
            String url =  strategyFileService.uploadFile(file, stroageArgs);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }
}