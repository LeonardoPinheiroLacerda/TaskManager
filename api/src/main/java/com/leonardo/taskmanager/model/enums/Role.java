package com.leonardo.taskmanager.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Role {
    
    COMMON (
		Arrays.asList(
			Authority.ANY_READ,
			Authority.CLASSIFICATION_READ,
			Authority.CLIENT_READ,
			Authority.USER_READ,
			Authority.TEAM_READ
		)
	),
    ADMIN (
		Arrays.asList(
			Authority.ANY_READ, 
			Authority.ANY_WRITE,
			Authority.CLASSIFICATION_READ,
			Authority.CLASSIFICATION_WRITE,
			Authority.CLIENT_READ,
			Authority.CLIENT_WRITE,
			Authority.USER_READ,
			Authority.USER_WRITE,
			Authority.TEAM_READ,
			Authority.TEAM_WRITE
		)
	);

    private final List<Authority> authorities;

    public Set<SimpleGrantedAuthority> getAuthorities(){
		
		Set<SimpleGrantedAuthority> set = authorities
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
				.collect(Collectors.toSet());
		
		set.add(new SimpleGrantedAuthority("ROLE_" + name()));
		
		return set;
		
	}

}
