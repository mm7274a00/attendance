package com.example.attendance.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.attendance.constants.RtnCode;
import com.example.attendance.entity.Employee;
import com.example.attendance.entity.JobPosition;
import com.example.attendance.entity.LeaveApplication;
import com.example.attendance.entity.LeaveType;
import com.example.attendance.respository.EmployeeDao;
import com.example.attendance.respository.LeaveApplicationDao;
import com.example.attendance.service.ifs.LeaveApplicationService;
import com.example.attendance.vo.BasicRes;
import com.example.attendance.vo.LeaveApplicationReq;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService{
	
	// slf4j
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private LeaveApplicationDao dao;
	
	@Override
	public BasicRes apply(LeaveApplicationReq req) {
		if(LeaveType.parser(req.getEmployeeDepartment()) == null){
			return new BasicRes(RtnCode.LEAVE_TYPE_ERROR);
		}
		if(req.getLeaveEndDatetime().isAfter(req.getLeaveEndDatetime())
				|| req.getAppliedDatetime() == null){
			return new BasicRes(RtnCode.LEAVE_APPLIED_DATETIME_ERROR);
		}
		if(StringUtils.hasText(req.getLeaveReason())) {
			return new BasicRes(RtnCode.LEAVE_REASON_CANNOT_BE_EMPTY);
		}
		if(StringUtils.hasText(req.getLeaveReason())) {
			return new BasicRes(RtnCode.LEAVE_REVIEWER_ID_CANNOT_BE_EMPTY);
		}
		Optional<Employee> op = employeeDao.findById(req.getReviewerId());
		if(op.isEmpty()) {
			return new BasicRes(RtnCode.LEAVE_REVIEWER_ID_NOT_FOUND);	
		}
		Employee reviewer = op.get();
		if(!JobPosition.hasReviewPermission(reviewer.getJobPosition())) {
			return new BasicRes(RtnCode.PERMISSION_DENIED);	
		}
		LocalDateTime now = LocalDateTime.now();
		req.setApplicationlNo(now.toString().replaceAll("[-T:.]", ""));
		req.setUpdateDatetime(LocalDateTime.now());
		try {
			dao.save((LeaveApplication)req);
			//�H�e Email �� reviewer�F�H�󤺮e�n������s��
		}catch(Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnCode.LEAVE_APPLICATION_ERROR);	
		}
		return new BasicRes(RtnCode.SUCCESSFUL);
	}
	
	
	@Override
	public BasicRes review(String reviewer, String applicationNo) {
		// ���ΧP�_ reviewerId �O�_���šA�]������k�����O login ����~��ϥΡA�blogin��k�w���P�_
		if(StringUtils.hasText(applicationNo)) {
			return new BasicRes(RtnCode.PARAM_ERROR);
		}
		List<LeaveApplication> op = dao.findByApplicationNo(applicationNo);
		if(op.isEmpty()) {
			return new BasicRes(RtnCode.LEAVE_REVIEWER_ID_NOT_FOUND);	
		}
		LeaveApplication application = op.get();
		Employee reviewer = employee.findById(reviewerId).get();
		//����ӽЪ̭n�P�f�֪̦P����
		if(!application.getEmployeeDepartment().equalsIgnoreCase(reviewer).getDepartment())){
			return new BasicRes(RtnCode.PERMISSION_DENIED);
		}
	}

}
