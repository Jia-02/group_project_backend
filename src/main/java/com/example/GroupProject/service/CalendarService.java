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
    	
    	// 1. 驗證：標題不得為空 (呼叫 getCalendarTitle())
        if (calendar.getCalendarTitle().trim().isEmpty()) {
            return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
                "活動標題不得為空。");
        }
        
        // 2. 驗證：開始時間晚於結束時間 (呼叫 getCalendarStartDate(), getCalendarEndDate())
        if (calendar.getCalendarStartDate().isAfter(calendar.getCalendarEndDate())) {
            return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
                ResCodeMessage.CALENDAR_DATE_ERROR.getMessage()); 
        }
    	
    	// 執行資料庫操作 (回傳 int)
        // 注意：DAO 呼叫中的參數 (calendar) 會自動使用 DTO 的新 Getter 來取值
        int rows = calendarDao.create(calendar); 

        if (rows > 0) {
            // ✅ 成功：Service 構建成功 BasicRes
            return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage()); 
        } else {
            // 失敗：例如 ID 已存在或資料庫錯誤
            return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(), "新增失敗，請檢查數據。");
        } 
    }
    
 // ⭐ 查詢當天及後三天活動的核心邏輯 ⭐
    public List<Calendar> getUpcomingActivities() {
        // 1. 取得今天日期
        LocalDate today = LocalDate.now();
        
        // 2. 範圍起始時間: 今天 00:00:00
        LocalDateTime startDate = LocalDateTime.of(today, LocalTime.MIN);
        
        // 3. 範圍結束時間: 今天 + 3 天的 23:59:59.999999999
        LocalDate endDateLimit = today.plusDays(3);
        LocalDateTime endDate = LocalDateTime.of(endDateLimit, LocalTime.MAX);
        
        // 4. 呼叫 DAO 執行查詢
        return calendarDao.findActivitiesByDateRange(startDate, endDate);
    }
    
    // ... 其他方法直接寫在這裡 (updateCalendarData, getCalendarById, deleteCalendar) ...
    
    public int updateDataById(Calendar calendar) {
        // 假設已經有狀態檢查邏輯
    	// 1. 驗證：標題不得為空 (呼叫 getCalendarTitle())
        if (calendar.getCalendarTitle().trim().isEmpty()) {
            return -1; 
        }
        // 2. 驗證：開始時間晚於結束時間 (呼叫 getCalendarStartDate(), getCalendarEndDate())
        if (calendar.getCalendarStartDate().isAfter(calendar.getCalendarEndDate())) {
            return -2; 
        }
        
        // DAO 呼叫保持不變，因為參數是 DTO 物件
        return calendarDao.updateDataById(calendar);
    }

    public Calendar getCalendarById(int calendarId) { // 這裡的參數可以選擇是否改成 calendarId
    	// 這裡的 DAO 呼叫是傳入基本類型 int，保持不變
        return calendarDao.selectById(calendarId); 
    }

    public int deleteCalendar(int calendarId) { // 這裡的參數可以選擇是否改成 calendarId
    	// 這裡的 DAO 呼叫是傳入基本類型 int，保持不變
        return calendarDao.deleteById(calendarId);
    }
    
}