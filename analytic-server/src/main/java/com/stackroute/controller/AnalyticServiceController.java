package com.stackroute.controller;

import com.stackroute.domain.AnalysisResult;
import com.stackroute.service.AnalyticService;
import com.stackroute.domain.Paragraph;
import com.stackroute.service.ParagraphProviderService;
import com.stackroute.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AnalyticServiceController {
    private ParagraphService paragraphService;
    private AnalyticService analyticService;
    private ParagraphProviderService paragraphProviderService;

    @Autowired
    public AnalyticServiceController(ParagraphProviderService paragraphProviderService, ParagraphService paragraphService, AnalyticService analyticService) {
        this.paragraphService = paragraphService;
        this.analyticService = analyticService;
        this.paragraphProviderService = paragraphProviderService;
    }

    // This method is used to call the various service method send paragraph for AnalyticService
    @PostMapping("paragraph")
    public ResponseEntity setParagraph(@RequestBody Paragraph paragraph) {
        ResponseEntity responseEntity;
        try {
            // paragraphService is the service which calls AnalyticService
            paragraphService.takeParagraph(paragraph);
            //paragraphProviderService just stores the input paragraph and if any other service requires
            // paragraph it can just call the paragraphProviderService.getParagraph()
            paragraphProviderService.setParagraph(paragraph);
            responseEntity = new ResponseEntity<>("Paragraph is successfully taken.", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Paragraph is not taken.", HttpStatus.BAD_GATEWAY);
            return responseEntity;
        }
    }

    // This method returns the output of AnalyticService results
    @GetMapping("analysisResult")
    public ResponseEntity<List<AnalysisResult>> getAnalysisResult() {
        ResponseEntity responseEntity;
        try {
            List<AnalysisResult> analysisResultsList;
            analysisResultsList = analyticService.getAnalysisResults();
            return new ResponseEntity<>(analysisResultsList, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("No results found.", HttpStatus.EXPECTATION_FAILED);
            return responseEntity;
        }
    }
}
