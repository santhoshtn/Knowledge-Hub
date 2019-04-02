//web document service interface for web document service methods

package com.stackroute.service;

import com.stackroute.domain.SearchDocument;
import com.stackroute.domain.WebDocument;
import com.stackroute.exception.EmptyFileException;
import com.stackroute.exception.FileNotFoundException;


public interface WebDocumentService {
    public void sendSearchdoc(SearchDocument searchDocument) throws EmptyFileException,
            FileNotFoundException;
    public String extractTitle(SearchDocument searchDocument) throws FileNotFoundException;
    public String extractDescription(SearchDocument searchDocument)throws FileNotFoundException;
    public String extractKeywords(SearchDocument searchDocument)throws FileNotFoundException;
    public int extractImageCount(SearchDocument searchDocument)throws FileNotFoundException;
    public void extractCodeSnippets(SearchDocument searchDocument)throws FileNotFoundException;
    public WebDocument getContentExtractorResults();
}
