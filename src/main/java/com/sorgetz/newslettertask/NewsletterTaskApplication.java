package com.sorgetz.newslettertask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewsletterTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsletterTaskApplication.class, args);
    }

}
