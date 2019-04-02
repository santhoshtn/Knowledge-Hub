package com.stackroute.service;

import com.stackroute.domain.IntentWordWithFrequencyCount;
import com.stackroute.domain.Terms;
import com.stackroute.repository.IntentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class IntentServiceImplTest {
    private Terms termNode;
    private List<Terms> termNodeList;
    private IntentWordWithFrequencyCount intentWordWithFrequencyCount;
    private List<IntentWordWithFrequencyCount> intentWordWithFrequencyCountList;
    @Mock
    private IntentRepository intentRepository;
    @InjectMocks
    private IntentServiceImpl intentServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        termNode = new Terms(1001, "define", "SPRING:2", "Knowledge", "term", "indicatorOf", "9","Spring:2");
        termNodeList = new ArrayList<>();
        termNodeList.add(termNode);
        intentWordWithFrequencyCount = new IntentWordWithFrequencyCount();
        intentWordWithFrequencyCount = new IntentWordWithFrequencyCount("define", 0, "Knowledge", "indicatorOf", 9);
        intentWordWithFrequencyCountList = new ArrayList<>();
        intentWordWithFrequencyCountList.add(intentWordWithFrequencyCount);
    }

    @Test
    public void getAllIntentWords() {
        intentRepository.save(termNode);
        when(intentRepository.getAllTerms()).thenReturn(termNodeList);
        intentServiceImpl.setAllTermNodes(new ArrayList<>(termNodeList));
        List<IntentWordWithFrequencyCount> expectedIntentWordList = intentWordWithFrequencyCountList;
        List<IntentWordWithFrequencyCount> actualIntentWordList = intentServiceImpl.getAllIntentWords();
        Assert.assertEquals(expectedIntentWordList, actualIntentWordList);
    }
}