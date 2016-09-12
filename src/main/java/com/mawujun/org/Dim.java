package com.mawujun.org;

/**
 * 
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public enum Dim {
	base("基本维度"),manager("管理维度");
	
	private String name;
	
	Dim(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
