package com.bov.petclinic;
import com.bov.petclinic.entity.Specialty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SpecialtyControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addSpecialty() throws Exception {
        Specialty specialty = new Specialty(1L,"Хірург");
        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/specialty/add")
        .content(asJsonString(specialty))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(print());
    }
    @Test
    public void getAllSpecialties() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/specialty/")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }
    @Test
    public void getSpecialtyById() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/owner/{id}",5L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
