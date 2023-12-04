package com.example.attendance.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.entity.JobPosition;

@Repository
public interface JobPositionDao extends JpaRepository<JobPosition,String>{

}
