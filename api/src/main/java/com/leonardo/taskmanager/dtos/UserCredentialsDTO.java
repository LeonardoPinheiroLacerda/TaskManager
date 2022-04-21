package com.leonardo.taskmanager.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;

}
