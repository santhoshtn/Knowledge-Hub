package com.stackroute.service;

import com.stackroute.domain.IntentWordWithFrequencyCount;
import com.stackroute.domain.Terms;

import java.util.ArrayList;
import java.util.Collection;

public interface IntentService {
    public Collection<IntentWordWithFrequencyCount> getAllIntentWords();

    public void setAllTermNodes(ArrayList<Terms> allTermNodes);
}
