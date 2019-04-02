package com.stackroute.service;

import com.stackroute.domain.Concept;
import com.stackroute.domain.Terms;

import java.util.Collection;

public interface NodeCreatorService {

    public void createConceptNode(Concept concept);

    public Collection<String> getParentNode();

    public String getConceptNodeCount();

    public String getCountOfConcpets();

    public String insertRelationship(String name,String IntentLevel);

    public String insertConceptRelationship(String childName,String parentName);


    public Concept[] getPerticularNode(String name);

}
