package com.stackroute.searchupstreamservice.service;

import com.stackroute.searchupstreamservice.domain.Search;
import com.stackroute.searchupstreamservice.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements  SearchService {
    private SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository){
        this.searchRepository=searchRepository;
    }

    @Override
    public String saveSearchText(Search search){
        if(search.getSearchString()!=null && !search.getSearchString().isEmpty() &&
                search.getSessionId()!=null && !search.getSessionId().isEmpty()
                && search!=null ) {
            Search savedText = searchRepository.save(search);
            return "saved successfully";
        }
        else
        {
            return "not saved";
        }

    }

}
