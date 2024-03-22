package com.animals_back;

import com.animals_back.config.EnvLoaderConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalsBackApplication {

    public static void main(String[] args) {
        EnvLoaderConnection.loadEnvVariables();
        SpringApplication.run(AnimalsBackApplication.class, args);
    }

}
