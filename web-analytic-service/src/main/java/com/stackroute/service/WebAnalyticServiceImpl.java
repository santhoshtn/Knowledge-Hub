package com.stackroute.service;

import com.stackroute.domain.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@PropertySource(value = "classpath:application.yml")
public class WebAnalyticServiceImpl implements WebAnalyticService {
    private WebDocumentProviderService webDocumentProviderService;
    private IntentService intentService;
    @Value("${intentNames}")
    private String[] intents;
    @Value(("${htmlTags}"))
    private String[] htmlTags;
    @Value(("${htmlTagScores}"))
    private int[] htmlTagScores;
    private List<HtmlTagWithContentAndScore> htmlTagWithContentAndScoreList;
    private List<IntentWithConfidenceScore> intentWithConfidenceScoresList;
    private List<IntentWordWithFrequencyCount> intentWordWithFrequencyCountList;
    private List<IntentWordWithFrequencyCount> allIntentTermNodesWithFrequencyCount;

    @Autowired
    public WebAnalyticServiceImpl(WebDocumentProviderService webDocumentProviderService,
                                  IntentService intentService) {
        this.webDocumentProviderService = webDocumentProviderService;
        this.intentService = intentService;
    }

    // this method fetches the contents of each htmlTag
    public List<HtmlTagWithContentAndScore> getAllHtmlTagsWithContentAndScore() throws IOException {
        List<HtmlTagWithContentAndScore> htmlTagWithContentAndScoreList = new ArrayList<>();
        Document document = Jsoup.connect(webDocumentProviderService.getWebDocument().getLink()).get();
        for (int i = 0; i < htmlTags.length; i++) {
            String htmlTagText = document.getElementsByTag(htmlTags[i]).text();
            htmlTagWithContentAndScoreList.add(new HtmlTagWithContentAndScore(htmlTags[i], htmlTagText.toLowerCase(), htmlTagScores[i]));
        }
        return htmlTagWithContentAndScoreList;
    }

    // this method finds the frequecy of occurence of each intentWord in htmlTag content
    public List<IntentWordWithFrequencyCount> getIntentWordWithFrequencyCount() {
        ArrayList<IntentWordWithFrequencyCount> wordsFrequencyMap = new ArrayList<>();
        for (int i = 0; i < allIntentTermNodesWithFrequencyCount.size(); i++) {
            wordsFrequencyMap.add(allIntentTermNodesWithFrequencyCount.get(i));
        }
        for (int i = 0; i < htmlTagWithContentAndScoreList.size(); i++) {
            if (htmlTagWithContentAndScoreList.get(i).getTagContent() != null &&
                    htmlTagWithContentAndScoreList.get(i).getTagContent().length() != 0) {
                for (int j = 0; j < wordsFrequencyMap.size(); j++) {
                    String pattenString = wordsFrequencyMap.get(j).getIntentWord().toLowerCase();
                    Pattern pattern = Pattern.compile(pattenString);
                    Matcher matcher = pattern.matcher(htmlTagWithContentAndScoreList.get(i).getTagContent());
                    while (matcher.find()) {
                        long tempCount = wordsFrequencyMap.get(j).getFrequencyCount();
                        tempCount++;
                        wordsFrequencyMap.get(j).setFrequencyCount(tempCount * htmlTagWithContentAndScoreList.get(i).getTagScore());
                    }
                }
            }
        }
        return wordsFrequencyMap;
    }

