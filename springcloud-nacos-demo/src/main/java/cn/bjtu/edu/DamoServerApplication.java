package cn.bjtu.edu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * spring boot 核心启动类
 *
 * @SpringBootApplication spring boot 启动核心注解
 * @EnableDiscoveryClient 开启Spring Cloud的服务注册与发现，由于这里引入了spring-cloud-starter-alibaba-nacos-discovery模块，所以Spring Cloud Common中定义的那些与服务治理相关的接口将使用Nacos的实现
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class DamoServerApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(DamoServerApplication.class, args).getEnvironment();
        log.info("\n\n** ================================================================ **" +
                "\n** 服务端 '{}' 启动成功！" +
                "\n** ================================================================ **\n",
                env.getProperty("spring.application.name"));
    }

}
