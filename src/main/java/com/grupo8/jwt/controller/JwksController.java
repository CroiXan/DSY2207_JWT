package com.grupo8.jwt.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.jwt.component.KeyProvider;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

@RestController
@RequestMapping("/.well-known")
public class JwksController {

    private final KeyProvider keyProvider;

    public JwksController(KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @GetMapping("/jwks.json")
    public Map<String, Object> getJwks() {
        RSAKey jwk = new RSAKey.Builder(keyProvider.getPublicKey())
            .keyUse(KeyUse.SIGNATURE)
            .algorithm(JWSAlgorithm.RS256)
            .keyID("rsa-key-1")
            .build();

        return new JWKSet(jwk).toJSONObject();
    }
    
}
