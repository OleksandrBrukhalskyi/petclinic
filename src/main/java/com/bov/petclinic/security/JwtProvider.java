package com.bov.petclinic.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    @Value("$(jwt.key)")
    private String jwtKey;

    public String generateToken(String login){
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.ES512,jwtKey)
                .compact();

    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return false;
    }
    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
