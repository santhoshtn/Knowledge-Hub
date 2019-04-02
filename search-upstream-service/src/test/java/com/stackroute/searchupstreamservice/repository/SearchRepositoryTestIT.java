package com.stackroute.searchupstreamservice.repository;

import com.stackroute.searchupstreamservice.domain.Search;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
@ActiveProfiles("test")
public class SearchRepositoryTestIT {
    @Autowired
    private SearchRepository searchRepository;

    private Search search;

    @Before
    public void setUp() throws Exception {
        search =new Search();
        search.setSearchString("abc");
        search.setSessionId("1233");
        searchRepository.save(search);
    }

    @After
    public void tearDown() throws Exception {
        search=null;
        searchRepository.deleteAll();

    }
    @Test
    public void testSaveSearchString(){
        Search savedSearch = searchRepository.findBySessionId("1233");
        Assert.assertEquals("1233",savedSearch.getSessionId());
    }
    @Test
    public void testSaveSearchFailure(){
        Search savedSearch = searchRepository.findBySessionId("1233");
        Assert.assertNotEquals("12233",savedSearch.getSessionId());
    }

}