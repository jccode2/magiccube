package com.magiccube.core.util.tools;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.MediaPrintableArea;

import com.magiccube.core.util.tools.DateTimeFormatUtils;
import com.magiccube.core.util.tools.StringUtil;
import com.magiccube.order.model.OrderFoodView;
import com.magiccube.order.model.OrderView;
import com.magiccube.order.model.PlateVO;


/**
 * 
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-3-25 黄科林
 */
class PosPrinter {
	
	private String logoPath;
	private boolean isFirstTime;
	private String operator;
	private List<DiscountInfo> discountInfos = new ArrayList<DiscountInfo>();
	
	
	public void print(OrderView orderView) throws Exception {
		StringBuffer sbPrinter = new StringBuffer();
		sbPrinter.append(getHead(orderView));
		sbPrinter.append(getContent(orderView));
		sbPrinter.append(getTail(orderView));
		
		//doPrint(1, sbPrinter.toString());
		
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		try {
			System.out.println(sbPrinter.toString());
			
			// 使用默认打印机，如果默认打印机不是POS打印机，请通过名称查找。    
		    PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
		    DocPrintJob job = printer.createPrintJob();
		    
		    
		    byte[] buf1 = sbPrinter.toString().getBytes();
		    byte[] data3 = new byte[buf1.length];
		    System.arraycopy(buf1,0,data3,0,buf1.length);
		    
		    // 获得打印属性
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			Doc doc2 = new SimpleDoc(new FileInputStream(logoPath), DocFlavor.INPUT_STREAM.PNG, null);
			
			MediaPrintableArea mp = new MediaPrintableArea(0f, 0f, 90f, 20f, Size2DSyntax.MM);// 5f, 5f, 100f, 40f
			pras.add(mp);
			DocPrintJob job2 = printer.createPrintJob();
		    job2.print(doc2, pras);
		    
		    InputStream stream = new ByteArrayInputStream(data3);
		    Doc doc = new SimpleDoc(stream, flavor, null);
		    job.print(doc, null);
		    
		}catch (Exception e) {
		    e.printStackTrace();
		    throw e;
		}
		
	}
	
	private String getHead(OrderView orderView){
		StringBuffer bf = new StringBuffer(128);
		String strPhone = orderView.getContact() != null ? orderView.getContact():orderView.getPhone();
		bf.append("          千方百味     　               \n");
		bf.append("        健康 营养 美味     　　　 \n");
		bf.append("*******************************\n");
		bf.append("订单："+orderView.getId()+"\n");
		bf.append("地址："+orderView.getAddress()+"\n");
		if (isFirstTime) {
			bf.append("电话：["+strPhone+"]\n");
		}else{
			bf.append("电话："+strPhone+"\n");
		}
		bf.append("期望时间："+orderView.getExceptTime()+"\n");
		bf.append("*******************************\n");
		return bf.toString();
	}
	
	private String getContent(OrderView orderView){
		StringBuffer bf = new StringBuffer(256);
		List<PlateVO> plates = orderView.getPlateList();
		for (int i = 0; i < plates.size(); i++) {
			PlateVO plateVO = plates.get(i);
			//bf.append("餐盘"+(i+1)+"\r\n");
			bf.append("\r\n");
			List<OrderFoodView> foodList = plateVO.getFoodList();
			for (int j = 0; j < foodList.size(); j++) {
				OrderFoodView food = foodList.get(j);
				bf.append(PosPrinter.getFormatFoodName(food.getFood())+""+PosPrinter.formateMoney(food.getPrice())+"    "+food.getAmount()+""+"\r\n");
			}
			//bf.append("----------------餐盘"+(i+1)+"合计:"+plateVO.getPrice()+"\r\n");
			bf.append("----------------------合计:"+plateVO.getPrice()+"\r\n"); 
			
			//遍历orderView中的优惠项
//			List lstDiscount = new ArrayList();
//			lstDiscount.add(1);
//			for (int j = 0; j < lstDiscount.size(); j++) {
//				//优惠信息  以及  优惠金额，读取lstDiscount中的信息
//				String strDiscountInfo = "5份及5份以上优惠   ";
//				double dDiscount = 5.00;
//				bf.append(" 优惠"+(j+1)+":"+strDiscountInfo+dDiscount+"\r\n"); 
//			}
			
			for (int j = 0; j < discountInfos.size(); j++) {
				//优惠信息  以及  优惠金额，读取lstDiscount中的信息
				DiscountInfo discountInfo = discountInfos.get(j);
				bf.append(" 优惠"+(j+1)+":"+discountInfo.discountMsg + discountInfo.discountTotalPrice+"\r\n"); 
			}
			
			
		}
		bf.append("\t\t总计金额："+orderView.getTotalPrice()+"\r\n");
		return bf.toString();
		
	}
	
