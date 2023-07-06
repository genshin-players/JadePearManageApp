package cn.bdqn;

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
@EnableHystrix
public class UploadApp {
    public static void main(String[] args) {
        SpringApplication.run(UploadApp.class,args);
    }
}
