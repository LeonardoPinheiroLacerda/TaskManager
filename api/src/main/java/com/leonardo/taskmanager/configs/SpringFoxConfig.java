package com.leonardo.taskmanager.configs;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    
    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.leonardo.taskmanager"))
            .paths(PathSelectors.ant("/api/v1/**"))
            .build()
            .apiInfo(apiDetails())
            .useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, Arrays.asList(success(), badRequest(), unauthorized(), forbidden(), notFound()))
            .globalResponses(HttpMethod.POST, Arrays.asList(created(), badRequest(), unauthorized(), forbidden()))
            .globalResponses(HttpMethod.PUT, Arrays.asList(success(), badRequest(), unauthorized(), forbidden(), notFound()))
            .globalResponses(HttpMethod.DELETE, Arrays.asList(noContent(), badRequest(), unauthorized(), forbidden(), notFound()));
    }

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

    public Response success(){
        return new ResponseBuilder()
            .code("200")
            .description("Success")
            .build();
    }

    public Response created(){
        return new ResponseBuilder()
            .code("201")
            .description("Created")
            .build();
    }

    public Response noContent(){
        return new ResponseBuilder()
            .code("204")
            .description("No Content")
            .build();
    }

    public Response badRequest(){
        return new ResponseBuilder()
            .code("400")
            .description("Bad Request")
            .build();
    }

    public Response unauthorized(){
        return new ResponseBuilder()
            .code("401")
            .description("Unauthorized")
            .build();
    }

    public Response forbidden(){
        return new ResponseBuilder()
            .code("403")
            .description("Forbidden")
            .build();
    }

    public Response notFound(){
        return new ResponseBuilder()
            .code("404")
            .description("Not Found")
            .build();
    }

}
