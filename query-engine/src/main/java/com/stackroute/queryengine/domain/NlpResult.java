package com.stackroute.queryengine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NlpResult implements Serializable {

    private String intent;
    private String concept;
    private String sessonId;
}
