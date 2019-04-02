package com.stackroute.service;

import com.stackroute.domain.SearchJSON;

public interface SearchService {
    public SearchJSON getSearchJSON();

    public void setSearchJSON(SearchJSON searchJSON);

    public SearchJSON takeSearchJSON(SearchJSON searchJSON);
}
