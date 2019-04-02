package com.stackroute.service;

import com.stackroute.domain.Concept;
import com.stackroute.repository.ConceptRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConceptServiceImpl implements ConceptSerive {
    private ConceptRepository conceptRepository;

    public ConceptServiceImpl(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }

    // This method is used to fetch the concepts from Neo4J graph
    public ArrayList<String> getConcepts() {
        ArrayList<Concept> concepts = new ArrayList<>(conceptRepository.getAllConcepts());
        ArrayList<String> conceptNames = new ArrayList<>();
        for (int i = 0; i < concepts.size(); i++) {
            conceptNames.add(concepts.get(i).getName());
        }
        System.out.println("-------------getConcepts----------------");
        System.out.println(conceptNames);
        return conceptNames;
    }
}