package com.example.attendance.constants;

public enum RtnCode {
	//http static code
	SUCCESSFUL(200,"Successful!"), //
	PARAM_ERROR(400,"Param error!"), //
	ID_HAS_EXISTED(400,"Id has existed!"), //
	DEPARTMENT_NOT_FOUND(404,"Department not found!"), //
	ID_NOT_FOUND(404,"Id not found!"), //
	PASSWORD_ERROR(400,"Password error!"), //
	EMPLOYEE_CREATE_ERROR(400,"Employee create error!"), //
	PLEASE_LOGIN_FIRST(400,"Please login first!"), //
	UNAUTHORIZATED(401,"Unauthorizationed!"), //
	CHANGE_PASSWORD_ERROR(400,"Change password error!"), //
	OLD_PASSWORD_AND_NEW_PASSWORD_ARE_IDENTICAL(400,"Old password and new password are identical!"),//
	FORGOT_PASSWORD_ERROR(400,"Forgot password error!"), //
	AUTH_CODE_NOT_MATCHED(400,"Auth code not matched!"), //
	AUTH_CODE_EXPIRED(400,"Auth code expired!"), //
	UPDATE_FAILED(400,"Update failed!"),//
	UPDATE_ERROR(400,"Update error!"),//
	ACCOUNT_DEACTIVATE(400,"Account deactivate!"),//
	;
			
	private int code;
	
	private String message;

	private RtnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}//
