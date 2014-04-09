package com.vub.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.JsonResponse;

@Controller
public class ApiTraject {
	@RequestMapping(value="/api/traject/new", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse testPost(@RequestBody String string) {		
	final Logger logger = LoggerFactory.getLogger(this.getClass());
   
	System.out.println("Received something from target new");
    System.out.println(string);
	JsonResponse json = new JsonResponse();
    json.setStatus("success");
    json.setMessage("All went good");
    
    return json;
	}
}