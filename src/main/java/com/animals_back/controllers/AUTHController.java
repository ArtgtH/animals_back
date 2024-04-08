package com.animals_back.controllers;


import com.animals_back.DTO.JwtRequest;
import com.animals_back.DTO.RegistrationUserDTO;
import com.animals_back.services.AuthService;
import com.animals_back.services.UserService;
import com.animals_back.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AUTHController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDTO) {
        return authService.createNewUser(registrationUserDTO);
    }
}
