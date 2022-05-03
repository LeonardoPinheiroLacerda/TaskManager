package com.leonardo.taskmanager.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.Response;

@Configuration
public class SpringFoxResponsesConfig {
    
    @Bean("success")
    public Response success(){
        return new ResponseBuilder()
            .code("200")
            .description("Success")
            .build();
    }

    @Bean("created")
    public Response created(){
        return new ResponseBuilder()
            .code("201")
            .description("Created")
            .build();
    }

    @Bean("noContent")
    public Response noContent(){
        return new ResponseBuilder()
            .code("204")
            .description("No Content")
            .build();
    }

    @Bean("badRequest")
    public Response badRequest(){
        return new ResponseBuilder()
            .code("400")
            .description("Bad Request")
            .build();
    }

    @Bean("unauthorized")
    public Response unauthorized(){
        return new ResponseBuilder()
            .code("401")
            .description("Unauthorized")
            .build();
    }

    @Bean("forbidden")
    public Response forbidden(){
        return new ResponseBuilder()
            .code("403")
            .description("Forbidden")
            .build();
    }

    @Bean("notFound")
    public Response notFound(){
        return new ResponseBuilder()
            .code("404")
            .description("Not Found")
            .build();
    }

}
