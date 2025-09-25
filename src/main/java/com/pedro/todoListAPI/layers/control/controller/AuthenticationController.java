package com.pedro.todoListAPI.layers.control.controller;

import com.pedro.todoListAPI.layers.domain.dto.LoginResponseDTO;
import com.pedro.todoListAPI.layers.domain.dto.RegisterDTO;
import com.pedro.todoListAPI.layers.domain.dto.AuthenticationDTO;
import com.pedro.todoListAPI.layers.service.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
