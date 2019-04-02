package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
@Builder
public class PublishQuestion {
    @Id
    private String uniqueId;
    private String intentLevel;
    public String questionString;
}
