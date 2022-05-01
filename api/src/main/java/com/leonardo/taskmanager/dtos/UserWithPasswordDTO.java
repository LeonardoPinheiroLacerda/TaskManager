package com.leonardo.taskmanager.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@ApiModel("User with password")
public class UserWithPasswordDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(hidden = true)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank
    private String telephoneNumber;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
    // private Set<Team> leaderOf;
    // private Set<Team> teams;
    // private Set<DetailedHour> detailedHours;
    // private Set<Backlog> backlogs;
    // private Set<BacklogNotification> notifications;

}
