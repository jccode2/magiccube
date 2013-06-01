package com.magiccube.order.util;

import java.util.ArrayList;
import java.util.List;

import com.magiccube.core.util.tools.DateTimeFormatUtils;
import com.magiccube.order.model.OrderFoodView;
import com.magiccube.order.model.OrderVOWithFood;
import com.magiccube.order.model.OrderView;
import com.magiccube.order.model.PlateVO;

public class OrderUtils {
	
	

	/**
	 * 将List<OrderVOWithFood>转换为List<OrderViewVO>
	 * 
	 * @param list
	 *            List<OrderVOWithFood>
	 * @return List<OrderViewVO>
	 */
	public static List<OrderView> transferOrderVOToView(List<OrderVOWithFood> list) {
		List<OrderView> ret = new ArrayList<OrderView>();
		int oldOrderId = 0, oldPlate = 0;
		OrderView view = null;
		List<PlateVO> plateList = null;
		PlateVO plate = null;
		for (OrderVOWithFood vo : list) {
			if (oldOrderId != vo.getId()) { // new
				// reset
				oldOrderId = vo.getId();
				oldPlate = 0;

				if (view != null)
					ret.add(view); // add the prior one
				view = new OrderView();
				view.setId(vo.getId());
				view.setAddress(vo.getAddress());
				view.setContact(vo.getContact());
				view.setCreateTime(DateTimeFormatUtils.formatDateTime(vo
						.getCreateTime()));
				view.setExceptTime(DateTimeFormatUtils.formatDateTime(vo
						.getExceptTime()));
				view.setOrderStatus(vo.getOrderStatus());
				view.setPhone(vo.getPhone());
				view.setTotalPrice(vo.getTotalPrice());
				view.setActuallyPrice(vo.getActuallyPrice());

				plateList = new ArrayList<PlateVO>();
				view.setPlateList(plateList);
			}

			if (oldPlate != vo.getPlate()) {
				oldPlate = vo.getPlate();
				plate = new PlateVO(vo.getPlate());
				plateList.add(plate);
			}

			OrderFoodView foodView = new OrderFoodView();
			foodView.setId(vo.getFoodId());
			foodView.setFood(vo.getFoodName());
			foodView.setAmount(vo.getAmount());
			foodView.setPrice(vo.getPrice());
			plate.addFood(foodView);
		}
		if (view != null) { // add the last one
			ret.add(view);
		}
		return ret;
	}
}
