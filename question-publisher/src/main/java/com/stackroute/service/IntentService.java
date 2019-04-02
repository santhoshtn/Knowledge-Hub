package com.stackroute.service;

import com.stackroute.domain.Terms;

public interface IntentService {

    public String createTermNode(Terms term);

    public String getCount();

    public String createIntentLevelTermRelationship(String intentLevel,String termName);
}