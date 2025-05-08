package com.grupo8.jwt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo8.jwt.client.RoleAPIClient;
import com.grupo8.jwt.client.UserAPIClient;
import com.grupo8.jwt.model.JwtResponse;
import com.grupo8.jwt.model.LoginReq;
import com.grupo8.jwt.model.LoginRequest;
import com.grupo8.jwt.model.LoginResponse;
import com.grupo8.jwt.service.AuditService;
import com.grupo8.jwt.service.JwtService;

@RestController
@RequestMapping("/api/grupo8/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserAPIClient userAPIClient;
    private final AuditService auditService;
    private final RoleAPIClient roleAPIClient;
    @Value("${azure.function.role.key}")
    private String roleApiKey;

    public AuthController(JwtService jwtService, UserAPIClient userAPIClient, AuditService auditService, RoleAPIClient roleAPIClient) {
        this.jwtService = jwtService;
        this.userAPIClient = userAPIClient;
        this.auditService = auditService;
        this.roleAPIClient = roleAPIClient;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginReq loginReq = new LoginReq(request.getUsername(),request.getPassword());
        String response = userAPIClient.loginUsuario(loginReq);

        ObjectMapper mapper = new ObjectMapper();
        LoginResponse responseLogin;
        int userId = 0;
        List<String> roles = new ArrayList<>();

        try {
            responseLogin = mapper.readValue(response, LoginResponse.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            this.auditService.audLoginInsert(userId,"Credenciales inválidas|UserName:"+request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.auditService.audLoginInsert(userId,"Credenciales inválidas|UserName:"+request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        if (!responseLogin.getID_USUARIO().contains("no coinciden")) {
            try {
                userId = Integer.parseInt(responseLogin.getID_USUARIO());
                String rolesText = roleAPIClient.getRoleNameByUserID(Long.valueOf(userId),roleApiKey);
                try {
                    roles = mapper.readValue(rolesText, new TypeReference<List<String>>() {});
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al parsear Id");
            }
            String token = jwtService.generateToken(loginReq.getNickname(),userId,roles);
            this.auditService.audLoginInsert(userId,"Credenciales Válidas|UserName:"+request.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            this.auditService.audLoginInsert(userId,"Credenciales inválidas|UserName:"+request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

}
