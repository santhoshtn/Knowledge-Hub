package com.stackroute.queryengine.service;

import com.stackroute.queryengine.domain.Search;
import com.stackroute.queryengine.domain.SearchFrequency;
import com.stackroute.queryengine.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SearchServiceImpl implements SearchService {
    private SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }



    @Override
    public List<Search> getAllSearch() {

        return searchRepository.findAll();
    }

    //function to get recommendations
    @Override
    public List<SearchFrequency> getSearchStringResults() {
        List<Search> allQuestions = getAllSearch();
        System.out.println("Inside getSearch"+allQuestions.toString());
        List<SearchFrequency> searchFrequencies=new ArrayList<>();
        HashMap map = new HashMap();
        map = countFreq(allQuestions,allQuestions.size());
        map = sortByValue(map);
        Iterator<Map.Entry<Search, Integer>> itr = map.entrySet().iterator();
        int count=0;
        while(itr.hasNext())
        {
            if(count>9){
                break;
            }
            Map.Entry<Search, Integer> entry = itr.next();
            SearchFrequency searchFrequency=new SearchFrequency();
            searchFrequency.setFrequency(entry.getValue());
            searchFrequency.setSearchString(entry.getKey().getSearchString());
            searchFrequencies.add(searchFrequency);
            count++;
        }

        return searchFrequencies;
    }

    //function to count frequencies of each element of list
    @Override
    public HashMap countFreq(List<Search> allquestions, int n)
    {
        HashMap map = new HashMap();
        boolean visited[] = new boolean[n];

        Arrays.fill(visited, false);

        for (int i = 0; i < n; i++) {

            if (visited[i] == true)
                continue;

            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (allquestions.get(i).getSearchString().equalsIgnoreCase(allquestions.get(j).getSearchString())) {
                    visited[j] = true;
                    count++;
                }
            }
            map.put(allquestions.get(i),count);
           // System.out.println(allquestions.get(i) + " " + count);
        }
        return map;
    }

    // function to sort hashmap by values
    @Override
    public  HashMap<Search, Integer> sortByValue(HashMap<Search, Integer> allquestions)
    {
        List<Map.Entry<Search, Integer> > list =
                new LinkedList<Map.Entry<Search, Integer> >(allquestions.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Search, Integer> >() {
            public int compare(Map.Entry<Search, Integer> o1,
                               Map.Entry<Search, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<Search, Integer> temp = new LinkedHashMap<Search, Integer>();
        for (Map.Entry<Search, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}

