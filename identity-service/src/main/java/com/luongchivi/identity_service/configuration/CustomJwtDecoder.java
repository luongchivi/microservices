package com.luongchivi.identity_service.configuration;

import java.text.ParseException;
import java.time.Instant;
import java.util.Map;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomJwtDecoder implements JwtDecoder {
    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            Instant issueTime = signedJWT.getJWTClaimsSet().getIssueTime().toInstant();
            Instant expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime().toInstant();
            Map<String, Object> claims = signedJWT.getJWTClaimsSet().getClaims();
            Map<String, Object> header = signedJWT.getHeader().toJSONObject();
            return new Jwt(token, issueTime, expirationTime, header, claims);
        } catch (ParseException e) {
            throw new JwtException("Invalid token");
        }
    }
}
