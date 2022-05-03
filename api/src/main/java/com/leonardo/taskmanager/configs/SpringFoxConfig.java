package com.leonardo.taskmanager.configs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.classmate.TypeResolver;
import com.leonardo.taskmanager.exceptions.dto.StandardError;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import lombok.AllArgsConstructor;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@AllArgsConstructor

@Configuration
public class SpringFoxConfig {
    
    private final TypeResolver typeResolver;

    @Qualifier("success")
    private final Response success;

    @Qualifier("created")
    private final Response created;

    @Qualifier("noContent")
    private final Response noContent;

    @Qualifier("badRequest")
    private final Response badRequest;

    @Qualifier("unauthorized")
    private final Response unauthorized;

    @Qualifier("forbidden")
    private final Response forbidden;

    @Qualifier("notFound")
    private final Response notFound;


    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.leonardo.taskmanager"))
            .paths(PathSelectors.ant("/api/v1/**"))
            .build()

            .additionalModels(typeResolver.resolve(StandardError.class))

            .apiInfo(apiDetails())
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))

            .useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, Arrays.asList(success, badRequest, unauthorized, forbidden, notFound))
            .globalResponses(HttpMethod.POST, Arrays.asList(created, badRequest, unauthorized, forbidden))
            .globalResponses(HttpMethod.PUT, Arrays.asList(success, badRequest, unauthorized, forbidden, notFound))
            .globalResponses(HttpMethod.DELETE, Arrays.asList(noContent, badRequest, unauthorized, forbidden, notFound));
    }

    
    //Configuração do botão authorize do swagger
    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
    } 
    
    private List<SecurityReference> defaultAuth() { 
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
        authorizationScopes[0] = authorizationScope; 
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
    }

    //Detalhes da api
    private ApiInfo apiDetails(){
        return new ApiInfo(
            /*Title*/               "Task Manager", 
            /*Description*/         "Back-end da aplicação task manager, essa aplicação tem como objetivo ser um gerenciador de tarefas para equipes direcionado para equipes.", 
            /*Version*/             "0.0.1-SNAPSHOT", 
            /*TermsOfServiceUrl*/   null, 
            /*Contact*/             new Contact("Leonardo Pinheiro Lacerda", "leonardopinheirolacerda.github.io", "leon.lacerda2015@gmail.com"),
            /*License*/             null, 
            /*LicenseUrl*/          null, 
            /*VendorExtensions*/    Collections.emptyList());
    }


}
