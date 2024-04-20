package com.animals_back.controllers;

import com.animals_back.services.RestService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class RESTController {
    private final RestService restService;

    @GetMapping("/animals")
    public ResponseEntity<?> getAllAnimals() {
        return restService.getAllAnimals();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/save-animal")
    public ResponseEntity<?> saveAnimal(@Nullable @RequestParam("image") MultipartFile multipartFile,
                                        @RequestParam("json") String json) throws IOException {
        return restService.saveAnimal(multipartFile, json);
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<?> getAnimalById(@PathVariable("id") Integer id) {
        return restService.getAnimalById(id);
    }

    @PostMapping("/update-animal/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateAnimal(@PathVariable("id") Integer id,
                                          @Nullable @RequestParam("image") MultipartFile multipartFile,
                                          @RequestParam("json") String json) throws IOException {
        return restService.updateAnimal(id, multipartFile, json);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-animal/{id}")
    public ResponseEntity<?> deleteAnimalById(@PathVariable("id") Integer id) {
        return restService.deleteAnimalById(id);
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return restService.userData(principal);
    }
}
