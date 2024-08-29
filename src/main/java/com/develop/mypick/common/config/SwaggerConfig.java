package com.develop.mypick.common.config;

import static org.springframework.security.config.Elements.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(components())
                .addSecurityItem(securityRequirement())
                .info(new Info().title("MyPick API 명세서")
                        .version("v0.0.1")
                        .description("식단관리 애플리케이션입니다."));
    }

    private SecurityRequirement securityRequirement() {
        return new SecurityRequirement().addList(JWT);
    }


    private Components components() {
        return new Components().addSecuritySchemes(JWT, securityScheme());
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name(JWT)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat(JWT);
    }
}
