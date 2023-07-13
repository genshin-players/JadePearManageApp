package cn.bdqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;


@EnableFeignClients
@SpringBootApplication
@MapperScan("cn.bdqn.mapper")
public class PageApp {
    public static void main(String[] args) {
        SpringApplication.run(PageApp.class, args);
    }
}
