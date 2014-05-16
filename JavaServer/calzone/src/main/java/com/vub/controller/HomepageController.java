package com.vub.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vub.model.Program;
import com.vub.model.Traject;
import com.vub.service.ProgramService;
import com.vub.service.TrajectService;

@Controller
public class HomepageController {
	@Autowired
	ProgramService programService;
	@Autowired
	TrajectService trajectService;

	/**
	 * Controller which handles /home, which is the homepage (redirected from /calzone/) 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String serveMainPage(Model model) {
		JsonObject dataSource = new JsonObject();
		Set<Program> programs = new HashSet<Program>();
		programs = programService.getPrograms();
		List<Program> programlist = new ArrayList<Program>();
		programlist.addAll(programs);
		Collections.sort(programlist);
		dataSource.add("listItems", this.constructProgramJsonArray(programlist));
		System.out.println(dataSource.toString());
		model.addAttribute("programDataSource", dataSource.toString());
		return "home";
	}
	
	/**
	 * Constructs an alphabetically sorted (tby program name) json array from a set of programs.
	 * @param programs Programs to include in the json array
	 * @return Json array of all programs (where each array entry has an id and name)
	 */
	private JsonArray constructProgramJsonArray(List<Program> programs) {
		TreeMap<String, JsonObject> sortedMap = new TreeMap<String, JsonObject>();
		JsonArray array = new JsonArray();
		for(Program program : programService.getPrograms()) {
			String name = program.getProgramName();
			JsonObject programObj = new JsonObject();
			programObj.addProperty("id", program.getId());
			programObj.addProperty("name", name);
			programObj.add("trajectories", this.constructTrajectJsonArray(program.getTrajects()));
			sortedMap.put(name, programObj);
		}
		for(JsonObject obj : sortedMap.values())
			array.add(obj);
		return array;
	}
	/**
	 * Constructs an alphabetically sorted (by trajectory name) json array from a set of trajectories
	 * @param trajectories The trajectories to include in the array
	 * @return Json array of all trajectories, where each array entry has an id and a name
	 */
	private JsonArray constructTrajectJsonArray(Set<Traject> trajectories) {
		TreeMap<String, JsonObject> sortedMap = new TreeMap<String, JsonObject>();
		JsonArray array = new JsonArray();
		for(Traject traject : trajectories) {
			String name = traject.getTrajectName();
			JsonObject trajectObj = new JsonObject();
			trajectObj.addProperty("id", traject.getId());
			trajectObj.addProperty("name", name);
			sortedMap.put(name, trajectObj);
		}
		for(JsonObject obj : sortedMap.values())
			array.add(obj);
		return array;
	}
}

