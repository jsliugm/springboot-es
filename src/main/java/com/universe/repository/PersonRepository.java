package com.universe.repository;

import com.universe.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    Iterable<Person> findByName(String name);

    Iterable<Person> findByAddress(String address);

    Page<Person> findByName(String name, PageRequest pageRequest);
}