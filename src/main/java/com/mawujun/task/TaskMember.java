package com.mawujun.task;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;



@Entity(name="ems_task_member")
@IdClass(TaskMember.PK.class)
public class TaskMember {
	@Id
	@Column(length=36)
	private String task_id;
	@Id
	@Column(length=36)
	private String user_id;
	
	//只是一个标识，没有用，只有在旧系统切换到新系统的时候，初始化过来的数据才会标识为true
	private Boolean isinit=false;
	
	public static class PK implements Serializable {
		/**
		 * @author mawujun qq:16064988 mawujun1234@163.com
		 */
		private static final long serialVersionUID = 1L;
		private String task_id;
		private String user_id;
		public String getTask_id() {
			return task_id;
		}
		public void setTask_id(String task_id) {
			this.task_id = task_id;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Boolean getIsinit() {
		return isinit;
	}

	public void setIsinit(Boolean isinit) {
		this.isinit = isinit;
	}
	
	
}
