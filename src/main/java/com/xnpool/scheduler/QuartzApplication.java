package com.xnpool.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@EnableAutoConfiguration
@ServletComponentScan // 扫描到自己编写的servlet和filter
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) ///*scanBasePackages="com.xnpool.scheduler",*/
public class QuartzApplication {

    public static void main(String[] args) {


        SpringApplication.run(QuartzApplication.class, args);
    }

}
