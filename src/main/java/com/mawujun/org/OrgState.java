package com.mawujun.org;

/**
 * 组织节点状态
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public enum OrgState {

	GB("关闭"),KQ("开启");
	
	private String name;
	OrgState(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
