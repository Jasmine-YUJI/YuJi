package com.yuji.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 * 
 * @author Liguoqiang
 */
@EnableAdminServer
@SpringBootApplication
public class YuJiMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YuJiMonitorApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  监控中心启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
