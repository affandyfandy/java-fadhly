package com.week10.authentication.controller;

import com.week10.authentication.entity.Authentication;
import com.week10.authentication.service.AuthenticationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v2/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping(value = "/validate")
    public boolean checkApiKey(@RequestParam String apiKey) {
        return authenticationService.validateApiKey(apiKey);
    }

    @PostMapping(value ="/")
    public ResponseEntity<Authentication> createNewApiKey(@RequestBody Authentication authentication) {
        Authentication auth = authenticationService.createApiKey(authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(auth);
    }
}
