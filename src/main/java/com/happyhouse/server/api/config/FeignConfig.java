package com.happyhouse.server.api.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients(basePackages = "com.happyhouse")
@Import(FeignClientsConfiguration.class)
public class FeignConfig {

    @Bean
    public AuthInterceptor authFeign() {
        return new AuthInterceptor();
    }

    class AuthInterceptor implements RequestInterceptor {

        private static final String AUTHORIZATION_HEADER="Authorization";
        private static final String TOKEN_TYPE = "Bearer";

        @Override
        public void apply(RequestTemplate template) {

            template.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, "jtw_xxxxxxxxxxxxxxxxxxxxxx"));
        }
    }
}