package com.mawujun.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.IdEntity;

@Entity
@Table(name="ems_brand")
public class Brand implements IdEntity<String>{
	@Id
	@Column(length=15)
	private String id;
	@Column(length=30)
	private String name;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
