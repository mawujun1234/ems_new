package com.mawujun.mobile.gps.model;

import java.util.Date;

public class GpsMsg extends BaseMsg {
	public GpsMsg() {
		super();
		// TODO Auto-generated constructor stub
		super.setType(MsgType.GPS);
	}
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	
	private String sessionId;//回话id，可以用来区分同一天中的不同轨迹段
	private String uuid;//设备的id	
	private String loginName;//登录的用户名
	private String user_id;
	
	
	
	

	private String longitude;//经度--baidu
	private String latitude;//维度--baidu
	private String longitude_orgin;//经度--gps
	private String latitude_orgin;//维度--gps
	
	private Double altitude;//获取高度信息，目前只有是GPS定位结果时才有效，单位米.默认值Double.MIN_VALUE,但是数据库存储会出问题，所以默认值就改成0
	private Float  radius;///获取定位精度半径，单位是米
    public Float direction; // gps定位结果时，行进的方向，单位度，范围【0-360】，手机上部正朝向北的方向为0°方向
    public Float speed;// GPS速度当service的type是1，且创建该track的时候输入了这个字段才会返回。单位公里/小时，默认值0.0f
    public Double distance;//在同个会话中，距离上一次地点的距离,单位 米

    public String loc_type;//定位类型是gps定位还是网络定位
    private Date loc_time;// gps的上传时间
    private Long loc_time_interval;//两次定位的时间间隔，毫秒，主要用于过滤漂移的点位，gps_interval也可以，但是可能会存在定位数据丢失，所以自已有重新定义了一个
    								//第一个点位为0
    private Integer gps_interval;//定位时间间隔，毫秒，这是定义的时间间隔
    
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public Float getRadius() {
		return radius;
	}
	public void setRadius(Float radius) {
		this.radius = radius;
	}
	public Float getDirection() {
		return direction;
	}
	public void setDirection(Float direction) {
		this.direction = direction;
	}
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getLoc_type() {
		return loc_type;
	}
	public void setLoc_type(String loc_type) {
		this.loc_type = loc_type;
	}
	public Date getLoc_time() {
		return loc_time;
	}
	public void setLoc_time(Date loc_time) {
		this.loc_time = loc_time;
	}

	public Integer getGps_interval() {
		return gps_interval;
	}
	public void setGps_interval(Integer gps_interval) {
		this.gps_interval = gps_interval;
	}
	public String getLongitude_orgin() {
		return longitude_orgin;
	}
	public void setLongitude_orgin(String longitude_orgin) {
		this.longitude_orgin = longitude_orgin;
	}
	public String getLatitude_orgin() {
		return latitude_orgin;
	}
	public void setLatitude_orgin(String latitude_orgin) {
		this.latitude_orgin = latitude_orgin;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Long getLoc_time_interval() {
		return loc_time_interval;
	}
	public void setLoc_time_interval(Long loc_time_interval) {
		this.loc_time_interval = loc_time_interval;
	}
	@Override
	public String toString() {
		return "GpsMsg [sessionId=" + sessionId + ", uuid=" + uuid + ", loginName=" + loginName + ", user_id=" + user_id
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", longitude_orgin=" + longitude_orgin
				+ ", latitude_orgin=" + latitude_orgin + ", altitude=" + altitude + ", radius=" + radius
				+ ", direction=" + direction + ", speed=" + speed + ", distance=" + distance + ", loc_type=" + loc_type
				+ ", loc_time=" + loc_time + ", loc_time_interval=" + loc_time_interval + ", gps_interval="
				+ gps_interval + "]";
	}
   

}
