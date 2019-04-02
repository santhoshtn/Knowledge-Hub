////test class for web document service
//
//package com.stackroute.service;
//
//import com.stackroute.domain.SearchDocument;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class WebDocumentServiceImplTest {
//    private WebDocumentServiceImpl webDocumentService;
//    private SearchDocument searchDocument;
//
//    @Before
//    public void setUp() throws Exception {
//        webDocumentService = new WebDocumentServiceImpl();
//        searchDocument = new SearchDocument();
//        searchDocument.setId("1");
//        searchDocument.setConceptName("concept");
//        searchDocument.setDomain("Spring");
//        searchDocument.setLink("https://www.tutorialspoint.com/spring/spring_hello_world_example.htm");
//    }
//
//    @Test
//    public void testExtractTitleFailure(){
//        String titleExpected="Spring Hello";
//        String titleActual= webDocumentService.extractTitle(searchDocument);
//        assertNotEquals(titleExpected,titleActual);
//    }
//
//    @Test
//    public void testExtractDescriptionFailure(){
//        String descriptionExpected="Learn Java Spring Framework version 4.1.6 in simple and easy steps starting from basic to advanced concepts with examples including Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Transaction Management, Web MVC framework, logging with Log4j, Web Flow, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.";
//        String descriptionActual=webDocumentService.extractDescription(searchDocument);
//        assertNotEquals(descriptionExpected,descriptionActual);
//    }
//    @Test
//    public void testExtractKeywordsFailure(){
//        String keywordExpected="Reference, Manual, Guide, transaction, Management, Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Web MVC framework, logging with Log4j, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.";
//        String keywordActual=webDocumentService.extractKeywords(searchDocument);
//        assertNotEquals(keywordExpected,keywordActual);
//    }
//    @Test
//    public void testExtractImageCountFailure(){
//        int imageCountExpected = 6;
//        int imageCountActual = webDocumentService.extractImageCount(searchDocument);
//        assertNotEquals(imageCountExpected,imageCountActual);
//    }
//
//
//    @Test
//    public void testExtractTitle(){
//        String titleExpected="Spring Hello World Example";
//        String titleActual= webDocumentService.extractTitle(searchDocument);
//        assertEquals(titleExpected,titleActual);
//    }
//
//    @Test
//    public void testExtractDescription(){
//        String descriptionExpected="Spring Hello World Example - Learn Java Spring Framework version 4.1.6 in simple and easy steps starting from basic to advanced concepts with examples including Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Transaction Management, Web MVC framework, logging with Log4j, Web Flow, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.";
//        String descriptionActual=webDocumentService.extractDescription(searchDocument);
//        assertEquals(descriptionExpected,descriptionActual);
//    }
//    @Test
//    public void testExtractKeywords(){
//        String keywordExpected="Free, Spring, Tutorials, beginners, Framework, Web Flow,  Reference, Manual, Guide, transaction, Management, Overview, Architecture, environment setup, Hello World Example, inversion of control (IoC), dependency injection, bean definition, scopes, bean life cycle, Post Processors, definition inheritance, dependency injection,  inner beans, injecting collection, autowiring, Annotation, Java based configuration, event handling, custom events, different modules, aspect oriented programming (AOP), database access (JDBC), Web MVC framework, logging with Log4j, Exception handling, EJB integration and Sending email, Spring Interview Questions, Answers, Quiz and Online Mock Test.";
//        String keywordActual=webDocumentService.extractKeywords(searchDocument);
//        assertEquals(keywordExpected,keywordActual);
//    }
//    @Test
//    public void testExtractImageCount(){
//        int imageCountExpected = 16;
//        int imageCountActual = webDocumentService.extractImageCount(searchDocument);
//        assertEquals(imageCountExpected,imageCountActual);
//    }
//}