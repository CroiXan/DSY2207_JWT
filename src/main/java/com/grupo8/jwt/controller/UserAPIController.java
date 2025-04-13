package com.grupo8.jwt.controller;

import org.springframework.web.bind.annotation.*;

import com.grupo8.jwt.client.UserAPIClient;
import com.grupo8.jwt.model.Usuario;
import com.grupo8.jwt.service.AuditService;
import com.grupo8.jwt.util.ClaimsUtil;

import java.util.Optional;

@RestController
@RequestMapping("/api/grupo8/user")
public class UserAPIController {

    private final UserAPIClient userAPIClient;
    private final AuditService auditService;
    private ClaimsUtil claimsUtil;

    public UserAPIController(UserAPIClient userAPIClient, AuditService auditService) {
        this.userAPIClient = userAPIClient;
        this.auditService = auditService;
        claimsUtil = new ClaimsUtil();
    }

    @PostMapping("/insertUsuario")
    public String insertUsuario(@RequestBody Usuario usuario) {
        this.auditService.audUserInsert("CREATEUSER",this.claimsUtil.getClaimUserId(),"Crea Usuario|NickName:"+usuario.getNickname());
        return userAPIClient.insertUsuario(usuario);
    }

    @PutMapping("/actualizarUsuario")
    public String actualizarUsuario(@RequestBody Usuario usuario) {
        this.auditService.audUserInsert("UPDATEUSER",this.claimsUtil.getClaimUserId(),"Modifica Usuario |UserId:"+usuario.getId());
        return userAPIClient.actualizarUsuario(usuario);
    }

    @DeleteMapping("/eliminarUsuario")
    public String eliminarUsuario(@RequestBody String id) {
        this.auditService.audUserInsert("DELETEUSER",this.claimsUtil.getClaimUserId(),"Elimina Usuario|UserId:"+id);
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
