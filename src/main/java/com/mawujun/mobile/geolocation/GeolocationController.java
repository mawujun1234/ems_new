package com.mawujun.mobile.geolocation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.mobile.login.WaringGps;



@Controller
public class GeolocationController {
	static Logger logger=LogManager.getLogger(GeolocationController.class);
	@Autowired
	private GeolocationService geolocationService;
	
	private Map<String,WaringGps> waringGpsMap=new HashMap<String,WaringGps>();
	

//	@RequestMapping("/geolocation/mobile/upload.do")
//	public String upload(Geolocation geolocation) {
//		//如果是很么值都没有就表示是心跳反应
//		if(geolocation==null || geolocation.getLatitude()==null || geolocation.getLongitude()==null){
//			return "{success:true}";
//		}
//		//System.out.println(ff.format(new Date())+"-----"+geolocation.getSessionId());
//		
//		//logger.info("==========================================================================");
//		//logger.info("经度:{},维度:{},uuid:{},loginname:{}",longitude,latitude,uuid,loginName);
//		
//		if(geolocation.getUuid()==null && geolocation.getLoginName()==null){
//			return "failure";
//		}
//		
//		
//
//		geolocation.setCreateDate(new Date());
//		
//		
//		geolocationService.create(geolocation);
//		
//		SecurityUtils.getSubject().getSession().getId();
//		//updateGpsUploadTime(geolocation.getSessionId(),geolocation.getLongitude(),geolocation.getLatitude(),geolocation.getLoc_time());
//		updateGpsUploadTime(geolocation);
//		return "{success:true}";
//	}
	

	
	/**
	 * 更新某个作业单位最近一次gps上传信息
	 * @author mawujun 16064988@qq.com 
	 * @param loginName
	 */
	//public void updateGpsUploadTime(String sessionId,String lasted_longitude, String lasted_latitude,Date loc_time) {
	public void updateGpsUploadTime(Geolocation geolocation) {
		WaringGps waringGps=waringGpsMap.get(geolocation.getSessionId());
		waringGps.setIsUploadGps(true);
		waringGps.setLastedUploadTime(geolocation.getLoc_time());
		waringGps.setLasted_longitude(geolocation.getLongitude());
		waringGps.setLasted_latitude(geolocation.getLatitude());
		
		TraceList traceList=new TraceList();
		try {
			BeanUtils.copyProperties(traceList, geolocation);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waringGps.addTraceListes(traceList);
		
//		TraceList traceList1=new TraceList();
//		try {
//			BeanUtils.copyProperties(traceList1, geolocation);
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		traceList1.setLongitude("121.52997");
//		traceList1.setLatitude("29.825078");
//		waringGps.addTraceListes(traceList1);
	}
	
	
	/**
	 * 当登录了，单没有gps信息上传过来的作业单位
	 * @author mawujun 16064988@qq.com 
	 * @return
	 */
	@RequestMapping("/geolocation/unuploadGpsWorkunit.do")
	@ResponseBody
	public List<WaringGps> unuploadGpsWorkunit(String status) {
		List<WaringGps> list=new ArrayList<WaringGps>();
		if("unuploadgps".equals(status)){
			for(Entry<String,WaringGps> entry:waringGpsMap.entrySet()){
				WaringGps waringGps=entry.getValue();
				if(!waringGps.getIsUploadGps()){
					list.add(waringGps);
				}
				
			}
		} else if("logined".equals(status)) {
			for(Entry<String,WaringGps> entry:waringGpsMap.entrySet()){
				WaringGps waringGps=entry.getValue();
				list.add(waringGps);
				
			}
		} else {
			for(Entry<String,WaringGps> entry:waringGpsMap.entrySet()){
				WaringGps waringGps=entry.getValue();
				list.add(waringGps);
				
			}
		}
		
		//JsonConfigHolder.setDatePattern("yyyy-MM-dd HH:mm:ss");
		return list;
	}
	/**
	 * 查询正在工作，也表示正在上传gps数据的作业单位
	 * @author mawujun 16064988@qq.com 
	 * @return
	 */
	@RequestMapping("/geolocation/queryWorkingWorkunit.do")
	@ResponseBody
	public List<WaringGps> queryWorkingWorkunit(){
		List<WaringGps> list=new ArrayList<WaringGps>();
		for(Entry<String,WaringGps> entry:waringGpsMap.entrySet()){
			WaringGps waringGps=entry.getValue();
			if(waringGps.getIsUploadGps()){
				list.add(waringGps);
			}
			
		}
		
		//JsonConfigHolder.setDatePattern("yyyy-MM-dd HH:mm:ss");
		return list;
		
	}

	public Map<String, WaringGps> getWaringGpsMap() {
		return waringGpsMap;
	}
	

}
