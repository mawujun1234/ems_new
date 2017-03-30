package com.mawujun.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.UUIDEntity;

@Entity
@Table(name="ems_pole_photo")
public class PolePhoto extends UUIDEntity{
	
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	@Column(length=36)
	private String pole_id;
	@Column(length=2000)
	private String base64;//可以用来存放缩略图
	@Column(length=200)
	private String url;//图片的位置
	@Column(length=36)
	private String user_id;//拍照者

	public String getPole_id() {
		return pole_id;
	}

	public void setPole_id(String pole_id) {
		this.pole_id = pole_id;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


}
