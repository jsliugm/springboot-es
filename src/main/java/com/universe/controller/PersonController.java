package com.universe.controller;

import com.universe.domain.Person;
import com.universe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public Object findByAddress(@PathVariable String address) {
        return personService.findByAddress(address);
    }

    @GetMapping
    public Iterable<Person> findAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping("/deleteIndex")
    public Object deleteIndex() {
        return personService.deleteIndex();
    }

    @GetMapping("/search/page/{name}/{page}/{pageSize}")
    public Object findPagePersonByName(@PathVariable String name, @PathVariable int page, @PathVariable int pageSize) {
        return personService.findByName(name, page, pageSize);
    }

}