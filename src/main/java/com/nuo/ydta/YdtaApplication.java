package com.nuo.ydta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YdtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YdtaApplication.class, args);
    }

}
