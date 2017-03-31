package com.mawujun.baseinfo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ems_pole_photo")
public class PolePhoto{
	
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;//20170101121212
	
	@Column(length=36)
	private String pole_id;
	@Column(length=200)
	private String thumb_url;//可以用来存放缩略图
	@Column(length=200)
	private String url;//图片的位置
	@Column(length=36)
	private String user_id;//拍照者
	
	private Date uploadDate;

	public String getPole_id() {
		return pole_id;
	}

	public void setPole_id(String pole_id) {
		this.pole_id = pole_id;
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

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}


}
