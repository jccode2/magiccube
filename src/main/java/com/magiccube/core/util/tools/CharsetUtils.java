/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.core.util.tools;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.CharEncoding;

/**
 * 字符编码转换工具类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-21 黄科林
 */
public class CharsetUtils {
	/** HTMLCODE 数组 */
    private static final String[] HTMLCODE = new String[256];
    
    /** 用于编码的字符 */
    static private final char[] ALPHABET =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    
    /** 用于解码的字节（0-255） */
    static private final byte[] CODES = new byte[256];
    
    /** 中文超大字符集 * */
    public static final String GBK = "GBK";
    
    /** GB2312码 */
    public static final String GB2312 = "GB2312";
    
    /**
     * 构造函数
     */
    private CharsetUtils() {
    }
    
    static {
        for (int i = 0; i < 10; i++) {
            HTMLCODE[i] = "&#00" + i + ";";
        }
        for (int i = 10; i < 32; i++) {
            HTMLCODE[i] = "&#0" + i + ";";
        }
        for (int i = 32; i < 128; i++) {
            HTMLCODE[i] = String.valueOf((char) i);
        }
        HTMLCODE['\n'] = "<BR>\n";
        HTMLCODE['\"'] = "&quot;";
        HTMLCODE['&'] = "&amp;";
        HTMLCODE['<'] = "&lt;";
        HTMLCODE['>'] = "&gt;";
        for (int i = 128; i < 256; i++) {
            HTMLCODE[i] = "&#" + i + ";";
        }
    }
    
    /**
     * 编码传入的字符串
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String encode(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        int iLength = str.length();
        char chChar;
        StringBuffer sbBuffer = new StringBuffer();
        for (int i = 0; i < iLength; i++) {
            chChar = str.charAt(i);
            try {
                sbBuffer.append(HTMLCODE[chChar]);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                sbBuffer.append(chChar);
            }
        }
        return sbBuffer.toString();
    }
    
    static {
        for (int i = 0; i < 256; i++) {
            CODES[i] = -1;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            CODES[i] = (byte) (i - 'A');
        }
        for (int i = 'a'; i <= 'z'; i++) {
            CODES[i] = (byte) (26 + i - 'a');
        }
        for (int i = '0'; i <= '9'; i++) {
            CODES[i] = (byte) (52 + i - '0');
        }
        CODES['+'] = 62;
        CODES['/'] = 63;
    }
    
    /**
     * 编码传入的字节数组，输出编码后的字符数组
     * 
     * @param data 字节数组
     * @return 编码后的字符数组
     */
    static public char[] encode(byte[] data) {
        char[] chOut = new char[((data.length + 2) / 3) * 4];
        
        // 对字节进行Base64编码,每三个字节转化为4个字符.
        // 输出总是能被4整除的偶数个字符
        for (int i = 0, iIndex = 0; i < data.length; i += 3, iIndex += 4) {
            boolean bQuad = false;
            boolean bTrip = false;
            
            int iVal = 0xFF & data[i];
            iVal <<= 8;
            if ((i + 1) < data.length) {
                iVal |= 0xFF & data[i + 1];
                bTrip = true;
            }
            iVal <<= 8;
            if ((i + 2) < data.length) {
                iVal |= 0xFF & data[i + 2];
                bQuad = true;
            }
            chOut[iIndex + 3] = ALPHABET[bQuad ? (iVal & 0x3F) : 64];
            iVal >>= 6;
            chOut[iIndex + 2] = ALPHABET[bTrip ? (iVal & 0x3F) : 64];
            iVal >>= 6;
            chOut[iIndex + 1] = ALPHABET[iVal & 0x3F];
            iVal >>= 6;
            chOut[iIndex + 0] = ALPHABET[iVal & 0x3F];
        }
        return chOut;
    }
    
    /**
     * 将编码后的字节数组还原为原始的字节数组
     * 
     * @param data 编码后的字符数组
     * @return 原始字节数组
     */
    static public byte[] decode(char[] data) {
        /*
         * 程序中有判断如果有回车、空格等非法字符，则要去掉这些字符 这样就不会计算错误输出的内容
         */
        int iTempLen = data.length;
        for (int iX = 0; iX < data.length; iX++) {
            if ((data[iX] > 255) || CODES[data[iX]] < 0) {
                --iTempLen; // 去除无效的字符
            }
        }
        
        /*
         * 计算byte的长度 -- 每四个有效字符输出三个字节的内容 -- 如果有额外的3个字符，则还要加上2个字节, 或者如果有额外的2个字符，则要加上1个字节
         */

        int iLen = (iTempLen / 4) * 3;
        if ((iTempLen % 4) == 3) {
            iLen += 2;
        }
        if ((iTempLen % 4) == 2) {
            iLen += 1;
        }
        
        byte[] byteOut = new byte[iLen];
        
        int iShift = 0;
        int iAccum = 0;
        int iIndex = 0;
        
        // 一个一个字符地解码（注意用的不是tempLen的值进行循环）
        for (int iX = 0; iX < data.length; iX++) {
            int iValue = (data[iX] > 255) ? -1 : CODES[data[iX]];
            // 忽略无效字符
            if (iValue >= 0) {
                iAccum <<= 6;
                iShift += 6;
                iAccum |= iValue;
                if (iShift >= 8) {
                    iShift -= 8;
                    byteOut[iIndex++] = (byte) ((iAccum >> iShift) & 0xff);
                }
            }
        }
        
        // 如果数组长度和实际长度不符合，那么抛出错误
        if (iIndex != byteOut.length) {
            // throw new Error("数据 长度不一致(实际写入了 " + index + "字节，但是系统指示有" +
            // out.length + "字节)");
            return new byte[0];
        }
        
        return byteOut;
    }
    
