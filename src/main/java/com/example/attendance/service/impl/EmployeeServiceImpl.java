package com.example.attendance.service.impl;

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
import com.example.attendance.vo.EmployeeCreateRes;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private DepartmentsDao departmentsDao;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Override
	public EmployeeCreateReq create(EmployeeCreateReq req) {
		if(!StringUtils.hasText(req.getId()) || !StringUtils.hasText(req.getDepartments())
				|| !StringUtils.hasText(req.getName()) || !StringUtils.hasText(req.getPwd())
				|| !StringUtils.hasText(req.getEmail()) || !StringUtils.hasText(req.getJobPosition())
				|| req.getArrivalDate() == null || req.getBirthDate() == null) {
			return new EmployeeCreateRes(RtnCode.PARAM_ERROR);
		}
		if(dao.exitstsById(req.getId())) {
			return new EmployeeCreateRes(RtnCode.ID_HAS_EXISTED);
		}
		//	check department_name
		if(!departmentsDao.existsByName(req.getDepartments())) {
			return new EmployeeCreateRes(RtnCode.DEPARTMENT_NOT_FOUND);
		}
		req.setPwd(encoder.encode(req.getPwd()));
		dao.save((Employee)req);
		return new EmployeeCreateRes(RtnCode.SUCCESSFUL);
	
	}
}
