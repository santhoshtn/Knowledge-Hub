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
public class Terms {

    @Id
    private String id3;
    private String name;
    private String parent_id;
    private String parent_node_type;
    private String type;
    private String relationship;
    private String weight;

}
