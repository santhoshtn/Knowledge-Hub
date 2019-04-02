package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConceptNameFrequency {
    // This class has conceptName and frequencyCount
    // (To store how many time a concepName has occured in a paragraph)
    String conceptName;
    long frequencyCount;
}
