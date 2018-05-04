package com.boot.stickershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class StickershopApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(StickershopApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
		/*if(!registry.hasMappingForPattern("/webjars/**")){
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:META-INF/resources/webjars/**");
        }*/
	}
}
