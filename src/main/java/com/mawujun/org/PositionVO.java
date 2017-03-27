package com.mawujun.org;

public class PositionVO extends Position {
	private String org_all_name;//职位所属的组织单元，展现形式是 aa>bb>cc>dd

	public String getOrg_all_name() {
		if(this.org_all_name==null){
			return "";
		}
		return org_all_name;
	}

	public void setOrg_all_name(String org_all_name) {
		
		this.org_all_name = org_all_name;
	}


}
