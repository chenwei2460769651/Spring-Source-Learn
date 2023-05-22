package org.chenwei;

import org.chenwei.learn.common.Common01;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
public class SpringSourceLearnApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringSourceLearnApplication.class, args);

        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        //获取bean Get the Spring container bean
        map.entrySet().stream().filter(e -> e.getKey().startsWith("StudentBean")).forEach(e -> {
            System.out.println(e.getKey() + "=" + e.getValue());
        });

        //获取目录下spring配置文件 Obtain the spring configuration file in the directory
        Resource[] resources = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
        //获取配置信息 Get environment variable configuration information or properties configuration information
        ConfigurableEnvironment environment = context.getEnvironment();
        System.out.println(environment.getProperty("java_home"));
        System.out.println(environment.getProperty("spring.application.name"));
        System.out.println(environment.getProperty("server.port"));


//        context.publishEvent(new UserRegisterEventPublish(context));


        Common01 common01Bean = context.getBean(Common01.class);
        common01Bean.register();

    }

}
