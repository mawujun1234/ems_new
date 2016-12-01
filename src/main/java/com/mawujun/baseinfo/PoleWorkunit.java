package com.mawujun.baseinfo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mawujun.org.Org;
import com.mawujun.utils.Assert;

/**
 * 存放作业单位和点位的关系，一个点位可能被多个作业单位管理
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity(name = "ems_pole_workunit")
public class PoleWorkunit implements Serializable{

	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name="pole_id")
	private Pole pole;
	@Id
	@ManyToOne
	@JoinColumn(name="workunit_id")
	private Org workunit;
	
	public PoleWorkunit() {
		super();
	}
	
	public PoleWorkunit(Pole pole, Org workunit) {
		Assert.notNull(pole);
		Assert.notNull(workunit);
		this.pole = pole;
		this.workunit = workunit;
	}

	public Pole getPole() {
		return pole;
	}

	public void setPole(Pole pole) {
		this.pole = pole;
	}

	public Org getWorkunit() {
		return workunit;
	}

	public void setWorkunit(Org workunit) {
		this.workunit = workunit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pole == null) ? 0 : pole.hashCode());
		result = prime * result + ((workunit == null) ? 0 : workunit.hashCode());
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
		PoleWorkunit other = (PoleWorkunit) obj;
		if (pole == null) {
			if (other.pole != null)
				return false;
		} else if (!pole.equals(other.pole))
			return false;
		if (workunit == null) {
			if (other.workunit != null)
				return false;
		} else if (!workunit.equals(other.workunit))
			return false;
		return true;
	}
}
