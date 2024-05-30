package com.yuji.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yuji.common.security.annotation.EnableCustomConfig;
import com.yuji.common.security.annotation.EnableRyFeignClients;
import com.yuji.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author Liguoqiang
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YuJiGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YuJiGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
