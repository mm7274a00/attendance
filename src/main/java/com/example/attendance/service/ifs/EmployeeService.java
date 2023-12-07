package com.example.attendance.service.ifs;

import com.example.attendance.vo.EmployeeCreateReq;

import javax.servlet.http.HttpSession;

import com.example.attendance.vo.BasicRes;

public interface EmployeeService {
	
	public BasicRes create(EmployeeCreateReq Req);
	
	public BasicRes login(String id, String pwd, HttpSession session);
	
	public BasicRes changePassword(String id, String oldPwd, String newPwd);
	
	public BasicRes forgotPassword(String id, String email);
	
	public BasicRes changePasswordByAuthCode(String id, String authCode, String newPwd);
	
	public BasicRes activate(String executorId, String emplyeeId);

	public BasicRes deactivate(String executorId, String emplyeeId);
	
	// 同時可達到 activate 跟 deactivate 的效用
	public BasicRes updateActivate(String executorId, String emplyeeId, boolean isActive);
	
	public BasicRes resign(String executorId);
}
