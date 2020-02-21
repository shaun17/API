package com.dfyz.provied.yiliutochain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component("com.dfyz.provied.yiliutochain")
public class YiliutochainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(YiliutochainApplication.class, args);
//        String[] beanDefinitionNames = run.getBeanDefinitionNames();
//        for(String name : beanDefinitionNames){
//            if(name.equals("common")){
//                Common com = (Common)run.getBean("common");
//                System.out.println(Common.PRIVATE_KEY);
//                System.out.println(Common.PROJECT_ID);
//                System.out.println(Common.SIGN
//                );
//            }
//        }
    }

}
