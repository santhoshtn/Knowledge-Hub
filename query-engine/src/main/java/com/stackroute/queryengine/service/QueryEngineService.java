package com.stackroute.queryengine.service;

import com.stackroute.queryengine.domain.Concept;
import com.stackroute.queryengine.domain.Knowledge;
import com.stackroute.queryengine.domain.WebAnalyticsKnowledge;

public interface QueryEngineService {
    public Iterable<Knowledge> getQueryResult(String concept, String intentLevel);
    public Iterable<WebAnalyticsKnowledge> getWebQueryResult(String concept, String intentLevel);

    public Iterable<String> getRecommendations(String concept);

    public Iterable<String> Recommendations(String concept);

}
