package com.example.attendance.constants;

public enum RtnCode {
	//http static code
	SUCCESSFUL(200,"Successful!"), //
	PARAM_ERROR(400,"Param error!"), //
	ID_HAS_EXISTED(400,"Id has existed!"), //
	DEPARTMENT_NOT_FOUND(404,"Department not found!"), //
	ID_NOT_FOUND(404,"Id not found!"), //
	PASSWORD_ERROR(400,"Password error!"), //
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
