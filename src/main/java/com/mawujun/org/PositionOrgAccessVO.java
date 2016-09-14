package com.mawujun.org;

public class PositionOrgAccessVO {
	private String position_id;
	private String org_id;
	private Boolean look=false;
	private Boolean edit=false;
	
	private String org_code;
	private String org_name;
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public Boolean getLook() {
		return look;
	}
	public void setLook(Boolean look) {
		this.look = look;
	}
	public Boolean getEdit() {
		return edit;
	}
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

}
