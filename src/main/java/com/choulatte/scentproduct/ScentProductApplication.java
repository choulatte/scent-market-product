package com.choulatte.scentproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@SpringBootApplication
public class ScentProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScentProductApplication.class, args);
    }

}
