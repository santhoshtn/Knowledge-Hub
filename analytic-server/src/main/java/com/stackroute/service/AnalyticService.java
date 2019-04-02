package com.stackroute.service;

import com.stackroute.domain.*;

import java.util.ArrayList;
import java.util.List;

public interface AnalyticService {

    public List<ConceptNameFrequency> getFrequencyOfSpringConcepts();

    // returns the highest no:of times used conceptName
    public List<String> getTopConceptName();

    public List<IntentWord> getIntentWordWithFrequencyCount();

    public List<IntentWithConfidenceScore> getConfidenceScoreOfMostAccurateIntents();

    // returns the intent level of the paragraph by analysis the terms present in paragraph
    public String getIntentLevel();

    public double getConfidenceScore();

    public List<AnalysisResult> getAnalysisResults();

    public void setConceptNames(ArrayList<String> conceptNames);

    public void setNlpResultService(NlpResultService nlpResultService);

    public void setIntentService(IntentService intentService);

    public void setParagraphProviderService(ParagraphProviderService paragraphProviderService);

    public void setFrequencyOfSpringConcept(ArrayList<ConceptNameFrequency> frequencyOfSpringConcept);

    public void setNlpResult(NlpResult nlpResult);

    public void setIntentWordWithFrequencyList(List<IntentWord> intentWordWithFrequencyList);

    public void setIntentWithConfidenceScores(List<IntentWithConfidenceScore> intentWithConfidenceScores);

    public void setAllIntentterms(ArrayList<IntentWord> allIntentterms);

    public void setIntents(String[] intents);
}
