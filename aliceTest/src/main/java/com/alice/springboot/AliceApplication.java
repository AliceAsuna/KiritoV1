package com.alice.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan("com.alice.springboot.mapper.*")
public class AliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliceApplication.class, args);
    }

}
