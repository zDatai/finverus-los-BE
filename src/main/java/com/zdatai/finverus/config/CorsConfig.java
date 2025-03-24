package com.zdatai.finverus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS")// add OPTIONS for allow pre-flight requests
                .allowedOriginPatterns("http://build.zdatai.com", "http://104.198.239.68",
                        "http://104.198.239.68:3000", "http://build.zdatai.com:3000","http://localhost",
                        "http://localhost:5173","http://localhost:5174", "http://localhost:8080", "http://localhost:8081");


    }
}
