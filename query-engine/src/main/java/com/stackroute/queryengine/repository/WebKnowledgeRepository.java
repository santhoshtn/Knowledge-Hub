package com.stackroute.queryengine.repository;

import com.stackroute.queryengine.domain.WebAnalyticsKnowledge;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;


public interface WebKnowledgeRepository extends Neo4jRepository<WebAnalyticsKnowledge,String> {

    @Query("MATCH p=(c:Concept{name:{0}})-[r:knowledgeOf]->() RETURN p")
    Collection<WebAnalyticsKnowledge> getWebKnowledgeNode(String concept, String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:comprehensionOf]->() RETURN p")
    Collection<WebAnalyticsKnowledge> getWebComprehensionNode(String concept,String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:applicationOf]->() RETURN p")
    Collection<WebAnalyticsKnowledge> getWebApplicationNode(String concept, String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:analysisOf]->() RETURN p")
    Collection<WebAnalyticsKnowledge> getWebAnalysisNode(String concept,String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:synthesisOf]->() RETURN p")
    Collection<WebAnalyticsKnowledge> getWebSynthesisNode(String concept,String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:evaluationOf]->() RETURN p")
    Collection<WebAnalyticsKnowledge> getWebEvaluationNode(String concept,String intentLevel);
}
