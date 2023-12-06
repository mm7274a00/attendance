package com.example.attendance.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.attendance.constants.RtnCode;
import com.example.attendance.entity.Employee;
import com.example.attendance.respository.DepartmentsDao;
import com.example.attendance.respository.EmployeeDao;
import com.example.attendance.service.ifs.EmployeeService;
import com.example.attendance.vo.EmployeeCreateReq;
import com.example.attendance.vo.BasicRes;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	// slf4j
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private DepartmentsDao departmentsDao;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Override
	public BasicRes create(EmployeeCreateReq req) {
		if(!StringUtils.hasText(req.getId()) || !StringUtils.hasText(req.getDepartments())
				|| !StringUtils.hasText(req.getName()) || !StringUtils.hasText(req.getPwd())
				|| !StringUtils.hasText(req.getEmail()) || !StringUtils.hasText(req.getJobPosition())
				|| req.getArrivalDate() == null || req.getBirthDate() == null) {
			return new BasicRes(RtnCode.PARAM_ERROR);
		}
		if(dao.exitstsById(req.getId())) {
			return new BasicRes(RtnCode.ID_HAS_EXISTED);
		}
		//	check department_name
		if(!departmentsDao.existsByName(req.getDepartments())) {
			return new BasicRes(RtnCode.DEPARTMENT_NOT_FOUND);
		}
		req.setPwd(encoder.encode(req.getPwd()));
		dao.save((Employee)req);
		return new BasicRes(RtnCode.SUCCESSFUL);
	
	}
	
	@Cache
	@Override 
	public BasicRes login(String id, String pwd, HttpSession session) {
		if(!StringUtils.hasText(id) || !StringUtils.hasText(pwd)) {
			return new BasicRes(RtnCode.PARAM_ERROR);
		}
		//check id & pwd
		Optional<Employee>op = dao.findById(id);
		if(op.isEmpty()) {
			return new BasicRes(RtnCode.ID_NOT_FOUND);
		}
		Employee employee = op.get();
		if(!encoder.matches(pwd, employee.getPwd())) {
			return new BasicRes(RtnCode.PASSWORD_ERROR);
		}
		session.setAttribute(pwd, employee);
		return new BasicRes(RtnCode.SUCCESSFUL);
	}
}
