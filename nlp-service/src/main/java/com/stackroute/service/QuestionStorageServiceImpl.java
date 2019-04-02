package com.stackroute.service;

import com.stackroute.domain.QueryQuestions;
import com.stackroute.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionStorageServiceImpl implements QuestionStorageService {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionStorageServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    /*This method saves the question in database and return saved question*/
    public QueryQuestions saveQuestion(String question) {
        String uniqueId = UUID.randomUUID().toString();
        QueryQuestions queryQuestions1 = new QueryQuestions(uniqueId,question);
        QueryQuestions savedQueryQuestions = questionRepository.save(queryQuestions1);
        return savedQueryQuestions;
    }
}
