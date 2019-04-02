package com.stackroute.repository;

import com.stackroute.domain.QueryQuestions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository  extends MongoRepository<QueryQuestions,String> {
}
