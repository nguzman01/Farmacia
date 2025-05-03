package com.example.Farmacia.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/// / esta en la prueba de trabajo

@Configuration
public class CorsConfig {

    @Bean(name = "customCorsConfigurer")
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        // Permitir solicitudes desde el emulador
                        .allowedOrigins("http://10.0.2.2:8081")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

}





