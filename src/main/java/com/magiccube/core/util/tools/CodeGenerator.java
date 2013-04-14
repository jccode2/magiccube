package com.magiccube.core.util.tools;

import java.util.Calendar;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2013-1-9 Xingling build
 */
public class CodeGenerator {

	public enum TYPE {
		FOOD("F"), SHOP("S"), USER("U"),PACKAGE("P");

		private TYPE(String type) {
			value = type;
		}

		public String toString() {
			return value;
		}

		private String value;

	}

	/**
	 * 生成Code
	 * 
	 * @param type 生成Code的类型(sample:CodeGenerator.TYPE.FOOD)
	 * @param uid 生成Code时使用的唯一标识（可使用当前数据库表中记录数作为uid）
	 * @return String code
	 */
	public static String getCode(TYPE type,Object uid) {
		String result = type.toString();
		result += Calendar.getInstance().get(Calendar.YEAR);
		result += Calendar.getInstance().get(Calendar.MONTH);
		result += Calendar.getInstance().get(Calendar.DATE);
		result += "-";
		result += String.valueOf(uid);
		return result;
	}
}
