package com.stackroute.service;

import com.stackroute.domain.IntentWord;
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
    private IntentWord intentWord;
    private List<IntentWord> intentWordList;
    @Mock
    private IntentRepository intentRepository;
    @InjectMocks
    private IntentServiceImpl intentServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        termNode = new Terms(1001, "define", "SPRING:2", "Knowledge", "term", "indicatorOf", "9","spring:2");
        termNodeList = new ArrayList<>();
        termNodeList.add(termNode);
        intentWord = new IntentWord();
        intentWord = new IntentWord("define", 0, "Knowledge", "indicatorOf", 9);
        intentWordList = new ArrayList<>();
        intentWordList.add(intentWord);
    }

    @Test
    public void getAllIntentWords() {
        intentRepository.save(termNode);
        when(intentRepository.getAllTerms()).thenReturn(termNodeList);
        intentServiceImpl.setAllTermNodes(new ArrayList<>(termNodeList));
        List<IntentWord> expectedIntentWordList = intentWordList;
        List<IntentWord> actualIntentWordList = intentServiceImpl.getAllIntentWords();
        Assert.assertEquals(expectedIntentWordList, actualIntentWordList);
    }
}