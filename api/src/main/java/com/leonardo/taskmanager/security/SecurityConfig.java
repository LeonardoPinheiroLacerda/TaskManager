package com.leonardo.taskmanager.security;

import java.util.Arrays;

import javax.crypto.SecretKey;

import com.leonardo.taskmanager.repositories.UserRepository;
import com.leonardo.taskmanager.security.configs.JwtConfig;
import com.leonardo.taskmanager.security.jwt.JwtUtil;
import com.leonardo.taskmanager.security.jwt.filters.AppUsernamePasswordAuthenticationFilter;
import com.leonardo.taskmanager.security.jwt.filters.TokenVerifierFilter;
import com.leonardo.taskmanager.security.users.AppUserDetailsService;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final Environment env;

    private final JwtConfig jwtConfig;
    private final JwtUtil jwtUtil;
    private final SecretKey secretKey;
    private final UserRepository userRepository;

    private static final String[] SWAGGER_AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        // -- Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**",
        "/swagger-ui/**"
    };

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

        http.csrf().disable()
        
        .addFilter(passwordUsernameAuthenticationFilter())
        .addFilterAfter(new TokenVerifierFilter(jwtConfig, secretKey, userDetailsService()), AppUsernamePasswordAuthenticationFilter.class)


        .authorizeRequests()
        
        .antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
        .anyRequest().authenticated();

	}

    @Override
    protected UserDetailsService userDetailsService() {
        return new AppUserDetailsService(userRepository);
    }

    //Filtro de autenticação com url padrão modificada
    public AppUsernamePasswordAuthenticationFilter passwordUsernameAuthenticationFilter() throws Exception{
        AppUsernamePasswordAuthenticationFilter filter = new AppUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig, jwtUtil, secretKey);
        filter.setFilterProcessesUrl("/api/v1/auth/login");
        return filter;
    }

}
