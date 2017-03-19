package com.mawujun.mobile.task;

import com.mawujun.task.TaskType;

public class TaskesNum {
	private TaskType type;
	
	
	private Integer total_num;
	private Integer newTask_num;
	private Integer read_num;
	private Integer handling_num;
	private Integer submited_num;
	public TaskType getType() {
		return type;
	}
	public void setType(TaskType type) {
		this.type = type;
	}
	public Integer getTotal_num() {
		return total_num;
	}
	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}
	public Integer getNewTask_num() {
		return newTask_num;
	}
	public void setNewTask_num(Integer newTask_num) {
		this.newTask_num = newTask_num;
	}
	public Integer getRead_num() {
		return read_num;
	}
	public void setRead_num(Integer read_num) {
		this.read_num = read_num;
	}
	public Integer getHandling_num() {
		return handling_num;
	}
	public void setHandling_num(Integer handling_num) {
		this.handling_num = handling_num;
	}
	public Integer getSubmited_num() {
		return submited_num;
	}
	public void setSubmited_num(Integer submited_num) {
		this.submited_num = submited_num;
	}

}