	private static String getFormatFoodName(String name){
		int length = StringUtil.getStringLength(name);
		StringBuffer bf = new StringBuffer(32);
		if (length < 20) {
			bf.append(name);
			while (StringUtil.getStringLength(bf.toString()) < 20) {
				bf.append(" ");
			}
		}
		//System.out.println(bf.toString());
		//System.out.println(bf.toString().length());
		return bf.toString();
	}
	
	private String getTail(OrderView orderView){
		StringBuffer bf = new StringBuffer(128);
		bf.append("*******************************\n");
		bf.append("操作员：" + operator + " \r\n");
		
		bf.append("出单时间："+DateTimeFormatUtils.formatDateTime(new Date())+"\r\n");
		bf.append("    谢谢惠顾，欢迎网上订餐\r\n");
		bf.append("       www.1000funs.com\r\n");
		bf.append("      服务电话：23946364\r\n");
		
		return bf.toString();
	}
	
	private static String formateMoney(double d){
		return String.format("%1$6s", String.valueOf(d));
	}
	
	public static void main(String[] args) throws Exception {
		OrderView orderView = new OrderView();
		orderView.setId(198198);
		orderView.setAddress("福田区莲花路香丽大厦一楼");
		orderView.setPhone("13888888888");
		orderView.setExceptTime("12点钟");
		
		List plateList = new ArrayList();
		
		PlateVO plate1 = new PlateVO();
		List foodList = new ArrayList();
		OrderFoodView food1 = new OrderFoodView();
		food1.setFood("农家小炒肉套餐");
		food1.setPrice(14.00);
		food1.setAmount(2);
		
		OrderFoodView food2 = new OrderFoodView();
		food2.setFood("萝卜牛腩");
		food2.setPrice(10.00);
		food2.setAmount(1);
		
		OrderFoodView food3 = new OrderFoodView();
		food3.setFood("米饭");
		food3.setPrice(2.00);
		food3.setAmount(1);
		
		foodList.add(food1);
		foodList.add(food2);
		foodList.add(food3);
		plate1.setFoodList(foodList);
//		plate1.setPrice(40.00);
		
		PlateVO plate2 = new PlateVO();
		List foodList2 = new ArrayList();
		OrderFoodView food12 = new OrderFoodView();
		food12.setFood("相汁排骨套餐饭");
		food12.setPrice(18.00);
		food12.setAmount(2);
		
		OrderFoodView food22 = new OrderFoodView();
		food22.setFood("西红柿鸡蛋");
		food22.setPrice(6.00);
		food22.setAmount(1);
		
		OrderFoodView food32 = new OrderFoodView();
		food32.setFood("米饭");
		food32.setPrice(2.00);
		food32.setAmount(1);
		
		foodList2.add(food12);
		foodList2.add(food22);
		foodList2.add(food32);
		plate2.setFoodList(foodList2);
//		plate2.setPrice(44.00);
		
		plateList.add(plate1);
		plateList.add(plate2);
		
		orderView.setPlateList(plateList);
		orderView.setTotalPrice(84.00);
		
		
		PosPrinter p = new PosPrinter();
		p.setLogoPath("~/temp/funs3.png");
		p.setFirstTime(true);
		p.setOperator("admin");
		p.addDiscountInfo("5份及5份以上优惠   ", 5.0);
		
		p.print(orderView);
		
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public boolean isFirstTime() {
		return isFirstTime;
	}

	public void setFirstTime(boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public void addDiscountInfo(String msg, double discountTotalPrice) {
		discountInfos.add(new DiscountInfo(msg, discountTotalPrice));
	}
	
	/**
	 * 优惠信息
	 * @author jcchen
	 *
	 */
	class DiscountInfo {
		private String discountMsg;
		private double discountTotalPrice;
		public DiscountInfo(String discountMsg, double discountTotalPrice) {
			this.discountMsg = discountMsg;
			this.discountTotalPrice = discountTotalPrice;
		}
	}
}

