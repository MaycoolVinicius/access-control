package com.maycool.access_control.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class JwtService {

    //colocar o nome da propriedade que você definiu no application.properties
    @Value("${app.jwt.secret}")
    private String apiKey;
    private static final long EXPIRATION_MS = 86400000;
    public String getApiKey() {
        return apiKey;
    }
    //O JwtService precisa de dois metodos,generateToken(User user) — usa a chave secreta para assinar e criar o token e validateToken(String token) — faz o caminho inverso: recebe o token, valida a assinatura e extrai o username
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(Keys.hmacShaKeyFor(apiKey.getBytes()))
                .compact();
    }
    public String validateToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(apiKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();


        String subject = claims.getSubject();
        return subject;
    }
}
