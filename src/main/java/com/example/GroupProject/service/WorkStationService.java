package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.WorkStationDao;
import com.example.GroupProject.response.BasicRes;

@Service
public class WorkStationService {

	@Autowired
	private WorkStationDao workStation;

	@Transactional(rollbackFor = Exception.class)
	public BasicRes addWorkStation(String workStationName) {

		if (workStationName.isBlank()) {
			return new BasicRes(ResCodeMessage.WORKSTATION_NAME_ERROR.getCode(), //
					ResCodeMessage.WORKSTATION_NAME_ERROR.getMessage());
		}

		workStation.addWorkStation(workStationName);
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

}
