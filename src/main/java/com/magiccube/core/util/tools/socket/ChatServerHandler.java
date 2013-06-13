package com.magiccube.core.util.tools.socket;

import java.util.Date;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-6-12 黄科林
 */
public class ChatServerHandler extends SimpleChannelHandler {
	
	final static Logger logger = LoggerFactory.getLogger(ChatServerHandler.class);
	public static ChannelGroup channelGroup = new DefaultChannelGroup("client-channel-group");

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		Channel channel = e.getChannel();
		channel.close();
		if (channelGroup.contains(channel)) {
			logger.info("一个客户端退出："+channel.getId());
			channelGroup.remove(channel);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		String content = (String) e.getMessage();
		logger.debug("服务器收到" + e.getChannel().getId()
				+ " 的消息   时间：" + new Date().toLocaleString() + " 消息内容:\n"+ content);
		content=e.getChannel().getId()+":"+content;
		if (content.equalsIgnoreCase("bye")) {
			e.getChannel().close();
			channelGroup.remove(e.getChannel());
			return;
		} 
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)throws Exception {
		logger.info("新的客户端连入："+e.getChannel().getId());
		channelGroup.add(e.getChannel());
	}

	public static ChannelGroup getChannelGroup() {
		return channelGroup;
	}
}
