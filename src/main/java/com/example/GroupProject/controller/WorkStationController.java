package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.WorkStationDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.workStationListRes;
import com.example.GroupProject.service.WorkStationService;

@CrossOrigin // 允許前端來源
@RestController
public class WorkStationController {

	@Autowired
	private WorkStationService workStationService;

	@PostMapping(value = "workstation/add")
	public BasicRes addWorkStation(@RequestParam("workStationName") String workStationName) {
		return workStationService.addWorkStation(workStationName);
	}

	@GetMapping(value = "workstation/list")
	public workStationListRes getWorkStationList() {
		return workStationService.getWorkStationList();
	}
	
	@PostMapping(value = "workstation/delete")
	public BasicRes deleteWorkStation(@RequestParam("workStationId") int workStationId) {
		return workStationService.deleteWorkStation(workStationId);
	}
	
	@PostMapping(value = "workstation/update")
	public BasicRes updateWorkStation(@RequestBody WorkStationDto workStation) {
		return workStationService.updateWorkStation(workStation);
	}

}
