package com.grupo8.jwt.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grupo8.jwt.model.AudRolRequest;
import com.grupo8.jwt.model.AudUsuarioRequest;
import com.grupo8.jwt.model.AudUsuarioRolRequest;

@FeignClient(name = "auditoriaGraphql", url = "https://azuredsy2207g8auditoriafunction.azurewebsites.net")
public interface AuditoriaGraphqlClient {

    @PostMapping(value = "/api/auditoria", consumes = "application/json")
    String ejecutarQuery(@RequestBody Map<String, Object> query);

    @PostMapping(value = "/api/auditsearch", consumes = "application/json")
    String SearchAuditFunction(@RequestBody Map<String, Object> query);

    @PostMapping(value = "/api/userAuditInsert?code=EHhIN3qvTTMaoURTumzQSi7B01rgKGUCQeqPcquLnS7iAzFuhNypqQ==", consumes = "application/json", produces = "text/plain")
    String UserAuditInsert(@RequestBody AudUsuarioRequest audUser);

    @PostMapping(value = "/api/rolAuditInsert?code=EHhIN3qvTTMaoURTumzQSi7B01rgKGUCQeqPcquLnS7iAzFuhNypqQ==", consumes = "application/json", produces = "text/plain")
    String RolAuditInsert(@RequestBody AudRolRequest audRol);

    @PostMapping(value = "/api/userRolAuditInsert?code=EHhIN3qvTTMaoURTumzQSi7B01rgKGUCQeqPcquLnS7iAzFuhNypqQ==", consumes = "application/json", produces = "text/plain")
    String UserRolAuditInsert(@RequestBody AudUsuarioRolRequest audUserRol);

}
