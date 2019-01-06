package com.demo.tdd.service;

import com.demo.tdd.domain.Tache;
import com.demo.tdd.domain.TacheRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TacheService {

    private TacheRepository TacheRepository;

    public TacheService(TacheRepository TacheRepository) {
        this.TacheRepository = TacheRepository;
    }

    @Cacheable("Taches")
    public Tache getTacheDetails(String name) {
        Tache Tache = TacheRepository.findByName(name);
        if(Tache == null) {
            throw new TacheNotFoundException();
        }
        return Tache;
    }
}
