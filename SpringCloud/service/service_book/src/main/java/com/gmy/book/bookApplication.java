package com.gmy.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//设置包的扫描规则,都扫描
@ComponentScan(basePackages = {"com.gmy"})
@EnableDiscoveryClient
@EnableFeignClients
public class bookApplication {
    public static void main(String[] args) {
        SpringApplication.run(bookApplication.class, args);
    }
}
