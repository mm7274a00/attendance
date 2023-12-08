package com.example.attendance.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.LeaveApplication;

@Repository
public interface LeaveApplicationDao extends JpaRepository<LeaveApplication, Integer>{

}
