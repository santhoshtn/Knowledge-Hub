package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*Query result parameter class */
public class ChatMessage implements Serializable {
        private String name; //will contain the ontology name.
        private String sessionId; //will store the session id.
        private String paragraphId; //will store the praragraph Id as passed by paragraph tokenizer.
        private String documentId; //will store the document Id.
        private String domain; //will store the domain.
        private String concept; //will store the bloom's taxanomy concept.
        private String intentLevel; //will describe the intent level of query.
        private String confidenceScore; //will store the confidence score of result.
}
