package com.leonardo.taskmanager;

import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.model.enums.Role;
import com.leonardo.taskmanager.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@EnableWebMvc
@SpringBootApplication
public class TaskmanagerApplication implements CommandLineRunner{

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String username = "leonardo";

		User user = new User("Leonardo", "Lacerda'", "leon.lacerda2015@gmail.com", "11953248804", username, passwordEncoder.encode("senha123"));
		user.getRoles().add(Role.COMMON);

		if(!userRepository.findByUsername(username).isPresent()){
			userRepository.save(user);
		}

	}

}
