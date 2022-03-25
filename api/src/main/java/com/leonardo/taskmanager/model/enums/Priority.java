package com.leonardo.taskmanager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Priority {
    BAIXA   ("Baixa"    , "green"),
    MEDIA   ("Média"    , "yellow"),
    ALTA    ("Alta"     , "red"),
    URGENTE ("Urgente"  , "purple");

    private final String friendlyName;
    private final String color;

}
