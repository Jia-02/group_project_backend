package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.TablesDto;

@Mapper
public interface TablesDao {

	/** 新增桌位 */
	public void addTable(TablesDto table);

	/** 桌位列表 */
	public List<TablesDto> getTableList();

	/** 桌位是否存在 */
	public boolean existsById(@Param("tableId") String tableId);

	/** 取得桌位容量人數大小 */
	public int getTableCapacityById(@Param("tableId") String tableId);

	/** 刪除桌位 */
	public void delTableByTableId(TablesDto table);

	/** 更新桌位 */
	public void updateByTable(TablesDto table);

	public void updateStatusByTableId(@Param("tableId") String tableId, //
			@Param("tableStatus") String tableStatus);

}
