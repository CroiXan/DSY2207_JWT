package com.grupo8.jwt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grupo8.jwt.model.GraphQLRequest;

@FeignClient(name = "correoGraphql", url = "https://azuredsy2207g8correofunction.azurewebsites.net")
public interface CorreoGraphqlClient {

    @PostMapping(value = "/api/graphqlCorreo", consumes = "application/json", produces = "text/plain")
    String ejecutarQueryCorreo(@RequestBody GraphQLRequest graphqlRequest);
}

