package com.leonardo.taskmanager.security.jwt;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.taskmanager.exceptions.dto.StandardError;
import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.security.configs.JwtConfig;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
    
    public String generateToken(Authentication authResult, JwtConfig jwtConfig, SecretKey secretKey){
        String token = Jwts.builder()
            .setSubject(authResult.getName())
            .claim("authorities", getRoles(authResult))
            .setIssuedAt(Date.valueOf(LocalDate.now()))
            .setExpiration(Date.valueOf(LocalDate.now().plusDays(Integer.parseInt(jwtConfig.getTokenExpirationAfterDays()))))
            .signWith(secretKey)
            .compact();
        return jwtConfig.getTokenPrefix() + token;
    }

    public String generateToken(User user, JwtConfig jwtConfig, SecretKey secretKey){       
        String token = Jwts.builder()
            .setSubject(user.getUsername())
            .claim("authorities", getRoles(user))
            .setIssuedAt(Date.valueOf(LocalDate.now()))
            .setExpiration(Date.valueOf(LocalDate.now().plusDays(Integer.parseInt(jwtConfig.getTokenExpirationAfterDays()))))
            .signWith(secretKey)
            .compact();
            
        return jwtConfig.getTokenPrefix() + token;
    }

    public HttpServletResponse sendErrorMessage(HttpServletResponse response, HttpServletRequest request,  Exception e){
        try{
            response.getWriter().print(
                new ObjectMapper().writeValueAsString(new StandardError(
                    System.currentTimeMillis(),
                    HttpStatus.UNAUTHORIZED.value(),
                    "Token invalido.",
                    e.getMessage(),
                    request.getRequestURI()
                ))
            );

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
        }catch(IOException ex){}
        
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return response;
    }
    
    private Set<GrantedAuthority> getRoles(Authentication authentication){

        Set<GrantedAuthority> roles = authentication.getAuthorities()
            .stream()
            .filter(role -> role.getAuthority().startsWith("ROLE_"))
            .collect(Collectors.toSet());

        return roles;
    }

    private Set<GrantedAuthority> getRoles(User user){

        Set<GrantedAuthority> roles = user.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
            .collect(Collectors.toSet());

        return roles;
    }

}
