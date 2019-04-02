package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResult {
    // This is the format of AnalysisResult Json object which is put in kafka message bus after analysing paragraph
    // This Json is taken by knowledge-indexer
    private String paragraphId;
    private String paragraphContent;
    private String documentId;
    private String domain;
    private String concept;
    private String intentLevel;
    private double confidenceScore;
}
