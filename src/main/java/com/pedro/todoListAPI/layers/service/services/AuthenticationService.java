package com.pedro.todoListAPI.layers.service.services;

import com.auth0.jwt.JWT;
import com.pedro.todoListAPI.layers.domain.dto.*;
import com.pedro.todoListAPI.layers.domain.model.User;
import com.pedro.todoListAPI.layers.infra.security.AuthTokenService;
import com.pedro.todoListAPI.layers.infra.security.RefreshTokenService;
import com.pedro.todoListAPI.layers.repository.UserRepository;
import com.pedro.todoListAPI.miscelaneous.exceptions.LoginAlreadyInUseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public LoginResponseDTO authenticateUser(@RequestBody @Valid AuthenticationDTO data){

        var user = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(user);

        return new LoginResponseDTO(authTokenService.generateToken((User) auth.getPrincipal()),
                refreshTokenService.generateToken((User) auth.getPrincipal()));
    }

    public void registerUser(@RequestBody @Valid RegisterDTO data)
    {
        if(this.userRepository.findByLogin(data.login()) !=null) throw new LoginAlreadyInUseException();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.nickname());

        this.userRepository.save(newUser);
    }

    public RefreshResponseDT0 refreshUser(String data){
        return new RefreshResponseDT0(authTokenService.generateToken(
                JWT.decode(data.replaceFirst("Bearer ", "")).getSubject()
        ));
    }

}
