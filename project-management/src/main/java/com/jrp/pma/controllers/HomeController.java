package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
public class HomeController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		//for table projects
		List<Project> projects = proService.getAll();
		model.addAttribute("projects", projects);
		
		//converting projectData object into a json structure for use in javascript
		List<ChartData> projectData=proService.getProjectStatus();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		model.addAttribute("projectStatusCnt", jsonString);
		
		//for table employees
		List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		return "main/home";
	}


}
