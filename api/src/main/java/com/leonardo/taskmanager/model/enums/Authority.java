package com.leonardo.taskmanager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Authority {
    
    READ ("any:read"),
    WRITE ("any:write");

    private final String authority;

}
