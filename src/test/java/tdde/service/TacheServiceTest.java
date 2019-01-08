package tdde.service;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tdde.domain.Tache;
import tdde.domain.TacheRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TacheServiceTest {


    @Mock
    private TacheRepository TacheRepository;

    private TacheService TacheService;

    @Before
    public void setUp() throws Exception {
        TacheService = new TacheService(TacheRepository);
    }

    @Test
    public void getTacheDetails_returnsTacheInfo() {
        given(TacheRepository.findByName("tache0")).willReturn(new Tache("tache0", "impression"));

        Tache Tache = TacheService.getTacheDetails("tache0");

        assertThat(Tache.getName()).isEqualTo("tache0");
        assertThat(Tache.getType()).isEqualTo("impression");
    }

    @Test(expected = TacheNotFoundException.class)
    public void getTacheDetails_whenTacheNotFound() throws Exception {
        given(TacheRepository.findByName("tache0")).willReturn(null);

        TacheService.getTacheDetails("tache0");
    }
}