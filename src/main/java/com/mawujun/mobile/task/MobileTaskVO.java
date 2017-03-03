package com.mawujun.mobile.task;

import java.util.List;

public class MobileTaskVO {
	private String id;
	private String memo;
	
	private String pole_name;
	private String pole_code;
	private String pole_address;
	
	private List<Equiplist> equiplist;
	private List<Members> members;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPole_name() {
		return pole_name;
	}
	public void setPole_name(String pole_name) {
		this.pole_name = pole_name;
	}
	public String getPole_code() {
		return pole_code;
	}
	public void setPole_code(String pole_code) {
		this.pole_code = pole_code;
	}
	public String getPole_address() {
		return pole_address;
	}
	public void setPole_address(String pole_address) {
		this.pole_address = pole_address;
	}
	public List<Equiplist> getEquiplist() {
		return equiplist;
	}
	public void setEquiplist(List<Equiplist> equiplist) {
		this.equiplist = equiplist;
	}
	public List<Members> getMembers() {
		return members;
	}
	public void setMembers(List<Members> members) {
		this.members = members;
	}
}
