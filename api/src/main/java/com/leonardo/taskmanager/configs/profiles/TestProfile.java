package com.leonardo.taskmanager.configs.profiles;

import com.leonardo.taskmanager.services.MockService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestProfile implements CommandLineRunner{

    private final MockService mockService;

    public TestProfile(MockService mockService){
        this.mockService = mockService;
    }

    @Override
    public void run(String... args) throws Exception {
        mockService.mock();
    }

}
