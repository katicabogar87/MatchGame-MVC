package application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StaticControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getHomePageTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/"))
                .andExpect(
                        status().isOk()  // status code 200
                )
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        //assertTrue(content.contains( "href=\"/form\""));
        assertFalse(content.contains("data-th-href"));

    }

    @Test
    void getInvalidPage() throws Exception {
        mockMvc.perform(get("/non_exist"))
                .andExpect(
                        status().is(404)
                )
                .andReturn();
    }
}