package com.stackroute.service;

import com.stackroute.domain.SearchJSON;

public interface SearchJSONProviderService {
    public SearchJSON getSearchJSON();

    public void setSearchJSON(SearchJSON searchJSON);
}
