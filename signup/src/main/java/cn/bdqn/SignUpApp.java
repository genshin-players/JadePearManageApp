package cn.bdqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan("cn.bdqn.mapper")
public class SignUpApp {
    public static void main(String[] args) {
        SpringApplication.run(SignUpApp.class, args);
    }
}
