package com.example.attendance.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leave_application")
public class LeaveApplication {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "application_no")
	private String applicationlNo;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "employee_department")
	private String employeeDepartment;
	
	@Column(name = "leave_type")
	private String leaveType;
	
	@Column(name = "leave_start_datetime")
	private LocalDateTime leaveStartDatetime;
	
	@Column(name = "leave_end_datetime")
	private LocalDateTime leaveEndDatetime;
	
	@Column(name = "total_hours")
	private int totalHours;
	
	@Column(name = "leave_reason")
	private String leaveReason;
	
	@Column(name = "reviewer_id")
	private String reviewerId;
	
	@Column(name = "reviewer_datetime")
	private LocalDateTime reviewerDatetime;
	
	@Column(name = "reviewer_status")
	private String reviewerStatus;
	
	@Column(name = "reject_reason")
	private String rejectReason;
	
	@Column(name = "applied_datetime")
	private LocalDateTime appliedDatetime;
	
	@Column(name = "update_datetime")
	private LocalDateTime updateDatetime;

	public LeaveApplication() {
		super();
	}

	public LeaveApplication(String applicationNo, String employeeId, String employeeDepartment, String leaveType,
			LocalDateTime leaveStartDatetime, LocalDateTime leaveEndDatetime, int totalHours, String leaveReason,
			String reviewerId, LocalDateTime reviewerDatetime, String reviewerStatus, String rejectReason,
			LocalDateTime appliedDatetime, LocalDateTime updateDatetime) {
		super();
		this.applicationlNo = applicationNo;
		this.employeeId = employeeId;
		this.employeeDepartment = employeeDepartment;
		this.leaveType = leaveType;
		this.leaveStartDatetime = leaveStartDatetime;
		this.leaveEndDatetime = leaveEndDatetime;
		this.totalHours = totalHours;
		this.leaveReason = leaveReason;
		this.reviewerId = reviewerId;
		this.reviewerDatetime = reviewerDatetime;
		this.reviewerStatus = reviewerStatus;
		this.rejectReason = rejectReason;
		this.appliedDatetime = appliedDatetime;
		this.updateDatetime = updateDatetime;
	}

	public LeaveApplication(LeaveApplication application) {
		super();
		this.applicationlNo = application.getApplicationlNo();
		this.employeeId = application.getEmployeeId();
		this.employeeDepartment = application.getEmployeeDepartment();
		this.leaveType = application.getLeaveType();
		this.leaveStartDatetime = application.getLeaveStartDatetime();
		this.leaveEndDatetime = application.getLeaveEndDatetime();
		this.totalHours = application.getTotalHours();
		this.leaveReason = application.getLeaveReason();
		this.reviewerId = application.getReviewerId();
		this.reviewerDatetime = application.getReviewerDatetime();
		this.reviewerStatus = application.getReviewerStatus();
		this.rejectReason = application.getRejectReason();
		this.appliedDatetime = application.getAppliedDatetime();
		this.updateDatetime = application.getUpdateDatetime();
	}

	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDateTime getLeaveStartDatetime() {
		return leaveStartDatetime;
	}

	public void setLeaveStartDatetime(LocalDateTime leaveStartDatetime) {
		this.leaveStartDatetime = leaveStartDatetime;
	}

	public LocalDateTime getLeaveEndDatetime() {
		return leaveEndDatetime;
	}

	public void setLeaveEndDatetime(LocalDateTime leaveEndDatetime) {
		this.leaveEndDatetime = leaveEndDatetime;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}

	public LocalDateTime getReviewerDatetime() {
		return reviewerDatetime;
	}

	public void setReviewerDatetime(LocalDateTime reviewerDatetime) {
		this.reviewerDatetime = reviewerDatetime;
	}

	public String getReviewerStatus() {
		return reviewerStatus;
	}

	public void setReviewerStatus(String reviewerStatus) {
		this.reviewerStatus = reviewerStatus;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public LocalDateTime getAppliedDatetime() {
		return appliedDatetime;
	}

	public void setAppliedDatetime(LocalDateTime appliedDatetime) {
		this.appliedDatetime = appliedDatetime;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getApplicationlNo() {
		return applicationlNo;
	}

	public void setApplicationlNo(String applicationlNo) {
		this.applicationlNo = applicationlNo;
	}
	
}
