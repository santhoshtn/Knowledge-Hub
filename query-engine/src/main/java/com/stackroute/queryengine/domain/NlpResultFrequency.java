package com.stackroute.queryengine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class NlpResultFrequency implements Serializable {
    String concept;
    int frequency;
}
