package cn.bdqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 学社工作模块
 *
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.bdqn.mapper")
public class WorkApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(WorkApp.class,args);
    }
}
