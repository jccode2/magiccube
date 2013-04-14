package com.magiccube.shop.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.magiccube.food.model.FoodGroupVO;
import com.magiccube.food.model.FoodVO;
import com.magiccube.order.model.OrderFoodView;
import com.magiccube.order.model.OrderView;
import com.magiccube.packages.model.PackageVO;

/**
 * 店铺管理Service
 * @author jcchen
 *
 */
@Service
public class ShopService {

	/**
	 * 查询订单列表
	 * @return
	 */
	public List<OrderView> queryOrderList() {
		List<OrderView> list = new ArrayList<OrderView>();
		
		int id = 10000101;
		String address = "深圳市福田区莲花路2075号香丽大厦二楼", 
				phone = "15818501051";
		double price = 20.0;
		
		OrderView vo = new OrderView();
		vo.setId(id);
		vo.setAddress(address);
		vo.setPhone(phone);
		vo.setTotalPrice(price);
		vo.setExceptTime("11:50");
		vo.setCreateTime("9:50");
//		vo.setFoodList(generateOrderViewFoods(11));
		list.add(vo);
		
		vo = new OrderView();
		vo.setId(++id);
		vo.setAddress(address);
		vo.setPhone(phone);
		vo.setTotalPrice(price -= 3);
		vo.setExceptTime("12:30");
		vo.setCreateTime("11:50");
//		vo.setFoodList(generateOrderViewFoods(8));
		list.add(vo);
		
		vo = new OrderView();
		vo.setId(++id);
		vo.setAddress(address);
		vo.setPhone(phone);
		vo.setTotalPrice(price -= 3);
		vo.setExceptTime("11:30");
		vo.setCreateTime("9:30");
//		vo.setFoodList(generateOrderViewFoods(5));
		list.add(vo);
		
		
		System.out.println("[ShopService] queryOrderList !");
		return list;
	}
	
	private List<OrderFoodView> generateOrderViewFoods(int amount) {
		List<OrderFoodView> all = allOrderFoods(), ret;
		if(amount > all.size()) {
			return all;
		} else {
			ret = new ArrayList<OrderFoodView>(amount);
			Random rd = new Random();
			int i = 0;
			while(i++ < amount) {
				ret.add(all.remove(rd.nextInt(all.size())));
			}
		}
		return ret;
	}
	
	/**
	 * 订单上所有可用的食物.
	 * @return
	 */
	private List<OrderFoodView> allOrderFoods() {
		List<OrderFoodView> foods = new ArrayList<OrderFoodView>();
		Random rd = new Random();
		foods.add(new OrderFoodView("煎羊扒配羅勒青醬汁", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("蠔豉髮菜花生燜豬腳", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("菠菜蕃茄千層粉", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("蠔油鮑魚西生菜", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("西蘭花雙菇炒紅腰紅", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("蠔油菇絲蟹柳扒節瓜甫", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("萝卜牛腩", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("清蒸鲈鱼", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("西兰花炒鲜鱿", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("猪扒", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("青瓜炒猪肝", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("铁板茄子", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("蕃茄蛋", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("麻婆豆腐", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("菜心", 1 + rd.nextInt(3), 10.0));
		foods.add(new OrderFoodView("正宗湖南手撕包菜", 1 + rd.nextInt(3), 10.0));
		return foods;
	}
	
	/**
	 * 查询食物分类列表
	 * @return
	 */
	public List<FoodGroupVO> queryFoodGroups() {
		List<FoodGroupVO> list = new ArrayList<FoodGroupVO>();
		list.add(newFoodGroupVO("10元区", null));
		list.add(newFoodGroupVO("9元区", null));
		list.add(newFoodGroupVO("8元区", null));
		list.add(newFoodGroupVO("7元区", null));
		list.add(newFoodGroupVO("6元区", null));
		list.add(newFoodGroupVO("例汤区", null));
		list.add(newFoodGroupVO("小吃", null));
		list.add(newFoodGroupVO("饮品", null));
		return list;
	}
	
	/**
	 * 组装一个FoodGroupVO
	 * @param name
	 * @param img
	 * @return
	 */
	private FoodGroupVO newFoodGroupVO(String name, String img) {
		FoodGroupVO vo = new FoodGroupVO();
		vo.setGroupName(name);
		vo.setImage(img);
		return vo;
	}
	
	/**
	 * 配餐模式页面的所有食物列表;包括各个分组及其下的所有食物。
	 * @return
	 */
	public Map<String, List<FoodVO>> queryFoods() {
		Map<String, List<FoodVO>> res = new LinkedHashMap<String, List<FoodVO>>();
		res.put("10元区", generateFoodList("排骨", "web/img/paigu.jpg", 7));
		res.put("9元区", generateFoodList("菜心", "web/img/chaixin.jpg", 4));
		res.put("8元区", generateFoodList("苦瓜炒蛋", "web/img/kuguachaodang.jpg", 3));
		res.put("7元区", generateFoodList("", "", 0));
		res.put("6元区", generateFoodList("", "", 0));
		res.put("例汤区", generateFoodList("", "", 0));
		res.put("小吃", generateFoodList("", "", 0));
		res.put("饮品", generateFoodList("", "", 0));
		return res;
	}
	
	/**
	 * 生成一个食物分组列表,测试代码
	 * @param name
	 * @param img
	 * @param amount
	 * @return
	 */
	private List<FoodVO> generateFoodList(String name, String img, int amount) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		FoodVO vo;
		for(int i = 0; i < amount; i++) {
			vo = new FoodVO();
			vo.setFoodName(name);
			vo.setImage(img);
			list.add(vo);
		}
		return list;
	}
	
	/**
	 * 套餐页面的所有套餐列表
	 * @return
	 */
	public Map<String, List<PackageVO>> queryPackages() {
		Map<String, List<PackageVO>> res = new LinkedHashMap<String, List<PackageVO>>();
		res.put("25元区", generatePackageList("猪扒套餐", "web/img/taochan2.jpg", 7));
		res.put("20元区", generatePackageList("菜心套餐", "web/img/taochan1.jpg", 5));
		res.put("18元区", generatePackageList("鸭腿套餐", "web/img/taochan3.jpg", 3));
		res.put("15元区", generatePackageList("", "", 0));
		return res;
	}
	
	/**
	 * 生成套餐列表,测试代码
	 * @param name
	 * @param img
	 * @param amount
	 * @return
	 */
	private List<PackageVO> generatePackageList(String name, String img, int amount) {
		List<PackageVO> list = new ArrayList<PackageVO>();
		PackageVO vo;
		for(int i = 0; i < amount; i++) {
			vo = new PackageVO();
			vo.setPackageName(name);
			vo.setImage(img);
			list.add(vo);
		}
		return list;
	}
}
