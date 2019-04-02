package com.stackroute.queryengine.service;

import com.stackroute.queryengine.domain.NlpResult;
import com.stackroute.queryengine.domain.NlpResultFrequency;
import com.stackroute.queryengine.repository.NlpResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NlpResultSeviceImpl implements NlpResultService{
    private NlpResultRepository nlpresultRepository;

    @Autowired
    public NlpResultSeviceImpl(NlpResultRepository nlpresultRepository) {
        this.nlpresultRepository = nlpresultRepository;
    }

//    @Override
//    public NlpResult saveNlpResult(NlpResult nlpresult){
//        if(nlpresultRepository.existsById(nlpresult.getSessonId())){
//            System.out.println("NlpResult already exits");
//        }
//        NlpResult savedNlpResult = nlpresultRepository.save(nlpresult);
//        if(savedNlpResult==null){
//            System.out.println("NlpResult already exits");
//        }
//        return savedNlpResult;
//    }

    @Override
    public List<NlpResult> getAllNlpResult() {

        return nlpresultRepository.findAll();
    }

    @Override
    public List<NlpResultFrequency> getNLPFrequencyResults() {
        List<NlpResult> nlpResults = getAllNlpResult();
        List<NlpResultFrequency> nlpResultFrequencies=new ArrayList<>();
        HashMap map = new HashMap();
        map = countFreq(nlpResults,nlpResults.size());
        map = sortByValue(map);
       // System.out.println("size of map"+map.size());
        Iterator<Map.Entry<NlpResult, Integer>> itr = map.entrySet().iterator();
        int count=0;
        while(itr.hasNext())
        {
            if(count>9){
                break;
            }
            Map.Entry<NlpResult, Integer> entry = itr.next();
            NlpResultFrequency nlpResultFrequency=new NlpResultFrequency();
          //  System.out.println("aaaaaaaaaaaaa");
           // System.out.println(entry.getKey().getConcept());
           // System.out.println(entry.getValue());
            nlpResultFrequency.setConcept(entry.getKey().getConcept());
            nlpResultFrequency.setFrequency(entry.getValue());
            nlpResultFrequencies.add(nlpResultFrequency);
            count++;
        }

        return nlpResultFrequencies;
    }

    //function to count frequencies of each element of list
    @Override
    public HashMap countFreq(List<NlpResult> nlpresult, int n)
    {
        HashMap map = new HashMap();
        boolean visited[] = new boolean[n];

        Arrays.fill(visited, false);

        for (int i = 0; i < n; i++) {

            if (visited[i] == true)
                continue;

            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (nlpresult.get(i).getConcept().equalsIgnoreCase(nlpresult.get(j).getConcept())) {
                    visited[j] = true;
                    count++;
                }
            }
            map.put(nlpresult.get(i),count);
           // System.out.println(nlpresult.get(i) + " " + count);
        }
        return map;
    }

    // function to sort hashmap by values
    @Override
    public  HashMap<NlpResult, Integer> sortByValue(HashMap<NlpResult, Integer> nlpresult)
    {
        List<Map.Entry<NlpResult, Integer> > list =
                new LinkedList<Map.Entry<NlpResult, Integer> >(nlpresult.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<NlpResult, Integer> >() {
            public int compare(Map.Entry<NlpResult, Integer> o1,
                               Map.Entry<NlpResult, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<NlpResult, Integer> temp = new LinkedHashMap<NlpResult, Integer>();
        for (Map.Entry<NlpResult, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
