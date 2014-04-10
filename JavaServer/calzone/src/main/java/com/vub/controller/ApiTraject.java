package com.vub.controller;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.JsonResponse;
import com.vub.model.Traject;

@Controller
public class ApiTraject {
	@RequestMapping(value="/api/traject/new", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse testPost(@RequestBody String string) {		
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	logger.info(string);
	
	//Extracting all values form the string format key=value&key=value
	ArrayList<String> arrayList2 = new ArrayList<>();
	String[] parts = string.split("&");
	for (int i=0;i<parts.length;i++) {
		int index = parts[i].lastIndexOf("=");
		arrayList2.add(parts[i].substring(index+1));
	}
	
	//Ceating traject object
	Traject traject = new Traject();
	traject.setTrajectName(arrayList2.get(0));
	traject.setTrajectName(arrayList2.get(1));
	
	System.out.println(traject);
	
	
	JsonResponse json = new JsonResponse();
    json.setStatus("success");
    json.setMessage("All went good");
    
    return json;
	}
}