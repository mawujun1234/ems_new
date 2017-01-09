package com.mawujun.org;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mawujun.utils.Assert;

/**
 * 这个职位可访问的组织单元
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity(name="t_position_org_access")
public class PositionOrgAccess implements Serializable{
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	Position position;
	@Id
	@ManyToOne
	Org org;//可以访问的组织节点
	
	private Boolean look=false;
	private Boolean edit=false;

	
	public PositionOrgAccess(){
		super();
	}
	
	public PositionOrgAccess(Position position, Org org) {
		super();
		Assert.notNull(position);
		Assert.notNull(org);
		this.position = position;
		this.org = org;

	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Org getOrg() {
		return org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}

	public Boolean getLook() {
		return look;
	}

	public void setLook(Boolean look) {
		this.look = look;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
