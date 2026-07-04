package com.example.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PayLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayLearningApplication.class, args);
    }
}
