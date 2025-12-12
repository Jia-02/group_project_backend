package com.example.GroupProject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.ReservationDao;
import com.example.GroupProject.dao.TableDailyDao;
import com.example.GroupProject.dao.TablesDao;
import com.example.GroupProject.dto.TableDailyDto;
import com.example.GroupProject.dto.TablesDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.TableListRes;

@Service
public class TablesService {

	@Autowired
	private TablesDao tableDao;

	@Autowired
	private TableDailyDao tableDailyDao;
	
	@Autowired
	private ReservationDao reservationDao;

	// 新增桌位
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addTable(TablesDto table) {

		if (table.getTablePositionX() + table.getLengthX() > 500) {
			return new BasicRes(ResCodeMessage.TABLE_POSITION_ERROR.getCode(),
					ResCodeMessage.TABLE_POSITION_ERROR.getMessage());
		}

		if (table.getTablePositionY() + table.getLengthY() > 500) {
			return new BasicRes(ResCodeMessage.TABLE_POSITION_ERROR.getCode(),
					ResCodeMessage.TABLE_POSITION_ERROR.getMessage());
		}

		for (TablesDto item : tableDao.getTableList()) {
			// 迴圈檢查存在中的桌位ID是否有與新增的桌位ID重複
			if (table.getTableId().equalsIgnoreCase(item.getTableId())) {
				return new BasicRes(ResCodeMessage.TABLE_ID_EXIST.getCode(),
						ResCodeMessage.TABLE_ID_EXIST.getMessage());
			}
			// 迴圈檢查該位置是否已存在桌位
			if (item.getTablePositionX() >= table.getTablePositionX() && //
					item.getTablePositionX() <= table.getTablePositionX() + table.getLengthX()) {
				if (item.getTablePositionY() >= table.getTablePositionY() && //
						item.getTablePositionY() <= table.getTablePositionY() + table.getLengthY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				} else if (item.getTablePositionY() <= table.getTablePositionY() && //
						item.getTablePositionY() + item.getLengthY() >= table.getTablePositionY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				}
			} else if (item.getTablePositionX() <= table.getTablePositionX() && //
					item.getTablePositionX() + item.getLengthX() >= table.getTablePositionX()) {
				if (item.getTablePositionY() >= table.getTablePositionY() && //
						item.getTablePositionY() <= table.getTablePositionY() + table.getLengthY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				} else if (item.getTablePositionY() <= table.getTablePositionY() && //
						item.getTablePositionY() + item.getLengthY() >= table.getTablePositionY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				}
			}
		}

		tableDao.addTable(table);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// list查詢

	@Transactional(rollbackFor = Exception.class)
	public TableListRes getTableList() {

		return new TableListRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				tableDao.getTableList());
	}

