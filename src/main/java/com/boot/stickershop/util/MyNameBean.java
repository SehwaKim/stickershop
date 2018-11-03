package com.boot.stickershop.util;

import com.boot.stickershop.config.MailConfig;
import examples.boot.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyNameBean {
    @Autowired
    Environment environment;

    @Value("${name}")
    private String name;

    @Autowired
    MailConfig mailConfig;

    @Autowired
    ServerInfo serverInfo;

    @PostConstruct
    public void printName(){
        System.out.println("-----------------");
        System.out.println("name : " + environment.getProperty("name"));
        System.out.println("name : " + name);
        System.out.println(mailConfig.getIp());
        System.out.println(mailConfig.getPort());
        System.out.println(serverInfo.getAddress());
        System.out.println(serverInfo.getPort());
        System.out.println("-----------------");
    }
}
