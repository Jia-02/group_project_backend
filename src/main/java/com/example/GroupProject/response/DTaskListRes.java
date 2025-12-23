package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.dto.DeliveryTask;

public class DTaskListRes extends BasicRes{

	private List<DeliveryTask> DTaskList;

	public DTaskListRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DTaskListRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public DTaskListRes(int code, String message, List<DeliveryTask> dTaskList) {
		super(code, message);
		DTaskList = dTaskList;
	}

	public List<DeliveryTask> getDTaskList() {
		return DTaskList;
	}

	public void setDTaskList(List<DeliveryTask> dTaskList) {
		DTaskList = dTaskList;
	}
	
	
	
}
