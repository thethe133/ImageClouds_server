package com.sparta.imageclouds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ImageCloudsApplication {

    public static void main(String[] args) {SpringApplication.run(ImageCloudsApplication.class, args);
    }

}
