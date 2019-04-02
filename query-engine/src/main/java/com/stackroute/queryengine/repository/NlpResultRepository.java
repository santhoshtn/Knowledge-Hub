package com.stackroute.queryengine.repository;

import com.stackroute.queryengine.domain.NlpResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NlpResultRepository extends MongoRepository<NlpResult, String> {
    public boolean existsBySessonId(String sessionId);
}
