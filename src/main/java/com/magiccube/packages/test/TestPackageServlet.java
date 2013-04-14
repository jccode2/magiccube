package com.magiccube.packages.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magiccube.core.base.model.ResultVO;
import com.magiccube.packages.action.PackageAction;
import com.magiccube.packages.model.PackageGroupVO;
import com.magiccube.packages.model.PackageVO;

public class TestPackageServlet extends HttpServlet {

	static PackageAction action = new PackageAction();

	/**
	 * FOR TEST
	 * init test datas
	 */
	public static void insertPackage() {
		
		PackageVO packageVO = new PackageVO();
		String code = "2013P-00101-00001";
		packageVO.setCode(code);
		packageVO.setPackageName("12元经济型农家小炒肉套餐A");
		packageVO.setDetail("好吃又实惠！");
		packageVO.setImage("/web/images/" + code + ".png");
		packageVO.setCurrentPrice(12.0);
		packageVO.setOriginPrice(12.0);
		packageVO.setShopId(1);
		packageVO.setStock(10);
		
		ResultVO result = action.insertPackage(packageVO);
		if (result.isSuccess()) {
			System.out.println("【insertPackage()测试通过】");
		}else{
			System.out.println("!!!!insertPackage()测试未通过!!!!");
		}
	}
	
	public static void insertPackageGroup(){
		PackageGroupVO packageGroupVO = new PackageGroupVO();
		packageGroupVO.setGroupName("12元套餐");
		packageGroupVO.setDetail("12元套餐,好吃又实惠！");
		//packageGroupVO.setImage("/web/images/" + code + ".png");
		
		ResultVO result = action.insertPackageGroup(packageGroupVO);
		if (result.isSuccess()) {
			System.out.println("【insertPackageGroup()测试通过】");
		}else{
			System.out.println("!!!!insertPackageGroup()测试未通过!!!!");
		}
	}

	public static void queryPackages() {
		List<PackageVO> result = action.queryPackages();
		if (result == null || result.size() == 0) {
			System.out.println("!!!!queryPackages()未查到数据!!!!");
		}else{
			System.out.println("【queryPackages()】查到"+result.size()+"条数据");
			System.out.println("第一条数据是："+result.get(0));
		}
	}

	private static final long serialVersionUID = 2399166345711874635L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getRequestURI().endsWith("queryPackages")){
			queryPackages();
		}else if(req.getRequestURI().endsWith("insertPackage")){
			insertPackage();
		}else if(req.getRequestURI().endsWith("insertPackageGroup")){
			insertPackageGroup();
		}
	}
}
