package com.magiccube.core.util.tools.socket;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-6-12 黄科林
 */
public class ChatServer {
	
	final static Logger logger = LoggerFactory.getLogger(ChatServer.class);
	
	//socket端口
	public static final int PORT = 8888;
	
	ServerBootstrap bootstrap;

	public void init() {
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				
				pipeline.addLast("encode", new ObjectEncoder());
				pipeline.addLast("decode", new ObjectDecoder());
				pipeline.addLast("handler", new ChatServerHandler());
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		bootstrap.setOption("reuseAddress", true);
	}

	public void startup() {
		bootstrap.bind(new InetSocketAddress(PORT));
		logger.info("服务器启动.   端口:" + PORT);
	}

	public void stop() {
		bootstrap.releaseExternalResources();
	}
	
}
