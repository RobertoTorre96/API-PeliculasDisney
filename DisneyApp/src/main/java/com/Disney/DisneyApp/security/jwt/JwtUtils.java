package com.Disney.DisneyApp.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.expiration.time}")
    private String timeExpirator;


    public String generarToken(String username){

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(timeExpirator)))
                .signWith(getFirmaToken(),SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getFirmaToken() {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getFirmaToken())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (Exception e){
            log.error("token invalido".concat(e.getMessage()));
            return false;
        }
    }
    public Claims getAllClamims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getFirmaToken())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsFunction){
        Claims claims =getAllClamims(token);
        return claimsFunction.apply(claims);
    }

    public String getUsernameFromToken (String token){
        return getClaim(token,Claims::getSubject);
    }
}
