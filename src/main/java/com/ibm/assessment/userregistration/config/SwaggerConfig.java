package com.ibm.assessment.userregistration.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI userRegistrationOpenApi(@Value("@project.version@") String appVersion) {
        return new OpenAPI().info(new Info().title("User Registration API Doc")
                .description("The API Documentation for user registration Rest Service")
                .version(appVersion));
    }
}
