package com.animals_back;

import com.animals_back.configurations.EnvLoaderConnectionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalsBackApplication {

    public static void main(String[] args) {
        EnvLoaderConnectionConfiguration.loadEnvVariables();
        SpringApplication.run(AnimalsBackApplication.class, args);
    }

}
