package com.animals_back.configurations;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public class EnvLoaderConnectionConfiguration {
    public static void loadEnvVariables() {
        Dotenv dotenv = Dotenv.configure().filename(".env").load();
        System.setProperty("spring.datasource.url", Objects.requireNonNull(dotenv.get("DB_URL")));
        System.setProperty("spring.datasource.username", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
        System.setProperty("spring.datasource.password", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
    }
}
