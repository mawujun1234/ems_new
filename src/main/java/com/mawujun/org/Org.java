package com.mawujun.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FieldDefine;
import com.mawujun.annotation.ShowType;
import com.mawujun.utils.State;

@Entity
@Table(name="t_org")
public class Org {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(
	        name = "uuid",
	        strategy = "org.hibernate.id.UUIDGenerator"
	    )
	@FieldDefine(title="id",sort=7,hidden=false)
	@Column(length=36,nullable=false)
	private String id;
	@Column(length=30,nullable=true,unique=true)
	@FieldDefine(title="组织编码",sort=6)
	private String code;
	@Column(length=30,nullable=false)
	@FieldDefine(title="组织名称",sort=6)
	private String name;
	@Column(length=30,nullable=true)
	@FieldDefine(title="组织短名称",sort=5)
	public String name_short;
	
	@Column(length=15,nullable=false)
	@Enumerated(EnumType.STRING)
	@FieldDefine(title="组织类型",sort=4,showType=ShowType.combobox,hidden=false)//
	private OrgType orgType;//


	@Column(length=15,nullable=false)
	@Enumerated(EnumType.STRING)
	@FieldDefine(title="状态",sort=4,showType=ShowType.combobox,hidden=false)//
	private State state;
	@FieldDefine(title="排序",sort=4,hidden=true)	
	private Integer sort;
	
	public String getState_name() {
		if(state==null){
			return "";
		}
		return state.getName();
	}
	/**
	 * //值等于id，为了显示"所有"
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	public String getKey() {
		return this.getId();
	}

	
	
	
	public OrgType getOrgType() {
		return orgType;
	}
	public void setOrgType(OrgType orgType) {
		this.orgType = orgType;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getName_short() {
		return name_short;
	}
	public void setName_short(String name_short) {
		this.name_short = name_short;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	

	

}
