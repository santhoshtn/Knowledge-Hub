package com.stackroute.repository;

import com.stackroute.domain.Concept;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface ConceptRepository extends Neo4jRepository<Concept,Integer> {

        //get particular concept node form neo4j
        @Query("match(p:Concept{name:{0}}) return p")
        Collection<Concept> getConcept(String name);

}
