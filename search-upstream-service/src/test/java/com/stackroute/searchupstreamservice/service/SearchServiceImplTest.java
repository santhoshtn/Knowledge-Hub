package com.stackroute.searchupstreamservice.service;

import com.stackroute.searchupstreamservice.domain.Search;
import com.stackroute.searchupstreamservice.repository.SearchRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SearchServiceImplTest {

    @Mock
    private SearchRepository searchRepository;

    @InjectMocks
    private SearchServiceImpl searchService;

    private Search search;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        search = new Search();
        search.setSessionId("123");
        search.setSearchString("abc");
    }

    @After
    public void tearDown() {
    search=null;
    }

    @Test
    public void saveSearchTextSuccess() {
        when(searchRepository.save(any())).thenReturn(search);
        Search savedSearch = searchRepository.save(search);
        Assert.assertEquals(search,savedSearch);
        verify(searchRepository,times(1)).save(search);
    }
    @Test
    public void saveSearchTextFailure() {
        when(searchRepository.save(any())).thenReturn(any());
        Search savedSearch = searchRepository.save(search);
        Assert.assertNotEquals(search,savedSearch);
        verify(searchRepository,times(1)).save(search);
    }
}