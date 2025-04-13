package com.grupo8.jwt.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo8.jwt.client.CorreoGraphqlClient;
import com.grupo8.jwt.model.GraphQLRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/grupo8")
public class CorreoController {

    private final CorreoGraphqlClient correoClient;
    private final ObjectMapper objectMapper;

    public CorreoController(CorreoGraphqlClient correoClient, ObjectMapper objectMapper) {
        this.correoClient = correoClient;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/correo")
    public ResponseEntity<?> enviarQueryCorreo(@RequestBody GraphQLRequest request) {
        try {
            String respuesta = correoClient.ejecutarQueryCorreo(request);
            JsonNode json = objectMapper.readTree(respuesta);

            if (json.has("errors")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
            }

            return ResponseEntity.ok(json.get("data") != null ? json.get("data") : json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
}

