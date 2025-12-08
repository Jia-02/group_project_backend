package com.example.GroupProject.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CalendarDao;
import com.example.GroupProject.dto.Calendar;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.CalendarRes;

//使用排序
@EnableScheduling
@Service
public class CalendarService {

	@Autowired
	private CalendarDao calendarDao;

	// --- 創建方法 (包含驗證邏輯) ---
	@Transactional(rollbackFor = Exception.class)
	public BasicRes create(Calendar calendar) {

		// 1. 驗證：標題不得為空 (呼叫 getCalendarTitle())
		if (!StringUtils.hasText(calendar.getCalendarTitle())) {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		if (calendar.getCalendarTitle() == "") {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		// 1.2 驗證：敘述不得為空
		if (!StringUtils.hasText(calendar.getCalendarDescription())) {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		if (calendar.getCalendarDescription() == "") {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}

		// 1.3 驗證：圖片URL不得為空
		if (!StringUtils.hasText(calendar.getCalendarPhoto())) {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		if (calendar.getCalendarPhoto() == "") {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		// 2. 驗證：開始時間晚於結束時間 (呼叫 getCalendarStartDate(), getCalendarEndDate())
		if (calendar.getCalendarStartDate().isAfter(calendar.getCalendarEndDate())) {
			return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage());
		}
		// 驗證：結束時間早於開始時間
		if (calendar.getCalendarEndDate().isBefore(calendar.getCalendarStartDate())) {
			return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage());
		}
		// 驗證：結束時間相等於開始時間
//        if(calendar.getCalendarEndDate().isEqual(calendar.getCalendarStartDate())) {
//			return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
//					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage());
//		}

		// 執行資料庫操作 (回傳 int)
		// 注意：DAO 呼叫中的參數 (calendar) 會自動使用 DTO 的新 Getter 來取值
		int rows = calendarDao.create(calendar);

		if (rows > 0) {
			// ✅ 成功：Service 構建成功 BasicRes
			return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
		} else {
			// 失敗：例如 ID 已存在或資料庫錯誤
			return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage());
		}
	}

	// ⭐ 查詢當天及後三天活動的核心邏輯 ⭐
	@Transactional(readOnly = true)
	public CalendarRes findActByDateRange() {
		// 1. 取得今天日期
		LocalDate today = LocalDate.now();

		// 2. 範圍起始時間: 今天 00:00:00
		LocalDateTime startDate = LocalDateTime.of(today, LocalTime.MIN);

		// 3. 範圍結束時間: 今天 + 3 天的 23:59:59.999999999
		LocalDate endDateLimit = today.plusDays(3);
		LocalDateTime endDate = LocalDateTime.of(endDateLimit, LocalTime.MAX);

		// 4. 呼叫 DAO 執行查詢

		List<Calendar> activities = calendarDao.findActByDateRange(startDate, endDate);

		// 5. 執行業務邏輯檢查（可選）
		if (activities == null || activities.isEmpty()) {
			return new CalendarRes(ResCodeMessage.NOT_FOUND.getCode(), ResCodeMessage.NOT_FOUND.getMessage(), null);
		}

		// 6. 封裝結果並回傳 CalendarRes
		return new CalendarRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(), activities // 傳入
																													// List<Calendar>
		);
	}

	// ... 其他方法直接寫在這裡 (updateCalendarData, getCalendarById, deleteCalendar) ...
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateDataById(Calendar calendar) {
		// 假設已經有狀態檢查邏輯
		// 1. 驗證：標題不得為空 (呼叫 getCalendarTitle())
		if (!StringUtils.hasText(calendar.getCalendarTitle())) {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		if (calendar.getCalendarTitle() == "") {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		// 1.2 驗證：敘述不得為空
		if (!StringUtils.hasText(calendar.getCalendarDescription())) {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		if (calendar.getCalendarDescription() == "") {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		// 1.3 驗證：圖片URL不得為空
		if (!StringUtils.hasText(calendar.getCalendarPhoto())) {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		if (calendar.getCalendarPhoto() == "") {
			return new BasicRes(ResCodeMessage.CALENDAR_NOT_FOUND.getCode(),
					ResCodeMessage.CALENDAR_NOT_FOUND.getMessage());
		}
		// 2. 驗證：開始時間晚於結束時間 (呼叫 getCalendarStartDate(), getCalendarEndDate())
		if (calendar.getCalendarStartDate().isAfter(calendar.getCalendarEndDate())) {
			return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage());
		}
		// 驗證：結束時間早於開始時間
		if (calendar.getCalendarEndDate().isBefore(calendar.getCalendarStartDate())) {
			return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage());
		}
		// 驗證：結束時間相等於開始時間
//        if(calendar.getCalendarEndDate().isEqual(calendar.getCalendarStartDate())) {
//			return new BasicRes(ResCodeMessage.CALENDAR_DATE_ERROR.getCode(),
//					ResCodeMessage.CALENDAR_DATE_ERROR.getMessage());
//		}

		int res = calendarDao.updateDataById(calendar);
		if (res == 0) {
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(), ResCodeMessage.NOT_FOUND.getMessage());
		}
		// DAO 呼叫保持不變，因為參數是 DTO 物件

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

//    public Calendar getCalendarById(int calendarId) { // 這裡的參數可以選擇是否改成 calendarId
//    	// 這裡的 DAO 呼叫是傳入基本類型 int，保持不變
//        return calendarDao.selectById(calendarId); 
//    }

	@Transactional(rollbackFor = Exception.class)
	public BasicRes deleteById(int calendarId) { // 這裡的參數可以選擇是否改成 calendarId
		// 這裡的 DAO 呼叫是傳入基本類型 int，保持不變
		int res = calendarDao.deleteById(calendarId);
		if (res == 0) {
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(), ResCodeMessage.NOT_FOUND.getMessage());
		}
		// DAO 呼叫保持不變，因為參數是 DTO 物件

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	@Transactional(readOnly = true)
	public CalendarRes selectAll() {
		return new CalendarRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				calendarDao.selectAll());

	}

	@Transactional(readOnly = true)
	public CalendarRes selectDay(LocalDate selectDate) {

		if (selectDate == null) {
			selectDate = LocalDate.now();
		}

		// 去抓全部活動中，點選日期>startDate and endDate> 點選日期
		List<Calendar> activitiesOnSelect = calendarDao.selectDay(selectDate);

		return new CalendarRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				activitiesOnSelect);

	}

}