package com.yuji.system.api.factory;

import com.yuji.common.core.domain.R;
import com.yuji.system.api.RemoteConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 参数服务降级处理
 * 
 * @author Liguoqiang
 */
@Component
public class RemoteConfigFallbackFactory implements FallbackFactory<RemoteConfigService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteConfigFallbackFactory.class);

    @Override
    public RemoteConfigService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteConfigService()
        {
            @Override
            public R<String> getConfigKey(String configKey, String source)
            {
                return R.fail("获取参数失败:" + throwable.getMessage());
            }
        };
    }
}
