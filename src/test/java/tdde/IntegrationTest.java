package tdde;

import org.junit.Before;
import tdde.domain.Tache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import tdde.service.TacheService;

import java.time.Clock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(SpringRunner.class)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TacheService tacheService;

    @Before
    public void setup() throws Exception {

        tacheService.addTache(new Tache("tache0","impression"));

    }



    @Test
    public void getTaches_returnsTacheDetails() throws Exception {
        //Arrange

        //Act
        ResponseEntity<Tache> response = restTemplate.getForEntity("/Taches/tache0", Tache.class);

        System.out.println(response.toString());
        //Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("tache0");
        assertThat(response.getBody().getType()).isEqualTo("impression");
    }

    @Test
    public void deleteTachestest() throws Exception {

        //Act
        ResponseEntity<Tache> response = restTemplate.getForEntity("/Taches/delete/tache0", Tache.class);

        System.out.println(response.toString());
        //Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("tache0");
        assertThat(response.getBody().getType()).isEqualTo("impression");
    }


}
