//Kafka Consumer code

package com.stackroute.listener;

import com.stackroute.domain.SearchDocument;
import com.stackroute.domain.WebDocument;
import com.stackroute.service.WebDocumentService;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KafkaConsumer {
    private WebDocumentService webDocumentService;
    private KafkaProducer kafkaProducer;

    @Autowired
    public KafkaConsumer( WebDocumentService webDocumentService, KafkaProducer kafkaProducer) {
        this.webDocumentService = webDocumentService;
        this.kafkaProducer = kafkaProducer;
    }

    // It listens to topic name "ParagraphContents"
    @KafkaListener(topics = "WebSearch", groupId = "group_id")
    public void consume(String message) throws IOException {
                JSONObject object=(JSONObject) JSONValue.parse(message);
                //search document that has to be consumed
        SearchDocument searchDocument=new SearchDocument(object.get("id").toString(),object.get("conceptName").toString(), object.get("domain").toString(),object.get("url").toString());
        Document document =null;
        System.out.println("Web content extractor = " +message);
        URL url = new URL(searchDocument.getLink());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        if(code == 200){
            try {
                document = Jsoup.connect(searchDocument.getLink()).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .referrer("http://www.google.com").ignoreHttpErrors(true)
                        .get();
                //checking and avoiding youtube videos
                if (document!=null && (!searchDocument.getLink().contains("youtube.com"))) {
                    webDocumentService.sendSearchdoc(searchDocument);
                    webDocumentService.extractTitle(searchDocument);
                    webDocumentService.extractDescription(searchDocument);
                    webDocumentService.extractKeywords(searchDocument);
                    webDocumentService.extractImageCount(searchDocument);
                    webDocumentService.extractCodeSnippets(searchDocument);
                    WebDocument webDocument = webDocumentService.getContentExtractorResults();
                    //sending web document as kafka producer
                    kafkaProducer.postservice(webDocument);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
