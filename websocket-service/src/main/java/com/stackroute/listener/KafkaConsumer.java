package com.stackroute.listener;

import com.stackroute.domain.*;
import com.stackroute.service.WebSocketService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {
    private KafkaProducer kafkaProducer;

    private WebSocketService webSocketService;


    @Autowired
    SimpMessagingTemplate template;



    @Autowired
    public KafkaConsumer(WebSocketService webSocketService,
                         KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.webSocketService = webSocketService;
    }

    @KafkaListener(topics = "QueryEngineResults", groupId = "group_id")
    public void consume(String  message){

        System.out.println(message);

        List<String> recommendations=new ArrayList<>();
        List<Knowledge> knowledges =new ArrayList<>();
        List<WebAnalyticsKnowledge> webAnalyticsKnowledges=new ArrayList<>();
        List<NlpResultFrequency> nlpResultFrequencies=new ArrayList<>();
        List<SearchFrequency> searchFrequencies=new ArrayList<>();

        JSONObject object = new JSONObject(message);

        JSONArray jsonresults = object.getJSONArray("result");
        JSONArray jsonwebResults =object.getJSONArray("webResult");
        JSONArray jsonrecommendations =object.getJSONArray("recommendations");
        JSONArray jsonnlpResultFrequencies =object.getJSONArray("nlpResultFrequencies");
        JSONArray jsonsearchFrequencies=object.getJSONArray("searchFrequencies");



        String sessionId=object.get("sessionId").toString();

        System.out.println("sessionId"+sessionId);
        System.out.println("Inside json"+jsonnlpResultFrequencies.toString());




//        System.out.println("json recommendations");
//        System.out.println(jsonrecommendations);
//        if(jsonrecommendations!=null) {
//             int id;
//             String identity;
//             String name;
//             String parentId;
//             String type;
//             String classType;
//             String relation;
//             String context;
//
//            for (int i = 0; i < jsonrecommendations.length(); i++) {
//                JSONObject recommendation = jsonwebResults.getJSONObject(i);
//
////                if(recommendation.get("id")!=null)
////                {
////                     id=Integer.parseInt(recommendation.get("id").toString());
////                }
////                else
////                {
////                     id=0;
////                }
////                if(recommendation.get("identity")!=null)
////                {
////                     identity=recommendation.get("identity").toString();
////                }
////                else
////                {
////                     identity=null;
////                }
//                if(recommendation.get("name")!=null)
//                {
//                     name=recommendation.get("name").toString();
//                }
//                else
//                {
//                     name=null;
//                }
////                if(recommendation.get("parentId")!=null)
////                {
////                     parentId=recommendation.get("parentId").toString();
////                }
////                else
////                {
////                     parentId=null;
////                }
//                if(recommendation.get("type")!=null)
//                {
//                     type=recommendation.get("type").toString();
//                }
//                else
//                {
//                     type=null;
//                }
//                if(recommendation.get("classType")!=null)
//                {
//                     classType=recommendation.get("classType").toString();
//                }
//                else
//                {
//                     classType=null;
//                }
//                if(recommendation.get("relation")!=null)
//                {
//                     relation=recommendation.get("relation").toString();
//                }
//                else
//                {
//                     relation=null;
//                }
//                if(recommendation.get("context")!=null)
//                {
//                     context=recommendation.get("context").toString();
//                }
//                else
//                {
//                     context=null;
//                }
//
//
//                Concept concept = new Concept(
//                    name,type,classType,relation,context);
//                concepts.add(concept);
//            }
//        }
//
//
        if(jsonrecommendations!=null)
        {
            for(int i=0;i<jsonrecommendations.length();i++)
            {

                recommendations.add(jsonrecommendations.get(i).toString());
            }
        }

        if(jsonwebResults!=null) {
            for (int i = 0; i < jsonwebResults.length(); i++) {
                JSONObject webresult = jsonwebResults.getJSONObject(i);

                WebAnalyticsKnowledge webAnalyticsKnowledge = new WebAnalyticsKnowledge(
                        webresult.get("webAnalyticsId").toString(),
                        webresult.get("domain").toString(),
                        webresult.get("link").toString(),
                        webresult.get("conceptName").toString(),
                        webresult.get("keywords").toString(),
                        Integer.parseInt(webresult.get("imageCount").toString()),
                        Float.parseFloat(webresult.get("codePercentage").toString()),
                        webresult.get("title").toString(),
                        webresult.get("description").toString(),
                        webresult.get("intentLevel").toString(),
                        Double.parseDouble(webresult.get("confidenceScore").toString())
                );
                webAnalyticsKnowledges.add(webAnalyticsKnowledge);

            }
        }

        if(jsonresults!=null) {
            for (int i = 0; i < jsonresults.length(); i++) {
                JSONObject result = jsonresults.getJSONObject(i);
                Knowledge knowledge = new Knowledge(result.get("paragraphId").toString(),
                        result.get("name").toString(),
                        result.get("documentId").toString(),
                        result.get("domain").toString(),
                        result.get("concept").toString(),
                        result.get("intentLevel").toString(),
                        Double.parseDouble(result.get("confidenceScore").toString()));
                knowledges.add(knowledge);
            }
        }
        if(jsonnlpResultFrequencies!=null) {
            for (int i = 0; i < jsonnlpResultFrequencies.length(); i++) {
                JSONObject nlpResult = jsonnlpResultFrequencies.getJSONObject(i);

                NlpResultFrequency nlpResultFrequency=new NlpResultFrequency(
                        nlpResult.get("concept").toString(),
                        Integer.parseInt(nlpResult.get("frequency").toString())
                );
                nlpResultFrequencies.add(nlpResultFrequency);
            }
        }

        if(jsonsearchFrequencies!=null) {
            for (int i = 0; i < jsonsearchFrequencies.length(); i++) {
                JSONObject searchResult = jsonsearchFrequencies.getJSONObject(i);


                SearchFrequency searchFrequency=new SearchFrequency(
                        searchResult.get("searchString").toString(),
                        Integer.parseInt(searchResult.get("frequency").toString())
                );

                searchFrequencies.add(searchFrequency);
            }
        }








        JsonResult jsonResult=new JsonResult();
        jsonResult.setSessionId(sessionId);
        jsonResult.setNlpResultFrequencies(nlpResultFrequencies);
        jsonResult.setSearchFrequencies(searchFrequencies);
        jsonResult.setRecommendations(recommendations);
        jsonResult.setResult(knowledges);
        jsonResult.setWebResult(webAnalyticsKnowledges);

        System.out.println("FULL JSON OBJECT");
        System.out.println(jsonResult);

        template.convertAndSend("/topic/public/"+sessionId,webSocketService.sendMessageService(jsonResult));

    }



}