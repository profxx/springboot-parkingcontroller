package com.dio.parkingcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.parkingcontroller.respository.UserRepository;
import com.dio.parkingcontroller.service.AuthorizationService;
import com.dio.parkingcontroller.service.TokenService;
import com.dio.parkingcontroller.user.AuthenticationDTO;
import com.dio.parkingcontroller.user.LoginResponseDTO;
import com.dio.parkingcontroller.user.RegisterDTO;
import com.dio.parkingcontroller.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    // Método para fazer a autenticação do usuário e login
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        // Criação do Token para poder mascarar a senha e manter a conexão do usuário
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        // Autenticar
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    // Criar novo usuário
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if (userRepository.findByUsername(registerDTO.username()) != null) return ResponseEntity.badRequest().build(); 
        else{
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.username(), encryptedPassword, registerDTO.role());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
        }
    }
}
