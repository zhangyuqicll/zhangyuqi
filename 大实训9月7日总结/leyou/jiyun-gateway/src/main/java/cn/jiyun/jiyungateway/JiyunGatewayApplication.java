package cn.jiyun.jiyungateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient//eureka的客户端
@EnableZuulProxy//开启zuul服务
public class JiyunGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiyunGatewayApplication.class, args);
    }

}
