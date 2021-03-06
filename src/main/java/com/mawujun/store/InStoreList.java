package com.mawujun.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.UUIDEntity;

@Entity
@Table(name="ems_instorelist")
public class InStoreList extends UUIDEntity implements IEcodeCache{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length=15)
	private String inStore_id;//入库单id
	@Column(length=25)
	private String ecode;//设备编码
	
	private Boolean isnew=true;//是否是新产品,在入库的时候是新品还是旧品
	
	@Column(length=36)
	private String orderlist_id;//订单明细id
	
	public String getInStore_id() {
		return inStore_id;
	}
	public void setInStore_id(String inStore_id) {
		this.inStore_id = inStore_id;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getOrderlist_id() {
		return orderlist_id;
	}
	public void setOrderlist_id(String orderlist_id) {
		this.orderlist_id = orderlist_id;
	}
	public Boolean getIsnew() {
		return isnew;
	}
	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}
	

}
