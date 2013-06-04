package com.magiccube.core.util.tools;

import java.util.List;

import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.common.model.EnvironmentInfoVO;
import com.magiccube.common.util.EnvUtils;
import com.magiccube.order.model.OrderView;
import com.magiccube.order.util.OrderDiscount;
import com.magiccube.order.util.OrderDiscount.Type;
import com.magiccube.user.model.UserVO;

public class PosService {

	final static Logger logger = LoggerFactory.getLogger(PosPrinter.class);
	public static String LOGO_PATH = EnvironmentInfoVO.WEBROOT+"web/img/logo.png";
	
	
	public static void print(OrderView orderView) {
		try {
			logger.debug("开始打印小票....");
			
			PosPrinter p = getPrinter(orderView);
			p.print(orderView);
			
			logger.debug("打印结束....");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("打印小票出错.", e);
		}
	}
	
	private static PosPrinter getPrinter(OrderView orderView) {
		UserVO currUser = getCurrentUser();
		
		PosPrinter p = new PosPrinter();
		p.setLogoPath(LOGO_PATH);
		p.setOperator(currUser.getUserName());
		
		OrderDiscount od = new OrderDiscount(orderView.getDiscountCode());
		if(od.isDiscount()) {
			List<Type> list = od.getDiscounts();
			for(Type t : list) {
				p.addDiscountInfo(t.getShowText(), t.getPriceDiscount() * orderView.getFoundAmount());
			}
		}
		p.setFirstTime(od.isDiscount(OrderDiscount.Type.FIRST_ORDER));
		
		return p;
	}
	
	private static UserVO getCurrentUser() {
		return (UserVO)EnvUtils.session().getAttribute("current_user");
	}
}
