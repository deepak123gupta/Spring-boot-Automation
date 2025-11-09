package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
private final PersonService service;
public PersonController(PersonService service) { this.service = service; }

@PostMapping public Person create(@RequestBody Person person) { return service.save(person); }
@GetMapping public List<Person> getAll() { return service.getAll(); }
@GetMapping("/{id}") public Person getById(@PathVariable Long id) { return service.getById(id); }
@PutMapping("/{id}") public Person update(@PathVariable Long id, @RequestBody Person person) { return service.update(id, person); }
@DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}
