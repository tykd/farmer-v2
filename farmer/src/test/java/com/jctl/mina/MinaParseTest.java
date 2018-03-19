package com.jctl.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.sql.visitor.functions.Substring;  
/**
 * minaTcp通信模块测试
 * @author Administrator
 *
 */
public class MinaParseTest extends IoHandlerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(MinaParseTest.class);
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LOGGER.info("客户端创建会话");
		super.sessionCreated(session);
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LOGGER.info("客户端开启会话");
		super.sessionOpened(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOGGER.info("客户端关闭会话");
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		LOGGER.info("会话休眠");
		super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		LOGGER.error("客户端绘画异常",cause);
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		LOGGER.info("客户端接收到消息");
		super.messageReceived(session, message);
		String result = message.toString();
		LOGGER.info(result);
		session.write(createGatewayInfo());
		messageSent(session, createGatewayInfo());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		LOGGER.info("客户端消息发送");
		super.messageSent(session, message);
	}
	
	/**
	 * 生成中级节点信息
	 * @return
	 */
	public String createGatewayInfo() {
		return new StringBuffer().append("Gateway:0-323430").append((int)((Math.random()*9+1)*100000)).append(";1-91%;2-").append("0C05").append((int)((Math.random()*9+1)*100000)).append("\r\n").toString();
	}
	
	public static void main(String[] args) {
		MinaParseTest minaParseTest = new MinaParseTest();
		System.out.println(minaParseTest.createGatewayInfo());
	}
}
