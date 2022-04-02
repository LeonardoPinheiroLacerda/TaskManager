package com.leonardo.taskmanager.security.users;

import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class AppUserDetailsService implements UserDetailsService{
    
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("This username was not found."));

        return new AppUserDetails(user);
    }

}
