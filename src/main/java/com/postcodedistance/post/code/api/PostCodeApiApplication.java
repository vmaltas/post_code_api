package com.postcodedistance.post.code.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PostCodeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostCodeApiApplication.class, args);
        log.info("Post Code API Started.");
    }

}
