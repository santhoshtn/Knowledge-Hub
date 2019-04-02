package com.stackroute.listener;

import com.stackroute.domain.AnalysisResult;
import com.stackroute.service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private AnalyticService analyticService;

    @Autowired
    public KafkaProducer(AnalyticService analyticService) {
        this.analyticService = analyticService;
    }

    @Autowired
    private KafkaTemplate<String, AnalysisResult> kafkaTemplate2;

    private static final String TOPIC = "AnalyticsResults";

    // the postservice() will post the analyticResuls object in kafka message bus
    public String postservice() {
        for (int i = 0; i < analyticService.getAnalysisResults().size(); i++) {
            AnalysisResult analysisResult;
            analysisResult = analyticService.getAnalysisResults().get(i);
            kafkaTemplate2.send(TOPIC, analysisResult);
        }
        return "Published successfully";
    }
}
