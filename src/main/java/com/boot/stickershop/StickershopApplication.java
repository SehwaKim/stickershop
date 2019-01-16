package com.boot.stickershop;

import com.boot.stickershop.util.StickerShopStartEventListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class StickershopApplication implements WebMvcConfigurer {

	// java -java java파일명 어프리케이션아규먼트 ....
	public static void main(String[] args) {
//		SpringApplication.run(StickershopApplication.class, args);
		SpringApplication springApplication =
				new SpringApplication(StickershopApplication.class);
//		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.addListeners(new StickerShopStartEventListener());
		springApplication.run(args);
	}


	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).modules(new JavaTimeModule()).build();

		mapper.registerModule(new Hibernate5Module());
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		converter.setPrettyPrint(true);

		converters.add(converter);
	}

}
