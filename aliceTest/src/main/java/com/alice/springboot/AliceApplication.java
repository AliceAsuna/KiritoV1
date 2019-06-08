package com.alice.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.alice.springboot.mapper.*")
public class AliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliceApplication.class, args);
    }

}
