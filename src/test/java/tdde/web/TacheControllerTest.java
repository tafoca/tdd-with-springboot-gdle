package tdde.web;

import tdde.domain.Tache;
import tdde.service.TacheNotFoundException;
import tdde.service.TacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TacheController.class)
public class TacheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TacheService service;

    @Test
    public void getTache_ReturnsTacheDetails() throws Exception {
        given(service.getTacheDetails(anyString())).willReturn(new Tache("tache0", "impression"));

        mockMvc.perform(get("/Taches/tache0")).andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tache0"))
                .andExpect(jsonPath("type").value("impression"));
    }

    @Test
    public void getTache_notFound() throws Exception {

        given(service.getTacheDetails(anyString())).willThrow(new TacheNotFoundException());

        mockMvc.perform(get("/Taches/tache")).andExpect(status().isNotFound());


    }


}
