package com.stackroute.service;

import com.stackroute.domain.WebAnalyticsKnowledge;
import com.stackroute.repository.WebAnalyticsKnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WebKnowledgeIndexerServiceImpl implements WebKnowledgeIndexerService {


    private WebAnalyticsKnowledgeRepository webAnalyticsKnowledgeRepository;

    @Autowired
    public WebKnowledgeIndexerServiceImpl(WebAnalyticsKnowledgeRepository webAnalyticsKnowledgeRepository){
        this.webAnalyticsKnowledgeRepository=webAnalyticsKnowledgeRepository;
    }

    //method to save knowledge nodes to neo4j
    public void saveWebKnowledgeToDb(WebAnalyticsKnowledge webAnalyticsKnowledge) {
        webAnalyticsKnowledgeRepository.save(webAnalyticsKnowledge);
    }

    //method to add relationship between ontology nodes and knowledge nodes
    public void addRelationship(String concept, String webAnalyticsId, String intentLevel, double confidenceScore)
    {
        if(intentLevel.equalsIgnoreCase("knowledge"))
            webAnalyticsKnowledgeRepository.insertWebKnowledgeRelationship(concept,webAnalyticsId,intentLevel,confidenceScore);

        else if(intentLevel.equalsIgnoreCase("comprehension"))
            webAnalyticsKnowledgeRepository.insertWebComprehensionRelationship(concept,webAnalyticsId,intentLevel,confidenceScore);

        else if(intentLevel.equalsIgnoreCase("application"))
            webAnalyticsKnowledgeRepository.insertWebApplicationRelationship(concept,webAnalyticsId,intentLevel,confidenceScore);

        else if(intentLevel.equalsIgnoreCase("analysis"))
            webAnalyticsKnowledgeRepository.insertWebAnalysisRelationship(concept,webAnalyticsId,intentLevel,confidenceScore);

        else if(intentLevel.equalsIgnoreCase("synthesis"))
            webAnalyticsKnowledgeRepository.insertWebSynthesisRelationship(concept,webAnalyticsId,intentLevel,confidenceScore);

        else if(intentLevel.equalsIgnoreCase("evaluation"))
            webAnalyticsKnowledgeRepository.insertWebEvaluationRelationship(concept,webAnalyticsId,intentLevel,confidenceScore);

    }
}
