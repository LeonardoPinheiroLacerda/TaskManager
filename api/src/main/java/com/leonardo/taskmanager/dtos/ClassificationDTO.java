package com.leonardo.taskmanager.dtos;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@ApiModel(description = "Descreve uma classificação.")
public class ClassificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Identificador único responsável de uma classificação.")
    private Integer id;

    @ApiModelProperty(notes = "Descrição da classificação.")
    private String classification;
    
}
