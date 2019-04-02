package com.stackroute.service;

import com.stackroute.domain.NlpResult;
import com.stackroute.domain.Paragraph;
import com.stackroute.nlpservice.NlpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class ParagraphServiceImpl implements ParagraphService {
    private Paragraph paragraph;
    private NlpService nlpService;
    private ConceptSerive conceptSerive;
    private AnalyticService analyticService;
    private NlpResultService nlpResultService;

    @Autowired
    public ParagraphServiceImpl(NlpService nlpService, ConceptSerive conceptSerive, AnalyticService analyticService, NlpResultService nlpResultService) {
        this.nlpService = nlpService;
        this.conceptSerive = conceptSerive;
        this.analyticService = analyticService;
        this.nlpResultService = nlpResultService;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    // This method is used to sequencial calls to all the services such as NlpService and AnalyticService
    // for processing the paragraph upon receiving the input paragraph Json
    public Paragraph takeParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
        Iterator<String> iterator = conceptSerive.getConcepts().iterator();
        ArrayList<String> concepts = new ArrayList<>();
        while (iterator.hasNext()) {
            concepts.add(iterator.next());
        }
        nlpService.setParagraphContent(paragraph.getParagraphContent());
        nlpService.setConceptNames(concepts);
        NlpResult nlpResult = nlpService.getNlpResults();
        nlpResultService.setNlpResult(nlpResult);
        analyticService.setConceptNames(concepts);
        return paragraph;
    }
}
