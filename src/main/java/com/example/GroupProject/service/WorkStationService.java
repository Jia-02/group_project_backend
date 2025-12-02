package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.WorkStationDao;
import com.example.GroupProject.dto.WorkStationDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.workStationListRes;

@Service
public class WorkStationService {

	@Autowired
	private WorkStationDao workStationDao;

	@Transactional(rollbackFor = Exception.class)
	public BasicRes addWorkStation(String workStationName) {

		if (workStationName.isBlank()) {
			return new BasicRes(ResCodeMessage.WORKSTATION_NAME_ERROR.getCode(), //
					ResCodeMessage.WORKSTATION_NAME_ERROR.getMessage());
		}

		for (WorkStationDto workstation : workStationDao.getWorkStationList()) {
			if (workstation.getWorkStationName().equalsIgnoreCase(workStationName)) {
				return new BasicRes(ResCodeMessage.WORKSTATION_NAME_ERROR.getCode(), //
						ResCodeMessage.WORKSTATION_NAME_ERROR.getMessage());
			}
		}

		workStationDao.addWorkStation(workStationName);
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	public workStationListRes getWorkStationList() {
		return new workStationListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), workStationDao.getWorkStationList());
	}

	@Transactional(rollbackFor = Exception.class)
	public BasicRes deleteWorkStation(int workStationId) {
		
		if(workStationDao.deleteWorkStation(workStationId) < 1) {
			return new BasicRes(ResCodeMessage.WORKSTATION_NOT_FOUND.getCode(), //
					ResCodeMessage.WORKSTATION_NOT_FOUND.getMessage());
		}
		
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}
	
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateWorkStation(WorkStationDto workStation) {
		
		if(!workStationDao.checkWorkstationExist(workStation.getWorkStationId())) {
			return new BasicRes(ResCodeMessage.WORKSTATION_NOT_FOUND.getCode(), //
					ResCodeMessage.WORKSTATION_NOT_FOUND.getMessage());
		}
		
		if (workStation.getWorkStationName().isBlank()) {
			return new BasicRes(ResCodeMessage.WORKSTATION_NAME_ERROR.getCode(), //
					ResCodeMessage.WORKSTATION_NAME_ERROR.getMessage());
		}
		
		for (WorkStationDto workstation : workStationDao.getWorkStationList()) {
			if (workstation.getWorkStationName().equalsIgnoreCase(workStation.getWorkStationName())) {
				return new BasicRes(ResCodeMessage.WORKSTATION_NAME_ERROR.getCode(), //
						ResCodeMessage.WORKSTATION_NAME_ERROR.getMessage());
			}
		}
		
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}

}
