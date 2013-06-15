package com.magiccube.app;

import com.magiccube.core.util.tools.socket.SendPrintMessage;
import com.magiccube.order.model.OrderView;

public class TestPrint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ChatServer server = new ChatServer();
		server.init();
		server.startup();*/
		
		
		for (int i = 0; i < 20; i++) {
			
			OrderView order = new OrderView();
			order.setAddress("香丽大厦一楼"+i);
			
			
			System.out.println("添加person:"+order.getAddress());
			SendPrintMessage.vectPrintMessage.add(order);
			
			/*try {
				Thread.sleep(5000);
				SimpleChannelHandler handler = new ChatServerHandler();
				System.out.println("开始转发到其他客户端！:size="+ChatServerHandler.channelGroup.size());
				for(Channel ch:ChatServerHandler.channelGroup){
					System.out.println("开始转发到其他客户端！:"+ch.getId());
					ch.write(pseron);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		}
		
		
	}

}
