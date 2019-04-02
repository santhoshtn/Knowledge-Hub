package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paragraph {
    //  The input paragraph Json format which I get through kafka message bus
    private String paragraphId;
    private String paragraphContent;
    private String documentId;
}
