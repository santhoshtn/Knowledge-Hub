//test class for web controller

package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.WebDocument;
import com.stackroute.service.WebDocumentService;
import org.json.simple.JSONObject;
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
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebDocumentControllerTest {

    private WebDocument webDocument;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WebDocumentService webDocumentService;

    @InjectMocks
    WebDocumentController webDocumentController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(webDocumentController).build();
        webDocument= new WebDocument();
        webDocument.setLink("https://www.tutorialspoint.com/spring/spring_hello_world_example.htm");
        webDocument.setId("1");
        webDocument.setConceptName("concept");
        webDocument.setDomain("Spring");
        webDocument.setTitle("Spring Hello World Example");
        List<JSONObject> metadata = new ArrayList<>();
        JSONObject metatag = new JSONObject();
        metatag.put("name","description");
        metatag.put("content","Spring Hello World Example - Learn Java Spring Framework version 4.1.6 in simple and easy steps starting from basic to advanced concepts with examples including Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Transaction Management, Web MVC framework, logging with Log4j, Web Flow, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.");
        metadata.add(metatag);
        metatag.put("name","keywords");
        metatag.put("content","Free, Spring, Tutorials, beginners, Framework, Web Flow,  Reference, Manual, Guide, transaction, Management, Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Web MVC framework, logging with Log4j, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.");
        metadata.add(metatag);
        metatag.put("name","viewport");
        metatag.put("content","width=device-width,initial-scale=1.0,user-scalable=yes");
        metadata.add(metatag);
        metatag.put("property","og:locale");
        metatag.put("content","en_US");
        metadata.add(metatag);
        metatag.put("property","og:type");
        metatag.put("content","website");
        metadata.add(metatag);
        metatag.put("property","fb:app_id");
        metatag.put("content","471319149685276");
        metadata.add(metatag);
        metatag.put("property","og:site_name");
        metatag.put("content","www.tutorialspoint.com");
        metadata.add(metatag);
        metatag.put("name","apple-mobile-web-app-capable");
        metatag.put("content","yes");
        metadata.add(metatag);
        metatag.put("name","apple-mobile-web-app-status-bar-style");
        metatag.put("content","black");
        metadata.add(metatag);
        metatag.put("name","author");
        metatag.put("content","tutorialspoint.com");
        metadata.add(metatag);
        webDocument.setDescription("Spring Hello World Example - Learn Java Spring Framework version 4.1.6 in simple and easy steps starting from basic to advanced concepts with examples including Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Transaction Management, Web MVC framework, logging with Log4j, Web Flow, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.");
        webDocument.setKeywords("Free, Spring, Tutorials, beginners, Framework, Web Flow,  Reference, Manual, Guide, transaction, Management, Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Web MVC framework, logging with Log4j, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.");
        webDocument.setImageCount(16);
        webDocument.setCodeSnippets(3);
    }

    @Test
    public void testGetWebDoc() throws Exception{
        when(webDocumentService.getContentExtractorResults()).thenReturn(webDocument);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/webdoc")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(webDocument)))
                .andExpect(MockMvcResultMatchers.status().isOk())
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