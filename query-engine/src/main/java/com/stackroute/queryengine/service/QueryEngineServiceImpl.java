package com.stackroute.queryengine.service;

import com.stackroute.queryengine.domain.Concept;
import com.stackroute.queryengine.domain.Knowledge;
import com.stackroute.queryengine.domain.WebAnalyticsKnowledge;
import com.stackroute.queryengine.repository.KnowledgeRepository;
import com.stackroute.queryengine.repository.RecommendationsRepository;
import com.stackroute.queryengine.repository.WebKnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryEngineServiceImpl implements QueryEngineService {
    private KnowledgeRepository knowledgeRepository;
    private WebKnowledgeRepository webKnowledgeRepository;
    private RecommendationsRepository recommendationsRepository;

    public QueryEngineServiceImpl() {
    }

    @Autowired
    public QueryEngineServiceImpl(KnowledgeRepository knowledgeRepository,WebKnowledgeRepository webKnowledgeRepository,RecommendationsRepository recommendationsRepository){
        this.knowledgeRepository=knowledgeRepository;
        this.webKnowledgeRepository=webKnowledgeRepository;
        this.recommendationsRepository=recommendationsRepository;
    }


    @Override
    @Cacheable(value = "querycache")
    public Iterable<Knowledge> getQueryResult(String concept, String intentLevel) {

        System.out.println("uday");
        if(intentLevel.equalsIgnoreCase("Knowledge"))
        {
            return  knowledgeRepository.getKnowledgeNode(concept,intentLevel);
        }
        else if(intentLevel.equalsIgnoreCase("Comprehension"))
        {
            return  knowledgeRepository.getComprehensionNode(concept,intentLevel);
        }
        else if(intentLevel.equalsIgnoreCase("Analysis"))
        {
            return  knowledgeRepository.getAnalysisNode(concept,intentLevel);
        }

        else if(intentLevel.equalsIgnoreCase("Application"))
        {
            return  knowledgeRepository.getApplicationNode(concept,intentLevel);
        }

        else if(intentLevel.equalsIgnoreCase("Synthesis"))
        {
            return  knowledgeRepository.getSynthesisNode(concept,intentLevel);
        }
        else if(intentLevel.equalsIgnoreCase("Evaluation"))
        {
            return  knowledgeRepository.getEvaluationNode(concept,intentLevel);
        }
        else
            return  knowledgeRepository.getKnowledgeNode(concept,intentLevel);

    }

    @Override
    @Cacheable(value = "webquerycache")
    public Iterable<WebAnalyticsKnowledge> getWebQueryResult(String concept, String intentLevel) {
        if(intentLevel.equalsIgnoreCase("Knowledge"))
        {
            return  webKnowledgeRepository.getWebKnowledgeNode(concept,intentLevel);
        }
        else if(intentLevel.equalsIgnoreCase("Comprehension"))
        {
            return  webKnowledgeRepository.getWebComprehensionNode(concept,intentLevel);
        }
        else if(intentLevel.equalsIgnoreCase("Analysis"))
        {
            return  webKnowledgeRepository.getWebAnalysisNode(concept,intentLevel);
        }

        else if(intentLevel.equalsIgnoreCase("Application"))
        {
            return  webKnowledgeRepository.getWebApplicationNode(concept,intentLevel);
        }

        else if(intentLevel.equalsIgnoreCase("Synthesis"))
        {
            return  webKnowledgeRepository.getWebSynthesisNode(concept,intentLevel);
        }
        else if(intentLevel.equalsIgnoreCase("Evaluation"))
        {
            return  webKnowledgeRepository.getWebEvaluationNode(concept,intentLevel);
        }
        else
            return  webKnowledgeRepository.getWebKnowledgeNode(concept,intentLevel);
    }

    @Override
    @Cacheable(value = "recommendation1")
    public Iterable<String> getRecommendations(String concept) {
        return recommendationsRepository.getNodesSubconcept(concept);
    }

    @Override
    @Cacheable(value = "recommendation2")
    public Iterable<String> Recommendations(String concept) {
        return recommendationsRepository.getSubconceptNodes(concept);
    }


    @Scheduled(cron = "0 */5 * ? * *")
    @CacheEvict(value = {"querycache", "webquerycache","recommendation1","recommendation2"}, allEntries = true)
    public void clearrediscache(){

    }


}
