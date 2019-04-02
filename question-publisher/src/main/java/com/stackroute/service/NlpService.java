package com.stackroute.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public interface NlpService {
    public String getCleanerParagrah();

    /*
    This method will remove all stopwords and returns list of strings which are not a stopword
    */
    public ArrayList<String> getWordsWithoutStopWords();

    /*
    This method will remove all stopwords and returns paragraph without stopwords
    */
    public String getParagraphWithOutStopWords();

    /*
    This method will lemmitized each word and returns list of lemmitized words
    */
    public ArrayList<String> getLemmitizedWords();

    /*
    This method will lemmitized each word and returns lemmitized string
    */
    public String getParagraphWithLemmatizedWords();


    /*
 This method will return NLP result
 */
    public String getQueryQuestionResults();

    public String getParagraph();

    public void setParagraph(String paragraph);
}
