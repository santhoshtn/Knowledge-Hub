package com.stackroute.service;

import com.stackroute.domain.NlpResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class NlpResultServiceImplTest {
    private NlpResult nlpResult;

    @InjectMocks
    private NlpResultServiceImpl nlpResultServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        nlpResult = new NlpResult();
    }

    @Test
    public void getNlpResult() {
        nlpResultServiceImpl.setNlpResult(nlpResult);
        String expected = nlpResult.toString();
        String actual = nlpResultServiceImpl.getNlpResult().toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setNlpResult() {
    }
}