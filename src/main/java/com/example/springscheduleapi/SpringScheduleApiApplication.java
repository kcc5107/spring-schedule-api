package com.example.springscheduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.springscheduleapi.Lv3")
public class SpringScheduleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringScheduleApiApplication.class, args);
    }

}
