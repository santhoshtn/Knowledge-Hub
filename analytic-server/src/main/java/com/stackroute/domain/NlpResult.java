package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NlpResult {
    // This JSON is used to store results of Nlp service.
    // The NlpResult is used by AnalyticService.
    private String clearedParagraph;
    private List<String> allTokenedSentences;
    private List<String> lemmaWords;
    private List<String> stemmedWords;
    private List<String> wordsWithOutStopWords;
    private String paragraphWithOutStopWords;
    private List<POSTagging> posTaggings;
    private List<String> nounWords;
    private List<String> verbWords;
}
