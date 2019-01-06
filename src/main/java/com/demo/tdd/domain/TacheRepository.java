package com.demo.tdd.domain;


import org.springframework.data.repository.CrudRepository;

public interface TacheRepository extends CrudRepository<Tache, Long> {

    Tache findByName(String name);
}
