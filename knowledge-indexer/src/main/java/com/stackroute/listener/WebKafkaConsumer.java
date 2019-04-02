package com.stackroute.listener;

import com.stackroute.domain.WebAnalyticsKnowledge;
import com.stackroute.service.WebKnowledgeIndexerServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WebKafkaConsumer {

    private WebKnowledgeIndexerServiceImpl webKnowledgeIndexerServiceImpl;

    @Autowired
    public WebKafkaConsumer(WebKnowledgeIndexerServiceImpl webKnowledgeIndexerServiceImpl) {
        this.webKnowledgeIndexerServiceImpl = webKnowledgeIndexerServiceImpl;
    }

    //This method is used to consume json object from producer
    @KafkaListener(topics = "WebAnalyticsResults", groupId = "group_id")
    public void consume(String message)
    {
        JSONObject object = (JSONObject) JSONValue.parse(message);

        WebAnalyticsKnowledge webAnalyticsKnowledge=new WebAnalyticsKnowledge(
          object.get("id").toString(),object.get("domain").toString(),object.get("link").toString(),
          object.get("conceptName").toString(),object.get("keywords").toString(),Integer.parseInt(object.get("imageCount").toString()),
          Double.parseDouble(object.get("codeSnippets").toString()),object.get("title").toString(),object.get("description").toString(),
          object.get("intentLevel").toString(),Double.parseDouble(object.get("confidenceScore").toString())
        );

       webKnowledgeIndexerServiceImpl.saveWebKnowledgeToDb(webAnalyticsKnowledge);
       webKnowledgeIndexerServiceImpl.addRelationship(webAnalyticsKnowledge.getConceptName(),
               webAnalyticsKnowledge.getWebAnalyticsId(),webAnalyticsKnowledge.getIntentLevel(),
               webAnalyticsKnowledge.getConfidenceScore());


    }
}
