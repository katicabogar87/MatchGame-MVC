package application.controllers;

import application.testConfigs.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TestConfig.class)
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
    }


    @Test
    void getHomePageContentTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/"))
                .andExpect(
                        status().isOk()
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

    @WithMockUser("user")
    @Test
    void checkGameWithUser() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(
                        status().is(403)
                )
                .andReturn();
    }


    @Test
    @WithMockUser("admin")
    void checkGameWithAdmin() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(
                        status().isOk()
                )
                .andReturn();
    }

    @Test
    @WithUserDetails("user")
    void getGamePageWithUserAgain() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(status().is(403))
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void getGamePageWithAdminAgain() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(status().isOk())
                .andReturn();
    }




   //  /welcomeUser


    @WithMockUser("user")
    @Test
    void checkWelcomeUserWithUser() throws Exception {
        mockMvc.perform(get("/welcomeUser"))
                .andExpect(
                        status().isOk()
                )
                .andReturn();
    }


    @Test
    @WithMockUser("admin")
    void checkWelcomeUserWithAdmin() throws Exception {
        mockMvc.perform(get("/welcomeUser"))
                .andExpect(
                        status().isOk()
                )
                .andReturn();
    }

    @Test
    @WithUserDetails("user")
    void getWelcomeUserPageWithUserAgain() throws Exception {
        mockMvc.perform(get("/welcomeUser"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void getWelcomeUserWithAdminAgain() throws Exception {
        mockMvc.perform(get("/welcomeUser"))
                .andExpect(status().isOk())
                .andReturn();
    }


    // gameForUser

    @Test
    @WithMockUser("user")
    void getGameForUserPageWithUser() throws Exception {
        mockMvc.perform(get("/gameForUser"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void getGameForUserPageWithAdmin() throws Exception {
        mockMvc.perform(get("/gameForUser"))
                .andExpect(status().isOk())
                .andReturn();
    }
}