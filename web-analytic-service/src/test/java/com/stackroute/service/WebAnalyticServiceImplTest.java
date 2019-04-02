package com.stackroute.service;

import com.stackroute.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class WebAnalyticServiceImplTest {
    private WebDocument webDocument;
    private List<HtmlTagWithContentAndScore> htmlTagWithContentAndScoreList;
    private String[] htmlTags;
    private int[] htmlTagScores;
    private IntentWordWithFrequencyCount intentWordWithFrequencyCount;
    private List<IntentWordWithFrequencyCount> allIntentTermNodesWithFrequencyCount;
    private List<IntentWordWithFrequencyCount> intentWordWithFrequencyCountList;
    private IntentWithConfidenceScore intentWithConfidenceScore;
    private List<IntentWithConfidenceScore> intentWithConfidenceScoresList;
    private WebAnalysisResult webAnalysisResults;

    @Mock
    IntentService intentService;
    @Mock
    WebDocumentProviderService webDocumentProviderService;
    @InjectMocks
    WebAnalyticServiceImpl webAnalyticServiceImpl;

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

        htmlTags = new String[]{"title"};
        htmlTagScores = new int[]{45};
        htmlTagWithContentAndScoreList=new ArrayList<>();
        htmlTagWithContentAndScoreList.add(new HtmlTagWithContentAndScore ("title", "spring mvc framework", 45));

        intentWordWithFrequencyCount = new IntentWordWithFrequencyCount("define",0,"Knowledge","indicatorOf",9);
        allIntentTermNodesWithFrequencyCount = new ArrayList<>();
        allIntentTermNodesWithFrequencyCount.add(intentWordWithFrequencyCount);
        intentWordWithFrequencyCountList = new ArrayList<>();
        intentWordWithFrequencyCountList.add(intentWordWithFrequencyCount);

        intentWithConfidenceScore = new IntentWithConfidenceScore("Knowledge",0);
        intentWithConfidenceScoresList = new ArrayList<>();
        intentWithConfidenceScoresList.add(intentWithConfidenceScore);

        webAnalysisResults = new WebAnalysisResult();
        webAnalysisResults.setIntentLevel("Application");
        webAnalysisResults.setConfidenceScore(22.727272727272727);
        webAnalysisResults.setTitle("spring mvc");
        webAnalysisResults.setLink("https://www.tutorialspoint.com/spring/spring_web_mvc_framework.htm");
        webAnalysisResults.setKeywords("spring mvc, spring core");
        webAnalysisResults.setImageCount(15);
        webAnalysisResults.setId("webDoc001");
        webAnalysisResults.setDomain("spring framework");
        webAnalysisResults.setConceptName("spring mvc");
        webAnalysisResults.setCodeSnippets(10);
        webAnalysisResults.setDescription("spring mvc description");
    }

    @Test
    public void getAllHtmlTagsWithContentAndScore() throws IOException {
        when(webDocumentProviderService.getWebDocument()).thenReturn(webDocument);
        webAnalyticServiceImpl.setHtmlTags(htmlTags);
        webAnalyticServiceImpl.setHtmlTagScores(htmlTagScores);
        String expectedHtmlTagsWithContentAndScore = htmlTagWithContentAndScoreList.get(0).toString();
        String actualHtmlTagsWithContentAndScore = webAnalyticServiceImpl.getAllHtmlTagsWithContentAndScore().get(0).toString();
        Assert.assertEquals(expectedHtmlTagsWithContentAndScore,actualHtmlTagsWithContentAndScore);
    }

    @Test
    public void getIntentWordWithFrequencyCount() {
        webAnalyticServiceImpl.setAllIntentTermNodesWithFrequencyCount(allIntentTermNodesWithFrequencyCount);
        webAnalyticServiceImpl.setHtmlTagWithContentAndScoreList(htmlTagWithContentAndScoreList);
        String expectedIntentWordWithFrequencyCount = intentWordWithFrequencyCountList.get(0).toString();
        String actualIntentWordWithFrequencyCount = webAnalyticServiceImpl.getIntentWordWithFrequencyCount().get(0).toString();
        Assert.assertEquals(expectedIntentWordWithFrequencyCount,actualIntentWordWithFrequencyCount);
    }

    @Test
    public void getIntentLevel() {
        webAnalyticServiceImpl.setIntentWithConfidenceScoresList(intentWithConfidenceScoresList);
        String expectedIntentLevel = "no intentLevel found";
        String actualIntentLevel = webAnalyticServiceImpl.getIntentLevel();
        Assert.assertEquals(expectedIntentLevel,actualIntentLevel);
    }

    @Test
    public void getConfidenceScore() {
        webAnalyticServiceImpl.setIntentWithConfidenceScoresList(intentWithConfidenceScoresList);
        double expectedConfidenceScore = 0;
        double actualConfidenceScore = webAnalyticServiceImpl.getConfidenceScore();
        Assert.assertEquals(expectedConfidenceScore,actualConfidenceScore,0);
    }

}