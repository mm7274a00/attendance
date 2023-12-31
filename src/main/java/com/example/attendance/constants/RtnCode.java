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
	LEAVE_TYPE_ERROR(400,"LeaveType error!"),//
	LEAVE_APPLIED_DATETIME_ERROR(400,"Leave appiled datetime error!"),//
	LEAVE_REASON_CANNOT_BE_EMPTY(400,"Leave reason cannot be empty!"),//
	LEAVE_REVIEWER_ID_CANNOT_BE_EMPTY(400,"Leave reviewer id cannot be empty!"),//
	LEAVE_REVIEWER_ID_NOT_FOUND(404,"Leave reviewer id not found!"), //
	PERMISSION_DENIED(403, "Permission denied!"),//
	LEAVE_APPLICATION_ERROR(400,"Leave appilcation error!"),//
	LEAVE_APPLICATION_NOT_FOUND(400,"Leave appilcation not found!"),//


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
