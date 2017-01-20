package com.mawujun.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.UUIDEntity;

@Entity
@Table(name="ems_project")
public class Project extends UUIDEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length=30)
	private String name;
	@Column(length=30)
	private String sname;//简称
	@Column(length=100)
	private String memo;
	//@org.hibernate.annotations.Type(type="yes_no")
	private Boolean status=true;
	/**
	 * //值等于id，为了显示"所有"
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	public String getKey() {
		return this.getId();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	

}
