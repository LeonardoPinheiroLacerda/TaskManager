package com.leonardo.taskmanager.security.jwt.filters;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.taskmanager.dtos.UserCredentialsDTO;
import com.leonardo.taskmanager.security.configs.JwtConfig;
import com.leonardo.taskmanager.security.jwt.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class AppUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final JwtUtil jwtUtil;
    private final SecretKey secretKey;

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {

        try {

            //Converte o body enviado pela requisição para um objeto do tipo AppUserCredentialsDTO
            UserCredentialsDTO authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserCredentialsDTO.class);

            //Cria um objeto de autenticação com o nome de usuário e senha enviados pelo usuário
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword());

            //Checa se os dados são válidos
            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Executado se a chamada do método attemptAuthentication receber um resultado positivo
    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response, 
        FilterChain chain,
        Authentication authResult) throws IOException, ServletException {
        
        //Cria o token
        String token = jwtUtil.generateToken(authResult, jwtConfig, secretKey);
        
        //Anexa o token nos headers da resposta
        response.addHeader(jwtConfig.getAuthorizationHeaderName(), token);
    }


    //Executado se a chamada do método attemptAuthentication receber um resultado negativo
    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request, 
        HttpServletResponse response,
        AuthenticationException failed) throws IOException, ServletException {

        jwtUtil.sendErrorMessage(response, request, failed);
    }

}
