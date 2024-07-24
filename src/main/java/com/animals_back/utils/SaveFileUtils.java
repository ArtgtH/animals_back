package com.animals_back.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SaveFileUtils {
    private SaveFileUtils() {

    }

    public static String saveFile(MultipartFile file) throws IOException {
        String directoryPath = "src/main/resources/static/animalsPhotos";

        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        String fileName = file.getOriginalFilename();
        String safeFileName = Paths.get(fileName).getFileName().toString();
        Path filePath = directory.resolve(safeFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "static/animalsPhotos/" + safeFileName;
    }
}
