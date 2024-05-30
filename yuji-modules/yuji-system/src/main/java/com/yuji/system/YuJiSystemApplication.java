package com.yuji.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yuji.common.security.annotation.EnableCustomConfig;
import com.yuji.common.security.annotation.EnableRyFeignClients;
import com.yuji.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author Liguoqiang
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YuJiSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YuJiSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
