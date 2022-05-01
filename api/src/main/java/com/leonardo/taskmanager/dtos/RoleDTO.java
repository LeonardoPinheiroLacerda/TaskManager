package com.leonardo.taskmanager.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.leonardo.taskmanager.model.enums.Role;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@ApiModel("Role")
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private Role role;

}
