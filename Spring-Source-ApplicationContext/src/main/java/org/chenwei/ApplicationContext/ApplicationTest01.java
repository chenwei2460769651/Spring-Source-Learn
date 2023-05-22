package org.chenwei.ApplicationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ApplicationTest01 {

    public static void main(String[] args) {
//        textClassPathXmlApplicationContext();
//        textFileSystemXmlApplicationContext();


        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("ApplicationTest01.xml"));

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }


    //基于classpath下xml格式的配置文件来创建
    private static void textClassPathXmlApplicationContext(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationTest01.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }


        System.out.println(context.getBean(Bean3.class).getBean2().getBean1());
    }

    //基于磁盘下xml格式的配置文件来创建
    private static void textFileSystemXmlApplicationContext(){
        FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext("C:\\softWare\\devSourceCode\\java\\Spring-Source-Learn\\Spring-Source-ApplicationContext\\src\\main\\resources\\ApplicationTest01.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }


        System.out.println(context.getBean(Bean3.class).getBean2().getBean1());
    }
    //基于java配置类来创建
    private static void textAnnotationConfigXmlApplicationContext(){

    }
    //基于磁盘下xml格式的配置文件来创建
    private static void textFileSystemServletWevServerApplicationContext(){



    }


    static class Bean1 {
        protected final Log logger = LogFactory.getLog(this.getClass());
        public Bean1() {
            logger.debug("create Bean2~~~");
        }
    }
    static class Bean2 {
        private Bean1 bean1;
        protected final Log logger = LogFactory.getLog(this.getClass());
        public Bean2() {
            logger.debug("create Bean2~~~");
        }

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }


    }
    static class Bean3 {
        private Bean2 bean2;
        protected final Log logger = LogFactory.getLog(this.getClass());
        public Bean3() {
            logger.debug("create Bean2~~~");
        }

        public Bean2 getBean2() {
            return bean2;
        }

        public void setBean2(Bean2 bean2) {
            this.bean2 = bean2;
        }
    }
}
