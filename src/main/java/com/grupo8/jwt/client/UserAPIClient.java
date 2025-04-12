package com.grupo8.jwt.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.grupo8.jwt.model.LoginReq;
import com.grupo8.jwt.model.Usuario;

@FeignClient(name = "user-service", url = "https://azuredsy2207g8userfunction.azurewebsites.net/api")
public interface UserAPIClient {

    @PostMapping(value = "/insertUsuario", consumes = "application/json")
    String insertUsuario(@RequestBody Usuario usuario);

    @PutMapping(value = "/actualizarUsuario", consumes = "application/json")
    String actualizarUsuario(@RequestBody Usuario usuario);

    @DeleteMapping("/eliminarUsuario")
    String eliminarUsuario(@RequestBody String id);

    @GetMapping("/obtenerUsuarios")
    String obtenerUsuarios();

    @GetMapping("/buscarUsuarioPorRut")
    Optional<Usuario> buscarUsuarioPorRut(@RequestHeader("rut") String rut);

    @PostMapping("/loginUsuario")
    String loginUsuario(@RequestBody LoginReq login);
}
