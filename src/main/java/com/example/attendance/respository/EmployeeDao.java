package com.example.attendance.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,String>{

	boolean exitstsById(String id);

	Employee save(Employee employee);

}
