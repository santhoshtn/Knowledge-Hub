package com.stackroute.service;

import java.util.Collection;

public interface IntentService {
    public Collection<String> getKnowledgeTerms();
    public Collection<String> getComprehensionTerms();
    public Collection<String> getApplicationTerms();
    public Collection<String> getAnalysisTerms();
    public Collection<String> getSynthesisTerms();
    public Collection<String> getEvaluationTerms();
}
