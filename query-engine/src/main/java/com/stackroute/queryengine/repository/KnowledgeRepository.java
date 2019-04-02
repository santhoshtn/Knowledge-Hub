package com.stackroute.queryengine.repository;

import com.stackroute.queryengine.domain.Knowledge;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface KnowledgeRepository extends Neo4jRepository<Knowledge,String> {

    //get particular knowledge node
//    @Query("match(c:Concept{name:{0}}) match(k:Knowledge{intentLevel:{1}}) return k")
//    Collection<Knowledge> getKnowledgeNode(String concept, String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:knowledgeOf]->() RETURN p")
    Collection<Knowledge> getKnowledgeNode(String concept,String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:comprehensionOf]->() RETURN p")
    Collection<Knowledge> getComprehensionNode(String concept,String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:applicationOf]->() RETURN p")
    Collection<Knowledge> getApplicationNode(String concept,String intentLevel);


    @Query("MATCH p=(c:Concept{name:{0}})-[r:analysisOf]->() RETURN p")
    Collection<Knowledge> getAnalysisNode(String concept,String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:synthesisOf]->() RETURN p")
    Collection<Knowledge> getSynthesisNode(String concept,String intentLevel);

    @Query("MATCH p=(c:Concept{name:{0}})-[r:evaluationOf]->() RETURN p")
    Collection<Knowledge> getEvaluationNode(String concept,String intentLevel);

}
