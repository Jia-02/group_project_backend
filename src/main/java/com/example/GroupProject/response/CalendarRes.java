package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.Calendar;

public class CalendarRes extends BasicRes {
	
	private List<Calendar> calendarList;

	public CalendarRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalendarRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}
	
	public CalendarRes(int code, String message, List<Calendar> calendarList) {
		super(code, message);
		this.calendarList = calendarList;
	}

	public List<Calendar> getCalendarList() {
		return calendarList;
	}

	public void setCalendarList(List<Calendar> calendarList) {
		this.calendarList = calendarList;
	}	

	
}
