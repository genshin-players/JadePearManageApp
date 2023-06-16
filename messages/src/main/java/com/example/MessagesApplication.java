package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.
        SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("cn.bdqn.mapper")
@SpringBootApplication
public class MessagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesApplication.class, args);
    }

}
