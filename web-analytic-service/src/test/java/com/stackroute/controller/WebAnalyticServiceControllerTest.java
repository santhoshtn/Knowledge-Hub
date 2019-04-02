package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.WebAnalysisResult;
import com.stackroute.domain.WebDocument;
import com.stackroute.service.WebAnalyticService;
import com.stackroute.service.WebDocumentProviderService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebAnalyticServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private WebDocument webDocument;

    private WebAnalysisResult webAnalysisResults;

    @MockBean
    private WebDocumentProviderService webDocumentProviderService;

    @MockBean
    private WebAnalyticService webAnalyticService;

    @InjectMocks
    private WebAnalyticServiceController webAnalyticServiceController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(webAnalyticServiceController).build();

        webDocument = new WebDocument();
        webDocument.setLink("https://www.tutorialspoint.com/spring/spring_web_mvc_framework.htm");
        webDocument.setDomain("spring framework");
        webDocument.setCodeSnippets(10);
        webDocument.setConceptName("spring mvc");
        webDocument.setDescription("spring mvc description");
        webDocument.setId("webDoc001");
        webDocument.setImageCount(15);
        webDocument.setKeywords("spring mvc, spring core");
        webDocument.setTitle("spring mvc");
        webAnalysisResults = new WebAnalysisResult();
        webAnalysisResults.setIntentLevel("Application");
        webAnalysisResults.setConfidenceScore(4.55544);
        webAnalysisResults.setTitle("spring mvc");
        webAnalysisResults.setLink("https://www.tutorialspoint.com/spring/spring_web_mvc_framework.htm");
        webAnalysisResults.setKeywords("spring mvc, spring core");
        webAnalysisResults.setImageCount(15);
        webAnalysisResults.setId("webDoc001");
        webAnalysisResults.setDomain("spring framework");
        webAnalysisResults.setConceptName("spring mvc");
        webAnalysisResults.setCodeSnippets(10);
        webAnalysisResults.setDescription("spring mvc description");
    }

    @Test
    public void setWebDocument() throws Exception {
        doNothing().when(webDocumentProviderService).setWebDocument(webDocument);
        mockMvc.perform(MockMvcRequestBuilders.post("/webDocument")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(webDocument)))
                .andExpect(MockMvcResultMatchers.content().string("Web Document is successfully taken."))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getWebAnalysisResult() throws Exception {
        when(webAnalyticService.getWebAnalysisResult()).thenReturn(webAnalysisResults);
        mockMvc.perform(MockMvcRequestBuilders.get("/webAnalysisResult")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(webAnalysisResults)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }
}