package com.leonardo.taskmanager.security.jwt.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.model.enums.Role;
import com.leonardo.taskmanager.security.configs.JwtConfig;
import com.leonardo.taskmanager.security.jwt.JwtUtil;
import com.leonardo.taskmanager.security.users.AppUserDetails;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class TokenVerifierFilter extends OncePerRequestFilter{
 
    private final JwtConfig jwtConfig;
    private final JwtUtil jwtUtil;
    private final SecretKey secretKey;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        
        //Salva o header de autorização em uma variavel
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeaderName());
        
        //Verifica se o header de autorização está preenchido e se o prefixo está correto.
        if(authorizationHeader == null || authorizationHeader.equals("") || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
            filterChain.doFilter(request, response);
            return;
        }

        //Remove o prefixo mantendo apenas o token
        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix() , "");
        
        try{
            //Recebe o corpo do token JWS
            Claims body = getClaims(token).getBody();

            //Convente os dados do JWS em um objeto do tipo Authentication
            Authentication authentication = checkAuthenticationFromJwsBody(body);

            //Insere o objeto de autenticação no contexto de segurança do Spring
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Da continuidade ao processo de autorização
            filterChain.doFilter(request, response);
        }catch(JwtException e){
            jwtUtil.sendErrorMessage(response, request, e);
        }
    }
    

    protected Jws<Claims> getClaims(String token){
        //Extrai o body do token
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }

    protected Authentication checkAuthenticationFromJwsBody(Claims body){

        //Recebe o username presente no atributo subject
        String username = body.getSubject();

        //Recebe as autoridades do usuário e os salva em uma lista
        @SuppressWarnings("unchecked")
        List<Map<String, String>> roles = (List<Map<String, String>>) body.get("authorities");

        //Converte a lista de roles para um conjunto de SimpleGrantedAuthorities contendo os roles e authorities do token.       
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        roles.forEach(role -> {
            String roleName = role.get("authority").replace("ROLE_", "");
            simpleGrantedAuthorities.addAll(Role.valueOf(roleName).getAuthorities());
        });

        //Com base no username presente no JWS, o sistema busca o usuário correspondente no banco de dados.
        AppUserDetails userDetails = (AppUserDetails) userDetailsService.loadUserByUsername(username);
        User user = userDetails.getUser();

        //Constrói o objeto de autenticação com os dados extraídos a cima
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            user, 
            user.getPassword(),
            simpleGrantedAuthorities
        );

        return authentication;
    }

}
