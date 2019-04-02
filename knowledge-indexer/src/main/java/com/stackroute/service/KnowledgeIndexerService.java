package com.stackroute.service;

import com.stackroute.domain.Knowledge;

import java.util.Collection;

public interface KnowledgeIndexerService {
    public void saveKnowledgeToDb(Knowledge knowledge);
    public void addRelationship(String concept, String paragraphId, String intentLevel,double confidenceScore);
}
