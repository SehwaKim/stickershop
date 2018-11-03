package com.boot.stickershop.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mail.properties")
@ConfigurationProperties(prefix = "mail")
@Setter
@Getter
public class MailConfig {
    private String ip;
    private int port;
}
