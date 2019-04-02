package com.stackroute.nlpservice;

import com.stackroute.domain.NlpResult;
import com.stackroute.domain.POSTagging;

import java.util.List;

public interface NlpService {
    public String getCleanerParagrah();

    public List<String> getLemmitizedWords();

    public List<String> getWordsWithoutStopWords();

    public String getParagraphWithOutStopWords();

    public List<POSTagging> getPOSWords();

    public List<String> getNouns();

    public List<String> getVerbs();

    public NlpResult getNlpResults();

    public void setConceptNames(List<String> conceptNames);

    public String getParagraphContent();

    public void setParagraphContent(String paragraphContent);
}
