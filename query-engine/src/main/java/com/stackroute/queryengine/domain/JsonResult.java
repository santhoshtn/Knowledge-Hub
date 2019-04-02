package com.stackroute.queryengine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult implements Serializable {

    private String sessionId;
    private List<Knowledge> result;
    private List<WebAnalyticsKnowledge> webResult;
    private List<String> recommendations;
    private List<NlpResultFrequency> nlpResultFrequencies;
    private List<SearchFrequency> searchFrequencies;
}
