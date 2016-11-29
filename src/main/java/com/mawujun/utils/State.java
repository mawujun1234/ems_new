package com.mawujun.utils;

/**
 * 组织节点状态
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public enum State {

	valid("有效"),invalid("无效");
	
	private String name;
	State(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
