package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dao.CalendarDao;
import com.example.GroupProject.dto.Calendar;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
	@Autowired
    private CalendarDao calendarDao; // 假設您直接使用 DAO，實際應用應調用 Service

    // 1. 創建 (C - Create)
    @PostMapping("/")
    public String createCalendar(@RequestBody Calendar calendar) {
        calendarDao.create(calendar);
        return "新增成功，ID: " + calendar.getCalendar_id(); // 假設 MyBatis 會回寫 ID
    }

//    // 2. 查詢 (R - Read)
//    @GetMapping("/{id}")
//    public Calendar getCalendarById(@PathVariable("id") int id) {
//        return calendarDao.selectById(id);
//    }
//    
    // 3. 更新 (U - Update)
    @PutMapping("/")
    public String updateCalendar(@RequestBody Calendar calendar) {
        calendarDao.updateDataById(calendar);
        return "更新成功，ID: " + calendar.getCalendar_id();
    }
    
    // 4. 刪除 (D - Delete)
    @DeleteMapping("/{id}")
    public String deleteCalendar(@PathVariable("id") int id) {
        calendarDao.deleteById(id);
        return "刪除成功，ID: " + id;
    }
}
