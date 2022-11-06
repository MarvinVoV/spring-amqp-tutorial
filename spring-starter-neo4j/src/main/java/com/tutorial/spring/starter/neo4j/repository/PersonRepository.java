package com.tutorial.spring.starter.neo4j.repository;

import com.tutorial.spring.starter.neo4j.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author marvin
 * @version PersonRepository.java, v 0.1 2022/11/06 16:46 Exp $
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);

    List<Person> findByTeammatesName(String name);

    @Query(value = "match (p:Person) where p.name= $name return p")
    Person find(String name);
}
