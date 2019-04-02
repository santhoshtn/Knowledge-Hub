package com.stackroute.repository;

import com.stackroute.domain.WebAnalyticsKnowledge;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface WebAnalyticsKnowledgeRepository extends Neo4jRepository<WebAnalyticsKnowledge,String> {

    //get all knowledge nodes
    @Query("MATCH (u:WebAnalyticsKnowledge) RETURN u")
    Collection<WebAnalyticsKnowledge> getAllKnowledge();

    //get particular knowledge node
    @Query("match(k:WebAnalyticsKnowledge{paragraphId:{0},intentLevel:{1}}) return k")
    Collection<WebAnalyticsKnowledge> getWebAnalyticsKnowledge(String webAnalyticsId,String intentLevel);

    //insert knowledge relationship
    @Query("match(p:Concept{name:{0}}) match(k:WebAnalyticsKnowledge{webAnalyticsId:{1},intentLevel:{2}}) CREATE(p)-[:knowledgeOf {confidenceScore:{3}}]->(k)")
    Collection<WebAnalyticsKnowledge> insertWebKnowledgeRelationship(String name,String webAnalyticsId,String intentLevel,double confidenceScore);

    //insert comprehension relationship
    @Query("match(p:Concept{name:{0}}) match(k:WebAnalyticsKnowledge{webAnalyticsId:{1},intentLevel:{2}}) CREATE(p)-[:comprehensionOf {confidenceScore:{3}}]->(k)")
    Collection<WebAnalyticsKnowledge> insertWebComprehensionRelationship(String name,String webAnalyticsId,String intentLevel,double confidenceScore);

    //insert application relationship
    @Query("match(p:Concept{name:{0}}) match(k:WebAnalyticsKnowledge{webAnalyticsId:{1},intentLevel:{2}}) CREATE(p)-[:applicationOf {confidenceScore:{3}}]->(k)")
    Collection<WebAnalyticsKnowledge> insertWebApplicationRelationship(String name,String webAnalyticsId,String intentLevel,double confidenceScore);

    //insert analysis relationship
    @Query("match(p:Concept{name:{0}}) match(k:WebAnalyticsKnowledge{webAnalyticsId:{1},intentLevel:{2}}) CREATE(p)-[:analysisOf {confidenceScore:{3}}]->(k)")
    Collection<WebAnalyticsKnowledge> insertWebAnalysisRelationship(String name,String webAnalyticsId,String intentLevel,double confidenceScore);

    //insert synthesis relationship
    @Query("match(p:Concept{name:{0}}) match(k:WebAnalyticsKnowledge{webAnalyticsId:{1},intentLevel:{2}}) CREATE(p)-[:synthesisOf {confidenceScore:{3}}]->(k)")
    Collection<WebAnalyticsKnowledge> insertWebSynthesisRelationship(String name,String webAnalyticsId,String intentLevel,double confidenceScore);

    //insert evaluation relationship
    @Query("match(p:Concept{name:{0}}) match(k:WebAnalyticsKnowledge{webAnalyticsId:{1},intentLevel:{2}}) CREATE(p)-[:evaluationOf {confidenceScore:{3}}]->(k)")
    Collection<WebAnalyticsKnowledge> insertWebEvaluationRelationship(String name,String webAnalyticsId,String intentLevel,double confidenceScore);

}
