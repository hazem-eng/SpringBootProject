package com.example.microfinancepi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
 @EnableScheduling
public class MicroFinancePiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroFinancePiApplication.class, args);
    }

}
