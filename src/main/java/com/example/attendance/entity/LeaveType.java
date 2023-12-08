package com.example.attendance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leave_type")
public class LeaveType {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	public static Object parser(String employeeDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

}
