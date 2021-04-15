package com.bov.petclinic.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Date.from;

@Component
@Slf4j
public class JwtProvider {
    @Value("$(jwt.secret)")
    private String jwtKey;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateTokenWithAuthentication(Authentication authentication){
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .signWith(SignatureAlgorithm.HS512,jwtKey)
                .compact();
    }

    public String generateToken(String login){
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .setIssuedAt(from(Instant.now()))
                .signWith(SignatureAlgorithm.HS512,jwtKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt");
            
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt");
        } catch (SignatureException sEx) {
            log.error("Invalid signature");
        } catch (Exception e) {
            log.error("invalid token");
        }
        return false;
    }
    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public Long getJwtExpirationInMillis(){
        return jwtExpirationInMillis;
    }
}
