package com.yuji.system.api;

import com.yuji.common.core.constant.SecurityConstants;
import com.yuji.common.core.constant.ServiceNameConstants;
import com.yuji.common.core.domain.R;
import com.yuji.system.api.factory.RemoteConfigFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 参数服务
 * 
 * @author Liguoqiang
 */
@FeignClient(contextId = "remoteConfigService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteConfigFallbackFactory.class)
public interface RemoteConfigService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param configKey
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/config/configKey/{configKey}")
    public R<String> getConfigKey(@PathVariable("configKey") String configKey, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