	// 刪除桌位
	@Transactional(rollbackFor = Exception.class)
	public BasicRes delTable(TablesDto table) {

		tableDao.delTableByTableId(table);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// 更新桌位
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateTable(TablesDto table) {
		if (table.getTablePositionX() < 0) {
			return new BasicRes(ResCodeMessage.TABLE_POSITION_ERROR.getCode(),
					ResCodeMessage.TABLE_POSITION_ERROR.getMessage());
		}
		if (table.getTablePositionY() < 0) {
			return new BasicRes(ResCodeMessage.TABLE_POSITION_ERROR.getCode(),
					ResCodeMessage.TABLE_POSITION_ERROR.getMessage());
		}
		if (table.getLengthX() < 60) {
			return new BasicRes(ResCodeMessage.TABLE_WIDTH_ERROR.getCode(),
					ResCodeMessage.TABLE_WIDTH_ERROR.getMessage());
		}
		if (table.getLengthY() < 60) {
			return new BasicRes(ResCodeMessage.TABLE_HEIGHT_ERROR.getCode(),
					ResCodeMessage.TABLE_HEIGHT_ERROR.getMessage());
		}
		if (table.getTableCapacity() < 2) {
			return new BasicRes(ResCodeMessage.TABLE_CAPACITY_ERROR.getCode(),
					ResCodeMessage.TABLE_CAPACITY_ERROR.getMessage());
		}
		if (table.getTablePositionX() + table.getLengthX() > 500) {
			return new BasicRes(ResCodeMessage.TABLE_POSITION_ERROR.getCode(),
					ResCodeMessage.TABLE_POSITION_ERROR.getMessage());
		}

		if (table.getTablePositionY() + table.getLengthY() > 500) {
			return new BasicRes(ResCodeMessage.TABLE_POSITION_ERROR.getCode(),
					ResCodeMessage.TABLE_POSITION_ERROR.getMessage());
		}

		for (TablesDto item : tableDao.getTableList()) {
			// 迴圈檢查該位置是否已存在桌位
			if (item.getTablePositionX() >= table.getTablePositionX() && //
					item.getTablePositionX() <= table.getTablePositionX() + table.getLengthX()
					&& !(item.getTableId().equalsIgnoreCase(table.getTableId()))) {
				if (item.getTablePositionY() >= table.getTablePositionY() && //
						item.getTablePositionY() <= table.getTablePositionY() + table.getLengthY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				} else if (item.getTablePositionY() <= table.getTablePositionY() && //
						item.getTablePositionY() + item.getLengthY() >= table.getTablePositionY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				}
			} else if (item.getTablePositionX() <= table.getTablePositionX() && //
					item.getTablePositionX() + item.getLengthX() >= table.getTablePositionX()
					&& !(item.getTableId().equalsIgnoreCase(table.getTableId()))) {
				if (item.getTablePositionY() >= table.getTablePositionY() && //
						item.getTablePositionY() <= table.getTablePositionY() + table.getLengthY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				} else if (item.getTablePositionY() <= table.getTablePositionY() && //
						item.getTablePositionY() + item.getLengthY() >= table.getTablePositionY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				}
			}
		}

		tableDao.updateByTable(table);
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// 查某一天全部桌位的狀態
	@Transactional(readOnly = true)
	public List<TableDailyDto> getDailyStatus(LocalDate date) {
		return tableDailyDao.getDailyStatus(date);
	}

	// 更新桌位狀態
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateTableStatus(TableDailyDto dto) {
		// 確認桌位狀態存在
		Integer tableStaus = tableDailyDao.getTableStatus(dto.getTableDailyDate(), dto.getTableId());
		if (tableStaus == null || tableStaus < 0) {
			return new BasicRes( //
					ResCodeMessage.TABLE_STATUS_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.TABLE_STATUS_IS_NOT_FOUND.getMessage());
		}
		
		//如果該日有訂位，不可關閉
	    int count = reservationDao.reservationByDateAndTable(dto.getTableDailyDate(),  dto.getTableId());
	    if (count > 0) {
			return new BasicRes( //
					ResCodeMessage.RESERVATION_EXIST.getCode(), //
					ResCodeMessage.RESERVATION_EXIST.getMessage());
	    }
	    
		int updateStatusCount = tableDailyDao.updateTableStatus(dto);
		if (updateStatusCount < 0) {
			return new BasicRes( //
					ResCodeMessage.ADD_INFO_FAILED.getCode(), //
					ResCodeMessage.ADD_INFO_FAILED.getMessage());
		}
		return new BasicRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}

	// 新增桌位狀態
	@Transactional(rollbackFor = Exception.class)
	public BasicRes insertTableStatus(TableDailyDto dto) {
		
		//如果該日有訂位，不可關閉
	    int count = reservationDao.reservationByDateAndTable(dto.getTableDailyDate(),  dto.getTableId());
	    if (count > 0) {
			return new BasicRes( //
					ResCodeMessage.RESERVATION_EXIST.getCode(), //
					ResCodeMessage.RESERVATION_EXIST.getMessage());
	    }
		
		int insertStatusCount = tableDailyDao.insertTableStatus(dto);
		if (insertStatusCount < 0) {
			return new BasicRes( //
					ResCodeMessage.ADD_INFO_FAILED.getCode(), //
					ResCodeMessage.ADD_INFO_FAILED.getMessage());
		}
		return new BasicRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}

	// 刪除桌位狀態
	public BasicRes delTableStatus(TableDailyDto dto) {
		// 確認桌位狀態存在
		Integer tableStaus = tableDailyDao.getTableStatus(dto.getTableDailyDate(), dto.getTableId());
		if (tableStaus == null || tableStaus < 0) {
			return new BasicRes( //
					ResCodeMessage.TABLE_STATUS_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.TABLE_STATUS_IS_NOT_FOUND.getMessage());
		}
		
		//如果該日有訂位，不可關閉
	    int count = reservationDao.reservationByDateAndTable(dto.getTableDailyDate(),  dto.getTableId());
	    if (count > 0) {
			return new BasicRes( //
					ResCodeMessage.RESERVATION_EXIST.getCode(), //
					ResCodeMessage.RESERVATION_EXIST.getMessage());
	    }

		int delTableStatusCount = tableDailyDao.delTableStatus(dto);
		if (delTableStatusCount < 0) {
			return new BasicRes( //
					ResCodeMessage.ADD_INFO_FAILED.getCode(), //
					ResCodeMessage.ADD_INFO_FAILED.getMessage());
		}
		return new BasicRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}

}
