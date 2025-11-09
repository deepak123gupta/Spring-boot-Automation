package com.example.demo.service.impl;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@Service
public class PersonServiceImpl implements PersonService {
private f
inal PersonRepository repository;

public PersonServiceImpl(PersonRepository repository) {
this.repository = repository;
}

@Override public Person save(Person person) { return repository.save(person); }
@Override public List<Person> getAll() { return repository.findAll(); }
@Override public Person getById(Long id) { return repository.findById(id).orElse(null); }
@Override public Person update(Long id, Person person) { person.setId(id); return repository.save(person); }
@Override public void delete(Long id) { repository.deleteById(id); }
}
