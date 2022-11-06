package com.marvin.spring.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringTutorialApplication {
	@Autowired
	private MyComponent myComponent;

	public static void main(String[] args) {
		SpringApplication.run(SpringTutorialApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		myComponent.sayHi();
		return String.format("Hello %s!", name);
	}

}
