package com.tutorial.spring.starter.neo4j.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author marvin
 * @version Person.java, v 0.1 2022/11/06 16:44 Exp $
 */
@Node
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Person() {
        // Empty constructor required as of Neo4j API 2.0.5
    }


    public Person(String name) {
        this.name = name;
    }

    @Relationship(type = "TEAMMATE")
    public Set<Person> teammates;

    public void worksWith(Person person) {
        if (teammates == null) {
            teammates = new HashSet<>();
        }
        teammates.add(person);
    }

    public String toString() {
        return this.name + "'s teammates => "
                + Optional.ofNullable(this.teammates).orElse(
                        Collections.emptySet()).stream()
                .map(Person::getName).toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}