package com.stackroute.queryengine.controller;

import com.stackroute.queryengine.domain.Concept;
import com.stackroute.queryengine.domain.Knowledge;
import com.stackroute.queryengine.domain.WebAnalyticsKnowledge;
import com.stackroute.queryengine.service.QueryEngineServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class QueryEngineController {

    private QueryEngineServiceImpl queryEngineServiceImpl;

    @Autowired
    QueryEngineController(QueryEngineServiceImpl queryEngineServiceImpl) {
        this.queryEngineServiceImpl = queryEngineServiceImpl;
    }

    //Code used for testing the methods
    @GetMapping("/{concept}/{intentLevel}")
    public ResponseEntity<List<Knowledge>> getKnowledgeNode(@PathVariable("concept") String concept, @PathVariable("intentLevel") String intentLevel) {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<List<Knowledge>>((List<Knowledge>) queryEngineServiceImpl.getQueryResult(concept,intentLevel),HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/web/{concept}/{intentLevel}")
    public ResponseEntity<List<WebAnalyticsKnowledge>> getWebAnalyticsKnowledgeNode(@PathVariable("concept") String concept, @PathVariable("intentLevel") String intentLevel) {
        ResponseEntity responseEntity;
        List<WebAnalyticsKnowledge> list=(List<WebAnalyticsKnowledge>) queryEngineServiceImpl.getWebQueryResult(concept,intentLevel);
        Collections.sort(list,Collections.reverseOrder());
        responseEntity = new ResponseEntity<List<WebAnalyticsKnowledge>>(list,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/recommendations/{concept}")
    public ResponseEntity<List<String>> getRecommendations(@PathVariable("concept") String concept) {
        ResponseEntity responseEntity;
        List<String> recommendationsList1=(List<String>) queryEngineServiceImpl.getRecommendations(concept);
        List<String> recommendationsList2=(List<String>) queryEngineServiceImpl.Recommendations(concept);
        List<String> recommendations = new ArrayList<String>();
        recommendations.addAll(recommendationsList1);
        recommendations.addAll(recommendationsList2);
        responseEntity = new ResponseEntity<List<String>>(recommendations,HttpStatus.OK);
        return responseEntity;
    }


}