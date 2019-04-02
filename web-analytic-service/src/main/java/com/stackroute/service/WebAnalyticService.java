package com.stackroute.service;


import com.stackroute.domain.HtmlTagWithContentAndScore;
import com.stackroute.domain.IntentWithConfidenceScore;
import com.stackroute.domain.IntentWordWithFrequencyCount;
import com.stackroute.domain.WebAnalysisResult;

import java.io.IOException;
import java.util.List;

public interface WebAnalyticService {

    public List<HtmlTagWithContentAndScore> getAllHtmlTagsWithContentAndScore() throws IOException;

    public List<IntentWordWithFrequencyCount> getIntentWordWithFrequencyCount();

    public List<IntentWithConfidenceScore> getConfidenceScoreOfMostAccurateIntents();

    public String getIntentLevel();

    public double getConfidenceScore();

    public WebAnalysisResult getWebAnalysisResult() throws IOException;

    public void setIntents(String[] intents);

    public void setHtmlTags(String[] htmlTags);

    public void setHtmlTagScores(int[] htmlTagScores);

    public void setHtmlTagWithContentAndScoreList(List<HtmlTagWithContentAndScore> htmlTagWithContentAndScoreList);

    public void setIntentWithConfidenceScoresList(List<IntentWithConfidenceScore> intentWithConfidenceScoresList);

    public void setIntentWordWithFrequencyCountList(List<IntentWordWithFrequencyCount> intentWordWithFrequencyCountList);

    public void setAllIntentTermNodesWithFrequencyCount(List<IntentWordWithFrequencyCount> allIntentTermNodesWithFrequencyCount);
}
