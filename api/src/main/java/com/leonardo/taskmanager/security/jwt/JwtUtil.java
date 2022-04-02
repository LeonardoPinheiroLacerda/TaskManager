package com.leonardo.taskmanager.security.jwt;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.model.enums.Role;
import com.leonardo.taskmanager.security.configs.JwtConfig;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
    
    public String generateToken(Authentication authResult, JwtConfig jwtConfig, SecretKey secretKey){
        String token = Jwts.builder()
            .setSubject(authResult.getName())
            .claim("authorities", authResult.getAuthorities())
            .setIssuedAt(Date.valueOf(LocalDate.now()))
            .setExpiration(Date.valueOf(LocalDate.now().plusDays(Integer.parseInt(jwtConfig.getTokenExpirationAfterDays()))))
            .signWith(secretKey)
            .compact();
        return jwtConfig.getTokenPrefix() + token;
    }

    public String generateToken(User user, JwtConfig jwtConfig, SecretKey secretKey){
        Set<Role> roles = user.getRoles();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for(Role role : roles){
            authorities.addAll(role.getAuthorities());
        }
        
        String token = Jwts.builder()
            .setSubject(user.getUsername())
            .claim("authorities", authorities)
            .setIssuedAt(Date.valueOf(LocalDate.now()))
            .setExpiration(Date.valueOf(LocalDate.now().plusDays(Integer.parseInt(jwtConfig.getTokenExpirationAfterDays()))))
            .signWith(secretKey)
            .compact();
            
        return jwtConfig.getTokenPrefix() + token;
    }

}
