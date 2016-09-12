package com.mawujun.org;

public class NodeVO {
	
	private String id;//职位id或者orgno
	private String name;
	//private Boolean leaf;
	private String type;//节点类型，是组织节点和职位
	private String remark;
	
	private String org_id;//所在节点的组织单元，如果是组织单元就是自身的orgno
	
	private Boolean checked;//在查看职位可以访问的组织单元的时候用的
	
	public String getIconCls(){
		if("position".equals(this.getType())){
			return "icon-group";
		} else {
			return "";
		}
	}
	
	public Boolean getLeaf(){
		if( "position".equals(this.getType())){
			return true;
		} else {
			return false;
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

}
