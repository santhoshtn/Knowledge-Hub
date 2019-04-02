package com.stackroute.listener;

import com.stackroute.service.WebSearchService;
import com.stackroute.domain.SearchDocument;
import com.stackroute.domain.UIDocument;
import com.stackroute.exception.DomainNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//This service will send Search Results to Kafka Message Bus with topic name = WebSearch
@Service
public class KafkaProducer {
    private WebSearchService webSearchService;

    @Autowired
    public KafkaProducer(WebSearchService webSearchService) {
        this.webSearchService = webSearchService;
    }

    @Autowired
    private KafkaTemplate<String, SearchDocument> kafkaTemplate2;

    private static final String TOPIC = "WebSearch";

    public String postservice(UIDocument uiDocument) throws IOException, DomainNotFoundException {
        List<SearchDocument> searchDocumentList=new ArrayList<>();
        searchDocumentList=webSearchService.getUrls(uiDocument);
        System.out.println("postservice = "+uiDocument.toString());
        for (int i = 0; i < searchDocumentList.size(); i++) {
            SearchDocument searchDocument;
            searchDocument = webSearchService.getUrls(uiDocument).get(i);
            kafkaTemplate2.send(TOPIC, searchDocument);
        }
        return "Published successfully";
    }
}
