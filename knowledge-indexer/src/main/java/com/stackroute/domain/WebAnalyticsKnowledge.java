package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebAnalyticsKnowledge {

    @Id
    private String webAnalyticsId;
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
