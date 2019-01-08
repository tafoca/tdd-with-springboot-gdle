package tdde;

import tdde.domain.Tache;
import tdde.domain.TacheRepository;
import tdde.service.TacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class CachingTest {

    @Autowired
    private TacheService service;

    @MockBean
    private TacheRepository TacheRepository;

    @Test
    public void caching() throws Exception {
        given(TacheRepository.findByName(anyString())).willReturn(new Tache("tache0", "impression"));

        service.getTacheDetails("tache0");
        service.getTacheDetails("tache0");

        verify(TacheRepository, times(1)).findByName("tache0");


    }

}
