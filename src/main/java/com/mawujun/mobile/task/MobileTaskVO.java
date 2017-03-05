package com.mawujun.mobile.task;

import java.util.List;

public class MobileTaskVO {
	private String id;
	private String type;
	private String memo;
	
	private String pole_id;
	private String pole_name;
	private String pole_code;
	private String pole_address;
	private String pole_longitude;
	private String pole_latitude;
	
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
	public String getPole_id() {
		return pole_id;
	}
	public void setPole_id(String pole_id) {
		this.pole_id = pole_id;
	}
	public String getPole_longitude() {
		return pole_longitude;
	}
	public void setPole_longitude(String pole_longitude) {
		this.pole_longitude = pole_longitude;
	}
	public String getPole_latitude() {
		return pole_latitude;
	}
	public void setPole_latitude(String pole_latitude) {
		this.pole_latitude = pole_latitude;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
