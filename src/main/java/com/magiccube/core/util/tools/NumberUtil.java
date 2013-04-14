/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.util.tools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * 数值操作函数扩展
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-16 黄科林
 */
public final class NumberUtil {
	
	/**
     * 构造函数
     */
    private NumberUtil() {
    }
    
    /**
     * 转换String为int类型，转换失败返回默认指定的数字defaultValue
     * 
     * @param str 需转换的字符串
     * @param defaultValue 转换失败返回的默认数字
     * @return 转换后的数字，如果失败即为默认指定数字
     */
    public static int stringToInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }
    
    /**
     * 解析字符串获得整型数值
     * 
     * @param string 待解析的字符串
     * @return 字符串中包含的整型数值
     */
    public static int getIntValue(String string) {
        int iValue = 0;
        if (StringUtil.isNotEmpty(string)) {
            StringBuffer sbBuffer = new StringBuffer();
            char[] chArs = string.toCharArray();
            for (int i = 0; i < chArs.length; i++) {
                char chChar = chArs[i];
                if (chChar >= '0' && chChar <= '9') {
                    sbBuffer.append(chChar);
                } else if (chChar == ',') {
                    continue;
                } else {
                    if (sbBuffer.length() != 0) {
                        break;
                    }
                }
            }
            if (StringUtil.isNotEmpty(sbBuffer.toString())) {
                iValue = Integer.parseInt(sbBuffer.toString());
            }
        }
        return iValue;
    }
    
    /**
     * 转换一个字符串为Float型数字<br>
     * 输入null字符串就返回null
     * 
     * @param str 要转换的字符串，可以为null
     * @return 转换后的Float类型数字，输入null返回null
     */
    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }
    
    /**
     * 解析字符串获得双精度型数值
     * 
     * @param string 待解析的字符串
     * @return 字符串中包含的双精度型数值
     */
    public static double getDoubleValue(String string) {
        double dValue = 0;
        
        if (string != null && string.length() != 0) {
            StringBuffer sbBuffer = new StringBuffer();
            
            char[] chArs = string.toCharArray();
            for (int i = 0; i < chArs.length; i++) {
                char chChar = chArs[i];
                if (chChar >= '0' && chChar <= '9') {
                    sbBuffer.append(chChar);
                } else if (chChar == '.') {
                    if (sbBuffer.length() == 0) {
                        continue;
                    } else if (sbBuffer.indexOf(".") != -1) {
                        break;
                    } else {
                        sbBuffer.append(chChar);
                    }
                } else {
                    if (sbBuffer.length() != 0) {
                        break;
                    }
                }
            }
            if (StringUtil.isNotEmpty(sbBuffer.toString())) {
                dValue = Double.parseDouble(sbBuffer.toString());
            }
        }
        return dValue;
    }
    
    /**
     * 转换字符串为Integer类型，能转换8进制，10进制，16进制的形式。 8进制：070 ； 16 进制 ：0x10。 如果输入为null 返回null
     * 
     * @param str 需转换的字符串,可以为null。
     * @return 转换后的数字，输入为null返回null。
     */
    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        // decode() handles 0xAABD and 0777 (hex and octal) as well.
        return Integer.decode(str);
    }
    
    /**
     * 转换字符串为Short类型，能转换8进制，10进制，16进制的形式。 8进制：070 ； 16 进制 ：0x10。 如果输入为null 返回null
     * 
     * @param str 需转换的字符串,可以为null。
     * @return 转换后的数字，输入为null返回null。
     */
    public static Short createShort(String str) {
        if (str == null) {
            return null;
        }
        return Short.decode(str);
    }
    
    /**
     * 转换字符串为Long类型。 输入null返回null。
     * 
     * @param str 要转换的字符串
     * @return 转换后的数字
     */
    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.valueOf(str);
    }
    
    /**
     * 转换字符串为BigInteger类型。 输入null返回null。
     * 
     * @param str 要转换的字符串
     * @return 转换后的数字
     */
    public static BigInteger createBigInteger(String str) {
        if (str == null) {
            return null;
        }
        return new BigInteger(str);
    }
    
    /**
     * 转换字符串为BigDecimal类型。 输入null返回null。
     * 
     * @param str 要转换的字符串
     * @return 转换后的数字
     */
    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (NumberUtil.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        return new BigDecimal(str);
    }
    
    /**
     * 检查字符串中的每个字符是否为空白<br>
     * 关于空白的定义参考jdk文档中Character.isWhitespace关于空白的定义
     * 
     * @param str 需检查的字符串
     * @return true：字符串不是空白；false：字符串为空白。
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || str.length() == 0) {
            return true;
        }
        strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 获得一组int型数组中的最小值
     * 
     * @param array int数组，不能为null或者不包含数据
     * @return 数组中的最小值
     */
    public static int min(int[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        int iMin = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] < iMin) {
                iMin = array[j];
            }
        }
        return iMin;
    }
    
    /**
     * 获得一组short型数组中的最小值
     * 
     * @param array short数组，不能为null或者不包含数据
     * @return 数组中的最小值
     */
    public static short min(short[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        short sMin = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < sMin) {
                sMin = array[i];
            }
        }
        
        return sMin;
    }
    
    /**
     * 获得一组double型数组中的最小值
     * 
     * @param array double数组，不能为null或者不包含数据
     * @return 数组中的最小值
     */
    public static double min(double[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        double dMin = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < dMin) {
                dMin = array[i];
            }
        }
        return dMin;
    }
    
    /**
     * 获得一组float型数组中的最小值
     * 
     * @param array float数组，不能为null或者不包含数据
     * @return 数组中的最小值
     */
    public static float min(float[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        float fMin = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < fMin) {
                fMin = array[i];
            }
        }
        
        return fMin;
    }
    
    /**
     * 获得一组long型数组中的最大值
     * 
     * @param array long数组，不能为null或者不包含数据
     * @return 数组中的最大值
     */
    public static long max(long[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        long lMax = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > lMax) {
                lMax = array[j];
            }
        }
        
        return lMax;
    }
    
    /**
     * 获得一组int型数组中的最大值
     * 
     * @param array int数组，不能为null或者不包含数据
     * @return 数组中的最大值
     */
    public static int max(int[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        int iMax = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > iMax) {
                iMax = array[j];
            }
        }
        
        return iMax;
    }
    
    /**
     * 获得一组short型数组中的最大值
     * 
     * @param array short数组，不能为null或者不包含数据
     * @return 数组中的最大值
     */
    public static short max(short[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        short sMax = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > sMax) {
                sMax = array[i];
            }
        }
        
        return sMax;
    }
    
    /**
     * 获得一组double型数组中的最大值
     * 
     * @param array double数组，不能为null或者不包含数据
     * @return 数组中的最大值
     */
    public static double max(double[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        double dMax = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > dMax) {
                dMax = array[j];
            }
        }
        
        return dMax;
    }
    
    /**
     * 获得一组float型数组中的最大值
     * 
     * @param array float数组，不能为null或者不包含数据
     * @return 数组中的最大值
     */
    public static float max(float[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        float fMax = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > fMax) {
                fMax = array[j];
            }
        }
        
        return fMax;
    }
    
    /**
     * 返回3个long类型数字中的最小值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个long类型数字中的最小值
     */
    public static long min(long a, long b, long c) {
        long lX = a;
        if (b < lX) {
            lX = b;
        }
        if (c < lX) {
            lX = c;
        }
        return lX;
    }
    
    /**
     * 返回3个int类型数字中的最小值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个int类型数字中的最小值
     */
    public static int min(int a, int b, int c) {
        int lX = a;
        if (b < lX) {
            lX = b;
        }
        if (c < lX) {
            lX = c;
        }
        return lX;
    }
    
    /**
     * 返回3个short类型数字中的最小值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个short类型数字中的最小值
     */
    public static short min(short a, short b, short c) {
        short sX = a;
        if (b < sX) {
            sX = b;
        }
        if (c < sX) {
            sX = c;
        }
        return sX;
    }
    
    /**
     * 返回3个byte类型数据中的最小值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个byte类型数字中的最小值
     */
    public static byte min(byte a, byte b, byte c) {
        byte byteX = a;
        if (b < byteX) {
            byteX = b;
        }
        if (c < byteX) {
            byteX = c;
        }
        return byteX;
    }
    
    /**
     * 返回3个double类型数字中的最小值 如果参数中有NaN就返回NaN。Infinity能被处理。
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个double类型数字中的最小值
     */
    public static double min(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }
    
    /**
     * 返回3个float类型数字中的最小值 如果参数中有NaN就返回NaN。Infinity能被处理。
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个float类型数字中的最小值
     */
    public static float min(float a, float b, float c) {
        return Math.min(Math.min(a, b), c);
    }
    
    /**
     * 返回3个long类型数字中的最大值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个long类型数字中的最大值
     */
    public static long max(long a, long b, long c) {
        long lX = a;
        if (b > lX) {
            lX = b;
        }
        if (c > lX) {
            lX = c;
        }
        return lX;
    }
    
    /**
     * 返回3个int类型数字中的最大值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个int类型数字中的最大值
     */
    public static int max(int a, int b, int c) {
        int iX = a;
        if (b > iX) {
            iX = b;
        }
        if (c > iX) {
            iX = c;
        }
        return iX;
    }
    
    /**
     * 返回3个short类型数字中的最大值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个short类型数字中的最大值
     */
    public static short max(short a, short b, short c) {
        short sX = a;
        if (b > sX) {
            sX = b;
        }
        if (c > sX) {
            sX = c;
        }
        return sX;
    }
    
    /**
     * 返回3个byte类型数字中的最大值
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个byte类型数字中的最大值
     */
    public static byte max(byte a, byte b, byte c) {
        byte byteX = a;
        if (b > byteX) {
            byteX = b;
        }
        if (c > byteX) {
            byteX = c;
        }
        return byteX;
    }
    
    /**
     * 返回3个double类型数字中的最大值 如果参数中有NaN就返回NaN。Infinity能被处理。
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个double类型数字中的最大值
     */
    public static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }
    
    /**
     * 返回3个float类型数字中的最大值 如果参数中有NaN就返回NaN。Infinity能被处理。
     * 
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return 3个float类型数字中的最大值
     */
    public static float max(float a, float b, float c) {
        return Math.max(Math.max(a, b), c);
    }
    
    /**
     * 比较两个double类型数据的大小<br>
     * 如果第一个参数小于第二个参数返回-1;<br>
     * 如果第一个参数大于第二个参数返回+1;<br>
     * 如果第一个参数等于第二个参数返回0;
     * <p>
     * 数字从大到小的顺序依次为：
     * <ul>
     * <li>NaN
     * <li>Positive infinity
     * <li>Maximum double
     * <li>Normal positve numbers
     * <li>+0.0
     * <li>-0.0
     * <li>Normal negative numbers
     * <li>Minimum double (<code>-Double.MAX_VALUE</code>)
     * <li>Negative infinity
     * </ul>
     * </p>
     * NaN和NaN比较返回0
     * 
     * @param lhs 比较数1
     * @param rhs 比较数2
     * @return -1：lhs小，+1：lhs大，0：lhs和rhs相等
     */
    public static int compare(double lhs, double rhs) {
        if (lhs < rhs) {
            return -1;
        }
        if (lhs > rhs) {
            return +1;
        }
        // Need to compare bits to handle 0.0 == -0.0 being true
        // compare should put -0.0 < +0.0
        // Two NaNs are also == for compare purposes
        // where NaN == NaN is false
        long lLhsBits = Double.doubleToLongBits(lhs);
        long lRhsBits = Double.doubleToLongBits(rhs);
        if (lLhsBits == lRhsBits) {
            return 0;
        }
        // Something exotic! A comparison to NaN or 0.0 vs -0.0
        // Fortunately NaN's long is > than everything else
        // Also negzeros bits < poszero
        // NAN: 9221120237041090560
        // MAX: 9218868437227405311
        // NEGZERO: -9223372036854775808
        if (lLhsBits < lRhsBits) {
            return -1;
        }
        return +1;
    }
    
    /**
     * 比较两个float类型数据的大小<br>
     * 如果第一个参数小于第二个参数返回-1;<br>
     * 如果第一个参数大于第二个参数返回+1;<br>
     * 如果第一个参数等于第二个参数返回0;
     * <p>
     * 数字从大到小的顺序依次为：
     * <ul>
     * <li>NaN
     * <li>Positive infinity
     * <li>Maximum float
     * <li>Normal positve numbers
     * <li>+0.0
     * <li>-0.0
     * <li>Normal negative numbers
     * <li>Minimum float (<code>-Float.MAX_VALUE</code>)
     * <li>Negative infinity
     * </ul>
     * NaN和NaN比较返回0
     * 
     * @param lhs 比较数1
     * @param rhs 比较数2
     * @return -1：lhs小，+1：lhs大，0：lhs和rhs相等
     */
    public static int compare(float lhs, float rhs) {
        if (lhs < rhs) {
            return -1;
        }
        if (lhs > rhs) {
            return +1;
        }
        // Need to compare bits to handle 0.0 == -0.0 being true
        // compare should put -0.0 < +0.0
        // Two NaNs are also == for compare purposes
        // where NaN == NaN is false
        int iLhsBits = Float.floatToIntBits(lhs);
        int iRhsBits = Float.floatToIntBits(rhs);
        if (iLhsBits == iRhsBits) {
            return 0;
        }
        // Something exotic! A comparison to NaN or 0.0 vs -0.0
        // Fortunately NaN's int is > than everything else
        // Also negzeros bits < poszero
        // NAN: 2143289344
        // MAX: 2139095039
        // NEGZERO: -2147483648
        if (iLhsBits < iRhsBits) {
            return -1;
        }
        return +1;
    }
    
    /**
     * 提供精确的加法运算。
     * 
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double doubleAdd(double v1, double v2) {
        BigDecimal objB1 = new BigDecimal(Double.toString(v1));
        BigDecimal objB2 = new BigDecimal(Double.toString(v2));
        return objB1.add(objB2).doubleValue();
    }
    
    /**
     * 提供精确的减法运算。
     * 
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double doubleSub(double v1, double v2) {
        BigDecimal objB1 = new BigDecimal(Double.toString(v1));
        BigDecimal objB2 = new BigDecimal(Double.toString(v2));
        return objB1.subtract(objB2).doubleValue();
    }
    
    /**
     * 提供精确的乘法运算。
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double doubleMul(double v1, double v2) {
        BigDecimal objB1 = new BigDecimal(Double.toString(v1));
        BigDecimal objB2 = new BigDecimal(Double.toString(v2));
        return objB1.multiply(objB2).doubleValue();
    }
    
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double doubleDiv(double v1, double v2) {
        return doubleDiv(v1, v2, 10);
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double doubleDiv(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal objB1 = new BigDecimal(Double.toString(v1));
        BigDecimal objB2 = new BigDecimal(Double.toString(v2));
        return objB1.divide(objB2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal objB = new BigDecimal(Double.toString(v));
        return objB.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double doubleRound(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal objB = new BigDecimal(Double.toString(v));
        return objB.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String doubleRoundString(double v, int scale) {
        return Double.toString(doubleRound(v, scale));
    }
    
    /**
     * 将一个浮点数转换为一个带两位小数点的字符串，通常用于货币之间的转换
     * 
     * <pre>
     * NumberUtils.convertToMoney(0.0f)= 0.00
     * NumberUtils.convertToMoney(10.0f)= 10.00
     * NumberUtils.convertToMoney(1000.0f)= 1000.00
     * NumberUtils.convertToMoney(10.506f)= 10.52
     * NumberUtils.convertToMoney(5f)= 5.00
     * </pre>
     * 
     * @param money 需要被转换的浮点数
     * @return 返回被格式化的浮点数,通常情况下面该功能用于货币之间的转换。
     */
    public static String convertToMoney(float money) {
        BigDecimal objB = new BigDecimal(Double.toString(money));
        return objB.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * 以特定格式显示钱的数量，eg:100,000.23
     * 
     * <pre>
     * NumberUtils.moneyFormat(&quot;10.23&quot;)= 10.23
     * NumberUtils.moneyFormat(&quot;100.23&quot;)= 100.23
     * NumberUtils.moneyFormat(&quot;1000.23&quot;)= 1,000.23
     * NumberUtils.moneyFormat(&quot;10000.23&quot;)= 10,000.23
     * NumberUtils.moneyFormat(&quot;100000.23&quot;)= 100,000.23
     * NumberUtils.moneyFormat(&quot;0&quot;)= 0.00
     * NumberUtils.moneyFormat(&quot;-1&quot;)= -1.00
     * NumberUtils.moneyFormat(&quot;0.25&quot;)= 0.25
     * </pre>
     * 
     * @param money 需要被处理的钱的数据字符串。
     * @return 需要以某种特定格式进行转换后的字符串。
     */
    public static String moneyFormat(String money) {
        String strFormatMoney = "0.00";
        BigDecimal objBigDec = new BigDecimal(money);
        
        BigInteger objX100BigInt = objBigDec.multiply(new BigDecimal(100)).toBigInteger();
        String strX100Str = objX100BigInt.abs().toString();
        while (strX100Str.length() < 3) {
            strX100Str = "0" + strX100Str;
        }
        String strFDot = strX100Str.substring(0, strX100Str.length() - 2);
        String strBDot = strX100Str.substring(strX100Str.length() - 2);
        int i = strFDot.length() % 3;
        int j = strFDot.length() / 3;
        if (i == 0) {
            i = 3;
            j--;
        }
        StringBuffer objBuffer = new StringBuffer(strFDot.substring(0, i));
        for (int k = 0; k < j; k++) {
            objBuffer.append("," + strFDot.substring(i + (3 * k), i + (3 * (k + 1))));
        }
        strFDot = objBuffer.toString();
        strFormatMoney = strFDot + "." + strBDot;
        if (objX100BigInt.signum() == -1) {
            strFormatMoney = "-" + strFormatMoney;
        }
        return strFormatMoney;
    }
    
    /**
     * 以指定的格式来格式化数字。
     * 
     * <pre>
     * NumberUtils.formatDouble(0.25689d,&quot;#,##.#&quot;)= 0.3
     * NumberUtils.formatDouble(0.25689d,&quot;##,##.#&quot;)= 0.3
     * NumberUtils.formatDouble(0.25689d,&quot;###,##.##&quot;)= 0.26
     * NumberUtils.formatDouble(0.25689d,&quot;###,#,#.##&quot;)= 0.26
     * NumberUtils.formatDouble(0.25689d,&quot;#,#,#,##.##&quot;)= 0.26
     * NumberUtils.formatDouble(0.25689d,&quot;###,##.###&quot;)= 0.257
     * </pre>
     * 
     * @param value 需要被格式化的数字
     * @param format 指定将要被格式化的样式
     * @return 返回被格式化后的字符串。
     */
    public static String formatDouble(double value, String format) {
        DecimalFormat objDecimalFormat = new DecimalFormat(format);
        return objDecimalFormat.format(value);
    }
    
    /**
     * 转换String为long类型，转换失败返回默认指定的数字defaultValue
     * 
     * @param str 需转换的字符串
     * @param defaultValue 转换失败返回的默认数字
     * @return 转换后的数字，如果失败即为默认指定数字
     */
    public static long stringToLong(String str, long defaultValue) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }
    
    /**
     * 根据总记录数和每批次记录数获取
     * 
     * @param sizeCount 总记录数
     * @param pageSize 每批次记录数
     * @return 批次数量
     */
    public static int getPageCount(int sizeCount, int pageSize) {
        if (sizeCount <= 0) {
            return 0;
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("无效的每批次记录数");
        }
        int iPageNo = (sizeCount % pageSize == 0) ? (sizeCount / pageSize) : (sizeCount / pageSize + 1);
        return iPageNo;
    }
}
