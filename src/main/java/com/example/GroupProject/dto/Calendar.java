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
}
