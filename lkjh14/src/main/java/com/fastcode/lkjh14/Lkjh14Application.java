package com.fastcode.lkjh14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.fastcode.lkjh14", "org.springframework.versions" })
public class Lkjh14Application {

    public static void main(String[] args) {
        SpringApplication.run(Lkjh14Application.class, args);
    }
}
