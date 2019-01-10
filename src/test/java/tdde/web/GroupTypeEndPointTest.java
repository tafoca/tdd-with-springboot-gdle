package tdde.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tdde.domain.GroupType;
import tdde.service.GroupTypeService;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
public class GroupTypeEndPointTest  {

    @Mock
    private GroupTypeService groupTypeService;

    private MockMvc mockMvc;

    private List<GroupType> groupTypes = asList(new GroupType("First", "A"), new GroupType("Second", "A"));

    @Before
    public void init() {
        mockMvc = standaloneSetup(new GroupTypeEndPoint(groupTypeService))
                .build();
    }

    @Test
    public void groupeTypesAreReturnedForGroupType() throws Exception {
        when(groupTypeService.findByLabel("A")).thenReturn(groupTypes);
        mockMvc.perform(get("/groups?label=A").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("First"))
                .andExpect(jsonPath("$[0].label").value("A"))
                .andExpect(jsonPath("$[1].name").value("Second"))
                .andExpect(jsonPath("$[1].label").value("A"));
    }

    @Test
    public void ifLabelParamIsMissedThrowException() throws Exception {
        mockMvc.perform(get("/groups").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }




    public static String asJsonString(Object obj) {
        ObjectWriter writer=null;
            FilterProvider filters = new SimpleFilterProvider().addFilter("filtreGroupType", SimpleBeanPropertyFilter.filterOutAllExcept());
            //writer = filteredWriter(filters);

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}