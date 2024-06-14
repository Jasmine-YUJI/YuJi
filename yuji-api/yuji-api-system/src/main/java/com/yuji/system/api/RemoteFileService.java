package com.yuji.system.api;

import com.yuji.common.core.domain.StroageArgs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.yuji.common.core.constant.ServiceNameConstants;
import com.yuji.common.core.domain.R;
import com.yuji.system.api.domain.SysFile;
import com.yuji.system.api.factory.RemoteFileFallbackFactory;

/**
 * 文件服务
 * 
 * @author Liguoqiang
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService
{
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<SysFile> upload(@RequestPart("file") MultipartFile file, @RequestPart("stroageArgs") StroageArgs stroageArgs);

}
