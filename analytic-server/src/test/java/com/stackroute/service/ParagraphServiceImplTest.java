package com.stackroute.service;

import com.stackroute.domain.NlpResult;
import com.stackroute.domain.Paragraph;
import com.stackroute.nlpservice.NlpService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ParagraphServiceImplTest {
    private Paragraph paragraph;
    private List<String> conceptNamesList;
    private NlpResult nlpResult;
    private List<String> nouns;
    private List<String> verbs;
    private String paragraphWithOutStopWords;

    @Mock
    private NlpService nlpService;
    @Mock
    private ConceptSerive conceptSerive;
    @Mock
    private AnalyticService analyticService;
    @Mock
    private NlpResultService nlpResultService;
    @InjectMocks
    private ParagraphServiceImpl paragraphServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        paragraph = new Paragraph("P001", "what is spring framework", "D001");
        conceptNamesList = new ArrayList<>();
        conceptNamesList.add("spring framework");
        conceptNamesList.add("spring core");
        nouns = new ArrayList<>();
        nouns.add("spring");
        nouns.add("framework");
        verbs = new ArrayList<>();
        verbs.add("define");
        paragraphWithOutStopWords = "define spring framework";
        nlpResult = new NlpResult();
        nlpResult.setNounWords(nouns);
        nlpResult.setVerbWords(verbs);
        nlpResult.setParagraphWithOutStopWords(paragraphWithOutStopWords);
        nlpResult.setClearedParagraph("define spring framework");
    }

    @Test
    public void getParagraph() {
        paragraphServiceImpl.setParagraph(paragraph);
        String expectedParagraphString = paragraph.toString();
        String actualParagraphString = paragraphServiceImpl.getParagraph().toString();
        Assert.assertEquals(expectedParagraphString, actualParagraphString);
    }

    @Test
    public void setParagraph() {
    }

    @Test
    public void takeParagraph() {
        paragraphServiceImpl.setParagraph(paragraph);
        when(conceptSerive.getConcepts()).thenReturn(conceptNamesList);
        doNothing().when(nlpService).setParagraphContent(paragraph.getParagraphContent());
        doNothing().when(nlpService).setConceptNames(conceptNamesList);
        when(nlpService.getNlpResults()).thenReturn(nlpResult);
        doNothing().when(nlpResultService).setNlpResult(nlpResult);
        doNothing().when(analyticService).setConceptNames(new ArrayList<>(conceptNamesList));

        String expected = paragraph.toString();
        String actual = paragraphServiceImpl.takeParagraph(paragraph).toString();
        Assert.assertEquals(expected, actual);
    }
}