package com.vub.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.JsonResponse;
import com.vub.model.Program;
import com.vub.model.Traject;
import com.vub.service.ProgramService;
import com.vub.service.TrajectService;
//api/course/all/formated
@Controller
public class ApiProgram {
	@Autowired
	ProgramService programService;
	@Autowired
	TrajectService trajectService;
	
	@RequestMapping(value="/api/program/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse testPost(@RequestBody String string) {		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info(string);

		JsonResponse json = new JsonResponse();

		try {
			//Extracting all values form the string format key=value&key=value
			ArrayList<String> arrayList2 = new ArrayList<String>();
			String[] parts = string.split("&");
			for (int i=0;i<parts.length;i++) {
				int index = parts[i].lastIndexOf("=");
				arrayList2.add(parts[i].substring(index+1));
			}

			System.out.println("arrayList2: " + arrayList2.toString());
			logger.info(arrayList2.toString());

			//Creating program object
			Program program = new Program();
			program.setProgramName(arrayList2.get(0).replace("+", " "));

			//Getting trajectories associated with the id form the request
			Set<Traject> setTrajects = new HashSet<Traject>(0);
			for (int i=1;i<arrayList2.size();i++) {
				Traject traject = new Traject();
				traject = trajectService.findTrajectById(Integer.parseInt(arrayList2.get(i)));
				setTrajects.add(traject);
			}

			program = programService.createProgram(program);
			
			//Setting de program to which the trajectories belong
			for(Traject traject2: setTrajects){
				System.out.println(traject2.getId());
				traject2.setProgram(program);
				trajectService.updateTraject(traject2);
			}

			//Returning positive message to front-end
			json.setStatus("success");
			json.setMessage("All went good");

		} catch (Exception e){
			//something went wrong and returning the error to the front-end
			json.setStatus("error");
			json.setMessage("Something went worng: "+ e);
			logger.debug(e.toString());
		}

		return json;
	}

	@RequestMapping(value="/api/program/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse deleteProgram(@PathVariable int id) {		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("Deleting Traject with id: " + id);

		JsonResponse jsonResponse = new JsonResponse();

		try {
			Program program = programService.findProgramById(id);
			programService.deleteProgram(program);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setStatus("error");
			jsonResponse.setMessage("Program not found with id: " + id);
		} 		

		jsonResponse.setStatus("success");
		jsonResponse.setMessage("OK");

		return jsonResponse;
	}


	@RequestMapping(value="api/program/delete/traject/{trajectId}/{programId}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse deleteProgramTraject(@PathVariable int trajectId, @PathVariable int programId ) {		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("Deleting Traject of Program with id: " + programId);
		JsonResponse jsonResponse = new JsonResponse();

		try {
			Program program = programService.findProgramById(programId);
			Set<Traject> trajects = program.getTrajects();
			Traject toDelete = new Traject();
			for (Traject traject : trajects) {
				if (traject.getId() == trajectId) {
					toDelete = traject;
				}
			}
			if (toDelete != null) {
				trajects.remove(toDelete);
				program.setTrajects(trajects);
				System.out.println(program);
				programService.updateProgram(program);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setStatus("error");
			jsonResponse.setMessage("Program not found with id: " + programId);
		} 		

		jsonResponse.setStatus("success");
		jsonResponse.setMessage("OK");

		return jsonResponse;
	}
	
	@RequestMapping(value="api/program/traject/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse programTrajectAdd(@RequestParam(value="value") String value, @RequestParam(value="name") String name,@RequestParam(value="pk") int pk) {		
		JsonResponse json = new JsonResponse();
		
		try {
			Program program = programService.findProgramById(pk);
			Traject traject = trajectService.findTrajectById(Integer.parseInt(value));
			Set<Traject> trajects = program.getTrajects();
			trajects.add(traject);
			program.setTrajects(trajects);
			
			programService.updateProgram(program);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json.setStatus(JsonResponse.ERROR);
			return json;
		}
		json.setStatus(JsonResponse.SUCCESS);

		return json;
	}
}