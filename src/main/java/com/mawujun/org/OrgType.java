package com.mawujun.org;

/**
 * 渠道类型
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public enum OrgType {
	company("公司"),department("部门"),workunit("作业单位"),store("仓库"),repaircentre("维修中心");
	
	private String name;
	
	OrgType(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
