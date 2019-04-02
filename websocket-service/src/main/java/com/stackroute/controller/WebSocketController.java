package com.stackroute.controller;

import com.stackroute.domain.JsonResult;
import com.stackroute.listener.KafkaProducer;
import com.stackroute.domain.ChatMessage;
import com.stackroute.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class WebSocketController {
    private final SimpMessagingTemplate template;

    private WebSocketService webSocketService;
    private KafkaProducer kafkaProducer;

    @Autowired
    public WebSocketController(WebSocketService webSocketService,SimpMessagingTemplate template,KafkaProducer kafkaProducer ) {
        this.webSocketService = webSocketService;
        this.kafkaProducer=kafkaProducer;
        this.template = template;
    }


    /*same as @RequestMappingThe @MessageMapping annotation ensures
     *that if a message is sent to destination "/send/message"
     */


    @SendTo("/topic/public/{sessionId}")
    public JsonResult sendMessage(@Payload JsonResult jsonResult){
        return webSocketService.sendMessageService(jsonResult);
    }

    /*Method used for testing from postman*/
    @PostMapping("/addchat")
    public String addKnowledge(@RequestBody JsonResult jsonResult) {
        return kafkaProducer.postservice(jsonResult);
    }

}
