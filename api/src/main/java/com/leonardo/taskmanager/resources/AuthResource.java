package com.leonardo.taskmanager.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leonardo.taskmanager.services.SecurityService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {
    
    private final SecurityService securityService;

    @ApiImplicitParam(name = "Authorization", required = true, dataTypeClass = String.class, paramType = "header")  
    @GetMapping(value = "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        securityService.refreshToken(response);
    }

}
