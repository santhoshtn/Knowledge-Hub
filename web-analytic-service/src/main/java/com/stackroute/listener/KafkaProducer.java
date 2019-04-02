package com.stackroute.listener;

import com.stackroute.domain.WebAnalysisResult;
import com.stackroute.service.WebAnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaProducer {
    private WebAnalyticService webAnalyticService;

    @Autowired
    public KafkaProducer(WebAnalyticService webAnalyticService) {
        this.webAnalyticService = webAnalyticService;
    }

    @Autowired
    private KafkaTemplate<String, WebAnalysisResult> kafkaTemplate2;

    private static final String TOPIC = "WebAnalyticsResults";

    // the postservice() will post the WebAnalyticResuls object in kafka message bus
    public String postservice() throws IOException {
        System.out.println("Web Analytic service postservice");
        WebAnalysisResult webAnalysisResult;
        webAnalysisResult = webAnalyticService.getWebAnalysisResult();
            kafkaTemplate2.send(TOPIC, webAnalysisResult);
        return "Published successfully";
    }
}