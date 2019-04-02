package com.stackroute.service;

import com.stackroute.domain.IntentWordWithFrequencyCount;
import com.stackroute.domain.Terms;
import com.stackroute.repository.IntentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IntentServiceImpl implements IntentService {
    private IntentRepository intentRepository;
    private ArrayList<Terms> allTermNodes;

    @Autowired
    public IntentServiceImpl(IntentRepository intentRepository) {
        this.intentRepository = intentRepository;
    }

    //This method extracts all the intent words form Neo4J intent graph
    // and intializes the frequency = 0  for each intent word
    public ArrayList<IntentWordWithFrequencyCount> getAllIntentWords() {
        this.allTermNodes = new ArrayList<>(intentRepository.getAllTerms());
        ArrayList<IntentWordWithFrequencyCount> allIntentWordWithFrequencyCounts = new ArrayList<>();
        for (int i = 0; i < allTermNodes.size(); i++) {
            allIntentWordWithFrequencyCounts.add(new IntentWordWithFrequencyCount(allTermNodes.get(i).getName(),
                    0,
                    allTermNodes.get(i).getParent_node_type(),
                    allTermNodes.get(i).getRelationship(),
                    Double.parseDouble(allTermNodes.get(i).getWeight())));
        }
        return allIntentWordWithFrequencyCounts;
    }

    public void setAllTermNodes(ArrayList<Terms> allTermNodes) {
        this.allTermNodes = allTermNodes;
    }
}