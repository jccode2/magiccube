package com.magiccube.order.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Order websocket 用于服务器往客户端实时推送提交的订单.
 * 
 * Servlet implementation class OrderWebSocketServlet
 */
public class OrderWebSocketServlet extends WebSocketServlet {
	
	private static final long serialVersionUID = 1L;
	final static Logger LOGGER = LoggerFactory.getLogger(OrderWebSocketServlet.class);
	private final AtomicInteger connectionIds;
	private static Set<OrderMessageInbound> connections = new CopyOnWriteArraySet<OrderMessageInbound>();
       
    /**
     * @see WebSocketServlet#WebSocketServlet()
     */
    public OrderWebSocketServlet() {
       this.connectionIds = new AtomicInteger(0);
    }

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
		return new OrderMessageInbound(this.connectionIds.incrementAndGet());
	}
	
	public static void broadcast(String message) {
		for(OrderMessageInbound connection : connections) {
			try {
				CharBuffer buffer = CharBuffer.wrap(message);
				connection.getWsOutbound().writeTextMessage(buffer);
			} catch (IOException e) {
				LOGGER.error("订单WebSocket服务器往客户端推送消息失败了!", e);
			}
		}
	}
	
	private final class OrderMessageInbound extends MessageInbound {
		
		private AtomicInteger id; 
		
		private OrderMessageInbound(int id) {
			this.id = new AtomicInteger(id);
		}
		
		@Override
		protected void onClose(int status) {
			OrderWebSocketServlet.this.connections.remove(this);
			LOGGER.info("客户端"+id+", 退出websocket服务器");
		}
		
		@Override
		protected void onOpen(WsOutbound outbound) {
			LOGGER.info("客户端"+id+", 加入websocket服务器");
			OrderWebSocketServlet.this.connections.add(this);
		}

		@Override
		protected void onBinaryMessage(ByteBuffer message) throws IOException {
			throw new UnsupportedOperationException("Binary message not supported.");
		}

		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
			// do nothing here. 
			// 这个方法是客户端往服务器发消息使用.
			// 因为目前不存在客户端往服务器发消息的场景.因此这里什么都不需要做.
			// System.out.println("客户端发消息过来啦!!~ "+ message);
		}
	}

}
