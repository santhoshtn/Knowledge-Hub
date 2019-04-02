package com.stackroute.service;

import com.stackroute.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@PropertySource(value = "classpath:application.yml")
public class AnalyticServiceImpl implements AnalyticService {
    // Variables for AnalyticServiceImpl
    private ArrayList<String> conceptNames;
    private NlpResultService nlpResultService;
    private IntentService intentService;
    private ParagraphProviderService paragraphProviderService;
    private ArrayList<IntentWord> allIntentterms;
    private NlpResult nlpResult;
    private ArrayList<ConceptNameFrequency> frequencyOfSpringConcept;
    private List<IntentWord> intentWordWithFrequencyList;
    private List<IntentWithConfidenceScore> intentWithConfidenceScores;
    @Value("${intentNames}")
    private String[] intents;

    // constructor is to initialize all the services required by AnalyticService and also initialize variables
    @Autowired
    public AnalyticServiceImpl(IntentService intentService,
                               NlpResultService nlpResultService,
                               ParagraphProviderService paragraphProviderService) {
        this.nlpResultService = nlpResultService;
        this.paragraphProviderService = paragraphProviderService;
        this.intentService = intentService;
    }

    public List<ConceptNameFrequency> getFrequencyOfSpringConcepts() {
        String paragraphWithOutStopWords = nlpResult.getParagraphWithOutStopWords().toLowerCase();
        ArrayList<ConceptNameFrequency> wordsFrequencyMap = new ArrayList<>();
        for (int i = 0; i < conceptNames.size(); i++) {
            long counter = 0;
            wordsFrequencyMap.add(new ConceptNameFrequency(conceptNames.get(i), counter));
            String pattenString = conceptNames.get(i).toLowerCase();
            Pattern pattern = Pattern.compile(pattenString);
            Matcher matcher = pattern.matcher(paragraphWithOutStopWords);
            while (matcher.find()) {
                long tempCount = wordsFrequencyMap.get(i).getFrequencyCount();
                tempCount++;
                wordsFrequencyMap.get(i).setFrequencyCount(tempCount);
            }
        }
        return wordsFrequencyMap;
    }

    // returns the highest no:of times used conceptName
    public List<String> getTopConceptName() {
        frequencyOfSpringConcept.sort((o1, o2) -> (int) (o2.getFrequencyCount() - o1.getFrequencyCount()));
        List<String> topConceptNamesList = new ArrayList<>();
        long maxConceptFrequency = frequencyOfSpringConcept.get(0).getFrequencyCount();
        for (int i = 0; i < frequencyOfSpringConcept.size(); i++) {
            if (frequencyOfSpringConcept.get(i).getFrequencyCount() > 0) {
                topConceptNamesList.add(frequencyOfSpringConcept.get(i).getConceptName());
            }
        }
        if (maxConceptFrequency == 0) {
            topConceptNamesList.add("No concept found");
            return topConceptNamesList;
        } else {
            return topConceptNamesList;
        }
    }

    public List<IntentWord> getIntentWordWithFrequencyCount() {
        String paragraphWithOutStopWords = nlpResult.getParagraphWithOutStopWords().toLowerCase();
        ArrayList<IntentWord> wordsFrequencyMap = new ArrayList<>();
        for (int i = 0; i < allIntentterms.size(); i++) {
            String pattenString = allIntentterms.get(i).getIntentWord().toLowerCase();
            Pattern pattern = Pattern.compile(pattenString);
            Matcher matcher = pattern.matcher(paragraphWithOutStopWords);
            wordsFrequencyMap.add(allIntentterms.get(i));
            while (matcher.find()) {
                long tempCount = wordsFrequencyMap.get(i).getFrequencyCount();
                tempCount++;
                wordsFrequencyMap.get(i).setFrequencyCount(tempCount);
            }
        }
        return wordsFrequencyMap;
    }

    public List<IntentWithConfidenceScore> getConfidenceScoreOfMostAccurateIntents() {
        double[] confidenceScore = new double[6];
        int[] noOfTermsInEachIntent = new int[6];
        double indicator = 0;
        double counterIndicator = 0;
        for (int i = 0; i < intentWordWithFrequencyList.size(); i++) {
            switch (intentWordWithFrequencyList.get(i).getIntent()) {
                case "Knowledge":
                    noOfTermsInEachIntent[0]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[0] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Comprehension":
                    noOfTermsInEachIntent[1]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[1] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Application":
                    noOfTermsInEachIntent[2]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[2] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Analysis":
                    noOfTermsInEachIntent[3]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[3] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Synthesis":
                    noOfTermsInEachIntent[4]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[4] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Evaluation":
                    noOfTermsInEachIntent[5]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyList.get(i).getFrequencyCount() * intentWordWithFrequencyList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[5] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
            }
        }
        List<IntentWithConfidenceScore> intentWithConfidencyScoresList = new ArrayList<>();
        for (int i = 0; i < confidenceScore.length; i++) {
            confidenceScore[i] = confidenceScore[i] / noOfTermsInEachIntent[i];
            if (confidenceScore[i] >= 1) {
                intentWithConfidencyScoresList.add(new IntentWithConfidenceScore(intents[i], confidenceScore[i]));
            }
        }
        return intentWithConfidencyScoresList;
    }


