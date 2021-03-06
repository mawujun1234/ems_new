package com.mawujun.install;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.UUIDEntity;

/**
 * 借用单明细
 * @author mawujun email:16064988@163.com qq:16064988
 *
 */
@Entity
@Table(name="ems_borrowlist")
public class BorrowList  extends UUIDEntity{
	private static final long serialVersionUID = 1L;
	@Column(length=16)
	private String borrow_id;//入库单id
	@Column(length=25)
	private String ecode;//设备编码
	//@org.hibernate.annotations.Type(type="yes_no")
	private Boolean isReturn=false;//是否已经归还,如果是领用了(任务提交的时间)，那也是true
	//@org.hibernate.annotations.Type(type="yes_no")
	private Boolean isnew=false;//

	private Date returnDate;//归还时间。领用时间(任务提交的时间)
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	private BorrowListType borrowListType=BorrowListType.borrow;
	@Column(length=36)
	private String pole_id;//点位id，当InstallOutListType==installout的时候，这个pole_id就有值，就表示这个设备被领用安装在哪个点位上了
	@Column(length=36)
	private String installOutType_id;//领用类型
	@Column(length=60)
	private String installOutType_content;//二级领用各类型
	
	@Column(length=100)
	private String memo;//归还的时候备注信息

	public String getBorrow_id() {
		return borrow_id;
	}

	public void setBorrow_id(String borrow_id) {
		this.borrow_id = borrow_id;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public Boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BorrowListType getBorrowListType() {
		return borrowListType;
	}

	public void setBorrowListType(BorrowListType borrowListType) {
		this.borrowListType = borrowListType;
	}

	public Boolean getIsnew() {
		return isnew;
	}

	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}

	public String getPole_id() {
		return pole_id;
	}

	public void setPole_id(String pole_id) {
		this.pole_id = pole_id;
	}

	public String getInstallOutType_id() {
		return installOutType_id;
	}

	public void setInstallOutType_id(String installOutType_id) {
		this.installOutType_id = installOutType_id;
	}

	public String getInstallOutType_content() {
		return installOutType_content;
	}

	public void setInstallOutType_content(String installOutType_content) {
		this.installOutType_content = installOutType_content;
	}
	
}
