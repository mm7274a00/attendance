package com.example.attendance.respository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.attendance.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,String>{
	
	public Employee findByEmail(String email);

	public boolean existsById(String id);
	
	public boolean existsByIdAndActive(String id, boolean isActive);
	
	
	/*
	 * clearAutomatically = true: 清除持久化上下文，清除暫存資料
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update Employee set active = :inputActive where id = :inputId")
	public int updateActive(@Param("inputId")String employeeid, @Param("inputActive")boolean active);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update Employee as e set" //update Employee e 別名
			+ " department  = case when :inputDepartment   is null then e.department else :inputDepartment end," //當要更新的輸入值為null/0 -> 不更新
			+ " name	    = case when :inputName 		   is null then e.department else :inputName end,"
			+ " email	    = case when :inputEmail 	   is null then e.department else :inputEmail end,"
			+ " jobPosition = case when :inputJobPosition  is null then e.department else :inputJobPosition end,"
			+ " birthDate 	= case when :inputBirthDate    is null then e.department else :inputBirthDate end,"
			+ " arrivalDate = case when :inputArrivalDate  is null then e.department else :inputArrivalDate end,"
			+ " annualLeave = case when :inputAnnualLeave  == 0    then	e.department else :inputAnnualLeave end,"
			+ " sickLeave 	= case when :inputsickLeave    == 0    then	e.department else :inputsickLeave end"
			+ " where e.id  = :inputId") 
	public int updateInfo(
			@Param(":inputId")String id,//
			@Param(":inputDepartment")String department,//
			@Param(":inputName")String name,//
			@Param(":inputEmail")String email,//
			@Param(":inputJobPosition")String jobPosition,//
			@Param(":inputBirthDate")LocalDate birthDate,//
			@Param(":inputArrivalDate")LocalDate arrivalDate,//
			@Param(":inputAnnualLeave")int annualLeave,//
			@Param(":inputsickLeave")int sickLeave);//
}
