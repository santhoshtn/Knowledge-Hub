package com.stackroute.service;

import com.stackroute.domain.SearchDocument;
import com.stackroute.domain.UIDocument;
import com.stackroute.exception.DomainNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebSearchServiceTest {

    private SearchDocument searchDocument;
    private UIDocument uiDocument;
    private WebSearchServiceImpl webSearchService;
    private List<SearchDocument> searchDocumentList;

    @Before
    public void setUp()
    {
        uiDocument= new UIDocument();
        searchDocument=new SearchDocument();
        this.webSearchService = new WebSearchServiceImpl();
        searchDocumentList=new ArrayList<>();
        uiDocument.setDomain("Angular");
        uiDocument.setConceptName(new String[]{"java"});
    }

    @Test
    public void getUrlsSuccess() throws IOException, DomainNotFoundException {
        searchDocument.setConceptName("java");
        searchDocument.setDomain("Angular");
        searchDocument.setUrl("https://www.codecademy.com/catalog/language/java&sa=U&ved=0ahUKEwi91N6M4O_gAhWFHaYKHY_hCM4QFghfMAw&usg=AOvVaw0iNGV7I8ed4bQqiOHamKJz");
        searchDocumentList = webSearchService.getUrls(uiDocument);
        Assert.assertEquals(searchDocument.getConceptName(),searchDocumentList.get(0).getConceptName());
        Assert.assertEquals(searchDocument.getDomain(),searchDocumentList.get(0).getDomain());

    }


    @Test
    public void getUrlsFailure() throws IOException, DomainNotFoundException {
        searchDocument.setConceptName("java");
        searchDocument.setDomain("Angular");
        searchDocument.setUrl("https://www.codecademy.com/catalog/language/java&sa=U&ved=0ahUKEwi91N6M4O_gAhWFHaYKHY_hCM4QFghfMAw&usg=AOvVaw0iNGV7I8ed4bQqiOHamKJz");
        searchDocumentList = webSearchService.getUrls(uiDocument);
        Assert.assertEquals(searchDocument.getConceptName(),searchDocumentList.get(0).getConceptName());
        Assert.assertEquals(searchDocument.getDomain(),searchDocumentList.get(0).getDomain());
        Assert.assertNotEquals(searchDocument.getUrl(),searchDocumentList.get(0).getUrl());

    }
}
