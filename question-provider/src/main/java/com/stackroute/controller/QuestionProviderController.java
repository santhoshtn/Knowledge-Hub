package com.stackroute.controller;

import com.stackroute.domain.QueryQuestions;
import com.stackroute.service.QuestionProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class QuestionProviderController {
    private QuestionProviderService questionProviderService;

    @Autowired
    public QuestionProviderController(QuestionProviderService questionProviderService) {
        this.questionProviderService = questionProviderService;
    }

    @GetMapping("getAllQueryQuestions")
    public QueryQuestions[] getAllQueryQuestions() {
        List<QueryQuestions> queryQuestionsList = new ArrayList<>(questionProviderService.getAllQueryQuestions());
        QueryQuestions[] queryQuestionsArray = new QueryQuestions[queryQuestionsList.size()];
        for(int i=0;i<queryQuestionsArray.length;i++){
            queryQuestionsArray[i] = queryQuestionsList.get(i);
        }
        return queryQuestionsArray;
    }
}
