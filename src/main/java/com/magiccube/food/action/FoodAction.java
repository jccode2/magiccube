/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.food.action;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.magiccube.core.base.action.BaseAction;
import com.magiccube.core.springmvc.ApplicationContextInitor;
import com.magiccube.food.model.FoodGroupVO;
import com.magiccube.food.model.FoodQueryCondition;
import com.magiccube.food.model.FoodVO;
import com.magiccube.food.model.GroupFoods;
import com.magiccube.food.model.PackageItemVO;
import com.magiccube.food.model.PackageVO;
import com.magiccube.food.service.FoodService;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2012-12-18 Xingling build
 */
@Controller
@RemoteProxy
public class FoodAction extends BaseAction {
	
	final static Logger LOGGER = LoggerFactory.getLogger(FoodService.class);
	
	static ApplicationContext context = null;
	
	static FoodService foodService = null;
	
	static{
		context = ApplicationContextInitor.getContext();
		foodService = (FoodService)context.getBean("FoodService");
	}
	
	/**
	 * 增加食物（同时指定到店铺）
	 * @param foodVO
	 */
	public int insertFood(FoodVO foodVO){
		try{
			return foodService.insertFood(foodVO);
		}catch(Exception e){
			LOGGER.info("insertFood 出错："+e);
		}
		return 0;
	}
	
	public FoodVO getFood(int id) {
		return foodService.getFood(id);
	}
	
	public PackageVO getPackage(int id) {
		return foodService.getPackage(id);
	}
	
	public int insertFoodReShop(FoodVO foodVO){
		return foodService.insertFoodReShop(foodVO);
	}
	
	public int insertPackageItem(PackageItemVO packageItemVO) {
		return foodService.insertPackageItem(packageItemVO);
	}
	
	/**
	 * 新增一个套餐.
	 * @param packageVO
	 * @return
	 */
	public int insertPackage(PackageVO packageVO) {
		return foodService.insertPackage(packageVO);
	}
	
	/**
	 * 增加食物分组
	 * @param foodGroupVO
	 * @return int id
	 */
	public int insertFoodGroup(FoodGroupVO foodGroupVO){
		try{
			return foodService.insertFoodGroup(foodGroupVO); 
		}catch(Exception e){
			LOGGER.info("insertFood 出错："+e);
		}
		return 0;
	}
	
	/**
	 * 更新分组信息
	 * @param foodGroupVO
	 * @return
	 */
	public int updateGroup(FoodGroupVO foodGroupVO) {
		return foodService.updateGroup(foodGroupVO);
	}
	
	/**
	 * 更新食品信息
	 * @param foodVO
	 * @return
	 */
	public int updateFood(FoodVO foodVO) {
		return foodService.updateFood(foodVO);
	}
	
	/**
	 * 更新FoodReShop
	 * @param foodVO
	 * @return
	 */
	public int updateFoodReShop(FoodVO foodVO) {
		return foodService.updateFoodReShop(foodVO);
	}
	
	/**
	 * 更新FoodReShop下架状态
	 * @param foodId
	 * @param droped
	 * @return
	 */
	public int updateFoodReShopDroped(int foodId, boolean droped) {
		return foodService.updateFoodReShopDrop(foodId, droped);
	}
	
	/**
	 * 更新套餐
	 * @param packageVO
	 * @return true-成功; false-失败
	 */
	public boolean updatePackage(PackageVO packageVO) {
		return foodService.updatePackage(packageVO);
	}
	
	/**
	 * 查询所有在架食物单品
	 * @return List<FoodVO>
	 */
	@RemoteMethod
	public List<FoodVO> queryFoods(){
		List<FoodVO> result = foodService.queryFoods();
		return result;
	}
	
	@RemoteMethod
	public List<GroupFoods> queryAllGroupAndFoods(FoodQueryCondition foodQueryCondition) {
		return foodService.queryAllGroupAndFoods(foodQueryCondition);
	}
	
	public List<GroupFoods> queryAvailableGroupAndFoods(FoodQueryCondition foodQueryCondition) {
		return foodService.queryAvailableGroupAndFoods(foodQueryCondition);
	}
	
	@RemoteMethod
	public List<FoodGroupVO> queryGroups(int type) {
		return foodService.queryGroups(type);
	}
	
	/**
	 * 查询所有分组信息
	 * @return
	 */
	public List<FoodGroupVO> queryAllGroups() {
		return foodService.queryAllGroups();
	}
	
	/**
	 * 删除分组
	 * @param id
	 * @return
	 */
	public int deleteGroup(int id) {
		return foodService.deleteGroup(id);
	}
	
	/**
	 * 删除食物
	 * @param id
	 * @return
	 */
	public int deleteFood(int id) {
		return foodService.deleteFood(id);
	}
	
	/**
	 * 删除食物关联
	 * @param foodId Food ID
	 * @return 成功删除的条数
	 */
	public int deleteFoodReShop(int foodId) {
		return foodService.deleteFoodReShop(foodId);
	}
	
	/**
	 * 删除一个套餐
	 * @param packageVO 
	 * @return
	 */
	public boolean deletePackage(int packageId) {
		return foodService.deletePackage(packageId);
	}
	
	/**
	 * 查询单品食物
	 * @return
	 */
	public List<FoodVO> querySingleFoods(FoodQueryCondition queryCondition) {
		return foodService.querySingleFoods(queryCondition);
	}
	
	/**
	 * 查询可用食物(每种食物在每个店铺只能被添加一次)
	 * @return
	 */
	public List<FoodVO> queryAvailableFoods(FoodQueryCondition queryCondition) {
		return foodService.queryAvailableFoods(queryCondition);
	}
	
	/**
	 * 查询单品食物的总数
	 * @return
	 */
	public int querySingleFoodsCount() {
		return foodService.querySingleFoodsCount();
	}
}
