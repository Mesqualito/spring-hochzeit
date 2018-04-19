package rocks.gebsattel.hochzeit.controllers

import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import rocks.gebsattel.hochzeit.controllers.IndexController

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

class IndexControllerTest {

    private MockMvc mockMvc

    private IndexController indexController

    @Before
    void setup(){
        indexController = new IndexController()
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build()
    }

    @Test
    void testIndex() throws Exception{
        mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
    }
}