package com.mawujun.mobile.gps;

import java.net.InetAddress;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mawujun.mobile.geolocation.Geolocation;
import com.mawujun.mobile.geolocation.GeolocationService;
import com.mawujun.mobile.gps.model.GpsMsg;
import com.mawujun.mobile.gps.model.PushMsg;
import com.mawujun.utils.bean.BeanUtils;

@Service
public class LocServerService {
	@Autowired
	GeolocationService geolocationService;
	 private int port = 9091;
	
	@PostConstruct
	public void create() {
		
		NettyServerBootstrap.getInstance().create(port, new NettyServerBootstrap.OnStartListener() {

			@Override
			public void onSuccess() {
				System.out.println("netty创建成功！");
			}

			@Override
			public void onFailure() {
				System.out.println("netty创建失败...");
			}

			@Override
			public void onGpsupload(GpsMsg gpsMsg) {
				// TODO Auto-generated method stub
				System.out.println(gpsMsg.toString());
				Geolocation geolocation=BeanUtils.copyOrCast(gpsMsg, Geolocation.class);
				geolocation.setCreateDate(new Date());
				geolocationService.create(geolocation);
			}
		});
	}

	/**
	 * 向客户端推送消息
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	public void push() {
		String localName = "XXX";
		String localIp = "XXX";
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			localName = inetAddress.getHostName();
			localIp = inetAddress.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PushMsg pushMsg = new PushMsg();
		pushMsg.setTitle("服务器名称：" + localName);
		pushMsg.setContent("服务器IP：" + localIp);
		NettyServerBootstrap.getInstance().pushAll(pushMsg);
	}
}
