package com.universe.controller;

import com.universe.domain.Person;
import com.universe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
    }

    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable String id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/search/{name}")
    public Iterable<Person> findPersonByName(@PathVariable String name) {
        return personService.findByName(name);
    }

    @GetMapping("/address/{address}")
    public Iterable<Person> findByAddress(@PathVariable String address) {
        return personService.findByAddress(address);
    }

    @GetMapping
    public Iterable<Person> findAllPersons() {
        return personService.findAllPersons();
    }
}