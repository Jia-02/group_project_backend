package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.WorkStationDto;

public class workStationListRes extends BasicRes {

	private List<WorkStationDto> workStationList;

	public workStationListRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public workStationListRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public workStationListRes(int code, String message, List<WorkStationDto> workStationList) {
		super(code, message);
		this.workStationList = workStationList;
	}

	public List<WorkStationDto> getWorkStationList() {
		return workStationList;
	}

	public void setWorkStationList(List<WorkStationDto> workStationList) {
		this.workStationList = workStationList;
	}

}
