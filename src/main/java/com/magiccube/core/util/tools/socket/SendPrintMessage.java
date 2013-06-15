package com.magiccube.core.util.tools.socket;

import java.util.Vector;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.order.model.OrderView;

/**
 * 
 * 
 * @author 黄科林
 * @since jdk6.0
 * @version 2013-6-12 黄科林
 */
public class SendPrintMessage extends Thread {
	
	final static Logger logger = LoggerFactory.getLogger(SendPrintMessage.class);
	private static ChatServer chatServer;

	public static Vector<OrderView> vectPrintMessage = new Vector<OrderView>();

	static {
		SendPrintMessage send = new SendPrintMessage();
		send.start();
	}

	public SendPrintMessage() {
		chatServer = new ChatServer();
		chatServer.init();
		chatServer.startup();
		logger.info("启动打印服务器....");
	}

	public void run() {
		while (true) {
			if (SendPrintMessage.vectPrintMessage.size() > 0) {
				try {
					Thread.sleep(5000);
					logger.debug("开始发送打印消息至客户端！:size="
							+ ChatServerHandler.channelGroup.size());
					for (Channel ch : ChatServerHandler.channelGroup) {
						logger.debug("发送打印消息至客户端！:" + ch.getId());
						ch.write((OrderView) SendPrintMessage.vectPrintMessage
								.get(0));
					}
					OrderView ov = SendPrintMessage.vectPrintMessage.remove(0);
					logger.info("客户端打印: "+ov);
				} catch (Exception e) {
					logger.error("发送打印消息至客户端失败,异常：" + e);
				}
			}
		}
	}
}
