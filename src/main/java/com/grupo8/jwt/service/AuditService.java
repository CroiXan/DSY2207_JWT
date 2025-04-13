package com.grupo8.jwt.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.grupo8.jwt.client.AuditoriaGraphqlClient;
import com.grupo8.jwt.model.AudRolRequest;
import com.grupo8.jwt.model.AudUsuarioRequest;
import com.grupo8.jwt.model.AudUsuarioRolRequest;

@Service
public class AuditService {

    private final AuditoriaGraphqlClient audGraphClient;

    public AuditService(AuditoriaGraphqlClient audGraphClient) {
        this.audGraphClient = audGraphClient;
    }

    @Async
    public void audLoginInsert(int idUser, String detalle){
        AudUsuarioRequest audUser = new AudUsuarioRequest();
        audUser.setIdUsuarioAfectado(idUser);
        audUser.setIdUsuarioEjecutor(idUser);
        audUser.setAccion("LOGIN");
        audUser.setDetalleCambios(detalle);
        this.audGraphClient.UserAuditInsert(audUser);
    }

    @Async
    public void audUserInsert(String accion, int idUser, String detalle){
        AudUsuarioRequest audUser = new AudUsuarioRequest();
        audUser.setIdUsuarioAfectado(idUser);
        audUser.setIdUsuarioEjecutor(idUser);
        audUser.setAccion(accion);
        audUser.setDetalleCambios(detalle);
        this.audGraphClient.UserAuditInsert(audUser);
    }

    @Async
    public void rolAuditInsert(String accion, String detalle, int rolId, int userId){
        AudRolRequest audRol = new AudRolRequest();
        audRol.setAccion(accion);
        audRol.setDetalleCambios(detalle);
        audRol.setIdRolAfectado(rolId);
        audRol.setIdUsuarioEjecutor(userId);
        this.audGraphClient.RolAuditInsert(audRol);
    }

    @Async
    public void UserRolAuditInsert(String accion, int rolId, int userId, int execUserId){
        AudUsuarioRolRequest audUserRol = new AudUsuarioRolRequest();
        audUserRol.setAccion(accion);
        audUserRol.setIdRol(rolId);
        audUserRol.setIdUsuario(userId);
        audUserRol.setIdUsuarioEjecutor(execUserId);
        this.audGraphClient.UserRolAuditInsert(audUserRol);
    }
}
