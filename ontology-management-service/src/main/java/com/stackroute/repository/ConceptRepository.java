package com.stackroute.repository;

import com.stackroute.domain.Concept;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ConceptRepository extends Neo4jRepository<Concept, String> {


    @Query("match(c:Concept) return c")
    Collection<Concept> getAllNodes();


    @Query("MATCH(c:Concept) RETURN Count(*)")
    String getCountOfConcepts();

    @Query("match(c:Concept{name:{0}}) return c")
    Concept[] getPerticularNode(String name);

    @Query("MATCH(u:Concept) RETURN Count(*)")
    String getConceptNodeCount();

    @Query("MATCH(u:Concept{name:{0}}) MATCH(n:Concept{name:{1}}) CREATE(u)-[:subconceptOf]->(n)")
    String insertConceptRelationship(String childName,String parentName);
}
