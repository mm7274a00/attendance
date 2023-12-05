package com.example.attendance.vo;

import java.util.List;

import com.example.attendance.entity.Departments;
import com.example.attendance.entity.Employee;

public class EmployeeCreateReq {
	
	private List<Employee> EmployeeList;

	public EmployeeCreateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeCreateReq(Employee employee) {
		super();
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
