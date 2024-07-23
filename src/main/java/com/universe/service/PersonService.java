package com.universe.service;

import com.universe.domain.Person;
import com.universe.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
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
}