package com.mawujun.mobile.task;

import java.util.ArrayList;
import java.util.List;

import com.mawujun.task.TaskStatus;

public class MobileTaskVO {
	private String id;
	private String type;
	private String status;
	private String memo;
	
	private String hitchType_name;
	private String hitchReasonTpl_name;
	private String hitchReason;
	
	private String handleMethod_id;
	private String handleMethod_name;
	private String handle_contact;
	
	private String pole_id;
	private String pole_name;
	private String pole_code;
	private String pole_address;
	private String pole_longitude;
	private String pole_latitude;
	
	private List<Equiplist> equiplist;
	private List<Members> members;
	
	
	public String getHandle_contact() {
		return handle_contact==null?"":handle_contact;
	}
	public Boolean getCanedit(){
		if(TaskStatus.complete.toString().equals(status) || TaskStatus.submited.toString().equals(status)) {
			return false;
		} else {
			return true;
		}
	}
	public void addMember(Members member) {
		if(this.members==null){
			this.members=new ArrayList<Members>();
		}
		this.members.add(member);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPole_name() {
		return pole_name;
	}
	public void setPole_name(String pole_name) {
		this.pole_name = pole_name;
	}
	public String getPole_code() {
		return pole_code;
	}
	public void setPole_code(String pole_code) {
		this.pole_code = pole_code;
	}
	public String getPole_address() {
		return pole_address;
	}
	public void setPole_address(String pole_address) {
		this.pole_address = pole_address;
	}
	public List<Equiplist> getEquiplist() {
		return equiplist;
	}
	public void setEquiplist(List<Equiplist> equiplist) {
		this.equiplist = equiplist;
	}
	public List<Members> getMembers() {
		return members;
	}
	public void setMembers(List<Members> members) {
		this.members = members;
	}
	public String getPole_id() {
		return pole_id;
	}
	public void setPole_id(String pole_id) {
		this.pole_id = pole_id;
	}
	public String getPole_longitude() {
		return pole_longitude;
	}
	public void setPole_longitude(String pole_longitude) {
		this.pole_longitude = pole_longitude;
	}
	public String getPole_latitude() {
		return pole_latitude;
	}
	public void setPole_latitude(String pole_latitude) {
		this.pole_latitude = pole_latitude;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHitchType_name() {
		return hitchType_name;
	}
	public void setHitchType_name(String hitchType_name) {
		this.hitchType_name = hitchType_name;
	}
	public String getHitchReasonTpl_name() {
		return hitchReasonTpl_name;
	}
	public void setHitchReasonTpl_name(String hitchReasonTpl_name) {
		this.hitchReasonTpl_name = hitchReasonTpl_name;
	}
	public String getHitchReason() {
		return hitchReason;
	}
	public void setHitchReason(String hitchReason) {
		this.hitchReason = hitchReason;
	}
	public String getHandleMethod_id() {
		return handleMethod_id;
	}
	public void setHandleMethod_id(String handleMethod_id) {
		this.handleMethod_id = handleMethod_id;
	}
	public String getHandleMethod_name() {
		return handleMethod_name;
	}
	public void setHandleMethod_name(String handleMethod_name) {
		this.handleMethod_name = handleMethod_name;
	}
	
	public void setHandle_contact(String handle_contact) {
		this.handle_contact = handle_contact;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
