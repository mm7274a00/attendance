package com.example.attendance.constants;

public enum LeaveType {
	
	PERSONAL("personal"),//
	SICK("sick"),//
	OFFICIAL("official"),//
	ANNUAL("annual");
	
	private String type;
	
	private String parser;
	
	private LeaveType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public String getparser(String type) {
		for(LeaveType item:LeaveType.values()) {
			if(type.equalsIgnoreCase(item.getType())) {
				return item.getType();
			}
		}
		return null;
	}
}
