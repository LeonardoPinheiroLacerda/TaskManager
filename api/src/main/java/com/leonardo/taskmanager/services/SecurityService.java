package com.leonardo.taskmanager.services;

import java.util.Optional;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;

import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.security.configs.JwtConfig;
import com.leonardo.taskmanager.security.jwt.JwtUtil;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class SecurityService {
    
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
       
    public Optional<User> getAuthenticatedUser(){
        Optional<User> user = Optional.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return user;
    }

    public void refreshToken(HttpServletResponse response){
        response.addHeader(jwtConfig.getAuthorizationHeaderName(), jwtUtil.generateToken(getAuthenticatedUser().get(), jwtConfig, secretKey));
        response.setStatus(200);
    }

}