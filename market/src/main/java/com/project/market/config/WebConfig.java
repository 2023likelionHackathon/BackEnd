package com.project.market.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    // 클라이언트에서 local로 접근할 경우 호스트가 다르기 때문에
    // 클라이언트 측에서 프록시 서브를 이용해 서버의 localhost로 접근하는 것처럼 하는 방식도 있음.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://localhost:3000","http://54.180.53.205","http://localhost:5000") // 허용할 출처
                //.allowedMethods("GET", "POST") // 허용할 HTTP method
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}