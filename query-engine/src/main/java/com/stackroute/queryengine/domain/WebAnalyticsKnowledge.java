package com.stackroute.queryengine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebAnalyticsKnowledge implements Comparable<WebAnalyticsKnowledge>, Serializable {

    @Id
    private String webAnalyticsId;
    private String domain;
    private String link;
    private String conceptName;
    private String keywords;
    private int imageCount;
    private float codePercentage;
    private String title;
    private String description;
    private String intentLevel;
    private double confidenceScore;

    @Override
    public int compareTo(WebAnalyticsKnowledge m)
    {
        if(this.confidenceScore<m.confidenceScore)
            return -1;
        else if(m.confidenceScore<this.confidenceScore)
            return 1;
        return 0;
    }
}
