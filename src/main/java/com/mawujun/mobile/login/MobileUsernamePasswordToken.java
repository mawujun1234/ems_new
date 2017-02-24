package com.mawujun.mobile.login;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * 用于移动端的登录
 * @author mawujun 16064988@qq.com  
 *
 */
public class MobileUsernamePasswordToken extends UsernamePasswordToken {
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	private Boolean isMobile=true;
	 public MobileUsernamePasswordToken(final String username, final String password) {
	        super(username, password, false, null);
	    }
	 
	 public MobileUsernamePasswordToken(final String username, final String password, final boolean rememberMe) {
		 super(username, password, rememberMe, null);
	}

	public MobileUsernamePasswordToken(Boolean isMobile) {
		super();
		this.isMobile = isMobile;
	}

	public Boolean getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}
	
	

}
