package com.stackroute.searchupstreamservice.controller;

import com.stackroute.searchupstreamservice.domain.Search;
import com.stackroute.searchupstreamservice.listener.KafkaProducer;
import com.stackroute.searchupstreamservice.service.SearchService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//Controller class
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/")
public class SearchController {

    private SearchService searchService;

    private KafkaProducer kafkaProducer;

    @Autowired
    public SearchController(KafkaProducer kafkaProducer,SearchService searchService) {
        this.kafkaProducer = kafkaProducer;
        this.searchService = searchService;
    }

    @GetMapping("home")
    public String home(){
        return "service is up and working";
    }

    //rest end point for search
    @PostMapping("search")
    public String search(@RequestBody String search){
        JSONObject jsonObj = new JSONObject(search);
        JSONObject location=jsonObj.getJSONObject("queryResult");
        Search searchClass =new Search();
        searchClass.setSessionId(jsonObj.getString("responseId"));
        searchClass.setSearchString(location.getString("queryText"));
        searchService.saveSearchText(searchClass);
        return kafkaProducer.upStreamService(searchClass);
    }

    @PostMapping("vsearch")
    public String searchBox(@RequestBody Search search){

        Search searchClasses =new Search();
        searchClasses.setSearchString(search.getSearchString());
        searchClasses.setSessionId(search.getSessionId());
        searchService.saveSearchText(searchClasses);
        return kafkaProducer.upStreamService(searchClasses);
    }

}
