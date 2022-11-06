package com.tutorial.spring.starter.neo4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractNeo4jConfig;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author marvin
 * @version WithOutSpringBootTest.java, v 0.1 2022/11/06 20:47 Exp $
 */
@ExtendWith(SpringExtension.class)
public class WithOutSpringBootTest {
    @Test
    void thingsShouldWork(@Autowired Neo4jTemplate neo4jTemplate) {
        // Add your test
    }

    @Configuration
    @EnableNeo4jRepositories(considerNestedRepositories = true)
    @EnableTransactionManagement
    static class Config extends AbstractNeo4jConfig {

        @Bean
        public Driver driver() {
            // todo Should replace token
            return GraphDatabase.driver("bolt://yourtestserver:7687", AuthTokens.none());
        }
    }
}
