package com.mawujun.mobile.login;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.mawujun.permission.Menu;
import com.mawujun.permission.MenuService;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.permission.User;
import com.mawujun.permission.UserService;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;

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
//	@Resource
//	private GpsConfigService gpsConfigService;
//	@Autowired
//	private GeolocationController geolocationController;
	
	@Resource
	private MenuService menuService;
	@Resource
	private UserService userService;
	@Autowired
	private MobileLoginService mobileLoginService;
	
	@RequestMapping("/mobile/login/login.do")
	@ResponseBody
	public ResultModel logIn(HttpServletRequest request,HttpServletResponse response ,String loginname,String password,Boolean rememberMe){
		//return null;
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.addHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS"); 
		//response.addHeader("Access-Control-Allow-Headers", "Content-type,hello");
		//System.out.println(username);
		Subject subject = SecurityUtils.getSubject(); 
		
		MobileUsernamePasswordToken token = new MobileUsernamePasswordToken(loginname, password); 
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
            
            return ResultModel.getInstance().setMsg(error).setSuccess(false);
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
			userService.update(Cnd.update().set(M.User.lastlogintime, new Date()).andEquals(M.User.id, ShiroUtils.getUserId()));
             return ResultModel.getInstance().setRoot(ShiroUtils.getAuthenticationInfo());
//        }  
		
	}
	/**
	 * 获取用户可以访问的移动端功能
	 * @return
	 */
	@RequestMapping("/mobile/login/queryMobileMenuByUser.do")
	@ResponseBody
	public ResultModel queryMenues(){
		List<Menu> list= menuService.queryMobileMenuByUser(ShiroUtils.getUserId());
		//return ResultModel.getInstance().setRoot(list);
		//ResultModel result=ResultModel.getInstance();
		Map<String,Object> root=new HashMap<String,Object>();
		for(Menu menu:list){
			root.put(menu.getCode(), true);
		}
//		List<Map<String,Object>> tasknum=mobileTaskService.queryTasknum(ShiroUtils.getUserId());
//		for(Map<String,Object> map:tasknum){
//			Map<String,Object> num=new HashMap<String,Object>();
//			num.put("total_num",map.get("TOTAL_NUM") );
//			num.put("newTask_num", map.get("NEWTASK_NUM"));
//			num.put("read_num", map.get("READ_NUM"));
//			num.put("handling_num", map.get("HANDLING_NUM"));
//			num.put("submited_num", map.get("SUBMITED_NUM"));
//			root.put("task_"+map.get("TYPE"), num);
//		}
		//root.put("mobile_page_function_equip_have", true);
		return ResultModel.getInstance().setRoot(root);
	}
	
	@RequestMapping("/mobile/login/logout.do")
	@ResponseBody
	public ResultModel logout(){
		
		Subject subject = SecurityUtils.getSubject(); 
		 //geolocationController.getWaringGpsMap().remove(subject.getSession().getId().toString());
		subject.logout();
		
		
		return ResultModel.getInstance();
	}
	
	@RequestMapping("/mobile/login/queryMyinfo.do")
	@ResponseBody
	public ResultModel queryMyinfo(){
		String user_id=ShiroUtils.getUserId();
		List<Myinfo> list=mobileLoginService.queryMyinfo(user_id);
		ResultModel aa= ResultModel.getInstance().setRoot(list);
		
		aa.put("user_id", user_id);
		return aa;
	}
	@RequestMapping("/mobile/login/updatePassword.do")
	@ResponseBody
	public ResultModel updatePassword(String old_pwd,String new_pwd){
		//取消掉jsonpCallback，然后把www放到同个项目里好了
		//JsonConfigHolder.setJsonpCallback(jsonpCallback);
		User user=ShiroUtils.getAuthenticationInfo();
		
		ResultModel result=ResultModel.getInstance();
		if(!user.getPwd().equals(old_pwd)){
			result.setSuccess(false);
			result.setMsg("旧密码不正确");
		} else {
			userService.update(Cnd.update().set(M.User.pwd, new_pwd).andEquals(M.User.id, ShiroUtils.getUserId()));
		}
		
		return result;
	}
	
	
}
