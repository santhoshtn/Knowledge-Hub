package com.stackroute.service;

import com.stackroute.domain.Terms;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface IntentService {

    public void createTermNode(Terms term);

    public String getCount();

    public Collection<String> getKnowledgeTerms();
    public Collection<String> getComprehensionTerms();
    public Collection<String> getApplicationTerms();
    public Collection<String> getAnalysisTerms();
    public Collection<String> getSynthesisTerms();
    public Collection<String> getEvaluationTerms();
}
