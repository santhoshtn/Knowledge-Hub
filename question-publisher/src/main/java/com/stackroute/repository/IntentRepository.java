package com.stackroute.repository;

import com.stackroute.domain.PublishQuestion;
import com.stackroute.domain.QueryQuestions;
import com.stackroute.domain.Terms;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends Neo4jRepository<Terms,String> {

    @Query("MATCH(u:Terms) RETURN Count(*)")
    String getCount();

    @Query("MATCH (p:Level{name:{0}}) MATCH (q:Terms{name:{1}}) CREATE(q)-[:termsOf]->(p)")
    String createIntentRelationship(String intentLevel,String termName);
}
