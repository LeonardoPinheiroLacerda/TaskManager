package com.leonardo.taskmanager.dtos;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@ApiModel("Password")
public class PasswordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;

    @AssertTrue(message = "O campo de confirmação de senha deve conter um valor igual ao campo senha.")
    public boolean isPasswordConfirmated(){
        return this.password.equals(this.passwordConfirmation);
    }
}
