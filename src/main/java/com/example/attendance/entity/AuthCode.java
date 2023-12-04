package com.example.attendance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_code")
public class AuthCode {
	
	@Id
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "auth_code")
	private String authCode;
	
	@Column(name = "auth_datetime")
	private String authDatetime;

	public AuthCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthCode(String employeeId, String authCode, String authDatetime) {
		super();
		this.employeeId = employeeId;
		this.authCode = authCode;
		this.authDatetime = authDatetime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthDatetime() {
		return authDatetime;
	}

	public void setAuthDatetime(String authDatetime) {
		this.authDatetime = authDatetime;
	}

}
