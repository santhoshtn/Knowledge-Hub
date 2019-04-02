package com.stackroute.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@PropertySource(value = "classpath:application.yml")
public class NlpServiceImpl implements NlpService {
    @Value("${stopwords}")
    private String[] stopwords;
    private String paragraph;
    public String getCleanerParagrah() {
        String inputParagraph = this.paragraph;
        // Data Cleaning by removing extra spaces.
        inputParagraph = inputParagraph.trim();
        inputParagraph = inputParagraph.replaceAll("\\s+", " ");
        inputParagraph = inputParagraph.replaceAll("\\t", " ");

        String[] tokenizedWord = inputParagraph.split(" ");
        StringBuffer cleanedParagraph = new StringBuffer();
        for (int i = 0; i < tokenizedWord.length; i++) {
            cleanedParagraph.append(tokenizedWord[i] + " ");
        }
        return cleanedParagraph.toString().trim();
    }

    /*
    This method will remove all stopwords and returns list of strings which are not a stopword
    */
    public ArrayList<String> getWordsWithoutStopWords() {
        String wordsWithOutStopwords[] = getCleanerParagrah().split(" ");
        ArrayList<String> listWithOutStopWords = new ArrayList<>();
        for (int i = 0; i < wordsWithOutStopwords.length; i++) {
            listWithOutStopWords.add(wordsWithOutStopwords[i].trim());
        }
        for (int i = 0; i < stopwords.length; i++) {
            for (int j = 0; j < listWithOutStopWords.size(); j++) {
                if (listWithOutStopWords.get(j).equalsIgnoreCase(stopwords[i].trim())) {
                    listWithOutStopWords.remove(j);
                }
            }
        }
        return listWithOutStopWords;
    }

    /*
    This method will remove all stopwords and returns paragraph without stopwords
    */
    public String getParagraphWithOutStopWords() {
        ArrayList<String> wordsWithOutStopwords = getWordsWithoutStopWords();
        StringBuffer paragraphWithOutStopWords = new StringBuffer();
        for (int i = 0; i < wordsWithOutStopwords.size(); i++) {
            paragraphWithOutStopWords.append(wordsWithOutStopwords.get(i) + " ");
        }
        return paragraphWithOutStopWords.toString().trim();
    }
    /*
    This method will lemmitized each word and returns list of lemmitized words
    */
    public ArrayList<String> getLemmitizedWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "lemma");
        // StanfordCoreNLP uses pipeline and this pipeline is create
        // based on the properties we specity in java.util.Properties
        // different set of propeties provide different NLP tasks
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        // This annotations object gives the special meaning to the
        // string we used in properties.put() method
        Annotation annotations = new Annotation(getParagraphWithOutStopWords());
        // pipeline.annotate(annotations)  provies the annotation to those particular objects.
        pipeline.annotate(annotations);
        // sentenceList contains list of sentences
        ArrayList<String> lemmaWords = new ArrayList<>();
        ArrayList<String> originalWords = new ArrayList<>();
        List<CoreMap> sentenceList = annotations.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentenceList) {
            for (CoreLabel word : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmaWords.add(word.lemma());
                originalWords.add(word.originalText());
            }
        }
        return lemmaWords;
    }

    /*
    This method will lemmitized each word and returns lemmitized string
    */
    public String getParagraphWithLemmatizedWords() {
        ArrayList<String> lemmatizedWords = getLemmitizedWords();
        StringBuffer paragraphWithLemmatizedWords = new StringBuffer();
        for (int i = 0; i < lemmatizedWords.size(); i++) {
            paragraphWithLemmatizedWords.append(lemmatizedWords.get(i) + " ");
        }
        return paragraphWithLemmatizedWords.toString().trim();
    }


    /*
 This method will return NLP result
 */
    public String getQueryQuestionResults() {

        return getParagraphWithLemmatizedWords();
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
