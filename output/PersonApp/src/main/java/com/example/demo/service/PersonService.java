package com.example.demo.service;

import com.example.demo.entity.Person;
import java.util.List;

public interface PersonService {
Person save(Person person);
List<Person> getAll();
Person getById(Long id);
Person update(Long id, Person person);
void delete(Long id);
}