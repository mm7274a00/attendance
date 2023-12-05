package com.example.attendance;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.attendance.entity.Departments;
import com.example.attendance.service.ifs.DepartmentsService;
import com.example.attendance.vo.DepartmentsCreateReq;
import com.example.attendance.vo.EmployeeCreateRes;


@SpringBootTest
public class DepartmentsTests {
	
	@Autowired
	private DepartmentsService service;
	
	//	only for initial
	@Test
	public void adminDepTest() {
		Departments dep = new Departments("99","ADMIN");
		DepartmentsCreateReq req = new DepartmentsCreateReq(Arrays.asList(dep));
		EmployeeCreateRes res = service.create(req);
		Assert.isTrue(res.getRtnCode().getCode() == 200,"Departments create error!");
	}

}