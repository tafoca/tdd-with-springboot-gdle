package tdde.service;

import tdde.domain.Tache;
import tdde.domain.TacheRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TacheService {

    private TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    @Cacheable("Taches")
    public Tache getTacheDetails(String name) {
        Tache Tache = tacheRepository.findByName(name);
        if(Tache == null) {
            throw new TacheNotFoundException();
        }
        return Tache;
    }

    public Tache addTache(Tache tache){
        return tacheRepository.save(tache);
    }

    public Tache delete(String name) {
        Tache tache = tacheRepository.findByName(name);
        tacheRepository.delete(tache);
        return tache;
    }
}
