////domain object web document produced by kafka producer

package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebDocument {
    private String title;
    private String description;
    private String keywords;
    private int imageCount;
    private double codeSnippets;
    /* searchdocument domain objects */
    private String id;
    private String link;
    private String conceptName;
    private String domain;


}
