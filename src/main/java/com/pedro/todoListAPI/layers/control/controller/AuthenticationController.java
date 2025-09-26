package com.pedro.todoListAPI.layers.control.controller;

import com.pedro.todoListAPI.layers.domain.dto.*;
import com.pedro.todoListAPI.layers.service.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        return ResponseEntity.ok(service.authenticateUser(data));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        service.registerUser(data);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDT0> refresh(@RequestHeader("Authorization") String data){
        System.out.println(data);
        return ResponseEntity.ok(service.refreshUser(data));
    }
}
