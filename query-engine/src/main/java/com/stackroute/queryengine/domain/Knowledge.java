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
public class Knowledge implements Comparable<Knowledge>, Serializable {

    @Id
    private String paragraphId;
    private String name;  //paragraphContent(given as name to identify in neo4j database easily)
    private String documentId;
    private String domain;
    private String concept;
    private String intentLevel;
    private double confidenceScore;

    @Override
    public int compareTo(Knowledge m)
    {
        if(this.confidenceScore<m.confidenceScore)
            return -1;
        else if(m.confidenceScore<this.confidenceScore)
            return 1;
        return 0;
    }
}