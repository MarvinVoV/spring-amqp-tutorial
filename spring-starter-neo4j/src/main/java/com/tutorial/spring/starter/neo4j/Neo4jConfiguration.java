package com.tutorial.spring.starter.neo4j;

import com.tutorial.spring.starter.neo4j.model.Person;
import com.tutorial.spring.starter.neo4j.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author marvin
 * @version Neo4jConfiguration.java, v 0.1 2022/11/06 17:30 Exp $
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.tutorial.spring.starter.neo4j.repository")
public class Neo4jConfiguration {
    private final static Logger log = LoggerFactory.getLogger(Neo4jConfiguration.class);

    @Bean
    CommandLineRunner demo(PersonRepository personRepository) {
        return args -> {

            personRepository.deleteAll();

            Person greg = new Person("Greg");
            Person roy = new Person("Roy");
            Person craig = new Person("Craig");

            List<Person> team = Arrays.asList(greg, roy, craig);

            log.info("Before linking up with Neo4j...");

            team.forEach(person -> log.info("\t" + person.toString()));

            personRepository.save(greg);
            personRepository.save(roy);
            personRepository.save(craig);

            greg = personRepository.findByName(greg.getName());
            greg.worksWith(roy);
            greg.worksWith(craig);
            personRepository.save(greg);

            roy = personRepository.findByName(roy.getName());
            roy.worksWith(craig);
            // We already know that roy works with greg
            personRepository.save(roy);

            // We already know craig works with roy and greg

            log.info("Lookup each person by name...");
            team.forEach(person -> log.info(
                    "\t" + personRepository.findByName(person.getName()).toString()));

            List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
            log.info("The following have Greg as a teammate...");
            teammates.forEach(person -> log.info("\t" + person.getName()));

            Person findRoy = personRepository.find("Roy");
            log.info("Result is " + findRoy.getName());
        };
    }
}
