package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dto.Account;
import com.example.GroupProject.request.AccountLoginReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.DeliveryUserRes;
import com.example.GroupProject.service.AccountService;

import jakarta.validation.Valid;


@CrossOrigin
//@CrossOrigin(origins = "http://192.168.1.156:4200")
@RestController
public class AccountController {
	
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("addAccount")
	//有加Valid才會自動檢查參數 他如果有錯會拋出例外我們使用 GlobalExceptionHandler這個檔案萊處理變成我們想要看到的資訊
	public BasicRes addAccount(@Valid @RequestBody Account account) {
		try {
			 accountService.addAccount(account);
			 return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			
			return new BasicRes(ResCodeMessage.ADD_INFO_FALIED.getCode(), ResCodeMessage.ADD_INFO_FALIED.getMessage());
		}
	}
	
	
	@PostMapping("/login")
	public DeliveryUserRes login(@Valid @RequestBody AccountLoginReq req) {
	    try {
	        return accountService.login(req.getUserName(), req.getPassword());
	    } catch (Exception e) {
	    	 e.printStackTrace();
	        return new DeliveryUserRes(
	            ResCodeMessage.NOT_FOUND.getCode(),
	            ResCodeMessage.NOT_FOUND.getMessage()
	        );
	    }
	}
	
	 @GetMapping("/deliveryUser/select")
	 public DeliveryUserRes select(@RequestParam("userName") String userName) {
		 try {
			 return accountService.selectUser(userName); 
		 }catch (Exception e) {
			 return new DeliveryUserRes(
			            ResCodeMessage.ADD_INFO_FALIED.getCode(),
			            ResCodeMessage.ADD_INFO_FALIED.getMessage()
			        );
		 }
		
	 }
	
	
	
	


}
