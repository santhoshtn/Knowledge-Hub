package com.stackroute.queryengine.service;

import com.stackroute.queryengine.domain.Search;
import com.stackroute.queryengine.domain.SearchFrequency;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/*Search service interface*/

public interface SearchService {
//    public Search saveSearch(Search searchString);
    public List<Search> getAllSearch();
    public List<SearchFrequency> getSearchStringResults();
    public HashMap countFreq(List<Search> allquestions, int n);
    public  HashMap<Search, Integer> sortByValue(HashMap<Search, Integer> allquestions);
}
