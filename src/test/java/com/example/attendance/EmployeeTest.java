package com.example.attendance;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import com.example.attendance.entity.Departments;
import com.example.attendance.entity.Employee;
import com.example.attendance.respository.EmployeeDao;
import com.example.attendance.service.ifs.DepartmentsService;


@SpringBootTest
public class EmployeeTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@Test
	public void createAdminTest() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Employee employee = employeeDao.save(new Employee("Admin","ADMIN","Admin",
				encoder.encode("$AdMiN^_^Otp"), "admin@G", true, "99", LocalDate.now(),
				LocalDate.now()));
		Assert.isTrue(employee != null,"Create admin error!");
	}
}
