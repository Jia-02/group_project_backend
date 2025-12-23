package com.example.GroupProject.request;

public class UpdateStatusReq {
    private String orderNo;
    private String status;
    private Integer estimatedTime;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public UpdateStatusReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateStatusReq(String orderNo, String status, Integer estimatedTime) {
		super();
		this.orderNo = orderNo;
		this.status = status;
		this.estimatedTime = estimatedTime;
	}

    

}
