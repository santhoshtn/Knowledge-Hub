/*
This is the controller class that takes request and gives the appropriate response entity.
 */

package com.stackroute.controller;

import com.stackroute.exception.ParagraphNotFoundException;
import com.stackroute.service.ParagraphService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/v1")
public class ParagraphController {
    private ParagraphService paragraphService;

    JSONObject objects = new JSONObject();

    /*Constructor class*/

    @Autowired
    public ParagraphController(ParagraphService paragraphService){
        this.paragraphService = paragraphService;
    }

    /*Post mapping for posting document details*/
    @PostMapping("paragraph")
    public ResponseEntity<?> postJSONObject(@RequestBody JSONObject document){
        ResponseEntity responseEntity;
        try{
            this.objects=document;
            responseEntity = new ResponseEntity<String>("Successfully posted", HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    /*Get mapping for retrieving paragraph details*/
    @GetMapping("paragraphs")
    public ResponseEntity<?> getAllJSONObjects() throws ParagraphNotFoundException{
        this.objects.put("documentId","23233");
        this.objects.put("documentText","Hi my name is blah.\nI live in blah and i like to blah.\nhi again!\n");
        ResponseEntity responseEntity;
        try {
            responseEntity= new ResponseEntity<List<JSONObject>>(paragraphService.getParagraphObject(objects.get("documentId").toString(),objects.get("documentText").toString()), HttpStatus.OK);
        } catch (ParagraphNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
