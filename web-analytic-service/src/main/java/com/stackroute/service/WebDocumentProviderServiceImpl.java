package com.stackroute.service;

import com.stackroute.domain.WebDocument;
import org.springframework.stereotype.Service;

@Service
public class WebDocumentProviderServiceImpl implements WebDocumentProviderService {
    private WebDocument webDocument;

    public WebDocument getWebDocument() {
        return webDocument;
    }

    public void setWebDocument(WebDocument webDocument) {
        this.webDocument = webDocument;
    }
}
