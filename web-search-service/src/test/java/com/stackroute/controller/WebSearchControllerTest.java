package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.service.WebSearchService;
import com.stackroute.domain.SearchDocument;
import com.stackroute.domain.UIDocument;
import com.stackroute.listener.KafkaProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private UIDocument uiDocument;
    private SearchDocument searchDocument;
    private List<SearchDocument> searchDocumentList;

    @MockBean
    private WebSearchService webSearchService;
    @MockBean
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private WebSearchController webSearchController;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(webSearchController).build();
        uiDocument= new UIDocument();
        searchDocument= new SearchDocument();
        searchDocumentList=new ArrayList<>();
        uiDocument.setConceptName(new String[]{"java"});
        uiDocument.setDomain("Angular");
        searchDocument.setUrl("https://www.geeksforgeeks.org/java/&sa=U&ved=0ahUKEwiH0OCD3O_gAhXQBIgKHQc4CzIQFghfMAw&usg=AOvVaw0U2PoXBzTABPby55mXoKG8");
        searchDocument.setDomain("Angular");
        searchDocument.setConceptName("java");
        searchDocument.setId("6caeef9b-72e2-4c6d-8f76-38a177aacc32");
        searchDocumentList.add(searchDocument);
    }

    @Test
    public void getSearchList() throws Exception {
        when(webSearchService.getUrls(uiDocument)).thenReturn(searchDocumentList);
        mockMvc.perform(MockMvcRequestBuilders.get("/results")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(searchDocumentList.get(0))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postSearchList() throws Exception
    {
        when(kafkaProducer.postservice(uiDocument)).thenReturn("Published successfully");
        mockMvc.perform(MockMvcRequestBuilders.post("/domain")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(uiDocument)))
                .andExpect(MockMvcResultMatchers.content().string("Successfully upload"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
