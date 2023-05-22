package org.chenwei.learn.common;


import org.chenwei.learn.ApplicationContextLearn.UserRegisterEvent.UserRegisterEventPublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Common02 {

    private static final Logger log= LoggerFactory.getLogger(Common02.class);

    @EventListener
    public void Common02Listener(UserRegisterEventPublish event){
        log.info("{}",event);

        log.info("send Message");
    }


}
