package org.chenwei.learn.common;


import org.chenwei.learn.ApplicationContextLearn.UserRegisterEvent.UserRegisterEventPublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Common01 {
    private static final Logger log = LoggerFactory.getLogger(Common01.class);

    @Autowired
    private ApplicationEventPublisher context;

    //    @EventListener
    public void register() {
        log.info("user register~");

        //注册事件发生器
        context.publishEvent(new UserRegisterEventPublish(this));

    }

}
