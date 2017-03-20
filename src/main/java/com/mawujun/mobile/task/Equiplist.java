package com.mawujun.mobile.task;

import com.mawujun.task.TaskListTypeEnum;

public class Equiplist {
	private String ecode;
	private String subtype_name;
	
	private TaskListTypeEnum install_type;//是安装还是卸载
	
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getSubtype_name() {
		return subtype_name;
	}
	public void setSubtype_name(String subtype_name) {
		this.subtype_name = subtype_name;
	}
	public TaskListTypeEnum getInstall_type() {
		return install_type;
	}
	public void setInstall_type(TaskListTypeEnum install_type) {
		this.install_type = install_type;
	}


}
