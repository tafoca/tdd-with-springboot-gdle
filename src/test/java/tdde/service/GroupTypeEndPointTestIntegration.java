package tdde.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import tdde.web.GroupTypeEndPoint;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
/*@WebMvcTest(GroupTypeEndPoint.class)
@ActiveProfiles("dev")*/
@SpringBootTest
public class GroupTypeEndPointTestIntegration {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;



    @Autowired
    private GroupTypeEndPoint groupTypeEndPoint;


    @Before
    public void setup()  throws Exception {
        this.mockMvc = standaloneSetup(this.groupTypeEndPoint).build();

    }

    static int nb = 1;

    @Test
    public void requestIsSuccessfullyProcessedWithAvailableGroupTypeList() throws Exception {
        mockMvc.perform(get("/groups").param("label","A").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.*.label", hasItem(is("A"))));
    }


    //test to verify method that get all object of same label
    @Test
    public void requestIsSuccessfullyProcessedWithAvailableGroupTypeList_pathVar() throws Exception {
        mockMvc.perform(get("/groupes/A").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.*.id").exists())
                .andExpect(jsonPath("$.*.label", hasItem(is("A"))));
    }
    //test to creation of an object group type entity
    @Test
    public void verifySaveGroupTypeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Type B\", \"label\" : \"A\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //utile pour status de creation: isCreated 201
                .andDo(print())
                .andExpect(jsonPath("$.body.id").exists())
                .andExpect(jsonPath("$.body.name").exists())
                .andExpect(jsonPath("$.body.label").exists())
                .andExpect(jsonPath("$.body.name").value("Type B"))
                .andExpect(jsonPath("$.body.label").value("A"))
        ;
    }

    @Test
    public void verifySaveGroupTypeTest_without_response_entity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/groupes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Type B\", \"label\" : \"A\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //utile pour status de creation: isCreated 201
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.label").exists())
                .andExpect(jsonPath("$.name").value("Type B"))
                .andExpect(jsonPath("$.label").value("A"))
        ;
    }

    // test pour verifier le end point de suppression d'un grouptype

    @Test
    public void verifyValidGroupTypeWidthIdToDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/groups/delete/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }




}
