package com.example.attendance.vo;

import java.util.List;

import com.example.attendance.entity.Departments;
import com.example.attendance.entity.Employee;

public class EmployeeCreateReq extends Employee{
	
	private String createrId;

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	
}
