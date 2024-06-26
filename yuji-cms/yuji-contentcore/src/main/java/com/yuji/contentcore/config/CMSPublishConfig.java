package com.yuji.contentcore.config;

import com.yuji.contentcore.config.properties.CMSPublishProperties;
import com.yuji.contentcore.publish.IPublishTask;
import com.yuji.contentcore.publish.PublishTaskReceiver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.Map;

/**
 * 内容发布配置
 *
 * @author Liguoqiang
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(CMSPublishProperties.class)
@RequiredArgsConstructor
public class CMSPublishConfig {

    private final CMSPublishProperties properties;

    private final StringRedisTemplate redisTemplate;

    private final Map<String, IPublishTask<?>> publishTaskMap;

    public static final String PublishStreamName = "YujiCMSPublishStream";

    public static final String PublishConsumerGroup = "yujiCMSPublishConsumerGroup";

    @Bean
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer() {
        // 启动清理消息队列数据
        if (properties.isClearOnStart()) {
            redisTemplate.delete(PublishStreamName);
        }
        // 监听容器配置
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> streamMessageListenerContainerOptions = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                .builder()
                .batchSize(10) // 一次拉取消息数量
                .pollTimeout(Duration.ofSeconds(2)) // 拉取消息超时时间
                .executor(cmsPublishThreadPoolTaskExecutor())
                .build();
        // 创建监听容器
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> container = StreamMessageListenerContainer
                .create(redisTemplate.getRequiredConnectionFactory(), streamMessageListenerContainerOptions);
        //创建消费者组
        try {
            redisTemplate.opsForStream().createGroup(PublishStreamName, PublishConsumerGroup);
        } catch (Exception e) {
            log.info("消费者组:{} 已存在", PublishConsumerGroup);
        }
        // 添加消费者
        for (int i = 0; i < properties.getConsumerCount(); i++) {
            Consumer consumer = Consumer.from(PublishConsumerGroup, "cms-publish-consumer-" + i);
            PublishTaskReceiver publishTaskReceiver = new PublishTaskReceiver(publishTaskMap, redisTemplate);
            publishTaskReceiver.setConsumer(consumer);
            container.receive(consumer, StreamOffset.create(PublishStreamName, ReadOffset.lastConsumed()), publishTaskReceiver);
        }
        container.start();
        return container;
    }

    @Bean
    ThreadPoolTaskExecutor cmsPublishThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //TODO 待解决
        /*executor.setThreadNamePrefix(properties.getPool().getThreadNamePrefix());
        executor.setCorePoolSize(properties.getPool().getCoreSize());
        executor.setQueueCapacity(properties.getPool().getQueueCapacity());
        executor.setMaxPoolSize(properties.getPool().getMaxSize());
        executor.setKeepAliveSeconds((int) properties.getPool().getKeepAlive().getSeconds());
        executor.setAllowCoreThreadTimeOut(this.properties.getPool().isAllowCoreThreadTimeout());
        executor.setWaitForTasksToCompleteOnShutdown(properties.getShutdown().isAwaitTermination());
        executor.setAwaitTerminationSeconds((int) properties.getShutdown().getAwaitTerminationPeriod().toSeconds());*/
        log.info("Cms publish task executor initialize: {}", executor.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }
}
