package com.stackroute.searchupstreamservice.repository;


import com.stackroute.searchupstreamservice.domain.Search;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SearchRepository extends MongoRepository<Search,String> {
    Search findBySessionId(String sessionId);
}
