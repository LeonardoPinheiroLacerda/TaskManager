package com.leonardo.taskmanager.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leonardo.taskmanager.dtos.UserCredentialsDTO;
import com.leonardo.taskmanager.services.SecurityService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {
    
    private final SecurityService securityService;
    
    @GetMapping(value = "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        securityService.refreshToken(response);
    }

    @PostMapping(value = "/login")
    public void fakeLogin(@RequestBody UserCredentialsDTO dto){
        // Método de login fake para documentação no swagger.
        // O comportamento do mesmo está configurado na classe AppUsernamePasswordAuthenticationFilter.
    }
}
