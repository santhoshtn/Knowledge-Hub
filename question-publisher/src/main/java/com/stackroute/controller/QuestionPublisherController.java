package com.stackroute.controller;

import com.stackroute.domain.PublishQuestion;
import com.stackroute.domain.Terms;
import com.stackroute.service.IntentService;
import com.stackroute.service.NlpService;
import com.stackroute.service.QuestionDeleterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class QuestionPublisherController {
    private QuestionDeleterService questionDeleterService;
    private IntentService intentService;
    private NlpService nlpService;

    public QuestionPublisherController(QuestionDeleterService questionDeleterService,
                                       IntentService intentService,
                                       NlpService nlpService) {
        this.questionDeleterService = questionDeleterService;
        this.intentService = intentService;
        this.nlpService = nlpService;

    }

    @DeleteMapping("deleteQuestion/{uniqueId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("uniqueId") String uniqueId) {

        ResponseEntity responseEntity;
        System.out.println("uniqueID: " + uniqueId);
        questionDeleterService.deleteQuestion(uniqueId);
        try {
            responseEntity = new ResponseEntity<String>("QueryQuestions is deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("publishQuestion")
    public ResponseEntity<String> postQuestion(@RequestBody PublishQuestion publishQuestion) {
        ResponseEntity<String> responseEntity;

        String neo4jId = intentService.getCount();
        int tempId=Integer.parseInt(neo4jId)+1;

        System.out.println("Controller post "+publishQuestion.toString());

        Terms term = new Terms();
        term.setId3(publishQuestion.getUniqueId());
        nlpService.setParagraph(publishQuestion.getQuestionString());
        String queryQuestion = nlpService.getQueryQuestionResults();
        term.setName(queryQuestion);
        term.setWeight("8");
        term.setType("term");
        term.setRelationship("indicatorOf");
        term.setParent_node_type(publishQuestion.getIntentLevel());
        if (publishQuestion.getIntentLevel().equalsIgnoreCase("knowledge")) {
            term.setParent_id("SPRING:2");
        } else if (publishQuestion.getIntentLevel().equalsIgnoreCase("comprehension")) {
            term.setParent_id("SPRING:3");
        } else if (publishQuestion.getIntentLevel().equalsIgnoreCase("application")) {
            term.setParent_id("SPRING:4");
        } else if (publishQuestion.getIntentLevel().equalsIgnoreCase("analysis")) {
            term.setParent_id("SPRING:5");
        } else if (publishQuestion.getIntentLevel().equalsIgnoreCase("synthesis")) {
            term.setParent_id("SPRING:6");
        } else if (publishQuestion.getIntentLevel().equalsIgnoreCase("evaluation")) {
            term.setParent_id("SPRING:7");
        }

        String message = intentService.createTermNode(term);
        String message1=intentService.createIntentLevelTermRelationship(term.getParent_node_type(),term.getName());

        System.out.println("message = "+message);
        System.out.println("message 1"+" "+message1);
        System.out.println("Controller post "+publishQuestion.toString());

        responseEntity = new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        return responseEntity;
    }
}
