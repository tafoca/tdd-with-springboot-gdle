package com.demo.tdd.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TacheRepositoryTest {

    @Autowired
    private TacheRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByName_returnsTache() throws Exception {
        Tache savedTache = entityManager.persistFlushFind(new Tache("tache0", "impression"));
        System.out.println("------>  "+savedTache.toString());
        Tache Tache = repository.findByName("tache0");
        System.out.println("------>  "+Tache.toString());
        Assertions.assertThat(Tache.getName()).isEqualTo(savedTache.getName());
        Assertions.assertThat(Tache.getType()).isEqualTo(savedTache.getType());
    }

}