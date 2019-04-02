package com.stackroute.service;

import com.stackroute.domain.SearchJSON;
import org.springframework.stereotype.Service;

@Service
public class SearchJSONProviderServiceImpl implements SearchJSONProviderService {
    private SearchJSON searchJSON;

    /*This method returns searchJson object*/
    public SearchJSON getSearchJSON() {
        return searchJSON;
    }

    /*This method sets value in searchJson object*/
    public void setSearchJSON(SearchJSON searchJSON) {
        this.searchJSON = searchJSON;
    }
}
