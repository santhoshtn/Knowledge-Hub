package com.stackroute.service;


import com.stackroute.domain.WebAnalyticsKnowledge;

public interface WebKnowledgeIndexerService {
    public void saveWebKnowledgeToDb(WebAnalyticsKnowledge webAnalyticsKnowledge);
    public void addRelationship(String concept, String webAnalyticsId, String intentLevel,double confidenceScore);
}
