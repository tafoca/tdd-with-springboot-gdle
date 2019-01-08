package com.demo.tdd.service;

import com.demo.tdd.domain.Tache;
import com.demo.tdd.domain.TacheRepository;
import com.demo.tdd.domain.TacheResult;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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

    Logger logger = LoggerFactory.getLogger(TacheService.class);

    private static final String MANGA_SEARCH_URL="http://api.jikan.moe/search/manga/";

    @Autowired
    RestTemplate restTemplate;

    public  List<Tache> getTacheByTitle(String title) {

        return restTemplate.getForEntity(MANGA_SEARCH_URL+title, TacheResult.class).getBody().getTaches();

    }
}
