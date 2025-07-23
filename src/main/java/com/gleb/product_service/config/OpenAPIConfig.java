package com.gleb.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceApi() {
        return new OpenAPI()
                .info(new Info().title("Product Service Api")
                        .description("Rest Api description")
                        .version("0.0.1v")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("wiki link")
                        .url("https://www.google.com/"));
    }
}
