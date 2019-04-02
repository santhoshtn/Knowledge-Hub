package com.stackroute.searchupstreamservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.searchupstreamservice.domain.Search;
import com.stackroute.searchupstreamservice.listener.KafkaProducer;
import com.stackroute.searchupstreamservice.service.SearchService;
import org.junit.After;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Search search;

    @MockBean
    private SearchService searchService;

    @MockBean
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private SearchController searchController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
        search=new Search();
    }

    @After
    public void tearDown(){
        search =null;
    }
    @Test
    public void vSearchSuccess() throws  Exception{
        when(kafkaProducer.upStreamService(any())).thenReturn("success");
        when(searchService.saveSearchText(any())).thenReturn(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/vsearch")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(search)))
                .andExpect(MockMvcResultMatchers.content().string("success"))
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}