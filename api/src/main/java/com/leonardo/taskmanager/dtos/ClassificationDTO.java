package com.leonardo.taskmanager.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@ApiModel("Classification")
public class ClassificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    private String classification;
    
}
