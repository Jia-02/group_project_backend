package com.example.GroupProject.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dao.CalendarDao;
import com.example.GroupProject.dto.Calendar;

@CrossOrigin // 允許前端來源
@RestController

public class CalendarController {
    
    @Autowired
    private CalendarDao calendarDao; // 假設您直接使用 DAO

    // 1. 創建 (C - Create) - POST /calendar
    @PostMapping(value = "calendar/create") // 路徑: /calendar
    public String createCalendar(@RequestBody Calendar calendar) {
        int rows = calendarDao.create(calendar);
        if (rows > 0) {
            return "新增成功，ID: " + calendar.getCalendar_id(); // 假設 MyBatis 會回寫 ID
        }
        return "新增失敗。";
    }

    // 2. 查詢 (R - Read by ID) - GET /calendar/{id}
    @GetMapping(value = "calendar/byId") // 路徑: /calendar/{calendar_id}
    public Calendar getCalendarById(@PathVariable("calendar_id") int calendar_id) {
        // 確保參數名稱與 @PathVariable 匹配
        return calendarDao.selectById(calendar_id); 
    }
    
    @GetMapping(value = "calendar/byDate") 
    public List<Calendar> getActivitiesByDate(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // 假設傳入完整的 ISO 8601 時間格式
        LocalDateTime checkDate) {
        
        return calendarDao.findActivitiesByDate(checkDate);
    }
    
    // 3. 更新 (U - Update) - PUT /calendar
    @PutMapping(value = "calendar/update") // 路徑: /calendar
    public String updateCalendar(@RequestBody Calendar calendar) {
        int rows = calendarDao.updateDataById(calendar);
        if (rows > 0) {
            return "更新成功，ID: " + calendar.getCalendar_id();
        }
        return "更新失敗，ID: " + calendar.getCalendar_id() + " 不存在。";
    }
    
    // 4. 刪除 (D - Delete) - DELETE /calendar/{id}
    @DeleteMapping(value = "calendar/del") // 路徑: /calendar/{calendar_id}
    public String deleteCalendar(@PathVariable("calendar_id") int calendar_id) {
        int rows = calendarDao.deleteById(calendar_id);
        if (rows > 0) {
            return "刪除成功，ID: " + calendar_id;
        }
        return "刪除失敗，ID: " + calendar_id + " 不存在。";
    }
}