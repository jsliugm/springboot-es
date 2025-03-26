package com.universe.service;

import com.universe.domain.Person;
import com.universe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public boolean deleteIndex() {
        return elasticsearchTemplate.deleteIndex(Person.class);
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }

    public Person findPersonById(String id) {
        return personRepository.findById(id).orElse(null);
    }

    public Iterable<Person> findAllPersons() {
        return personRepository.findAll();
    }


    public Iterable<Person> findByName(String name) {
        return personRepository.findByName(name);
    }


    public Iterable<Person> findByAddress(String Address) {
        return personRepository.findByAddress(Address);
    }

    public Page<Person> findByName(String name, int page, int pageSize) {
        return personRepository.findByName(name, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "score")));
    }

}