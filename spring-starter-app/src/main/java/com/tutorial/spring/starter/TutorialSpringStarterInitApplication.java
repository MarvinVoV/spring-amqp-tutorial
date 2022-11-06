package com.tutorial.spring.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author marvin
 * @version TutorialSpringStarterInitApplication.java, v 0.1 2022/11/06 16:37 Exp $
 */

@SpringBootApplication(scanBasePackages = "com.tutorial.spring.starter")
@PropertySource({
        "classpath:application.properties"
})
public class TutorialSpringStarterInitApplication {
    public static void main(String[] args) {
        SpringApplication.run(TutorialSpringStarterInitApplication.class, args);
    }
}
