package com.grupo8.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo8.jwt.client.AuditoriaGraphqlClient;
import com.grupo8.jwt.model.GraphQLRequest;

@RestController
@RequestMapping("/api/grupo8/auditoria")
public class AudController {

    private final AuditoriaGraphqlClient audGraphClient;
    private final ObjectMapper objectMapper;

    public AudController(AuditoriaGraphqlClient audGraphClient, ObjectMapper objectMapper) {
        this.audGraphClient = audGraphClient;
        this.objectMapper = objectMapper;
    }


    @PostMapping
    public ResponseEntity<?> AudGraphQl(@RequestBody GraphQLRequest request){
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("query", request.getQuery());
            body.put("variables", request.getVariables() != null ? request.getVariables() : Map.of());

            String respuesta = audGraphClient.ejecutarQuery(body);
            JsonNode json = objectMapper.readTree(respuesta);

            if (json.has("errors")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
            }

            return ResponseEntity.ok(json.get("data"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
    
}
