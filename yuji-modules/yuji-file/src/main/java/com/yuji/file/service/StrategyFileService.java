package com.yuji.file.service;

import com.yuji.common.core.domain.StroageArgs;
import com.yuji.common.core.enums.FileType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class StrategyFileService implements ApplicationContextAware {
    private Map<String, ISysFileService> iFileStrategyMap = new ConcurrentHashMap<>();

    public String uploadFile(MultipartFile file , StroageArgs stroageArgs) throws Exception {
        ISysFileService sysFileService = iFileStrategyMap.get(stroageArgs.getFileType());
        if (sysFileService != null) {
            return sysFileService.uploadFile(file);
        }
        return null;
    }

    //把不同策略放到map
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ISysFileService> tmepMap = applicationContext.getBeansOfType(ISysFileService.class);
        tmepMap.values().forEach(strategyService -> iFileStrategyMap.put(strategyService.getType(), strategyService));
    }
}
