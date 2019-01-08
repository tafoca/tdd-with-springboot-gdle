package tdde.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
        Tache Tache = repository.findByName("tache0");
        Assertions.assertThat(Tache.getName()).isEqualTo(savedTache.getName());
        Assertions.assertThat(Tache.getType()).isEqualTo(savedTache.getType());
    }

}