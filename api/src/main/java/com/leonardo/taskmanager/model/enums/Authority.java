package com.leonardo.taskmanager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Authority {
    
    ANY_READ ("any:read"),
    ANY_WRITE ("any:write"),
    
    CLASSIFICATION_READ("classification:read"),
    CLASSIFICATION_WRITE("classification:write"),

    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String authority;

}
