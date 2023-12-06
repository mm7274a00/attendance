package com.example.attendance.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.attendance.constants.RtnCode;
import com.example.attendance.entity.AuthCode;
import com.example.attendance.entity.Employee;
import com.example.attendance.respository.AuthCodeDao;
import com.example.attendance.respository.DepartmentsDao;
import com.example.attendance.respository.EmployeeDao;
import com.example.attendance.service.ifs.EmployeeService;
import com.example.attendance.vo.EmployeeCreateReq;

import net.bytebuddy.utility.RandomString;

import com.example.attendance.vo.BasicRes;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	// slf4j
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Value("${authcode.expired.time}")
	private int authCodeExpiedTime;
	
	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private DepartmentsDao departmentsDao;
	
	
	@Autowired
	private AuthCodeDao authCodeDao;
	
	@Override
	public BasicRes create(EmployeeCreateReq req) {
		if(!StringUtils.hasText(req.getId()) || !StringUtils.hasText(req.getDepartments())
				|| !StringUtils.hasText(req.getName()) || !StringUtils.hasText(req.getPwd())
				|| !StringUtils.hasText(req.getEmail()) || !StringUtils.hasText(req.getJobPosition())
				|| req.getArrivalDate() == null || req.getBirthDate() == null) {
			return new BasicRes(RtnCode.PARAM_ERROR);
		}
		if(dao.existsById(req.getId())) {
			return new BasicRes(RtnCode.ID_HAS_EXISTED);
		}
		//	check department_name
		if(!departmentsDao.existsByName(req.getDepartments())) {
			return new BasicRes(RtnCode.DEPARTMENT_NOT_FOUND);
		}
		req.setPwd(encoder.encode(req.getPwd()));
		try {
			dao.save((Employee)req);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnCode.EMPLOYEE_CREATE_ERROR);
		}
		return new BasicRes(RtnCode.SUCCESSFUL);
	}
	
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
		session.setAttribute("id", id);
		session.setMaxInactiveInterval(3000);
		logger.info("loging successful!");
		return new BasicRes(RtnCode.SUCCESSFUL);
	}
	
	@Override
	public BasicRes changePassword(String id, String oldPwd, String newPwd) {
		if(!StringUtils.hasText(id) || !StringUtils.hasText(oldPwd) || !StringUtils.hasText(newPwd)) {
			return new BasicRes(RtnCode.PARAM_ERROR);
		}
		if(oldPwd.equals(newPwd)) {
			return new BasicRes(RtnCode.OLD_PASSWORD_AND_NEW_PASSWORD_ARE_IDENTICAL);
		}
		Optional<Employee>op = dao.findById(id);
		if(op.isEmpty()) {
			return new BasicRes(RtnCode.ID_NOT_FOUND);
		}
		Employee employee = op.get();
		if(!encoder.matches(oldPwd, employee.getPwd())) {
			return new BasicRes(RtnCode.PASSWORD_ERROR);
		}
		employee.setPwd(encoder.encode(newPwd));
		try {
			dao.save(employee);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnCode.EMPLOYEE_CREATE_ERROR);
		}
		return new BasicRes(RtnCode.SUCCESSFUL);
	}

	
	@Override
	public BasicRes forgotPassword(String id, String email) {
		if(!StringUtils.hasText(id) && !StringUtils.hasText(email)) {
			return new BasicRes(RtnCode.PARAM_ERROR);
		}
		Employee employee = null;
		if(StringUtils.hasText(id)) {
			Optional<Employee>op = dao.findById(id);
			if(op.isEmpty()) {
				return new BasicRes(RtnCode.ID_NOT_FOUND);
			}
			employee = op.get();
		} else {
			employee = dao.findByEmail(email);
			if(employee == null) {
				return new BasicRes(RtnCode.ID_NOT_FOUND);
			}
		}
		String ramdonPwd = RandomString.make(12);
		employee.setPwd(encoder.encode(ramdonPwd));
		// 產生驗證碼，有效時間為30分鐘
		String authCode = RandomString.make(6);
		LocalDateTime now = LocalDateTime.now();
		try {
			dao.save(employee);
			authCodeDao.save(new AuthCode(employee.getId(), authCode, now.plusMinutes(authCodeExpiedTime)));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnCode.FORGOT_PASSWORD_ERROR);
		}
		// 界接email並寄送
		return new BasicRes(RtnCode.SUCCESSFUL);
	}

	@Override
	public BasicRes changePasswordByAuthCode(String id, String authCode, String newPwd) {
		if(!StringUtils.hasText(id) && !StringUtils.hasText(authCode) || !StringUtils.hasText(newPwd)){
			return new BasicRes(RtnCode.PARAM_ERROR);
		}
		//check auth_code
		Optional<AuthCode> op = authCodeDao.findById(id);
		if(op.isEmpty()) {
			return new BasicRes(RtnCode.ID_NOT_FOUND);
		}
		AuthCode authCodeEntity = op.get();
		if(authCodeEntity.getAuthCode().equals(authCode)) {
			return new BasicRes(RtnCode.AUTH_CODE_NOT_MATCHED);
		}
		LocalDateTime now = LocalDateTime.now();
		if(now.isAfter(authCodeEntity.getAuthDatetime())){
			return new BasicRes(RtnCode.AUTH_CODE_EXPIRED);
		}
		Employee employee = dao.findById(id).get();
		employee.setPwd(encoder.encode(newPwd));
		try {
			dao.save(employee);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnCode.CHANGE_PASSWORD_ERROR);
		}
		return new BasicRes(RtnCode.SUCCESSFUL);
	}

	
}//
