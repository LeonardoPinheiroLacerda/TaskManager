package com.leonardo.taskmanager.security;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final Environment env;

    public SecurityConfig(Environment env){
        this.env = env;
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			
            //Permite acesso ao banco de dados H2 no profile test
            http
                .headers()
                .frameOptions()
                .disable();

            http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll();
		}
        if(Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			
            //Permite o acesso do Spring remote no profile dev
            http
                .authorizeRequests()
                .antMatchers("/.~~spring-boot!~/**").permitAll();
		}

        http.csrf().disable();

        http.authorizeRequests()           
            .anyRequest().authenticated()
            .and()
            .formLogin();    

	}

}
