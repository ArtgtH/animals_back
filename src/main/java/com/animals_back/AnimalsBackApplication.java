package com.animals_back;

import com.animals_back.configurations.EnvLoaderConnectionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AnimalsBackApplication {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        EnvLoaderConnectionConfiguration.loadEnvVariables();
        SpringApplication.run(AnimalsBackApplication.class, args);
    }
}
