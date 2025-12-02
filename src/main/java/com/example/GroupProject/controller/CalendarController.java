package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.Calendar;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.CalendarRes;
import com.example.GroupProject.service.CalendarService;

@CrossOrigin // 允許前端來源
@RestController

public class CalendarController {
	
	@Autowired
	private CalendarService calendarService;
    
//    @Autowired
//    private CalendarDao calendarDao; // 假設您直接使用 DAO

    // 1. 創建 (C - Create) - POST /calendar
    @PostMapping(value = "calendar/create") // 路徑: /calendar
    public BasicRes createCalendar(@RequestBody Calendar calendar) {
    		return calendarService.create(calendar);
        
    }    
    
    // 2. 更新 (U - Update) - PUT /calendar
    @PostMapping(value = "calendar/update") // 路徑: /calendar
    public BasicRes updateCalendar(@RequestBody Calendar calendar) {        
        return calendarService.updateDataById(calendar);
    }

    // 4. 刪除 (D - Delete) - DELETE /calendar/{id}
    @PostMapping(value = "calendar/del") // 路徑: /calendar/{calendar_id}
    public BasicRes deleteById(@RequestParam("calendarId") int calendarId) {
    		return calendarService.deleteById(calendarId);
    }
    
    		// ⭐ 查詢當天及後三天活動的 API ⭐
     @GetMapping(value = "calendar/selectDate")
     public CalendarRes findActByDateRange(){
    	 	return calendarService.findActByDateRange();
     }
    
     @GetMapping(value = "calendar/all")
     public CalendarRes selectAll() {
         return calendarService.selectAll();
     }
    
//    // 2. 查詢 (R - Read by ID) - GET /calendar/{id}
//    @GetMapping(value = "calendar/byId") // 路徑: /calendar/{calendar_id}
//    public Calendar getCalendarById(@PathVariable("calendarId") int calendarId) {
//        // 確保參數名稱與 @PathVariable 匹配
//        return calendarService.getCalendarById(calendarId); 
//    }

    
    
}