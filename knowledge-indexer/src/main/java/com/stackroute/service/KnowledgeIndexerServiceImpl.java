package com.stackroute.service;

import com.stackroute.domain.Knowledge;
import com.stackroute.repository.ConceptRepository;
import com.stackroute.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KnowledgeIndexerServiceImpl implements KnowledgeIndexerService {
     private KnowledgeRepository knowledgeRepository;

     @Autowired
     public KnowledgeIndexerServiceImpl(ConceptRepository conceptRepository, KnowledgeRepository knowledgeRepository){
          this.knowledgeRepository=knowledgeRepository;
     }

     //method to save knowledge nodes to neo4j
     public void saveKnowledgeToDb(Knowledge knowledge) {
          knowledgeRepository.save(knowledge);
     }

     //method to add relationship between ontology nodes and knowledge nodes
     public void addRelationship(String concept, String paragraphId, String intentLevel,double confidenceScore)
     {
          if(intentLevel.equalsIgnoreCase("knowledge"))
               knowledgeRepository.insertKnowledgeRelationship(concept,paragraphId,intentLevel,confidenceScore);

          else if(intentLevel.equalsIgnoreCase("comprehension"))
               knowledgeRepository.insertComprehensionRelationship(concept,paragraphId,intentLevel,confidenceScore);

          else if(intentLevel.equalsIgnoreCase("application"))
               knowledgeRepository.insertApplicationRelationship(concept,paragraphId,intentLevel,confidenceScore);

          else if(intentLevel.equalsIgnoreCase("analysis"))
               knowledgeRepository.insertAnalysisRelationship(concept,paragraphId,intentLevel,confidenceScore);

          else if(intentLevel.equalsIgnoreCase("synthesis"))
               knowledgeRepository.insertSynthesisRelationship(concept,paragraphId,intentLevel,confidenceScore);

          else if(intentLevel.equalsIgnoreCase("evaluation"))
               knowledgeRepository.insertEvaluationRelationship(concept,paragraphId,intentLevel,confidenceScore);
     }
}
