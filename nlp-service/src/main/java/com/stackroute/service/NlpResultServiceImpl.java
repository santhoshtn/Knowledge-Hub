package com.stackroute.service;

import com.stackroute.domain.NlpResult;
import com.stackroute.repository.NlpResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NlpResultServiceImpl implements NlpResultService {
    NlpResultRepository nlpresultRepository;

    @Autowired
    public NlpResultServiceImpl(NlpResultRepository nlpresultRepository) {
        this.nlpresultRepository = nlpresultRepository;
    }

    @Override
    public String saveNlpResult(NlpResult nlpresult){
        if(nlpresult.getIntent()!= null && !nlpresult.getIntent().isEmpty() &&
                nlpresult.getConcept()!=null && !nlpresult.getConcept().isEmpty() &&
                nlpresult!=null &&
                nlpresult.getSessonId()!=null && !nlpresult.getSessonId().isEmpty()) {
            if (nlpresultRepository.existsBySessonId(nlpresult.getSessonId())) {
                System.out.println("NlpResult already exits");
            }
            NlpResult savedNlpResult = nlpresultRepository.save(nlpresult);
            if (savedNlpResult == null) {
                System.out.println("NlpResult already exits");
            }
            return "saved successfully";
        }
        else
            return "not able to save";


    }
}