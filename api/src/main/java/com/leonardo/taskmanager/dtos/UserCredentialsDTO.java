package com.leonardo.taskmanager.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@ApiModel(value = "UserCredentials", description = "Objeto esperado no corpo no método de login")
public class UserCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
