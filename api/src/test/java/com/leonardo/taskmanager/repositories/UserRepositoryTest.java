package com.leonardo.taskmanager.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import com.leonardo.taskmanager.model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    public void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void shouldReturnUserWhenSearchForAExistentUsername(){
        //given
        String username = "leonardo";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);

        User user = new User(
            "Leonardo", 
            "Lacerda", 
            "leon.lacerda2015@gmail.com", 
            "11953248804", 
            username, 
            passwordEncoder.encode("senha123")
        );
        underTest.save(user);
        
        //when
        Optional<User> obj = underTest.findByUsername(username);
        User result = obj.get();

        //then
        String expected = username;
        assertThat(result.getUsername()).isEqualTo(expected);

    }

    @Test
    void shouldntReturnAnUserWhenSearchForANonexistentUsername(){
        //given
        String username = "leonardo";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);

        User user = new User(
            "Leonardo", 
            "Lacerda", 
            "leon.lacerda2015@gmail.com", 
            "11953248804", 
            username, 
            passwordEncoder.encode("senha123")
        );
        underTest.save(user);
        
        //when
        Optional<User> obj = underTest.findByUsername(username);
        User result = obj.get();

        //then
        String expected = username + "a";
        assertThat(result.getUsername()).isNotEqualTo(expected);

    }

}