    /**
     * 编码
     * 
     * @param src 原始字符串
     * @return 编码后的字符串
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer sbTmp = new StringBuffer();
        sbTmp.ensureCapacity(src.length() * 6);
        
        for (i = 0; i < src.length(); i++) {
            
            j = src.charAt(i);
            
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
                sbTmp.append(j);
            } else if (j < 256) {
                sbTmp.append("%");
                if (j < 16) {
                    sbTmp.append("0");
                }
                sbTmp.append(Integer.toString(j, 16));
            } else {
                sbTmp.append("%u");
                sbTmp.append(Integer.toString(j, 16));
            }
        }
        return sbTmp.toString();
    }
    
    /**
     * 解码
     * 
     * @param src 已经编码的字符串
     * @return 原始字符串
     */
    public static String unescape(String src) {
        StringBuffer sbTmp = new StringBuffer();
        sbTmp.ensureCapacity(src.length());
        int iLastPos = 0;
        int iPos = 0;
        char chSrc;
        while (iLastPos < src.length()) {
            iPos = src.indexOf('%', iLastPos);
            if (iPos == iLastPos) {
                if (src.charAt(iPos + 1) == 'u') {
                    chSrc = (char) Integer.parseInt(src.substring(iPos + 2, iPos + 6), 16);
                    sbTmp.append(chSrc);
                    iLastPos = iPos + 6;
                } else {
                    chSrc = (char) Integer.parseInt(src.substring(iPos + 1, iPos + 3), 16);
                    sbTmp.append(chSrc);
                    iLastPos = iPos + 3;
                }
            } else {
                if (iPos == -1) {
                    sbTmp.append(src.substring(iLastPos));
                    iLastPos = src.length();
                } else {
                    sbTmp.append(src.substring(iLastPos, iPos));
                    iLastPos = iPos;
                }
            }
        }
        return sbTmp.toString();
    }
    
    /**
     * 编码
     * 
     * @param str 已经编码的字符串
     * @return 原始字符串
     */
    public static String toTransferStr(String str) {
        char[] chTemp = CharsetUtils.encode(str.getBytes());
        return CharsetUtils.escape(new String(chTemp));
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * <pre>
     * 
     * </pre>
     * 
     * @param args 333
     */
    public static void main(String[] args) {
        String st1 = CharsetUtils.escape("abc汉字");
        System.out.println(st1);
        
        String st3 = CharsetUtils.unescape(st1);
        System.out.println(st3);
        
        System.out.println("==========");
        
        String str1 = CharsetUtils.toTransferStr("abc汉字");
        System.out.println(str1);
        
        String str3 = CharsetUtils.toUseStr(str1);
        System.out.println(str3);
        // System.out.println(CharsetUtils.toTransferStr(str1));
        // CharsetUtils.escape("abc");
    }
    
    /**
     * 解码
     * 
     * @param str 已经编码的字符串
     * @return 原始字符串
     */
    public static String toUseStr(String str) {
        byte[] byteTemp = CharsetUtils.decode(CharsetUtils.unescape(str).toCharArray());
        return new String(byteTemp);
    }
    
    /**
     * 将字符编码转换成US-ASCII码
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toASCII(String str) {
        return changeCharset(str, CharEncoding.US_ASCII);
    }
    
    /**
     * 将字符编码转换成ISO-8859-1
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toISO88591(String str) {
        return changeCharset(str, CharEncoding.ISO_8859_1);
    }
    
    /**
     * 将字符编码转换成UTF-8
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toUTF8(String str) {
        return changeCharset(str, CharEncoding.UTF_8);
    }
    
    /**
     * 将字符编码转换成UTF-16BE
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toUTF16BE(String str) {
        return changeCharset(str, CharEncoding.UTF_16BE);
    }
    
    /**
     * 将字符编码转换成UTF-16LE
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toUTF16LE(String str) {
        return changeCharset(str, CharEncoding.UTF_16LE);
    }
    
    /**
     * 将字符编码转换成UTF-16
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toUTF16(String str) {
        return changeCharset(str, CharEncoding.UTF_16);
    }
    
    /**
     * 将字符编码转换成GBK
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toGBK(String str) {
        return changeCharset(str, GBK);
    }
    
    /**
     * 将字符编码转换成GB2312
     * 
     * @param str 待处理字符串
     * @return String
     */
    public static String toGB2312(String str) {
        return changeCharset(str, GB2312);
    }
    
    /**
     * 根据编码格式转换字符串
     * 
     * @param string 待转换的字符串
     * @param newCharset 目标编码
     * @return 采用默认字符编码解码后重新编码的字符串
     */
    public static String changeCharset(String string, String newCharset) {
        if (string != null) {
            // 用默认字符编码解码字符串。与系统相关，中文windows默认为GB2312
            byte[] byteBs = string.getBytes();
            try {
                // 用新的字符编码生成字符串
                return new String(byteBs, newCharset);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException("不支持字符编码", e);
            }
        }
        return string;
    }
    
    /**
     * 将旧编码格式字符串转换为新编码格式字符串
     * 
     * @param string 待转换的字符串
     * @param oldCharset 源字符集
     * @param newCharset 目标字符集
     * @return 转码后的字符串
     */
    public static String changeCharset(String string, String oldCharset, String newCharset) {
        if (string != null) {
            // 用源字符编码解码字符串
            try {
                byte[] byteBs = string.getBytes(oldCharset);
                return new String(byteBs, newCharset);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException("不支持字符编码", e);
            }
        }
        return string;
    }
}
