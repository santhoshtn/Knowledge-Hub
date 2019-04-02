package com.stackroute.service;

import com.stackroute.domain.NlpResult;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource(value = "classpath:application.properties")
public interface NlpService {
    public ArrayList<String> getConceptName();

    public void setConceptName(ArrayList<String> conceptName);

    public String getParagraph();

    public String getSessonId();

    public void setSessonId(String sessonId);

    public void setParagraph(String paragraph);

    public String getCleanerParagrah();

    public ArrayList<String> getLemmitizedWords();

    public List<String> getStemmedWords();

    public ArrayList<String> getWordsWithoutStopWords();

    public String getParagraphWithOutStopWords();

    public String getUserIntent();

    public NlpResult getNlpResults();

}
