package com.grupo8.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo8.jwt.client.UserAPIClient;
import com.grupo8.jwt.model.JwtResponse;
import com.grupo8.jwt.model.LoginReq;
import com.grupo8.jwt.model.LoginRequest;
import com.grupo8.jwt.model.LoginResponse;
import com.grupo8.jwt.service.JwtService;

@RestController
@RequestMapping("/api/grupo8/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserAPIClient userAPIClient;

    public AuthController(JwtService jwtService, UserAPIClient userAPIClient) {
        this.jwtService = jwtService;
        this.userAPIClient = userAPIClient;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginReq loginReq = new LoginReq(request.getUsername(),request.getPassword());
        String response = userAPIClient.loginUsuario(loginReq);

        ObjectMapper mapper = new ObjectMapper();
        LoginResponse responseLogin;
        try {
            responseLogin = mapper.readValue(response, LoginResponse.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        if (!responseLogin.getID_USUARIO().contains("no coinciden")) {
            String token = jwtService.generateToken(loginReq.getNickname());
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

}
