package com.grupo8.jwt.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auditoriaGraphql", url = "https://azuredsy2207g8auditoriafunction.azurewebsites.net")
public interface AuditoriaGraphqlClient {

    @PostMapping(value = "/api/auditoria", consumes = "application/json")
    String ejecutarQuery(@RequestBody Map<String, Object> query);

}
