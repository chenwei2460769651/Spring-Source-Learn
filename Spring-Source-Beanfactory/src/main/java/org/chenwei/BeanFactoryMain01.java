package org.chenwei;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Objects;

public class BeanFactoryMain01 {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //bean的定义(class、scope、初始化、销毁)

        //Bean 定义生成器
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Config.class);
        //设置此定义的范围。单例or多例  singleton or prototype
        beanDefinitionBuilder.setScope("singleton");

        //获取bean定义对象
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //注册bean Config类下@bean不会生效
        beanFactory.registerBeanDefinition("config", beanDefinition);


        //给 beanFactory添加一些常用的后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);


        //执行beanFactory后置处理器  执行到他internalConfigurationAnnotationProcessor 就会加载Configuration注解下类里面的@bean 此时bean里面的@autowire注解不会生效 因为bean后置处理器没生效
        System.out.println("加载BeanFactory后置处理器");
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
            System.out.println("beanFactoryPostProcessor="+beanFactoryPostProcessor);
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });
        System.out.println();
        //Bean后置处理器，针对bean的生命周期的各个阶段提供扩展，例如@Autowire @resource ...
        System.out.println("加载Bean后置处理器");
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream()
                .sorted(beanFactory.getDependencyComparator())
                .forEach(beanPostProcessor -> {
            System.out.println(beanPostProcessor);
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        });
        System.out.println();
        //取消懒加载
        beanFactory.preInstantiateSingletons();//准备好所有单例

/*        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println("name=" + name);
        }*/

        Bean1 bean = beanFactory.getBean(Bean1.class);
        System.out.println(bean.getBean2());


        System.out.println(bean.getInter());
        /*
            a.BeanFactory 不会做的事
                1.不会主动调用BeanFactory后处理器
                2.不会主动添加Bean后处理器
                3.不会主动初始化单例对象
                4.不会解析beanFactory 不会解析#{}和${}
            B.bean后处理器有排序的逻辑

            @Autowire注解会获取这个类型的所有bean,但是名称对应的上bean 那么可以使用 否则报错 ,例如下面的Inter接口
            @Autowire注解相比@resource优先级更高  都具有getOrder方法 CommonAnnotationProcessor后置处理器优先级比ConfigurationAnnotationProcessor后置处理器高2
            CommonAnnotationProcessor order:Integer.MAXVALUE;
            ConfigurationAnnotationProcessor order:Integer.MAXVALUE-2;
         */
    }


    @Configuration
    static class Config {

        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }

        @Bean
        public Bean4 bean4() {
            return new Bean4();
        }
    }


    static class Bean1 {
        protected final Log logger = LogFactory.getLog(this.getClass());

        public Bean1() {
            logger.debug("create Bean1~~~");
        }

        @Autowired
        private Bean2 bean2;

        @Autowired
        @Resource(name = "bean4")
        Inter bean3;
        public Inter getInter(){
            return bean3;
        }

        public Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
        protected final Log logger = LogFactory.getLog(this.getClass());


        public Bean2() {
            logger.debug("create Bean2~~~");
        }
    }

    interface Inter {
        void create();
    }

    static class Bean3 implements Inter {
        protected final Log logger = LogFactory.getLog(this.getClass());

        public Bean3() {
            create();
        }

        @Override
        public void create() {
            System.out.println("create Bean3........");

        }
    }

    static class Bean4 implements Inter {
        protected final Log logger = LogFactory.getLog(this.getClass());

        public Bean4() {
            create();
        }

        @Override
        public void create() {
            System.out.println("create Bean4........");
        }
    }
}
