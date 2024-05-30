package com.yuji.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yuji.common.security.annotation.EnableCustomConfig;
import com.yuji.common.security.annotation.EnableRyFeignClients;
import com.yuji.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author Liguoqiang
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients   
@SpringBootApplication
public class YuJiJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YuJiJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