    // returns the intent level of the paragraph by analysis the terms present in paragraph
    public String getIntentLevel() {

        double maxConfidenceScore = Integer.MIN_VALUE;
        String intentLevel = null;
        for (int i = 0; i < intentWithConfidenceScores.size(); i++) {
            if (intentWithConfidenceScores.get(i).getConfidenceScore() > maxConfidenceScore) {
                maxConfidenceScore = intentWithConfidenceScores.get(i).getConfidenceScore();
                intentLevel = intentWithConfidenceScores.get(i).getIntnet();
            }
        }
        if (intentLevel == null) {
            return "no intentLevel found";
        }
        return intentLevel;
    }

    public double getConfidenceScore() {
        double maxConfidenceScore = Integer.MIN_VALUE;
        for (int i = 0; i < intentWithConfidenceScores.size(); i++) {
            if (intentWithConfidenceScores.get(i).getConfidenceScore() > maxConfidenceScore) {
                maxConfidenceScore = intentWithConfidenceScores.get(i).getConfidenceScore();
            }
        }

        return round(maxConfidenceScore,2);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public List<AnalysisResult> getAnalysisResults() {
        this.allIntentterms = null;
        System.out.println("---------------getAnalysisResults() before---------------------");
        System.out.println(allIntentterms);
        this.allIntentterms = new ArrayList<>(intentService.getAllIntentWords());
        System.out.println("---------------getAnalysisResults() after---------------------");
        System.out.println(allIntentterms);
        System.out.println("*****************************************************************");
        this.nlpResult = null;
        nlpResult = nlpResultService.getNlpResult();
        this.frequencyOfSpringConcept = null;
        frequencyOfSpringConcept = new ArrayList<>(getFrequencyOfSpringConcepts());
        this.intentWordWithFrequencyList = null;
        this.intentWordWithFrequencyList = getIntentWordWithFrequencyCount();
        this.intentWithConfidenceScores = null;
        this.intentWithConfidenceScores = getConfidenceScoreOfMostAccurateIntents();

        List<AnalysisResult> analysisResultList = new ArrayList<>();
        for (int i = 0; i < getTopConceptName().size(); i++) {
            analysisResultList.add(new AnalysisResult());
            analysisResultList.get(i).setConfidenceScore(getConfidenceScore());
            analysisResultList.get(i).setDocumentId(paragraphProviderService.getParagraph().getDocumentId());
            analysisResultList.get(i).setParagraphId(paragraphProviderService.getParagraph().getParagraphId());
            analysisResultList.get(i).setDomain("spring framework");
            analysisResultList.get(i).setIntentLevel(getIntentLevel());
            analysisResultList.get(i).setConcept(getTopConceptName().get(i));
            analysisResultList.get(i).setParagraphContent(nlpResultService.getNlpResult().getClearedParagraph());
        }
        return analysisResultList;
    }

    public void setConceptNames(ArrayList<String> conceptNames) {
        this.conceptNames = conceptNames;
    }

    public void setNlpResultService(NlpResultService nlpResultService) {
        this.nlpResultService = nlpResultService;
    }

    public void setIntentService(IntentService intentService) {
        this.intentService = intentService;
    }

    public void setParagraphProviderService(ParagraphProviderService paragraphProviderService) {
        this.paragraphProviderService = paragraphProviderService;
    }

    public void setFrequencyOfSpringConcept(ArrayList<ConceptNameFrequency> frequencyOfSpringConcept) {
        this.frequencyOfSpringConcept = frequencyOfSpringConcept;
    }

    public void setNlpResult(NlpResult nlpResult) {
        this.nlpResult = nlpResult;
    }

    public void setIntentWordWithFrequencyList(List<IntentWord> intentWordWithFrequencyList) {
        this.intentWordWithFrequencyList = intentWordWithFrequencyList;
    }

    public void setIntentWithConfidenceScores(List<IntentWithConfidenceScore> intentWithConfidenceScores) {
        this.intentWithConfidenceScores = intentWithConfidenceScores;
    }

    public void setAllIntentterms(ArrayList<IntentWord> allIntentterms) {
        this.allIntentterms = allIntentterms;
    }

    public void setIntents(String[] intents) {
        this.intents = intents;
    }
}