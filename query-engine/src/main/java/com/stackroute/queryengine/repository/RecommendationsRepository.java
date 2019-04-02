package com.stackroute.queryengine.repository;

import com.stackroute.queryengine.domain.Concept;
import com.stackroute.queryengine.domain.WebAnalyticsKnowledge;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface RecommendationsRepository extends Neo4jRepository<Concept,String> {

    @Query("MATCH (c:Concept{name:{0}})-[r:subconceptOf]->(p) RETURN p.name")
    Collection<String> getNodesSubconcept(String concept);

    @Query("MATCH (p)-[r:subconceptOf]->(c:Concept{name:{0}}) RETURN p.name")
    Collection<String> getSubconceptNodes(String concept);



}
