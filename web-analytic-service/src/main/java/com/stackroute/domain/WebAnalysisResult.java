package com.stackroute.domain;

import org.json.simple.JSONObject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebAnalysisResult {
    private String id;
    private String domain;
    private String link;
    private String conceptName;
    private String keywords;
    private int imageCount;
    private double codeSnippets;
    private String title;
    private String description;
    private String intentLevel;
    private double confidenceScore;
}