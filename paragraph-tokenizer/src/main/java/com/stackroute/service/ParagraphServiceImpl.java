/*
This is the service implementation class that gives the implementation of the service interface methods.
 */

package com.stackroute.service;

import com.stackroute.domain.Paragraph;
import com.stackroute.exception.ParagraphNotFoundException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParagraphServiceImpl implements ParagraphService{
    @Override
    public List<JSONObject> getParagraphObject(String documentId1,String text) throws ParagraphNotFoundException {

        try {
            Paragraph paraObj = new Paragraph();
            //paragraph detecting regular expression
            String patternStr = "[.?!][/\\s/g]?\\n+";
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(text);
            String[] para = text.split(patternStr);
            int i = 0;
            while (matcher.find()) {
                String paragraph = matcher.group();
                //getting each paragraph and storing it
                para[i] = para[i] + paragraph.charAt(0);
                i++;
            }
            int cnt = 0;
//            paraObj.setParagraphId(1);
//            int id = paraObj.getParagraphId();
            List<JSONObject> list = new ArrayList();
            while (cnt < para.length) {

                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                JSONObject obj = new JSONObject();
                obj.put("documentId", documentId1);
                obj.put("paragraphId", randomUUIDString);
                paraObj.setParagraphId(randomUUIDString);
                paraObj.setParagraphText(para[cnt]);
                obj.put("paragraphText", paraObj.getParagraphText());
                cnt++;
                //adding documentId, paragraphId, paragraphText in list for sending as object in kafka message bus
                list.add(obj);
            }
            return list;

        }catch (Exception ex){
            throw ex;
        }
    }
}
