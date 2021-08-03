package com.happyhouse.server.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/html/");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내서버가 응답을 할 때, json을 자바스크립트에서 처리할 수 있게 할지 설정한다는 의미
        config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용하겠다는 의미
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용하겠다는 의미
        config.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청을 허용하겠다는 의미

        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/management/**", config);
        source.registerCorsConfiguration("/v2/api-docs", config);
        source.registerCorsConfiguration("/v3/api-docs", config);
        source.registerCorsConfiguration("/swagger-resources", config);
        source.registerCorsConfiguration("/swagger-ui/**", config);

        return new CorsFilter(source);
    }

}
