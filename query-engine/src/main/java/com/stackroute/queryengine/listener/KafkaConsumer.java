package com.stackroute.queryengine.listener;


import com.stackroute.queryengine.domain.Knowledge;
import com.stackroute.queryengine.service.QueryEngineService;
import com.stackroute.queryengine.service.QueryEngineServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private QueryEngineService queryEngineService;
    private KafkaProducer kafkaProducer;


    @Autowired
    public KafkaConsumer(QueryEngineService queryEngineService, KafkaProducer kafkaProducer) {
        this.queryEngineService = queryEngineService;
        this.kafkaProducer = kafkaProducer;
    }


    //This method is used to consume json object from producer
    @KafkaListener(topics = "NLPResults", groupId = "group_id")
    public void consume(String message) {
        JSONObject object = (JSONObject) JSONValue.parse(message);

        System.out.println("Inside Kafka consume"+message);
        kafkaProducer.postservice(object.get("concept").toString(),object.get("intent").toString(),
                object.get("sessonId").toString());

        System.out.println("after kafka producer call");
    }
}