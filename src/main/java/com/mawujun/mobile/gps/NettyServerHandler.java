package com.mawujun.mobile.gps;

import com.mawujun.mobile.gps.NettyServerBootstrap.OnStartListener;
import com.mawujun.mobile.gps.model.BaseMsg;
import com.mawujun.mobile.gps.model.GpsMsg;
import com.mawujun.mobile.gps.model.MsgType;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {
	OnStartListener onStartListener;
	public  NettyServerHandler(OnStartListener onStartListener){
		super();
		this.onStartListener=onStartListener;
	}

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //channel失效，从Map中移除
        NettyChannelMap.remove((SocketChannel) ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("服务器端出现异常！");
    }

    //这里是从客户端过来的消息
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
    	if(MsgType.CONNECT==baseMsg.getType()) {
        	NettyChannelMap.remove(baseMsg.getClientId());
            //登录成功,把channel存到服务端的map中
            NettyChannelMap.add(baseMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
    	} else if(MsgType.GPS==baseMsg.getType()) {
    		GpsMsg gpsMsg=(GpsMsg)baseMsg;
    		onStartListener.onGpsupload(gpsMsg);
    	}

        
        ReferenceCountUtil.release(baseMsg);
        
        
//        if (MsgType.LOGIN.equals(baseMsg.getType())) {
//            LoginMsg loginMsg = (LoginMsg) baseMsg;
//            if (isLogin(loginMsg)) {
//                NettyChannelMap.remove(loginMsg.getClientId());
//                //登录成功,把channel存到服务端的map中
//                NettyChannelMap.add(loginMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
//                System.out.println("用户 " + loginMsg.getClientId() + " 登录成功");
//            } else {
//                LoginFailMsg loginFailMsg = new LoginFailMsg();
//                loginFailMsg.setFailInfo("用户名或密码错误！");
//                channelHandlerContext.channel().writeAndFlush(loginFailMsg);
//                System.out.println("用户名或密码错误！");
//            }
//        } else {
//            if (NettyChannelMap.get(baseMsg.getClientId()) == null) {
//                //说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
//                LoginFailMsg loginFailMsg = new LoginFailMsg();
//                loginFailMsg.setFailInfo("连接失败，需要重新登录...");
//                channelHandlerContext.channel().writeAndFlush(loginFailMsg);
//            } else {
//                switch (baseMsg.getType()) {
//                    case PUSH:
//                    	System.out.println("收到消息"+baseMsg.getClientId());
//                        break;
//                    default:
//                        System.out.println("default。。");
//                        break;
//                }
//            }
//        }
//        ReferenceCountUtil.release(baseMsg);
    }


}