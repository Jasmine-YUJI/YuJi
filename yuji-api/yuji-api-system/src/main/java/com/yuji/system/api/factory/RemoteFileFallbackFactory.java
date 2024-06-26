package com.yuji.system.api.factory;

import com.yuji.common.core.domain.StroageArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.yuji.common.core.domain.R;
import com.yuji.system.api.RemoteFileService;
import com.yuji.system.api.domain.SysFile;

/**
 * 文件服务降级处理
 * 
 * @author Liguoqiang
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(Throwable throwable)
    {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService()
        {
            @Override
            public R<SysFile> upload(MultipartFile file, StroageArgs stroageArgs)
            {
                return R.fail("上传文件失败:" + throwable.getMessage());
            }

        };
    }
}
