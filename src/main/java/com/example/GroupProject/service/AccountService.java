package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.AccountDao;
import com.example.GroupProject.dto.Account;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.DeliveryUserRes;



@Service
public class AccountService {

	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	
	
	@Autowired
	private AccountDao accountDao;
	
	
	public BasicRes addAccount(Account account) {
		try {
			//若文件有要檢查帳號是否存在
			int count=accountDao.selectCountByUserName(account.getUserName());
			//因為是透過PK去查詢是否有存在值 count只會是0或1
			if(count==1) {
				return new BasicRes(ResCodeMessage.ACCOUNT_EXIST.getCode()//
						, ResCodeMessage.ACCOUNT_EXIST.getMessage());
			}
			//存進去DB中的密碼要加密
			String encodePwd = encoder.encode(account.getPassword());
			account.setPassword(encodePwd);   // 更新成加密後的密碼

			accountDao.addAccount(account);   

			return new BasicRes(ResCodeMessage.SUCCESS.getCode()//
					, ResCodeMessage.SUCCESS.getMessage());
		} catch (Exception e) {
		
			throw e;
		}
	
	}
	
	
	
	
	public DeliveryUserRes login(String userName, String password) {
	    Account data = accountDao.selectByUserName(userName);
	    if (data == null) {
	        return new DeliveryUserRes(
	            ResCodeMessage.NOT_FOUND.getCode(),
	            ResCodeMessage.NOT_FOUND.getMessage()
	        );
	    }

	    if (!encoder.matches(password, data.getPassword())) {
	        return new DeliveryUserRes(
	            ResCodeMessage.PASSWORD_MISMATCH.getCode(),
	            ResCodeMessage.PASSWORD_MISMATCH.getMessage()
	        );
	    }

	    // 登入成功，回傳 id, name, phone
	    return new DeliveryUserRes(
	        ResCodeMessage.SUCCESS.getCode(),
	        ResCodeMessage.SUCCESS.getMessage(),
	        data.getId(),   // <- userId
	        data.getName(),
	        data.getPhone()
	    );
	}
	
	public DeliveryUserRes selectUser(String userName) {
	    Account account = accountDao.selectNameByUserName(userName);
	    // 2. 檢查是否有資料
	    if (account == null) {
	    	return new DeliveryUserRes(ResCodeMessage.ACCOUNT_NOT_EXIST.getCode()//
					, ResCodeMessage.ACCOUNT_NOT_EXIST.getMessage());
	    }
	   
	    return new DeliveryUserRes(ResCodeMessage.SUCCESS.getCode()//
				, ResCodeMessage.SUCCESS.getMessage(), account.getId(),account.getName(),account.getPhone());
	}

}
