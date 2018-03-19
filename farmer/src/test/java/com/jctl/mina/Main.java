package com.jctl.mina;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.Test;

/**
 * 
 * @author Administrator
 *
 */
public class Main {
	@Test
	public void test() throws IOException {
		System.out.println("测试端已启动：。。。。");
		File file = new File("E:\\Developer\\GitHub\\cloud-farmer\\farmer\\src\\main\\resources\\proxyip.txt");
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String readLine = "";
			while(StringUtils.isNotEmpty(readLine = inputStream.readLine()))
				System.out.println(readLine.split(":")[0]);
				System.getProperties().setProperty("http.proxyHost", readLine.split(":")[0]);
				System.getProperties().setProperty("http.proxyPort", readLine.split(":")[1]);
				connect();
	}
	public void connect() {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		connector.setHandler(new MinaParseTest());
		ConnectFuture connectFuture = null;
		connectFuture = connector.connect(new InetSocketAddress("localhost", 41566));
		connectFuture.awaitUninterruptibly();
		IoSession session = connectFuture.getSession();
		session.getCloseFuture().awaitUninterruptibly();
	}
}
