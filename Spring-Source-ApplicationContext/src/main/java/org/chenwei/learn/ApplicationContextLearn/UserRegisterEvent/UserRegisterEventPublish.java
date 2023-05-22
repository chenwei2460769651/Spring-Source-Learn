package org.chenwei.learn.ApplicationContextLearn.UserRegisterEvent;

import org.springframework.context.ApplicationEvent;

public class UserRegisterEventPublish extends ApplicationEvent {
    public UserRegisterEventPublish(Object source) {
        super(source);
    }
}
