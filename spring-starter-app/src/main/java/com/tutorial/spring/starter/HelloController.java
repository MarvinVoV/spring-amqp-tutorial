package com.tutorial.spring.starter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin
 * @version HelloController.java, v 0.1 2022/11/06 16:24 Exp $
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
