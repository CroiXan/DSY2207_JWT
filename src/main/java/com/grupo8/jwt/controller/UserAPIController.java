package com.grupo8.jwt.controller;

import org.springframework.web.bind.annotation.*;

import com.grupo8.jwt.client.UserAPIClient;
import com.grupo8.jwt.model.Usuario;

import java.util.Optional;

@RestController
@RequestMapping("/api/grupo8/user")
public class UserAPIController {

    private final UserAPIClient userAPIClient;

    public UserAPIController(UserAPIClient userAPIClient) {
        this.userAPIClient = userAPIClient;
    }

    @PostMapping("/insertUsuario")
    public String insertUsuario(@RequestBody Usuario usuario) {
        return userAPIClient.insertUsuario(usuario);
    }

    @PutMapping("/actualizarUsuario")
    public String actualizarUsuario(@RequestBody Usuario usuario) {
        return userAPIClient.actualizarUsuario(usuario);
    }

    @DeleteMapping("/eliminarUsuario")
    public String eliminarUsuario(@RequestBody String id) {
        return userAPIClient.eliminarUsuario(id);
    }

    @GetMapping("/obtenerUsuarios")
    public String obtenerUsuarios() {
        return userAPIClient.obtenerUsuarios();
    }

    @GetMapping("/buscarUsuarioPorRut")
    public Optional<Usuario> buscarUsuarioPorRut(@RequestHeader("rut") String rut) {
        return userAPIClient.buscarUsuarioPorRut(rut);
    }
}
