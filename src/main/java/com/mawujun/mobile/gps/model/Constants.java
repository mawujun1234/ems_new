package com.mawujun.mobile.gps.model;

public class Constants {
    private static String clientId;
    private static String uuid;//设备的id	
    private static String loginName;//登录的用户名
    private static String user_id;
    private static Integer gps_interval=2000;//定位时间间隔，毫秒，这是定义的时间间隔
    
    private static String gps_host;
    private static String gps_port;

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        Constants.clientId = clientId;
    }

	public static String getUuid() {
		return uuid;
	}

	public static void setUuid(String uuid) {
		Constants.uuid = uuid;
	}

	public static String getLoginName() {
		return loginName;
	}

	public static void setLoginName(String loginName) {
		Constants.loginName = loginName;
	}

	public static Integer getGps_interval() {
		return gps_interval;
	}

	public static void setGps_interval(Integer gps_interval) {
		Constants.gps_interval = gps_interval;
	}

	public static String getUser_id() {
		return user_id;
	}

	public static void setUser_id(String user_id) {
		Constants.user_id = user_id;
	}

	public static String getGps_host() {
		return gps_host;
	}

	public static void setGps_host(String gps_host) {
		Constants.gps_host = gps_host;
	}

	public static String getGps_port() {
		return gps_port;
	}

	public static void setGps_port(String gps_port) {
		Constants.gps_port = gps_port;
	}
}
