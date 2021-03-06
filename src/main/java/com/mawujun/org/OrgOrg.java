package com.mawujun.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mawujun.annotation.FieldDefine;
import com.mawujun.utils.Assert;
import com.mawujun.utils.help.ReportCodeHelper;

@Entity(name = "t_org_org")
public class OrgOrg  implements Serializable{
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="parent_id",nullable=false)
	private Org parent;

	@Id
	@ManyToOne
	@JoinColumn(name="child_id")
	private Org child;
	
	@Id
	@Column(length=15,nullable=false)
	@Enumerated(EnumType.STRING)
	private Dim dim;
	
	@Column(length=50,nullable=true,unique=false)
	@FieldDefine(title="汇报编码",sort=6)
	private String parent_reportCode;//0001.0001这种的
	@Column(nullable=true)
	@FieldDefine(title="层级",sort=6)
	private Integer parent_reportLevel;//
	
	@Column(length=50,nullable=true,unique=false)
	@FieldDefine(title="汇报编码",sort=6)
	private String child_reportCode;//0001.0001这种的
	@Column(nullable=true)
	@FieldDefine(title="层级",sort=6)
	private Integer child_reportLevel;//

	public OrgOrg() {

	}

	public OrgOrg(Org parent, Org child) {
		Assert.notNull(parent);
		Assert.notNull(child);
		this.parent = parent;
		this.child = child;
	}
	public void setParent_reportCode(String parent_reportCode) {
		this.parent_reportCode = parent_reportCode;
		if(parent_reportCode!=null&&!"".equals(parent_reportCode)){
			String aa[]=parent_reportCode.split(ReportCodeHelper.getSperator());
			if(aa!=null && aa.length>0){
				this.parent_reportLevel=aa.length;
			}
		}
	}
	public void setChild_reportCode(String child_reportCode) {
		this.child_reportCode = child_reportCode;
		if(child_reportCode!=null&&!"".equals(child_reportCode)){
			String aa[]=child_reportCode.split(ReportCodeHelper.getSperator());
			if(aa!=null && aa.length>0){
				this.child_reportLevel=aa.length;
			}
		}
	}

	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	public Org getChild() {
		return child;
	}

	public void setChild(Org child) {
		this.child = child;
	}

	
	public Dim getDim() {
		return dim;
	}

	public void setDim(Dim dim) {
		this.dim = dim;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((dim == null) ? 0 : dim.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrgOrg other = (OrgOrg) obj;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (dim != other.dim)
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

	public Integer getParent_reportLevel() {
		return parent_reportLevel;
	}

	public void setParent_reportLevel(Integer parent_reportLevel) {
		this.parent_reportLevel = parent_reportLevel;
	}

	public Integer getChild_reportLevel() {
		return child_reportLevel;
	}

	public void setChild_reportLevel(Integer child_reportLevel) {
		this.child_reportLevel = child_reportLevel;
	}

	public String getParent_reportCode() {
		return parent_reportCode;
	}

	public String getChild_reportCode() {
		return child_reportCode;
	}


}
