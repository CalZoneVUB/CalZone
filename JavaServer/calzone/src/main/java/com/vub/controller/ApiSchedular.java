package com.vub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.JsonResponse;

/**
 * @author Tim
 * Controller for all ajax calls to the schedular
 */
@Controller
public class ApiSchedular {
	/**
	 * @param pk : primary key of the traject to schedule
	 * @return : teruns sucess or error message in json
	 */
	@RequestMapping(value="/api/schedular/{pk}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse testPost(@PathVariable int pk) {		
	final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	JsonResponse json = new JsonResponse();
	
	try {
	
	//Returning positive message to front-end
    json.setStatus("success");
    json.setMessage("All went good");
    
	} catch (Exception e){
		//something went worng and returning the error to the front-end
		json.setStatus("error");
		json.setMessage("Something went worng: "+ e);
		logger.debug(e.toString());
	} finally {	
	}
	
	return json;
	}
}