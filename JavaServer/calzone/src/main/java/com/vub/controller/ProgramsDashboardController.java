package com.vub.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Program;
import com.vub.service.ProgramService;
import com.vub.service.TrajectService;


@Controller
public class ProgramsDashboardController {
	@Autowired
	ProgramService programService;
	
	/**
	 * @param model : model for /programdashboard GET
	 * @return : returns programsdashboardtable.jsp with ProgramsList as Set<Programs>
	 */
	@RequestMapping(value = "/programdashboard", method = RequestMethod.GET)
	public String programDashboard(ModelMap model) {
		Set<Program> setPrograms = programService.getPrograms();
		model.addAttribute("programList", setPrograms);
		System.out.println("tada");
		return "ProgramDashboardTable";
	}
	
	/**
	 * @param model : model for /programdashboard/new GET
	 * @return : returns ProgramAddDashboardTable
	 */
	@RequestMapping(value = "/programdashboard/new", method = RequestMethod.GET)
	public String programDashboardNew(ModelMap model) {
		
		return "ProgramAddDashboard";
	}
	
	@RequestMapping(value = "/programdashboard/edit/{id}", method = RequestMethod.GET)
	public String trajectDashboardEdit(@PathVariable int id, ModelMap model) {
		
		Program program = programService.findProgramById(id);

		model.addAttribute("program", program);
		
		return "ProgramEditDashboard";
	}

}
