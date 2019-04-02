package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Concept implements Serializable {
//    @Id
//    private int id;
//    private String identity;
    private String name;
//    private String parentId;
    private String type;
    private String classType;
    private String relation;
    private String context;
}