package com.mawujun.report;

import java.util.Date;
/**
 * 借用明细报表
 * @author mawujun 16064988@qq.com  
 *
 */
public class InstoreListReport {
	private String instore_id;
	private String project_id;
	private String project_name;
	private String operatedate;
	private String ecode;
	private String store_id;
	private String store_name;
	private String subtype_name;
	private String brand_name;
	private String prod_name;
	private String prod_style;
	private String prod_unit;
	private String instoretype;//从借用出去的还是领用借出去的，还是调拨借出去的
	private String memo;//备注

	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getOperatedate() {
		return operatedate;
	}
	public void setOperatedate(String operatedate) {
		this.operatedate = operatedate;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getSubtype_name() {
		return subtype_name;
	}
	public void setSubtype_name(String subtype_name) {
		this.subtype_name = subtype_name;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_style() {
		return prod_style;
	}
	public void setProd_style(String prod_style) {
		this.prod_style = prod_style;
	}
	public String getProd_unit() {
		return prod_unit;
	}
	public void setProd_unit(String prod_unit) {
		this.prod_unit = prod_unit;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getInstoretype() {
		return instoretype;
	}
	public void setInstoretype(String instoretype) {
		this.instoretype = instoretype;
	}
	public String getInstore_id() {
		return instore_id;
	}
	public void setInstore_id(String instore_id) {
		this.instore_id = instore_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	
	

}