    // this method is used to calculate confidence score of each intent based on intentWord frequecny , intentword weight , and htmlTagWeight
    public List<IntentWithConfidenceScore> getConfidenceScoreOfMostAccurateIntents() {
        double[] confidenceScore = new double[6];
        int[] noOfTermsInEachIntent = new int[6];
        double indicator = 0;
        double counterIndicator = 0;
        System.out.println("get confidence score");
        System.out.println(intentWordWithFrequencyCountList);
        for (int i = 0; i < intentWordWithFrequencyCountList.size(); i++) {
            switch (intentWordWithFrequencyCountList.get(i).getIntent()) {
                case "Knowledge":
                    noOfTermsInEachIntent[0]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyCountList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() *
                                intentWordWithFrequencyCountList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount()
                                * intentWordWithFrequencyCountList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[0] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Comprehension":
                    noOfTermsInEachIntent[1]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyCountList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[1] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Application":
                    noOfTermsInEachIntent[2]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyCountList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[2] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Analysis":
                    noOfTermsInEachIntent[3]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyCountList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[3] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Synthesis":
                    noOfTermsInEachIntent[4]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyCountList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    }
                    if (indicator != 0) {
                        confidenceScore[4] += (indicator / (indicator + counterIndicator)) * 100;
                    }
                    break;
                case "Evaluation":
                    noOfTermsInEachIntent[5]++;
                    indicator = 0;
                    counterIndicator = 0;
                    if (intentWordWithFrequencyCountList.get(i).getRelationship().equalsIgnoreCase("indicatorOf")) {
                        indicator = indicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
                    } else {
                        counterIndicator = counterIndicator + intentWordWithFrequencyCountList.get(i).getFrequencyCount() * intentWordWithFrequencyCountList.get(i).getWeight();
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

    // this method returns the intentLevel having maximum confidenceScoreA
    public String getIntentLevel() {
        double maxConfidenceScore = 0;
        String intentLevel = null;
        for (int i = 0; i < intentWithConfidenceScoresList.size(); i++) {
            if (intentWithConfidenceScoresList.get(i).getConfidenceScore() > maxConfidenceScore) {
                maxConfidenceScore = intentWithConfidenceScoresList.get(i).getConfidenceScore();
                intentLevel = intentWithConfidenceScoresList.get(i).getIntnet();
            }
        }
        if (intentLevel == null) {
            return "no intentLevel found";
        }
        return intentLevel;
    }

    // this method returns the maximum confidenceScoreA
    public double getConfidenceScore() {
        double maxConfidenceScore = 0;
        for (int i = 0; i < intentWithConfidenceScoresList.size(); i++) {
            if (intentWithConfidenceScoresList.get(i).getConfidenceScore() > maxConfidenceScore) {
                maxConfidenceScore = intentWithConfidenceScoresList.get(i).getConfidenceScore();
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

    // This method returns the webAnalyticsResult object
    public WebAnalysisResult getWebAnalysisResult() throws IOException {
        WebAnalysisResult webAnalysisResult = new WebAnalysisResult();
        this.allIntentTermNodesWithFrequencyCount = null;
        this.htmlTagWithContentAndScoreList = null;
        this.intentWithConfidenceScoresList = null;
        this.intentWordWithFrequencyCountList = null;
        allIntentTermNodesWithFrequencyCount = new ArrayList<>(intentService.getAllIntentWords());
        System.out.println("getWebAnalysisResult all intent term nodes");
        System.out.println(allIntentTermNodesWithFrequencyCount);
        htmlTagWithContentAndScoreList = getAllHtmlTagsWithContentAndScore();
        System.out.println("getWebAnalysisResult html tag");
        System.out.println(htmlTagWithContentAndScoreList);
        intentWordWithFrequencyCountList = getIntentWordWithFrequencyCount();
        System.out.println("getWebAnalysisResult intent word with freq count");
        System.out.println(intentWordWithFrequencyCountList);
        intentWithConfidenceScoresList = getConfidenceScoreOfMostAccurateIntents();
        System.out.println("getWebAnalysisResult intent with conf score");
        System.out.println(intentWithConfidenceScoresList);
        webAnalysisResult.setCodeSnippets(webDocumentProviderService.getWebDocument().getCodeSnippets());
        webAnalysisResult.setConceptName(webDocumentProviderService.getWebDocument().getConceptName());
        webAnalysisResult.setDescription(webDocumentProviderService.getWebDocument().getDescription());
        webAnalysisResult.setDomain(webDocumentProviderService.getWebDocument().getDomain());
        webAnalysisResult.setId(webDocumentProviderService.getWebDocument().getId());
        webAnalysisResult.setImageCount(webDocumentProviderService.getWebDocument().getImageCount());
        webAnalysisResult.setKeywords(webDocumentProviderService.getWebDocument().getKeywords());
        webAnalysisResult.setLink(webDocumentProviderService.getWebDocument().getLink());
        webAnalysisResult.setTitle(webDocumentProviderService.getWebDocument().getTitle());
        webAnalysisResult.setConfidenceScore(getConfidenceScore());
        webAnalysisResult.setIntentLevel(getIntentLevel());
        return webAnalysisResult;
    }

    public void setIntents(String[] intents) {
        this.intents = intents;
    }

    public void setHtmlTags(String[] htmlTags) {
        this.htmlTags = htmlTags;
    }

    public void setHtmlTagScores(int[] htmlTagScores) {
        this.htmlTagScores = htmlTagScores;
    }

    public void setHtmlTagWithContentAndScoreList(List<HtmlTagWithContentAndScore> htmlTagWithContentAndScoreList) {
        this.htmlTagWithContentAndScoreList = htmlTagWithContentAndScoreList;
    }

    public void setIntentWithConfidenceScoresList(List<IntentWithConfidenceScore> intentWithConfidenceScoresList) {
        this.intentWithConfidenceScoresList = intentWithConfidenceScoresList;
    }

    public void setIntentWordWithFrequencyCountList(List<IntentWordWithFrequencyCount> intentWordWithFrequencyCountList) {
        this.intentWordWithFrequencyCountList = intentWordWithFrequencyCountList;
    }

    public void setAllIntentTermNodesWithFrequencyCount(List<IntentWordWithFrequencyCount> allIntentTermNodesWithFrequencyCount) {
        this.allIntentTermNodesWithFrequencyCount = allIntentTermNodesWithFrequencyCount;
    }
}
