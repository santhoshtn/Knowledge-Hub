package com.stackroute.service;

import com.stackroute.domain.Paragraph;
import org.springframework.stereotype.Service;

@Service
public class ParagraphProviderServiceImpl implements ParagraphProviderService {
    private Paragraph paragraph;

    // This service just stores the input paragraph Json.
    @Override
    public Paragraph getParagraph() {
        return paragraph;
    }

    @Override
    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }
}
