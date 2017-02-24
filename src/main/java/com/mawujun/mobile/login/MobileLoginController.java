package com.mawujun.mobile.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.mobile.geolocation.GeolocationController;
import com.mawujun.mobile.geolocation.GpsConfigService;
import com.mawujun.permission.ShiroUtils;

/**
 * 主要用在移动端的
 * @author mawujun 16064988@qq.com  
 *
 */
@Controller
public class MobileLoginController {
	private static Logger logger = LogManager.getLogger(MobileLoginController.class.getName());
//	@Resource
//	private WorkUnitService workUnitService;
	@Resource
	private GpsConfigService gpsConfigService;
//	@Autowired
//	private GeolocationController geolocationController;
	
	@RequestMapping("/mobile/login/login.do")
	@ResponseBody
	public ResultModel logIn(HttpServletRequest request,HttpServletResponse response ,String loginName,String password,Boolean rememberMe){
		//return null;
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.addHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS"); 
		//response.addHeader("Access-Control-Allow-Headers", "Content-type,hello");
		//System.out.println(username);
		Subject subject = SecurityUtils.getSubject(); 
		
		MobileUsernamePasswordToken token = new MobileUsernamePasswordToken(loginName, password); 
		token.setRememberMe(rememberMe==null?false:rememberMe);
		//subject.login(token);
		
		//Map<String,String> result=new HashMap<String,String>();
		
		String error=null;
		try {  
            subject.login(token);  
        } catch (AuthenticationException e ) {
        	logger.error(e);
            error = "用户名/密码错误";  
            e.printStackTrace();
            
            return ResultModel.getInstance().setMsg(error);
        } 
		//JsonConfigHolder.setRootName("reasons");
		//JsonConfigHolder.setJsonpCallback(jsonpCallback);
//        if(error != null) {//出错了，返回登录页面  
//            //req.setAttribute("error", error);  
//            //req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);  
//        	//JsonConfigHolder.setErrorsValue(error);
//        	JsonConfigHolder.setSuccessValue(false);
//        	JsonConfigHolder.addProperty("reasons",  Reason.getInstance().setReason(error));
//        	
//        	return new User();
//        	//return Reason.getInstance().setReason(error);
//        } else {//登录成功  
            //req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(req, resp); 
//        	 String successUrl = null;
//        	 SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
//             if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
//                 successUrl = savedRequest.getRequestUrl();
//             }
            
             //JsonConfigHolder.setDatePattern("yyyy-MM-dd hh:mm:ss");

             //JsonConfigHolder.addProperty("gps_interval",gpsConfigService.get().getInterval());
             //ShiroUtils.getAuthenticationInfo().setGps_interval(gpsConfigService.get().getInterval());
             //ShiroUtils.getAuthenticationInfo().setSessionId(subject.getSession().getId().toString());//就是用来和WaringGps统一管理的
             
//             WaringGps waringGps=new WaringGps();
//             waringGps.setSessionId(subject.getSession().getId().toString());
//             waringGps.setLoginName(loginName);
//             waringGps.setName(ShiroUtils.getAuthenticationInfo().getName());
//             waringGps.setPhone(ShiroUtils.getAuthenticationInfo().getPhone());
//             waringGps.setIsUploadGps(false);
//             waringGps.setLoginTime(new Date());
//             geolocationController.getWaringGpsMap().put(subject.getSession().getId().toString(), waringGps);
//             //return ShiroUtils.getAuthenticationInfo();
             return ResultModel.getInstance().setRoot(ShiroUtils.getAuthenticationInfo());
//        }  
		
	}
	
	@RequestMapping("/mobile/login/logout.do")
	@ResponseBody
	public String logout(){
		
		Subject subject = SecurityUtils.getSubject(); 
		 //geolocationController.getWaringGpsMap().remove(subject.getSession().getId().toString());
		subject.logout();
		
		
		return "logout";
	}
//	@RequestMapping("/mobile/updatePassword.do")
//	@ResponseBody
//	public String updatePassword(String password,String password_repeat){
//		//取消掉jsonpCallback，然后把www放到同个项目里好了
//		//JsonConfigHolder.setJsonpCallback(jsonpCallback);
//		User user=ShiroUtils.getAuthenticationInfo();
//		String loginName=user.getUsername();
//		workUnitService.update(Cnd.update().set(M.WorkUnit.password, password).andEquals(M.WorkUnit.loginName, loginName));	
//		return "{success:true}";
//	}
	
	
}
