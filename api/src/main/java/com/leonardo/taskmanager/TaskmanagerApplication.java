package com.leonardo.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

}
