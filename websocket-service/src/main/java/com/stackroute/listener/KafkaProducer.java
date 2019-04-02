package com.stackroute.listener;
import com.stackroute.domain.ChatMessage;
import com.stackroute.domain.JsonResult;
import com.stackroute.service.WebSocketService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private WebSocketService webSocketService;

    @Autowired
    public KafkaProducer(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate2;

    private static final String TOPIC="QueryEngineResults";



    public String postservice(JsonResult jsonResult)
    {
        kafkaTemplate2.send(TOPIC,jsonResult);

        return "Published successfully";
    }

}