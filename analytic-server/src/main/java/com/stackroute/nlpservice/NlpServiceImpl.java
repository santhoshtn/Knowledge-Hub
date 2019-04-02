package com.stackroute.nlpservice;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.Tokenization;
import com.aliasi.tokenizer.TokenizerFactory;
import com.stackroute.domain.NlpResult;
import com.stackroute.domain.POSTagging;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
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

    private String paragraphContent;
    private ArrayList<String> conceptNames;
    @Value("${stopwords}")
    private String[] stopwords;

    public String getCleanerParagrah() {
        String inputParagraph = getParagraphContent();
        inputParagraph = inputParagraph.trim();
        inputParagraph = inputParagraph.replaceAll("\\s+", " ");
        inputParagraph = inputParagraph.replaceAll("\\t", " ");

        String[] tokenizedWord = inputParagraph.split(" ");
        StringBuilder cleanedParagraph = new StringBuilder();
        for (int i = 0; i < tokenizedWord.length; i++) {
            cleanedParagraph.append(tokenizedWord[i] + " ");
        }
        return cleanedParagraph.toString().trim();
    }

    public List<String> getLemmitizedWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        Annotation annotations = new Annotation(getCleanerParagrah());
        pipeline.annotate(annotations);
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

    public List<String> getStemmedWords() {
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        TokenizerFactory porterFactory = new PorterStemmerTokenizerFactory(tokenizerFactory);
        ArrayList<String> wordTokens = new ArrayList<>(getLemmitizedWords());
        ArrayList<String> stemmedWordsList = new ArrayList<>();
        for (String word : wordTokens) {
            Tokenization tokenization = new Tokenization(word, porterFactory);
            stemmedWordsList.add(tokenization.tokenList().toString());
        }
        return stemmedWordsList;
    }

    public List<String> getWordsWithoutStopWords() {
        ArrayList<String> wordsWithOutStopwords = new ArrayList<>(getLemmitizedWords());
        for (int j = 0; j < stopwords.length; j++) {
            if (wordsWithOutStopwords.contains(stopwords[j])) {
                wordsWithOutStopwords.remove(stopwords[j]);
            }
        }
        return wordsWithOutStopwords;
    }

    public String getParagraphWithOutStopWords() {
        ArrayList<String> wordsWithOutStopwords = new ArrayList<>(getWordsWithoutStopWords());
        StringBuilder paragraphWithOutStopWords = new StringBuilder();
        for (int i = 0; i < wordsWithOutStopwords.size(); i++) {
            paragraphWithOutStopWords.append(wordsWithOutStopwords.get(i) + " ");
        }
        return paragraphWithOutStopWords.toString().trim();
    }

    public List<POSTagging> getPOSWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument coreDocument = new CoreDocument(getParagraphWithOutStopWords());
        pipeline.annotate(coreDocument);
        List<CoreLabel> coreLabelsList = coreDocument.tokens();
        ArrayList<POSTagging> wordsWithPOSTag = new ArrayList<>();
        for (CoreLabel coreLabel : coreLabelsList) {
            String partsOfSpeech = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            wordsWithPOSTag.add(new POSTagging(coreLabel.originalText(), partsOfSpeech));
        }
        return wordsWithPOSTag;
    }

    public List<String> getNouns() {
        ArrayList<POSTagging> posTaggings = new ArrayList<>(getPOSWords());
        ArrayList<String> nounWords = new ArrayList<>();
        for (int i = 0; i < posTaggings.size(); i++) {
            if (posTaggings.get(i).getPosTag().contains("NN")) {
                nounWords.add(posTaggings.get(i).getOriginalWord());
            }
        }
        return nounWords;
    }

    public List<String> getVerbs() {
        ArrayList<POSTagging> posTaggings = new ArrayList<>(getPOSWords());
        ArrayList<String> verbWords = new ArrayList<>();
        for (int i = 0; i < posTaggings.size(); i++) {
            if (posTaggings.get(i).getPosTag().contains("VB")) {
                verbWords.add(posTaggings.get(i).getOriginalWord());
            }
        }
        return verbWords;
    }

    public NlpResult getNlpResults() {
        NlpResult nlpResult = new NlpResult();
        nlpResult.setClearedParagraph(getCleanerParagrah());
        nlpResult.setWordsWithOutStopWords(getWordsWithoutStopWords());
        nlpResult.setParagraphWithOutStopWords(getParagraphWithOutStopWords());
        nlpResult.setPosTaggings(getPOSWords());
        nlpResult.setNounWords(getNouns());
        nlpResult.setVerbWords(getVerbs());
        return nlpResult;
    }

    public List<String> getConceptNames() {
        return conceptNames;
    }

    public void setConceptNames(List<String> conceptNames) {
        this.conceptNames = new ArrayList<>(conceptNames);
    }

    public String getParagraphContent() {
        return paragraphContent;
    }

    public void setParagraphContent(String paragraphContent) {
        this.paragraphContent = paragraphContent;
    }
}
