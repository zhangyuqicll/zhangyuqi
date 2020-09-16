package com.leyou.service.leyouservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.service.leyouservice.mapper")
public class LeyouServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouServiceApplication.class, args);
    }

}
