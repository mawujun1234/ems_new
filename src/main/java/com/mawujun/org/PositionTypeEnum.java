package com.mawujun.org;

public enum PositionTypeEnum {
	workunitgroup("作业单位小组"),employee("员工");
	
	private String name;
	
	PositionTypeEnum(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
