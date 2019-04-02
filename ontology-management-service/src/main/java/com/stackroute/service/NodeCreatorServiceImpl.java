package com.stackroute.service;

import com.stackroute.domain.Concept;
import com.stackroute.domain.Terms;
import com.stackroute.repository.ConceptRepository;
import com.stackroute.repository.IntentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NodeCreatorServiceImpl implements NodeCreatorService {

    private ConceptRepository conceptRepository;
    private IntentRepository intentRepository;

    @Autowired
    public NodeCreatorServiceImpl(ConceptRepository conceptRepository,IntentRepository intentRepository) {
        this.conceptRepository = conceptRepository;
        this.intentRepository=intentRepository;
    }


    public void createConceptNode(Concept concept) {

        conceptRepository.save(concept);
    }




    public ArrayList<String> getParentNode() {
        ArrayList<Concept> concepts = new ArrayList<>(conceptRepository.getAllNodes());
        ArrayList<String> conceptNames = new ArrayList<>();
        for (int i = 0; i < concepts.size(); i++) {
            if(!conceptNames.contains(concepts.get(i).getContext()))
            {
                conceptNames.add(concepts.get(i).getContext());
            }

        }
        return conceptNames;
    }

    @Override
    public String getConceptNodeCount() {
        return conceptRepository.getConceptNodeCount();
    }

    @Override
    public String getCountOfConcpets() {
        return conceptRepository.getCountOfConcepts();
    }

    @Override
    public String insertRelationship(String name, String IntentLevel) {
        intentRepository.insertRelationship(name,IntentLevel);
        return "Relationship inserted successfully";
    }

    @Override
    public String insertConceptRelationship(String childName, String parentName) {
        conceptRepository.insertConceptRelationship(childName,parentName);
        return "Relationship inserted successfully";
    }

    @Override
    public Concept[] getPerticularNode(String name) {
        return conceptRepository.getPerticularNode(name);
    }


}
