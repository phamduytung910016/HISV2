package com.ibme.pacs.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*")//xác định URi
                .allowedOrigins("*")//chỉ định các domain, * là tất cả
                .allowedMethods("PUT", "DELETE", "GET", "POST")//chỉ định các method, *là tất cả
                .allowedHeaders("*");//chỉ định các header cho phép

    }

}
