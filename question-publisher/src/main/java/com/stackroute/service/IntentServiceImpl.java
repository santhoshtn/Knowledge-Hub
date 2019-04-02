package com.stackroute.service;

import com.stackroute.domain.Terms;
import com.stackroute.repository.IntentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntentServiceImpl implements IntentService {

    private IntentRepository intentRepository;

    @Autowired
    public IntentServiceImpl(IntentRepository intentRepository) {
        this.intentRepository = intentRepository;
    }

    public String createTermNode(Terms term) {

        intentRepository.save(term);

        return "Term node saved successfully";
    }


    public String getCount() {
        return intentRepository.getCount();
    }

    @Override
    public String createIntentLevelTermRelationship(String intentLevel, String termName) {
        intentRepository.createIntentRelationship(intentLevel,termName);
        return "IntentTermRelationship Created successfully";
    }
}
