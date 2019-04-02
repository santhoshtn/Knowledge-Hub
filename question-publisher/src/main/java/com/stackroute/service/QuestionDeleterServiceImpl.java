package com.stackroute.service;

import com.stackroute.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionDeleterServiceImpl implements QuestionDeleterService {
    private QuestionsRepository questionsRepository;

    @Autowired
    public QuestionDeleterServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public void deleteQuestion(String id){
        System.out.println("delete"+id);
        questionsRepository.deleteById(id);
    }
}
