package com.example.GroupProject.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CalendarDao;
import com.example.GroupProject.dto.Calendar;
import com.example.GroupProject.response.BasicRes;
//使用排序
@EnableScheduling
@Service
public class CalendarService {
	
	@Autowired
    private CalendarDao calendarDao;
    
    // --- 創建方法 (包含驗證邏輯) ---
    public BasicRes create(Calendar calendar) {
    	// 執行資料庫操作 (回傳 int)
        int rows = calendarDao.create(calendar); 

        if (rows > 0) {
            // ✅ 成功：Service 構建成功 BasicRes
            return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage()); 
        } else {
            // 失敗：例如 ID 已存在或資料庫錯誤
            return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(), "新增失敗，請檢查數據。");
        }    
//    	// 1. 驗證：標題不得為空
//        if (calendar.getCalendar_title().trim().isEmpty()) {
//            return  new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
//					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage()); 
//        }
//        // 2. 驗證：開始時間晚於結束時間
//        if (calendar.getCalendar_start_date().isAfter(calendar.getCalendar_end_date())) {
//        	return  new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
//					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage()); 
//        }
//        
//        return calendarDao.create(calendar);
    }
    
    // --- 查詢當天及後三天活動 (邏輯從 Controller 移入) ---
    public List<Calendar> getUpcomingActivities() {
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = LocalDateTime.of(today, LocalTime.MIN);
        LocalDate endDateLimit = today.plusDays(3);
        LocalDateTime endDate = LocalDateTime.of(endDateLimit, LocalTime.MAX);
        
        return calendarDao.findActivitiesByDateRange(startDate, endDate);
    }
    
    // ... 其他方法直接寫在這裡 (updateCalendarData, getCalendarById, deleteCalendar) ...
    
    public int updateDataById(Calendar calendar) {
        // 假設已經有狀態檢查邏輯
    	// 1. 驗證：標題不得為空
        if (calendar.getCalendar_title().trim().isEmpty()) {
            return -1; 
        }
        // 2. 驗證：開始時間晚於結束時間
        if (calendar.getCalendar_start_date().isAfter(calendar.getCalendar_end_date())) {
        		return -2; 
        }
        return calendarDao.updateDataById(calendar);
    }

    public Calendar getCalendarById(int calendar_id) {
        return calendarDao.selectById(calendar_id);
    }

    public int deleteCalendar(int calendar_id) {
        return calendarDao.deleteById(calendar_id);
    }
}

