package mye30.project.db3_5030_5152;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import mye30.project.db3_5030_5152.controller.MainController;

@SpringBootTest
@AutoConfigureMockMvc
public class TestMainController {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void testMainControllerIsNotNull() {
        Assertions.assertNotNull(mainController);
    }

    @Test
    void testMockMvcIsNotNull() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void testHomeMenuReturnsPage() throws Exception {
        mockMvc.perform(get("/homeMenu"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomeMenu"));
    }

    @Test
    void testListJournalsReturnsPage() throws Exception {
        mockMvc.perform(get("/listJournals"))
                .andExpect(status().isOk())
                .andExpect(view().name("ListOfJournals"));
    }

    @Test
    void testListConferencesReturnsPage() throws Exception {
        mockMvc.perform(get("/listConferences"))
                .andExpect(status().isOk())
                .andExpect(view().name("ListOfConferences"));
    }

    @Test
    void testListAuthorsReturnsPage() throws Exception {
        mockMvc.perform(get("/listAuthors").param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("ListOfAuthors"));
    }

    @Test
    void testListYearsReturnsPage() throws Exception {
        mockMvc.perform(get("/listYears"))
                .andExpect(status().isOk())
                .andExpect(view().name("ListOfYears"));
    }

    @Test
    void testLineChartsPageReturnsPage() throws Exception {
        mockMvc.perform(get("/lineCharts"))
                .andExpect(status().isOk())
                .andExpect(view().name("LineChart"));
    }

    @Test
    void testGroupedBarChartsPageReturnsPage() throws Exception {
        mockMvc.perform(get("/groupedBarCharts"))
                .andExpect(status().isOk())
                .andExpect(view().name("BarChart"));
    }
}