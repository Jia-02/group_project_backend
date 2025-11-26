package com.example.GroupProject.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Calendar {

	private int calendar_id;
	
	private String calendar_title;

	private String calendar_description;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime calendar_start_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime calendar_end_date;
	
	private Boolean calendar_status;
	
	private String calendar_photo;

	public Boolean getCalendar_status() {
		return calendar_status;
	}

	public void setCalendar_status(Boolean calendar_status) {
		this.calendar_status = calendar_status;
	}

	public String getCalendar_photo() {
		return calendar_photo;
	}

	public void setCalendar_photo(String calendar_photo) {
		this.calendar_photo = calendar_photo;
	}

	public int getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(int calendar_id) {
		this.calendar_id = calendar_id;
	}

	public String getCalendar_title() {
		return calendar_title;
	}

	public void setCalendar_title(String calendar_title) {
		this.calendar_title = calendar_title;
	}

	public String getCalendar_description() {
		return calendar_description;
	}

	public void setCalendar_description(String calendar_description) {
		this.calendar_description = calendar_description;
	}

	public LocalDateTime getCalendar_start_date() {
		return calendar_start_date;
	}

	public void setCalendar_start_date(LocalDateTime calendar_start_date) {
		this.calendar_start_date = calendar_start_date;
	}

	public LocalDateTime getCalendar_end_date() {
		return calendar_end_date;
	}

	public void setCalendar_end_date(LocalDateTime calendar_end_date) {
		this.calendar_end_date = calendar_end_date;
	}
	
	
}
