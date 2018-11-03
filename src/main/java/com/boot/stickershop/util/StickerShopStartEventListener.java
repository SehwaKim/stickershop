package com.boot.stickershop.util;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class StickerShopStartEventListener
        implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("------------------------");
        System.out.println("Application Ready");
        System.out.println("------------------------");
    }
}


/*

@Component
class MyBean{
    @PostConstruct
    public void post(){

    }

    @PreDestroy
    public void destroy(){

    }
}
 */