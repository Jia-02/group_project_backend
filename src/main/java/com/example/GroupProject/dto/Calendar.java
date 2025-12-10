package com.example.GroupProject.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Calendar {

	private int calendarId; 
	private String calendarTitle;
	private String calendarDescription;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime calendarStartDate; // calendar_start_date -> calendarStartDate
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime calendarEndDate;   // calendar_end_date -> calendarEndDate
	
	private boolean calendarStatus;
	private String calendarPhoto;
	public int getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}
	public String getCalendarTitle() {
		return calendarTitle;
	}
	public void setCalendarTitle(String calendarTitle) {
		this.calendarTitle = calendarTitle;
	}
	public String getCalendarDescription() {
		return calendarDescription;
	}
	public void setCalendarDescription(String calendarDescription) {
		this.calendarDescription = calendarDescription;
	}
	public LocalDateTime getCalendarStartDate() {
		return calendarStartDate;
	}
	public void setCalendarStartDate(LocalDateTime calendarStartDate) {
		this.calendarStartDate = calendarStartDate;
	}
	public LocalDateTime getCalendarEndDate() {
		return calendarEndDate;
	}
	public void setCalendarEndDate(LocalDateTime calendarEndDate) {
		this.calendarEndDate = calendarEndDate;
	}
	public Boolean getCalendarStatus() {
		return calendarStatus;
	}
	public void setCalendarStatus(Boolean calendarStatus) {
		this.calendarStatus = calendarStatus;
	}
	public String getCalendarPhoto() {
		return calendarPhoto;
	}
	public void setCalendarPhoto(String calendarPhoto) {
		this.calendarPhoto = calendarPhoto;
	}
	
	
	
}
