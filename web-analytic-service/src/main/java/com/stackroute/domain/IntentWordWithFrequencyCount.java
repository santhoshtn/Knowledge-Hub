package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntentWordWithFrequencyCount {
    String intentWord;
    long frequencyCount;
    String intent;
    String relationship;
    double weight;
}
