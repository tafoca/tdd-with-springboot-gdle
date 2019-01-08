package tdde.domain;


import org.springframework.data.repository.CrudRepository;

public interface TacheRepository extends CrudRepository<Tache, Long> {

    Tache findByName(String name);

    @Override
    <S extends Tache> S save(S entity);
}
