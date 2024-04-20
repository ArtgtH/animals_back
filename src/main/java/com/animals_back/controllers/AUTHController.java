package com.animals_back.controllers;


import com.animals_back.DTO.JwtRequest;
import com.animals_back.DTO.RegistrationUserDTO;
import com.animals_back.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@EnableAspectJAutoProxy
public class AUTHController {
    private final AuthService authService;

    @PostMapping("/authorization") 
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PutMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDTO) {
        return authService.createNewUser(registrationUserDTO);
    }
}
