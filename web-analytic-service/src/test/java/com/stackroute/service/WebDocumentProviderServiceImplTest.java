package com.stackroute.service;

import com.stackroute.domain.WebDocument;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class WebDocumentProviderServiceImplTest {
    private WebDocument webDocument;
    @InjectMocks
    private WebDocumentProviderServiceImpl webDocumentProviderServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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
    }

    @Test
    public void getWebDocument() {
        webDocumentProviderServiceImpl.setWebDocument(webDocument);
        String expectedWebDocument = webDocument.toString();
        String actualWebDocument = webDocumentProviderServiceImpl.getWebDocument().toString();
        Assert.assertEquals(expectedWebDocument, actualWebDocument);
    }

    @Test
    public void setWebDocument() {

    }
}