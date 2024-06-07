package com.yuji.contentcore.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <TODO description class purpose>
 *
 * @author Liguoqiang
 */
public interface IPublishTask<T> {

    Logger logger = LoggerFactory.getLogger("publish");

    String BeanPrefix = "PublishTask_";

    String getType();

    /**
     * 创建发布任务
     */
    void publish(T data);

    /**
     * 静态化
     */
    void staticize(Map<String, String> dataMap);
}
