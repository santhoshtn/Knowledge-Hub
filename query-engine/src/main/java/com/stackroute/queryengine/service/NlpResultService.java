package com.stackroute.queryengine.service;

import com.stackroute.queryengine.domain.NlpResult;
import com.stackroute.queryengine.domain.NlpResultFrequency;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface NlpResultService {
   // public NlpResult saveNlpResult(NlpResult nlpResult);
    public List<NlpResult> getAllNlpResult();
    public List<NlpResultFrequency> getNLPFrequencyResults();
    public  HashMap<NlpResult, Integer> sortByValue(HashMap<NlpResult, Integer> nlpresult);
    public HashMap countFreq(List<NlpResult> nlpresult, int n);

}
