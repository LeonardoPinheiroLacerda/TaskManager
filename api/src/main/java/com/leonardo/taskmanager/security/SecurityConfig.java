package com.leonardo.taskmanager.security;

import java.util.Arrays;
import java.util.Map;

import javax.crypto.SecretKey;

import com.leonardo.taskmanager.repositories.UserRepository;
import com.leonardo.taskmanager.security.configs.JwtConfig;
import com.leonardo.taskmanager.security.jwt.JwtUtil;
import com.leonardo.taskmanager.security.jwt.filters.AppUsernamePasswordAuthenticationFilter;
import com.leonardo.taskmanager.security.jwt.filters.TokenVerifierFilter;
import com.leonardo.taskmanager.security.users.AppUserDetailsService;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor

@Slf4j

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

    private static final String CLASSIFICATION_ROUTE = "/api/v1/classifications/**";
    private static final String CLASSIFICATION_AUTHORITY_PREFIX = "classification";

    private static final String CLIENT_ROUTE = "/api/v1/clients/**";
    private static final String CLIENT_AUTHORITY_PREFIX = "classification";

    private static final String USER_ROUTE = "/api/v1/users/**";
    private static final String USER_AUTHORITY_PREFIX = "user";

    private static final Map<String, String> ANT_MATCHERS = Map.ofEntries(
        Map.entry(CLASSIFICATION_ROUTE, CLASSIFICATION_AUTHORITY_PREFIX),
        Map.entry(CLIENT_ROUTE, CLIENT_AUTHORITY_PREFIX),
        Map.entry(USER_ROUTE, USER_AUTHORITY_PREFIX)
    );
    

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

        //Desative o CSRF e aplica os filtros de segurança
        http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        
        .addFilter(passwordUsernameAuthenticationFilter())
        .addFilterAfter(new TokenVerifierFilter(jwtConfig, jwtUtil, secretKey, userDetailsService()), AppUsernamePasswordAuthenticationFilter.class);

        //Autoriza as rotas utilizadas pelo swagger
        http.authorizeRequests()
        .antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()

        //Usuários deslogados conseguem criar novos usuários
        .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()

        //Um usuário logado consegue atualizar seus proprios dados
        .antMatchers(HttpMethod.PUT, "/api/v1/users").authenticated()
        .antMatchers(HttpMethod.PUT, "/api/v1/users/password").authenticated();

   
        //Aplica as autorizações individuais de cada rota
        ANT_MATCHERS.forEach((route, authorityPrefix) -> {
            try{
                http.authorizeRequests()
                
                .antMatchers(HttpMethod.GET, route).hasAuthority(authorityPrefix + ":read")
                .antMatchers(HttpMethod.POST, route).hasAuthority(authorityPrefix + ":write")
                .antMatchers(HttpMethod.PUT, route).hasAuthority(authorityPrefix + ":write")
                .antMatchers(HttpMethod.DELETE, route).hasAuthority(authorityPrefix + ":write");

                log.info("Authorities applied on the '" + route + "' path.");

            }catch(Exception e){
                log.error("Failed on apply authorization rules on " + route);
            }
        });

        //Filtra qualquer requisição que não foi aceita por nenhuma configuração acima
        http.authorizeRequests().anyRequest().authenticated();

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
