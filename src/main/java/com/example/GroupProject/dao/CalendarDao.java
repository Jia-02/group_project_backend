package com.example.GroupProject.dao;


import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.Calendar;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.CalendarRes;

public interface CalendarDao {

	/* 2. 使用 dto(class) <br>
	 * 註: 回傳資料型態設定為 int，可以用來判斷是否有 insert 成功<br>
	 * 因為是新增單筆資料，所以回傳 1 表示新增成功；反之新增失敗 */
	public int create(Calendar calendar);
	
	/* update */
	public int updateDataById(Calendar calendar);
	/* delete */
	public int deleteById(@Param("calendarId") int calendarId);
	
	public Calendar selectById(@Param("calendarId") int calendarId);

	public List<Calendar> findActByDateRange(
	        @Param("startDate") LocalDateTime startDate, 
	        @Param("endDate") LocalDateTime endDate
	    );
	
	public List<Calendar> selectAll();
	
}
