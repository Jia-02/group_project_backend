package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.Account;

@Mapper
public interface AccountDao {

	public int addAccount(Account account);
	
	public int selectCountByUserName(@Param("userName") String userName);

	public Account selectByUserName(@Param("userName") String userName);
	
	public Account selectNameByUserName(@Param("userName")String userName);
	
	
}
