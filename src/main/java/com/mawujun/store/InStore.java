package com.mawujun.store;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.IdEntity;

@Entity
@Table(name="ems_instore")
public class InStore  implements IdEntity<String>{
	@Id
	@Column(length=15)
	private String id;//入库单号，年日日时分秒
	@Column(length=36)
	private String store_id;
	@Column(length=36)
	private String operater;//仓管id
	private Date operateDate;//入库时间
	//private Integer type;//入库类型 1：新品入库，2：设备返库，3：维修入库
	@Column(length=100)
	private String memo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
}
