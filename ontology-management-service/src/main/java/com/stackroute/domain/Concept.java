package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Concept {

    @Id
    private String Id1;
    private String classType;
    private String context;
    private String name;
    private String parent_id;
    private String relation;
    private String type;



}
