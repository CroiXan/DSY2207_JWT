package com.grupo8.jwt.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.Claims;

public class ClaimsUtil {

    public Claims getContextClaims() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getDetails() instanceof Claims) {
            return (Claims) auth.getDetails();
        }
        return null;
    }

    public int getClaimUserId(){
        int userId = 0;
        Claims claims = this.getContextClaims();
        if (claims != null) {
            userId = claims.get("userId", Integer.class);
        }
        return userId;
    }
}
