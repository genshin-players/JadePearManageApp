package cn.bdqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZedFeorius
 * @version 1.0.0
 * @date 06 10 2023  19:55:01
 * @packageName cn.bdqn
 * @className DisplayApp
 * @describe TODO
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.bdqn.mapper")
@EnableHystrix
public class DisplayApp {
    public static void main(String[] args) {
        SpringApplication.run(DisplayApp.class,args);
    }
}
