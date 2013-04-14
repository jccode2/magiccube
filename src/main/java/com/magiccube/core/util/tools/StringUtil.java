/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.util.tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;

/**
 * 字符串处理工具类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-16 黄科林
 */
public final class StringUtil {
	
	/**
     * 空字符串使用常量EMPTY来代表: <code>""</code>.
     */
    public static final String EMPTY = "";
    
    /**
     * <p>
     * 可变长的string所能扩展的最大长度常量.
     * </p>
     */
    public static final int PAD_LIMIT = 8192;
    
    /**
     * <p>
     * 一个用来填充的<code>String</code>数组.
     * </p>
     * <p>
     * 用于高效的空间填充. 根据需要扩展每个string的长度.
     * </p>
     */
    private static final String[] CONSTANTS_PADDING = new String[Character.MAX_VALUE];
    
    /** 空字符串数组 */
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    
    static {
        // space padding is most common, start with 64 chars
        CONSTANTS_PADDING[32] = "                                                                ";
    }
    
    /**
     * <p>
     * <code>StringUtils</code> 类中除了构造函数其他的都是静态方法 所以在程序中不应创建这个类的实例 应象这样使用:<code>StringUtils.trim(" foo ");</code>.
     * </p>
     * <p>
     * 默认的构造方法是为了 JavaBean 的操作。
     * </p>
     */
    private StringUtil() {
    }
    
    /**
     * <p>
     * 检查string的长度是否为0(空字符串)或者string是否为null.
     * </p>
     * 
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty(&quot;&quot;)        = true
     * StringUtils.isEmpty(&quot; &quot;)       = false
     * StringUtils.isEmpty(&quot;bob&quot;)     = false
     * StringUtils.isEmpty(&quot;  bob  &quot;) = false
     * </pre>
     * 
     * @param string 需要检查的string, 可能为null
     * @return <code>true</code> 如果string为null或者string的长度为0
     */
    public static boolean isEmpty(String string) {
        return org.apache.commons.lang.StringUtils.isEmpty(string);
    }
    
    /**
     * <p>
     * 是否string的长度不为0(空字符串)或者string不为null.
     * </p>
     * 
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty(&quot;&quot;)        = false
     * StringUtils.isNotEmpty(&quot; &quot;)       = true
     * StringUtils.isNotEmpty(&quot;bob&quot;)     = true
     * StringUtils.isNotEmpty(&quot;  bob  &quot;) = true
     * </pre>
     * 
     * @param string 需要检查的string, 可能为null
     * @return <code>true</code> 如果string不为null或者string的长度不为0或不是空格
     */
    public static boolean isNotEmpty(String string) {
        return org.apache.commons.lang.StringUtils.isNotEmpty(string);
    }
    
    /**
     * <p>
     * 检查string是否是空白或者string为null.
     * </p>
     * 
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank(&quot;&quot;)        = true
     * StringUtils.isBlank(&quot; &quot;)       = true
     * StringUtils.isBlank(&quot;bob&quot;)     = false
     * StringUtils.isBlank(&quot;  bob  &quot;) = false
     * </pre>
     * 
     * @param string 需要检查的string, 可能为null
     * @return <code>true</code> 如果string为null或者string中数据为空白
     * @since 2.0
     */
    public static boolean isBlank(String string) {
        return org.apache.commons.lang.StringUtils.isBlank(string);
    }
    
    /**
     * <p>
     * 是否string不为空白，string的长度不为0，string不为null.
     * </p>
     * 
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank(&quot;&quot;)        = false
     * StringUtils.isNotBlank(&quot; &quot;)       = false
     * StringUtils.isNotBlank(&quot;bob&quot;)     = true
     * StringUtils.isNotBlank(&quot;  bob  &quot;) = true
     * </pre>
     * 
     * @param string 需要检查的string, 可能为null
     * @return <code>true</code> 如果string不为null并且 string不为空白，string的长度不为0.
     * @since 2.0
     */
    public static boolean isNotBlank(String string) {
        return org.apache.commons.lang.StringUtils.isNotBlank(string);
    }
    
    /**
     * <p>
     * 移去string前后两边的控制字符(char &lt;= 32) 当遇到 <code>null</code> 时返回一个空string("").
     * </p>
     * 
     * <pre>
     * StringUtils.clean(null)          = &quot;&quot;
     * StringUtils.clean(&quot;&quot;)            = &quot;&quot;
     * StringUtils.clean(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtils.clean(&quot;    abc    &quot;) = &quot;abc&quot;
     * StringUtils.clean(&quot;     &quot;)       = &quot;&quot;
     * </pre>
     * 
     * @see java.lang.String#trim()
     * @param str 需要清空空白的string, 可能为null
     * @return 被清空空白的string,结果中不包含任何的<code>null</code>
     * @deprecated 使用这种更清晰的方法: {@link #trimToEmpty(String)}. 在 Commons Lang 3.0. 此方法将会被取消
     */
    @Deprecated
    public static String clean(String str) {
        return str == null ? EMPTY : str.trim();
    }
    
    /**
     * <p>
     * 移去string前后两边的控制字符 (char &lt;= 32) 当遇到 <code>null</code> 时返回null.
     * </p>
     * <p>
     * 这个清除string前后空白的方法使用了 {@link String#trim()}. 清除了string前后的字符(char &lt;= 32) 要清除空白字符可以选用 {@link #strip(String)}.
     * </p>
     * <p>
     * 清除string的中你所制定的字符,如清除"abc"中的"a"结果为"bc", 使用 {@link #strip(String, String)} 方法.
     * </p>
     * 
     * <pre>
     * StringUtils.trim(null)          = null
     * StringUtils.trim(&quot;&quot;)            = &quot;&quot;
     * StringUtils.trim(&quot;     &quot;)       = &quot;&quot;
     * StringUtils.trim(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtils.trim(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param string 需要进行处理的string,可能为空
     * @return 清除两端空白的string, 当输入为null时返回<code>null</code>
     */
    public static String trim(String string) {
        return org.apache.commons.lang.StringUtils.trim(string);
    }
    
    /**
     * <p>
     * 移除string中两端的控制字符(char &lt;= 32) string为null,string中字符都为空白(&lt;= 32)或者string的长度为0时 返回的结果为<code>null</code>
     * <p>
     * 这个清除string前后空白的方法使用了 {@link String#trim()}. 清除了string前后的(char &lt;= 32)字符. 要清除空白可以使用 {@link #stripToNull(String)}
     * .
     * </p>
     * 
     * <pre>
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull(&quot;&quot;)            = null
     * StringUtils.trimToNull(&quot;     &quot;)       = null
     * StringUtils.trimToNull(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtils.trimToNull(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param string 需要进行处理的string,可能为空
     * @return 返回处理后的数据 当输入的string中字符都为空白(&lt;= 32),string的长度为0 或者string为null时返回<code>null</code>
     * @since 2.0
     */
    public static String trimToNull(String string) {
        return org.apache.commons.lang.StringUtils.trimToNull(string);
    }
    
    /**
     * <p>
     * 移除string前后两端的控制字符 (char &lt;= 32) 当string为<code>null</code>,string的长度为0 或者string中的所有字符都为空白时. 结果返回("").
     * <p>
     * 这个清除string前后空白的方法使用了 {@link String#trim()}. 清除了string前后的(char &lt;= 32)字符. 要清除空白可以使用
     * {@link #stripToEmpty(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.trimToEmpty(null)          = &quot;&quot;
     * StringUtils.trimToEmpty(&quot;&quot;)            = &quot;&quot;
     * StringUtils.trimToEmpty(&quot;     &quot;)       = &quot;&quot;
     * StringUtils.trimToEmpty(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtils.trimToEmpty(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param string 需要进行处理的string,可能为空
     * @return 返回处理后的数据, 输入为<code>null</code>时返回""
     * @since 2.0
     */
    public static String trimToEmpty(String string) {
        return org.apache.commons.lang.StringUtils.trimToEmpty(string);
    }
    
    /**
     * <p>
     * 清除string两端的空白的字符.
     * </p>
     * <p>
     * 这个方法和{@link #trim(String)}类似,但是它移除whitespace. Whitespace 的定义在 {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * <code>null</code> 的String输入将返回 <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null)     = null
     * StringUtils.strip(&quot;&quot;)       = &quot;&quot;
     * StringUtils.strip(&quot;   &quot;)    = &quot;&quot;
     * StringUtils.strip(&quot;abc&quot;)    = &quot;abc&quot;
     * StringUtils.strip(&quot;  abc&quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot;abc  &quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot; abc &quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot; ab c &quot;) = &quot;ab c&quot;
     * </pre>
     * 
     * @param string 需要移除空白的string,可能为null
     * @return 处理后的结果, 输入为null时返回<code>null</code>
     */
    public static String strip(String string) {
        return org.apache.commons.lang.StringUtils.strip(string);
    }
    
    /**
     * <p>
     * 清除string两端的空白的字符 返回<code>null</code> 但string为null,string长度为0或者string中字符都为空白.
     * </p>
     * <p>
     * 这个方法和{@link #trimToNull(String)}类似,但是它移除whitespace. Whitespace 的定义在 {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null)     = null
     * StringUtils.strip(&quot;&quot;)       = null
     * StringUtils.strip(&quot;   &quot;)    = null
     * StringUtils.strip(&quot;abc&quot;)    = &quot;abc&quot;
     * StringUtils.strip(&quot;  abc&quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot;abc  &quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot; abc &quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot; ab c &quot;) = &quot;ab c&quot;
     * </pre>
     * 
     * @param string 需要移除空白的string,可能为null
     * @return 处理后的结果, 返回<code>null</code>但输入为null,string长度为0或者string中字符都为空白
     * @since 2.0
     */
    public static String stripToNull(String string) {
        return org.apache.commons.lang.StringUtils.stripToNull(string);
    }
    
    /**
     * <p>
     * 清除string两端的空白的字符 如果输入为<code>null</code>返回("").
     * </p>
     * <p>
     * 这个方法和 {@link #trimToEmpty(String)}类似,但是它移除whitespace. Whitespace 的定义在 {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null)     = &quot;&quot;
     * StringUtils.strip(&quot;&quot;)       = &quot;&quot;
     * StringUtils.strip(&quot;   &quot;)    = &quot;&quot;
     * StringUtils.strip(&quot;abc&quot;)    = &quot;abc&quot;
     * StringUtils.strip(&quot;  abc&quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot;abc  &quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot; abc &quot;)  = &quot;abc&quot;
     * StringUtils.strip(&quot; ab c &quot;) = &quot;ab c&quot;
     * </pre>
     * 
     * @param string 需要移除空白的string,可能为null
     * @return 处理后的结果, 输入<code>null</code>时返回"".
     * @since 2.0
     */
    public static String stripToEmpty(String string) {
        return org.apache.commons.lang.StringUtils.stripToEmpty(string);
    }
    
    /**
     * <p>
     * 清除string中指定的字符. 这个方法和 {@link String#trim()}类似,但你可以选择你需要移除的字符.
     * </p>
     * <p>
     * 第一个参数输入<code>null</code>时返回<code>null</code>. 输入 ("")时返回("").
     * </p>
     * <p>
     * 如果第二个参数为<code>null</code>, 在{@link Character#isWhitespace(char)}中定义的whitespace将被移除. 和使用{@link #strip(String)}一样.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null, *)          = null
     * StringUtils.strip(&quot;&quot;, *)            = &quot;&quot;
     * StringUtils.strip(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtils.strip(&quot;  abc&quot;, null)    = &quot;abc&quot;
     * StringUtils.strip(&quot;abc  &quot;, null)    = &quot;abc&quot;
     * StringUtils.strip(&quot; abc &quot;, null)    = &quot;abc&quot;
     * StringUtils.strip(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
     * </pre>
     * 
     * @param string 需要移除字符的string,可能为null
     * @param stripChars 选择要移除的字符, null被当作whitespace处理
     * @return 返回移除后的结果, 如果第一个参数为null返回<code>null</code>
     */
    public static String strip(String string, String stripChars) {
        return org.apache.commons.lang.StringUtils.strip(string, stripChars);
    }
    
    /**
     * <p>
     * 移除string的开端中指定的字符.
     * </p>
     * <p>
     * 第一个参数为<code>null</code>时返回<code>null</code>. 输入为 ("") 时返回 ("").
     * </p>
     * <p>
     * 第二个参数为 <code>null</code>, 在{@link Character#isWhitespace(char)}中定义的whitespace将被移除.
     * </p>
     * 
     * <pre>
     * StringUtils.stripStart(null, *)          = null
     * StringUtils.stripStart(&quot;&quot;, *)            = &quot;&quot;
     * StringUtils.stripStart(&quot;abc&quot;, &quot;&quot;)        = &quot;abc&quot;
     * StringUtils.stripStart(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtils.stripStart(&quot;  abc&quot;, null)    = &quot;abc&quot;
     * StringUtils.stripStart(&quot;abc  &quot;, null)    = &quot;abc  &quot;
     * StringUtils.stripStart(&quot; abc &quot;, null)    = &quot;abc &quot;
     * StringUtils.stripStart(&quot;yxabc  &quot;, &quot;xyz&quot;) = &quot;abc  &quot;
     * </pre>
     * 
     * @param string 需要移除字符的string,可能为null
     * @param stripChars 选择要移除的字符, null被当作whitespace处理
     * @return 返回移除后的结果, 如果第一个参数为null返回<code>null</code>
     */
    public static String stripStart(String string, String stripChars) {
        return org.apache.commons.lang.StringUtils.stripStart(string, stripChars);
    }
    
    /**
     * <p>
     * 移除string的末尾中指定的字符.
     * </p>
     * <p>
     * 第一个参数为<code>null</code>时返回<code>null</code>. 输入为 ("") 时返回 ("").
     * </p>
     * <p>
     * 第二个参数为 <code>null</code>, 在{@link Character#isWhitespace(char)}中定义的whitespace将被移除.
     * </p>
     * 
     * <pre>
     * StringUtils.stripEnd(null, *)          = null
     * StringUtils.stripEnd(&quot;&quot;, *)            = &quot;&quot;
     * StringUtils.stripEnd(&quot;abc&quot;, &quot;&quot;)        = &quot;abc&quot;
     * StringUtils.stripEnd(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtils.stripEnd(&quot;  abc&quot;, null)    = &quot;  abc&quot;
     * StringUtils.stripEnd(&quot;abc  &quot;, null)    = &quot;abc&quot;
     * StringUtils.stripEnd(&quot; abc &quot;, null)    = &quot; abc&quot;
     * StringUtils.stripEnd(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
     * </pre>
     * 
     * @param string 需要移除字符的string,可能为null
     * @param stripChars 选择要移除的字符, null被当作whitespace处理
     * @return 返回移除后的结果, 如果第一个参数为null返回<code>null</code>
     */
    public static String stripEnd(String string, String stripChars) {
        return org.apache.commons.lang.StringUtils.stripEnd(string, stripChars);
    }
    
    /**
     * <p>
     * 移除string数组中每个string的两端的空白字符. Whitespace 的定义在 {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * 每次都返回一个新的数组, 除了数组的长度为0时. 一个<code>null</code>数组将返回<code>null</code>. 空数组将返回它本身. 数组中为<code>null</code>的数组元素将被忽略.
     * </p>
     * 
     * <pre>
     * StringUtils.stripAll(null)             = null
     * StringUtils.stripAll([])               = []
     * StringUtils.stripAll([&quot;abc&quot;, &quot;  abc&quot;]) = [&quot;abc&quot;, &quot;abc&quot;]
     * StringUtils.stripAll([&quot;abc  &quot;, null])  = [&quot;abc&quot;, null]
     * </pre>
     * 
     * @param strs 需要处理的string数组,可以为null
     * @return 返回处理结果,如果输入为null将返回<code>null</code>
     */
    public static String[] stripAll(String[] strs) {
        return org.apache.commons.lang.StringUtils.stripAll(strs);
    }
    
    /**
     * <p>
     * 移除string数组中每个string的两端的指定字符 .
     * </p>
     * Whitespace 的定义在 {@link Character#isWhitespace(char)}. </p>
     * <p>
     * 每次都返回一个新的数组, 除了数组的长度为0时. 一个<code>null</code>数组将返回<code>null</code>. 空数组将返回它本身. 数组中为<code>null</code>的数组元素将被忽略.
     * 第二个参数为<code>null</code>时将移除在 {@link Character#isWhitespace(char)}定义的whitespace .
     * </p>
     * 
     * <pre>
     * StringUtils.stripAll(null, *)                = null
     * StringUtils.stripAll([], *)                  = []
     * StringUtils.stripAll([&quot;abc&quot;, &quot;  abc&quot;], null) = [&quot;abc&quot;, &quot;abc&quot;]
     * StringUtils.stripAll([&quot;abc  &quot;, null], null)  = [&quot;abc&quot;, null]
     * StringUtils.stripAll([&quot;abc  &quot;, null], &quot;yz&quot;)  = [&quot;abc  &quot;, null]
     * StringUtils.stripAll([&quot;yabcz&quot;, null], &quot;yz&quot;)  = [&quot;abc&quot;, null]
     * </pre>
     * 
     * @param strs 需要处理的string数组,可以为null
     * @param stripChars 要被移除的字符, null被认为是whitespace
     * @return 处理后的结果, 输入null的数组时返回<code>null</code>
     */
    public static String[] stripAll(String[] strs, String stripChars) {
        return org.apache.commons.lang.StringUtils.stripAll(strs, stripChars);
    }
    
    /**
     * <p>
     * 比较两个string, 相等时返回<code>true</code>.
     * </p>
     * <p>
     * 能处理比较中有为<code>null</code>的情况. 两个<code>null</code> 比较时认为是相等的. 比较时是区分大小写的.
     * </p>
     * 
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, &quot;abc&quot;)  = false
     * StringUtils.equals(&quot;abc&quot;, null)  = false
     * StringUtils.equals(&quot;abc&quot;, &quot;abc&quot;) = true
     * StringUtils.equals(&quot;abc&quot;, &quot;ABC&quot;) = false
     * </pre>
     * 
     * @see java.lang.String#equals(Object)
     * @param str1 第一个string参数,可能为null
     * @param str2 第二个string参数,可能为null
     * @return <code>true</code> 当两个string相等时, 区分大小写, 或者 同为<code>null</code>
     */
    public static boolean equals(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equals(str1, str2);
    }
    
    /**
     * <p>
     * 比较两个string, 忽略大小写时相等返回 <code>true</code>.
     * </p>
     * <p>
     * 能处理比较中有为<code>null</code>的情况. 两个<code>null</code> 比较时认为是相等的. 比较时是不区分大小写的.
     * </p>
     * 
     * <pre>
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, &quot;abc&quot;)  = false
     * StringUtils.equalsIgnoreCase(&quot;abc&quot;, null)  = false
     * StringUtils.equalsIgnoreCase(&quot;abc&quot;, &quot;abc&quot;) = true
     * StringUtils.equalsIgnoreCase(&quot;abc&quot;, &quot;ABC&quot;) = true
     * </pre>
     * 
     * @see java.lang.String#equalsIgnoreCase(String)
     * @param str1 第一个string参数,可能为null
     * @param str2 第二个string参数,可能为null
     * @return <code>true</code> 两个string相等, 不区分大小写, 或者 同为<code>null</code>
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equalsIgnoreCase(str1, str2);
    }
    
    /**
     * <p>
     * 检索string中第一个指定字符的位置, 能处理<code>null</code>的情况. 这个方法使用了 {@link String#indexOf(int)}.
     * </p>
     * <p>
     * 一个<code>null</code>字符串或者("")字符串将会返回<code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *)         = -1
     * StringUtils.indexOf(&quot;&quot;, *)           = -1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, 'a') = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, 'b') = 2
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChar 要进行检索的字符
     * @return 第一个出现检索的字符的位置, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     * @since 2.0
     */
    public static int indexOf(String string, char searchChar) {
        return org.apache.commons.lang.StringUtils.indexOf(string, searchChar);
    }
    
    /**
     * <p>
     * 检索出string中从起始位开始第一个指定字符的位置,能处理<code>null</code>的情况. 这个方法使用了 {@link String#indexOf(int, int)}.
     * </p>
     * <p>
     * 为<code>null</code>或者字符串为("")将返回<code>-1</code>. 如果起始位为负数将当作从第一位开始检索. 如果起始的位置比string的长度大将返回 <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf(&quot;&quot;, *, *)            = -1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, 'b', 0)  = 2
     * StringUtils.indexOf(&quot;aabaabaa&quot;, 'b', 3)  = 5
     * StringUtils.indexOf(&quot;aabaabaa&quot;, 'b', 9)  = -1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, 'b', -1) = 2
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChar 要进行检索的字符
     * @param startPos 起始位置，负数将当作零
     * @return 第一个出现检索的字符的位置, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     * @since 2.0
     */
    public static int indexOf(String string, char searchChar, int startPos) {
        return org.apache.commons.lang.StringUtils.indexOf(string, searchChar, startPos);
    }
    
    /**
     * <p>
     * 检索string中第一个指定字符的位置, 能处理 <code>null</code>的情况. T这个方法使用了 {@link String#indexOf(String)}.
     * </p>
     * <p>
     * 为<code>null</code> 的String 返回 <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *)          = -1
     * StringUtils.indexOf(*, null)          = -1
     * StringUtils.indexOf(&quot;&quot;, &quot;&quot;)           = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 2
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 0
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchStr 要进行检索的字符,可能为null
     * @return 第一个出现检索的字符的位置, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     */
    public static int indexOf(String string, String searchStr) {
        return org.apache.commons.lang.StringUtils.indexOf(string, searchStr);
    }
    
    /**
     * <p>
     * 检索出string中从指定位开始第一个指定字符的位置, 能处理 <code>null</code>的情况. 这个方法使用了 {@link String#indexOf(String, int)}.
     * </p>
     * <p>
     * 为<code>null</code> 的String 返回 <code>-1</code>. 负数的检索位置当作零. 空的字符("") 总是满足条件.
     * 如果检索的位置大于string的长度,只有当检索的数据为("")时才能返回结果 .
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf(*, null, *)          = -1
     * StringUtils.indexOf(&quot;&quot;, &quot;&quot;, 0)           = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = 2
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 0) = 1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 3)  = 5
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = -1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = 2
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;&quot;, 2)   = 2
     * StringUtils.indexOf(&quot;abc&quot;, &quot;&quot;, 9)        = 3
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchStr 要进行检索的字符,可能为null
     * @param startPos 起始位置，负数将当作零
     * @return 第一个出现检索的字符的位置, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     */
    public static int indexOf(String string, String searchStr, int startPos) {
        return org.apache.commons.lang.StringUtils.indexOf(string, searchStr, startPos);
    }
    
    /**
     * <p>
     * 检索string中最后一个指定字符的位置, 能处理 <code>null</code>的情况. 这个方法使用了 {@link String#lastIndexOf(int)}.
     * </p>
     * <p>
     * <code>null</code>的string或者("") String 返回 <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *)         = -1
     * StringUtils.lastIndexOf(&quot;&quot;, *)           = -1
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChar 要进行检索的字符
     * @return 最后出现检索的字符的位置, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     * @since 2.0
     */
    public static int lastIndexOf(String string, char searchChar) {
        return org.apache.commons.lang.StringUtils.lastIndexOf(string, searchChar);
    }
    
    /**
     * <p>
     * 检索出string中从指定位开始最后一个指定字符的位置, 能处理 <code>null</code>的情况. 这个方法使用了 {@link String#lastIndexOf(int, int)}.
     * </p>
     * <p>
     * <code>null</code>的string或者("") String 返回 <code>-1</code>. 如果起始位置为负数将返回<code>-1</code>.
     * 起始位置大于string的长度将检索整个string.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf(&quot;&quot;, *,  *)           = -1
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'b', 8)  = 5
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'b', 4)  = 2
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'b', 0)  = -1
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'b', 9)  = 5
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'b', -1) = -1
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, 'a', 0)  = 0
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChar 需要查询的字符
     * @param startPos 查询的起始位置
     * @return 最后一个指定字符的位置, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     * @since 2.0
     */
    public static int lastIndexOf(String string, char searchChar, int startPos) {
        return org.apache.commons.lang.StringUtils.lastIndexOf(string, searchChar, startPos);
    }
    
    /**
     * <p>
     * 检索出string中最后一个指定字符的位置, 能处理 <code>null</code>的情况. 这个方法使用了 {@link String#lastIndexOf(String)}.
     * </p>
     * <p>
     * <code>null</code>的String 将返回 <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *)          = -1
     * StringUtils.lastIndexOf(*, null)          = -1
     * StringUtils.lastIndexOf(&quot;&quot;, &quot;&quot;)           = 0
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 0  ---
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 2  ---
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1  ---
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 8
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchStr 需要查找的string, 可能为null
     * @return the last index of the search String, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     * @since 2.0
     */
    public static int lastIndexOf(String string, String searchStr) {
        return org.apache.commons.lang.StringUtils.lastIndexOf(string, searchStr);
    }
    
    /**
     * <p>
     * 检索出字符串str中指定位置startPos以前的字符串中 特定字符searchStr最后出现的位置, 能处理 <code>null</code>的情况. 这个方法使用了
     * {@link String#lastIndexOf(String, int)}.
     * </p>
     * <p>
     * <code>null</code> 的String 将返回 <code>-1</code>. 如果起始位置为负数将返回 <code>-1</code>. 除非起始位置为负数,那么空的string("")将总是能匹配.
     * 如果起始位超出了string的长度将对整个string进行检索.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf(*, null, *)          = -1
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 8)  = 7
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 8)  = 5
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 8) = 4
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = 5
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = -1
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
     * StringUtils.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = -1
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchStr 需要查询的string, 可能为null
     * @param startPos 开始查询的位置, 负数当作零
     * @return 检索出的位置, -1 string 中没有指定的字符或者输入<code>null</code>的字符串
     * @since 2.0
     */
    public static int lastIndexOf(String string, String searchStr, int startPos) {
        return org.apache.commons.lang.StringUtils.lastIndexOf(string, searchStr, startPos);
    }
    
    /**
     * <p>
     * 检查字符串str是否包含指定的字符searchChar, 能处理 <code>null</code>的情况. 这个方法使用了 {@link String#indexOf(int)}.
     * </p>
     * <p>
     * <code>null</code>字符或者空字符串("")将返回 <code>false</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.contains(null, *)    = false
     * StringUtils.contains(&quot;&quot;, *)      = false
     * StringUtils.contains(&quot;abc&quot;, 'a') = true
     * StringUtils.contains(&quot;abc&quot;, 'z') = false
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChar 要查找的字符
     * @return true string中包含要查找的字符, false 没有要查找的字符或者输入位 <code>null</code>
     * @since 2.0
     */
    public static boolean contains(String string, char searchChar) {
        return org.apache.commons.lang.StringUtils.contains(string, searchChar);
    }
    
    /**
     * <p>
     * 检查string是否出现指定的字符串, 能处理 <code>null</code>的情况. 这个方法使用了 {@link String#indexOf(int)}.
     * </p>
     * <p>
     * <code>null</code> 的String 返回 <code>false</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains(&quot;&quot;, &quot;&quot;)      = true
     * StringUtils.contains(&quot;abc&quot;, &quot;&quot;)   = true
     * StringUtils.contains(&quot;abc&quot;, &quot;a&quot;)  = true
     * StringUtils.contains(&quot;abc&quot;, &quot;z&quot;)  = false
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchStr 需要查找的字符串, 可能为null
     * @return true string中包含要查找的字符串, false 没有要查找的字符或者输入位 <code>null</code>
     * @since 2.0
     */
    public static boolean contains(String string, String searchStr) {
        return org.apache.commons.lang.StringUtils.contains(string, searchStr);
    }
    
    /**
     * <p>
     * 检索出字符串str中第一个包含字符数组searchChars中任意字符的位置 .
     * </p>
     * <p>
     * <code>null</code> 的String 返回<code>-1</code>. <code>null</code> 或者字符数组的长度为零返回<code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAny(null, *)                = -1
     * StringUtils.indexOfAny(&quot;&quot;, *)                  = -1
     * StringUtils.indexOfAny(*, null)                = -1
     * StringUtils.indexOfAny(*, [])                  = -1
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;,['z','a']) = 0
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;,['b','y']) = 3
     * StringUtils.indexOfAny(&quot;aba&quot;, ['z'])           = -1
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChars 需要查找的字符, 可能为null
     * @return 返回第一个检索出的位置, 没有适合的或者输入null都将返回-1
     * @since 2.0
     */
    public static int indexOfAny(String string, char[] searchChars) {
        return org.apache.commons.lang.StringUtils.indexOfAny(string, searchChars);
    }
    
    /**
     * <p>
     * 检索出字符串str中第一个出现字符串searchChars的任意字符的位置 .
     * </p>
     * <p>
     * <code>null</code> 的String 返回<code>-1</code>. 需要检索的string为<code>null</code> 返回 <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAny(null, *)            = -1
     * StringUtils.indexOfAny(&quot;&quot;, *)              = -1
     * StringUtils.indexOfAny(*, null)            = -1
     * StringUtils.indexOfAny(*, &quot;&quot;)              = -1
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 0
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;, &quot;by&quot;) = 3
     * StringUtils.indexOfAny(&quot;aba&quot;,&quot;z&quot;)          = -1
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChars 需要查找的字符, 可能为null
     * @return 返回第一个检索出的位置, 没有适合的或者输入null都将返回-1
     * @since 2.0
     */
    public static int indexOfAny(String string, String searchChars) {
        return org.apache.commons.lang.StringUtils.indexOfAny(string, searchChars);
    }
    
    /**
     * <p>
     * 检索出字符串str中第一个出现不是字符数组searchChars的字符的位置 .
     * </p>
     * <p>
     * <code>null</code> 的String 返回 <code>-1</code>. <code>null</code> 或者字符的长度为零返回<code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)           = -1
     * StringUtils.indexOfAnyBut(&quot;&quot;, *)             = -1
     * StringUtils.indexOfAnyBut(*, null)           = -1
     * StringUtils.indexOfAnyBut(*, [])             = -1
     * StringUtils.indexOfAnyBut(&quot;zzabyycdxx&quot;,'za') = 3
     * StringUtils.indexOfAnyBut(&quot;zzabyycdxx&quot;, '')  = 0
     * StringUtils.indexOfAnyBut(&quot;aba&quot;, 'ab')       = -1
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChars 要检索的字符, 可能为null
     * @return 返回第一个检索出的位置, 没有适合的或者输入null都将返回-1
     * @since 2.0
     */
    public static int indexOfAnyBut(String string, char[] searchChars) {
        return org.apache.commons.lang.StringUtils.indexOfAnyBut(string, searchChars);
    }
    
    /**
     * <p>
     * 检索出字符串str中第一个出现不是字符串searchChars的字符的位置 .
     * </p>
     * <p>
     * 第一个参数为<code>null</code>将返回<code>-1</code>. 第二个参数为<code>null</code>将返回<code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)            = -1
     * StringUtils.indexOfAnyBut(&quot;&quot;, *)              = -1
     * StringUtils.indexOfAnyBut(*, null)            = -1
     * StringUtils.indexOfAnyBut(*, &quot;&quot;)              = -1
     * StringUtils.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 3
     * StringUtils.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;&quot;)   = 0
     * StringUtils.indexOfAnyBut(&quot;aba&quot;,&quot;ab&quot;)         = -1
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchChars 字符串A中可能包含的字符串B, 可能为null
     * @return 返回第一个检索出的位置, 没有适合的或者输入null都将返回-1
     * @since 2.0
     */
    public static int indexOfAnyBut(String string, String searchChars) {
        return org.apache.commons.lang.StringUtils.indexOfAnyBut(string, searchChars);
    }
    
    /**
     * <p>
     * 字符串str中是否只有字符数组valid中的字符.
     * </p>
     * <p>
     * 第一个参数为<code>null</code> 返回 <code>false</code>. 第二个参数为<code>null</code> 返回 <code>false</code>. 第一个参数为 ("") 返回
     * <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly(&quot;&quot;, *)         = true
     * StringUtils.containsOnly(&quot;ab&quot;, '')      = false
     * StringUtils.containsOnly(&quot;abab&quot;, 'abc') = true
     * StringUtils.containsOnly(&quot;ab1&quot;, 'abc')  = false
     * StringUtils.containsOnly(&quot;abz&quot;, 'abc')  = false
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param valid 字符数组参数, 可能为null
     * @return true 字符串不为null,并且字符串中的字符都是字符数组中的字符
     */
    public static boolean containsOnly(String string, char[] valid) {
        return org.apache.commons.lang.StringUtils.containsOnly(string, valid);
    }
    
    /**
     * <p>
     * 字符串str中是否只有字符串validChars中的字符.
     * </p>
     * <p>
     * 第一个参数为<code>null</code> 返回 <code>false</code>. 第二个参数为<code>null</code> 返回 <code>false</code>. 第一个参数为 ("") 返回
     * <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly(&quot;&quot;, *)         = true
     * StringUtils.containsOnly(&quot;ab&quot;, &quot;&quot;)      = false
     * StringUtils.containsOnly(&quot;abab&quot;, &quot;abc&quot;) = true
     * StringUtils.containsOnly(&quot;ab1&quot;, &quot;abc&quot;)  = false
     * StringUtils.containsOnly(&quot;abz&quot;, &quot;abc&quot;)  = false
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param validChars 字符串参数, 可能为null
     * @return true 字符串不为null,并且字符串str中的字符都是字符串validChars中的字符
     * @since 2.0
     */
    public static boolean containsOnly(String string, String validChars) {
        return org.apache.commons.lang.StringUtils.containsOnly(string, validChars);
    }
    
    /**
     * <p>
     * 检查字符串str中是否不含有字符数组invalidChar中的字符.
     * </p>
     * <p>
     * 第一个参数为<code>null</code> 返回 <code>true</code>. 第二个参数为<code>null</code> 返回 <code>true</code>. 参数中有为空("") 时返回true.
     * </p>
     * 
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone(&quot;&quot;, *)         = true
     * StringUtils.containsNone(&quot;ab&quot;, '')      = true
     * StringUtils.containsNone(&quot;abab&quot;, 'xyz') = true
     * StringUtils.containsNone(&quot;ab1&quot;, 'xyz')  = true
     * StringUtils.containsNone(&quot;abz&quot;, 'xyz')  = false
     * </pre>
     * 
     * @param string 需要处理的string,可能为nulll
     * @param invalidChars 字符数组参数, 可能为null
     * @return true 字符串str中不包含字符数组invalidChar中的字符, 或者参数为null
     * @since 2.0
     */
    public static boolean containsNone(String string, char[] invalidChars) {
        return org.apache.commons.lang.StringUtils.containsNone(string, invalidChars);
    }
    
    /**
     * <p>
     * 检查字符串str中是否不含有字符串invalidChar中的字符.
     * </p>
     * <p>
     * 第一个参数str为<code>null</code> 返回 <code>true</code>. 第二个参数invalidChars为<code>null</code> 返回 <code>true</code>.
     * 参数中有为空("") 时返回true.
     * </p>
     * 
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone(&quot;&quot;, *)         = true
     * StringUtils.containsNone(&quot;ab&quot;, &quot;&quot;)      = true
     * StringUtils.containsNone(&quot;abab&quot;, &quot;xyz&quot;) = true
     * StringUtils.containsNone(&quot;ab1&quot;, &quot;xyz&quot;)  = true
     * StringUtils.containsNone(&quot;abz&quot;, &quot;xyz&quot;)  = false
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param invalidChars 字符串参数, 可能为null
     * @return true 字符串str中不包含字符串invalidChar中的字符, 或者参数为null
     * @since 2.0
     */
    public static boolean containsNone(String string, String invalidChars) {
        return org.apache.commons.lang.StringUtils.containsNone(string, invalidChars);
    }
    
    /**
     * <p>
     * 找出字符串str中第一次出现字符串数组searchStrs中元素的位置.
     * </p>
     * <p>
     * 第一个参数str为<code>null</code>返回<code>-1</code>. 第二个参数searchStrs为<code>null</code> 或者长度为0返回<code>-1</code>.
     * 第二个参数searchStrs为<code>null</code> 的情况将被忽略, 但如果为[""] 返回 <code>0</code> 当 <code>第一个参数</code>不为null时. 这个方法使用了
     * {@link String#indexOf(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAny(null, *)                     = -1
     * StringUtils.indexOfAny(*, null)                     = -1
     * StringUtils.indexOfAny(*, [])                       = -1
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;])   = 2
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;])   = 2
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;])   = -1
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;zab&quot;,&quot;aby&quot;]) = 1
     * StringUtils.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;&quot;])          = 0
     * StringUtils.indexOfAny(&quot;&quot;, [&quot;&quot;])                    = 0
     * StringUtils.indexOfAny(&quot;&quot;, [&quot;a&quot;])                   = -1
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchStrs 字符串数组参数, 可能为null
     * @return 字符串str中第一次出现字符串数组searchStrs中元素的位置, -1 没有适合的情况
     */
    public static int indexOfAny(String string, String[] searchStrs) {
        return org.apache.commons.lang.StringUtils.indexOfAny(string, searchStrs);
    }
    
    /**
     * <p>
     * 找出字符串str中最后出现字符串数组searchStrs中元素的位置.
     * </p>
     * <p>
     * 第一个参数str为<code>null</code>返回<code>-1</code>. A <code>null</code> search array will return <code>-1</code>. A
     * <code>null</code> or zero length search array entry will be ignored, 当第一个参数str不为null时,第二个参数searchStrs中有"" 的情况 将返回
     * <code>str</code>的长度. 这个方法使用了 {@link String#indexOf(String)}
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOfAny(null, *)                   = -1
     * StringUtils.lastIndexOfAny(*, null)                   = -1
     * StringUtils.lastIndexOfAny(*, [])                     = -1
     * StringUtils.lastIndexOfAny(*, [null])                 = -1
     * StringUtils.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;]) = 6
     * StringUtils.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;]) = 6
     * StringUtils.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
     * StringUtils.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
     * StringUtils.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;&quot;])   = 10
     * </pre>
     * 
     * @param string 需要处理的string,可能为null
     * @param searchStrs 字符串数组参数, 可能为null
     * @return 字符串str中最后出现字符串数组searchStrs中元素的位置, -1 没有适合的情况
     */
    public static int lastIndexOfAny(String string, String[] searchStrs) {
        return org.apache.commons.lang.StringUtils.lastIndexOfAny(string, searchStrs);
    }
    
    /**
     * <p>
     * 从字符串str中指定位置start后面的字符串.
     * </p>
     * <p>
     * 第二个参数start为负数时，取start的绝对值并从str的末端开倒数<code>n</code> 个字符.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>null</code>. An empty ("") String will return "".
     * </p>
     * 
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring(&quot;&quot;, *)     = &quot;&quot;
     * StringUtils.substring(&quot;abc&quot;, 0)  = &quot;abc&quot;
     * StringUtils.substring(&quot;abc&quot;, 2)  = &quot;c&quot;
     * StringUtils.substring(&quot;abc&quot;, 4)  = &quot;&quot;
     * StringUtils.substring(&quot;abc&quot;, -2) = &quot;bc&quot;
     * StringUtils.substring(&quot;abc&quot;, -4) = &quot;abc&quot;
     * </pre>
     * 
     * @param string 要从str中获得子字符串,可能为null
     * @param start 位置的开始位置, 负数时取start的绝对值并从str的末端开倒数字符
     * @return 从起始位置start开始的子串, 输入的第一个字符串为null时返回<code>null</code>
     */
    public static String substring(String string, int start) {
        return org.apache.commons.lang.StringUtils.substring(string, start);
    }
    
    /**
     * <p>
     * 从字符串str中获得从开始位置start到结束位end的子字符串，并且避免了异常.
     * </p>
     * <p>
     * start/end 能使用负数来表示,将会从字符串str的末端开始计数 .
     * </p>
     * <p>
     * 返回的字符串从<code>start</code>位置开始 到 <code>end</code> 位置结束. 字符串的开始位是以0开始的 -- i.e.,要以字符串的第一位开始<code>start = 0</code>.
     * 负数的开始位置偏移量是相对于str的末端开始的 .
     * </p>
     * <p>
     * 如果开始位置<code>start</code>不是在<code>end</code>位置的左边,将返回 "" .
     * </p>
     * 
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring(&quot;&quot;, * ,  *)    = &quot;&quot;;
     * StringUtils.substring(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
     * StringUtils.substring(&quot;abc&quot;, 2, 0)   = &quot;&quot;
     * StringUtils.substring(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
     * StringUtils.substring(&quot;abc&quot;, 4, 6)   = &quot;&quot;
     * StringUtils.substring(&quot;abc&quot;, 2, 2)   = &quot;&quot;
     * StringUtils.substring(&quot;abc&quot;, -2, -1) = &quot;b&quot;
     * StringUtils.substring(&quot;abc&quot;, -4, 2)  = &quot;ab&quot;
     * </pre>
     * 
     * @param string 要从中获得子字符串的string,可能为null
     * @param start 子字符串的开始位置, 负数代表从字符串的末端开始计数
     * @param end 子字符串的结束位置, 负数代表从字符串的末端开始计数
     * @return 从开始start到结束end的子字符串,如果输入的str为null返回<code>null</code>
     */
    public static String substring(String string, int start, int end) {
        return org.apache.commons.lang.StringUtils.substring(string, start, end);
    }
    
    /**
     * <p>
     * 从字符串的最左边获得指定长度<code>len</code> 的字符串.
     * </p>
     * <p>
     * 如果指定长度<code>len</code> 不是有效的, 或者str为<code>null</code>, str将作为返回值并不抛出异常. 如果指定的长度为负数将抛出异常.
     * </p>
     * 
     * <pre>
     * StringUtils.left(null, *)    = null
     * StringUtils.left(*, -ve)     = &quot;&quot;
     * StringUtils.left(&quot;&quot;, *)      = &quot;&quot;
     * StringUtils.left(&quot;abc&quot;, 0)   = &quot;&quot;
     * StringUtils.left(&quot;abc&quot;, 2)   = &quot;ab&quot;
     * StringUtils.left(&quot;abc&quot;, 4)   = &quot;abc&quot;
     * </pre>
     * 
     * @param string 需要转换的字符串string, 可能为null
     * @param len 要请求的字符串的长度, 必须为0或者正数
     * @return 指定长度的字符串最左边的字符串, 如果输入的字符串str为null返回<code>null</code>
     */
    public static String left(String string, int len) {
        return org.apache.commons.lang.StringUtils.left(string, len);
    }
    
    /**
     * <p>
     * 从字符串的最右边获得指定长度<code>len</code> 的字符串.
     * </p>
     * <p>
     * 如果指定长度<code>len</code> 不是有效的, 或者str为 <code>null</code>, str将作为返回值并不抛出异常 .如果指定的长度len为负数将抛出异常.
     * </p>
     * 
     * <pre>
     * StringUtils.right(null, *)    = null
     * StringUtils.right(*, -ve)     = &quot;&quot;
     * StringUtils.right(&quot;&quot;, *)      = &quot;&quot;
     * StringUtils.right(&quot;abc&quot;, 0)   = &quot;&quot;
     * StringUtils.right(&quot;abc&quot;, 2)   = &quot;bc&quot;
     * StringUtils.right(&quot;abc&quot;, 4)   = &quot;abc&quot;
     * </pre>
     * 
     * @param string 需要转换的字符串string, 可能为null
     * @param len 要请求的字符串的长度, 必须为0或者正数
     * @return 定长度的字符串最右边的字符串, 如果输入的字符串str为null返回</code>
     */
    public static String right(String string, int len) {
        return org.apache.commons.lang.StringUtils.right(string, len);
    }
    
    /**
     * <p>
     * 从字符串的中间获得指定长度<code>len</code> 的字符串.
     * </p>
     * <p>
     * 如果<code>len</code>参数为无效的,从pos开始的剩余的字符串将返回 而不抛出异常. 如果字符串str为<code>null</code>, 将返回<code>null</code> .
     * 如果指定的长度len为负数将抛出异常.
     * </p>
     * 
     * <pre>
     * StringUtils.mid(null, *, *)    = null
     * StringUtils.mid(*, *, -ve)     = &quot;&quot;
     * StringUtils.mid(&quot;&quot;, 0, *)      = &quot;&quot;
     * StringUtils.mid(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
     * StringUtils.mid(&quot;abc&quot;, 0, 4)   = &quot;abc&quot;
     * StringUtils.mid(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
     * StringUtils.mid(&quot;abc&quot;, 4, 2)   = &quot;&quot;
     * StringUtils.mid(&quot;abc&quot;, -2, 2)  = &quot;ab&quot;
     * </pre>
     * 
     * @param string 需要转换的字符串string, 可能为null
     * @param pos 返回的子串的开始位置, 如为负数当成0
     * @param len 要返回的子串的长度, 必须为0或者为正数
     * @return 中间的子字符串,如果输入的字符串str为null返回<code>null</code>
     */
    public static String mid(String string, int pos, int len) {
        return org.apache.commons.lang.StringUtils.mid(string, pos, len);
    }
    
    /**
     * <p>
     * 从字符串str中获得第一个出现隔离字符串separator之前的子字符串. 隔离字符串separator不作为结果返回.
     * </p>
     * <p>
     * 输入字符串str为<code>null</code>返回<code>null</code>. 空的字符串("")将返回空字符串(""). 隔离字符串separator为<code>null</code>
     * 将返回字符串str本身.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore(&quot;&quot;, *)        = &quot;&quot;
     * StringUtils.substringBefore(&quot;abc&quot;, &quot;a&quot;)   = &quot;&quot;
     * StringUtils.substringBefore(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
     * StringUtils.substringBefore(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
     * StringUtils.substringBefore(&quot;abc&quot;, &quot;d&quot;)   = &quot;abc&quot;
     * StringUtils.substringBefore(&quot;abc&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtils.substringBefore(&quot;abc&quot;, null)  = &quot;abc&quot;
     * </pre>
     * 
     * @param string 要处理的字符串, 可能为null
     * @param separator 隔离字符串, 可能为null
     * @return 字符串str中获得第一个出现隔离字符串separator之前的子字符串, 输入的字符串str为null时返回<code>null</code>
     * @since 2.0
     */
    public static String substringBefore(String string, String separator) {
        return org.apache.commons.lang.StringUtils.substringBefore(string, separator);
    }
    
    /**
     * <p>
     * 从字符串str中获得第一个出现隔离字符串separator之后的子字符串. 隔离字符串separator不作为结果返回.
     * </p>
     * <p>
     * 输入字符串str为<code>null</code>返回<code>null</code>. 空的字符串("")将返回空字符串(""). 如果第一个参数字符串不为<code>null</code>
     * 但隔离字符串separator为<code>null</code> 将返回空字符串(""). .
     * </p>
     * 
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter(&quot;&quot;, *)        = &quot;&quot;
     * StringUtils.substringAfter(*, null)      = &quot;&quot;
     * StringUtils.substringAfter(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
     * StringUtils.substringAfter(&quot;abcba&quot;, &quot;b&quot;) = &quot;cba&quot;
     * StringUtils.substringAfter(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
     * StringUtils.substringAfter(&quot;abc&quot;, &quot;d&quot;)   = &quot;&quot;
     * StringUtils.substringAfter(&quot;abc&quot;, &quot;&quot;)    = &quot;abc&quot;
     * </pre>
     * 
     * @param string 要处理的字符串, 可能为null
     * @param separator 隔离字符串, 可能为null
     * @return 字符串str中获得第一个出现隔离字符串separator之后的子字符串, 输入的字符串str为null时返回<code>null</code>
     * @since 2.0
     */
    public static String substringAfter(String string, String separator) {
        return org.apache.commons.lang.StringUtils.substringAfter(string, separator);
    }
    
    /**
     * <p>
     * 从字符串str中获得最后出现分割符separator之前的子字符串. 分割符separator不作为结果返回.
     * </p>
     * <p>
     * 输入字符串str为<code>null</code>返回<code>null</code>. 空的字符串("")将返回空字符串(""). 分割符separator为 <code>null</code>
     * 将返回输入字符串str本身.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBeforeLast(null, *)      = null
     * StringUtils.substringBeforeLast(&quot;&quot;, *)        = &quot;&quot;
     * StringUtils.substringBeforeLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;abc&quot;
     * StringUtils.substringBeforeLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
     * StringUtils.substringBeforeLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
     * StringUtils.substringBeforeLast(&quot;a&quot;, &quot;z&quot;)     = &quot;a&quot;
     * StringUtils.substringBeforeLast(&quot;a&quot;, null)    = &quot;a&quot;
     * StringUtils.substringBeforeLast(&quot;a&quot;, &quot;&quot;)      = &quot;a&quot;
     * </pre>
     * 
     * @param string 要处理的字符串, 可能为null
     * @param separator 分割符, 可能为null
     * @return 字符串str中获得最后出现分割符separator之前的子字符串, <code>null</code> 如果输入的字符串str为null
     * @since 2.0
     */
    public static String substringBeforeLast(String string, String separator) {
        return org.apache.commons.lang.StringUtils.substringBeforeLast(string, separator);
    }
    
    /**
     * <p>
     * 从字符串str中获得最后出现隔离字符串separator之后的子字符串. 隔离字符串separator不作为结果返回.
     * </p>
     * <p>
     * 输入字符串str为<code>null</code>返回<code>null</code>. 空的字符串("")将返回空字符串(""). 隔离字符串separator为 <code>null</code>
     * 将返回空的字符串("").
     * </p>
     * 
     * <pre>
     * StringUtils.substringAfterLast(null, *)      = null
     * StringUtils.substringAfterLast(&quot;&quot;, *)        = &quot;&quot;
     * StringUtils.substringAfterLast(*, &quot;&quot;)        = &quot;&quot;
     * StringUtils.substringAfterLast(*, null)      = &quot;&quot;
     * StringUtils.substringAfterLast(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
     * StringUtils.substringAfterLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
     * StringUtils.substringAfterLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
     * StringUtils.substringAfterLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
     * StringUtils.substringAfterLast(&quot;a&quot;, &quot;z&quot;)     = &quot;&quot;
     * </pre>
     * 
     * @param string 要处理的字符串, 可能为null
     * @param separator 隔离字符串, 可能为null
     * @return 字符串str中获得最后出现隔离字符串separator之后的子字符串, <code>null</code> 如果输入的字符串str为null
     * @since 2.0
     */
    public static String substringAfterLast(String string, String separator) {
        return org.apache.commons.lang.StringUtils.substringAfterLast(string, separator);
    }
    
    /**
     * <p>
     * 从字符串str中分离出嵌套在两个同样字符串tag中间的子字符串 .
     * </p>
     * <p>
     * 输入字符串str为<code>null</code>返回<code>null</code>. 第二个参数tag为<code>null</code>返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween(&quot;&quot;, &quot;&quot;)             = &quot;&quot;
     * StringUtils.substringBetween(&quot;&quot;, &quot;tag&quot;)          = null
     * StringUtils.substringBetween(&quot;tagabctag&quot;, null)  = null
     * StringUtils.substringBetween(&quot;tagabctag&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtils.substringBetween(&quot;tagabctag&quot;, &quot;tag&quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param string 包含子字符串的字符串, 可能为null
     * @param tag 子字符串之前和之后的字符串, 可能为null
     * @return 子字符串, <code>null</code> 如果没有匹配的情况
     * @since 2.0
     */
    public static String substringBetween(String string, String tag) {
        return org.apache.commons.lang.StringUtils.substringBetween(string, tag);
    }
    
    /**
     * <p>
     * 从字符串str中分离出嵌套在两个字符串open,close中间的子字符串. 只返回第一个匹配的子串.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> open/close returns
     * <code>null</code> (no match). An empty ("") open/close returns an empty string.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
     * StringUtils.substringBetween(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
     * StringUtils.substringBetween(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
     * StringUtils.substringBetween(&quot;yabcz&quot;, null, null) = null
     * StringUtils.substringBetween(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
     * StringUtils.substringBetween(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * StringUtils.substringBetween(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * </pre>
     * 
     * @param string 包含子串的字符串, 可能为null
     * @param open 子串前面的字符串, 可能为null
     * @param close 子串后面的字符串, 可能为null
     * @return 子串, <code>null</code> 如果没有匹配的情况
     * @since 2.0
     */
    public static String substringBetween(String string, String open, String close) {
        return org.apache.commons.lang.StringUtils.substringBetween(string, open, close);
    }
    
    /**
     * <p>
     * 从字符串str中分离出嵌套在两个字符串open,close中间的子字符串. 返回全部匹配的子串串数组.
     * </p>
     * 
     * @param string 包含子串的字符串, 可能为null
     * @param open 子串前面的字符串, 可能为null
     * @param close 子串后面的字符串, 可能为null
     * @return 全部子串数组, <code>null</code> 如果没有匹配的情况
     */
    public static String[] substringAllBetween(String string, String open, String close) {
        String strSub = getSubStringBetween(string, open, close);
        String[] strTest = strSub.split("&&");
        return strTest;
    }
    
    /**
     * <p>
     * 从字符串str中分离出嵌套在两个字符串open,close中间的子字符串. 返回全部匹配的子串.
     * </p>
     * 
     * @param str 包含子串的字符串, 可能为null
     * @param open 子串前面的字符串, 可能为null
     * @param close 子串后面的字符串, 可能为null
     * @return 全部子串, <code>null</code> 如果没有匹配的情况
     */
    public static String getSubStringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        StringBuffer sbBuffer = new StringBuffer();
        int iStart = str.indexOf(open);
        if (iStart != -1) {
            int iEnd = str.indexOf(close, iStart + open.length());
            if (iEnd != -1) {
                sbBuffer.append(str.substring(iStart + open.length(), iEnd));
                // 每个子串用&分隔开
                sbBuffer.append("&&");
                String strSubStr = str.substring(iEnd + 1, str.length());
                sbBuffer.append(getSubStringBetween(strSubStr, open, close));
            }
        }
        return sbBuffer.toString();
    }
    
    /**
     * <p>
     * 从字符串str中分离出嵌套在两个同样字符串tag中间的子字符串 .
     * </p>
     * <p>
     * 输入字符串str为<code>null</code>返回<code>null</code>. 第二个参数tag为<code>null</code>返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.getNestedString(null, *)            = null
     * StringUtils.getNestedString(&quot;&quot;, &quot;&quot;)             = &quot;&quot;
     * StringUtils.getNestedString(&quot;&quot;, &quot;tag&quot;)          = null
     * StringUtils.getNestedString(&quot;tagabctag&quot;, null)  = null
     * StringUtils.getNestedString(&quot;tagabctag&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtils.getNestedString(&quot;tagabctag&quot;, &quot;tag&quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param str 包含嵌套字符串的字符串, 可能为null
     * @param tag 在嵌套字符串前后的字符串, 可能为null
     * @return 嵌套字符串, <code>null</code> 如果没有匹配的情况
     * @deprecated 应使用更好的方法 {@link #substringBetween(String, String)}. 方法将在 Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String getNestedString(String str, String tag) {
        return substringBetween(str, tag, tag);
    }
    
    /**
     * <p>
     * 从字符串str中分离出嵌套在两个字符串open，close中间的子字符串. 只返回第一个匹配的子串.
     * </p>
     * <p>
     * 输入字符串str为<code>null</code>返回<code>null</code>. <code>null</code>的 open/close 参数返回 <code>null</code> (没有匹配的).
     * 空白字符的("") open/close 返回一个空字符.
     * </p>
     * 
     * <pre>
     * StringUtils.getNestedString(null, *, *)          = null
     * StringUtils.getNestedString(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
     * StringUtils.getNestedString(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
     * StringUtils.getNestedString(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
     * StringUtils.getNestedString(&quot;yabcz&quot;, null, null) = null
     * StringUtils.getNestedString(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
     * StringUtils.getNestedString(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * StringUtils.getNestedString(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * </pre>
     * 
     * @param str 包含嵌套字符串的字符串, 可能为null
     * @param open 嵌套字符串之前的字符串, 可能为null
     * @param close 嵌套字符串之后的字符串, 可能为null
     * @return 嵌套字符串, <code>null</code> 如果没有匹配的情况
     * @deprecated 应使用更好的方法 {@link #substringBetween(String, String, String)}. 方法将在 Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String getNestedString(String str, String open, String close) {
        return substringBetween(str, open, close);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, using whitespace as the separator. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
     * more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null)       = null
     * StringUtils.split(&quot;&quot;)         = []
     * StringUtils.split(&quot;abc def&quot;)  = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtils.split(&quot;abc  def&quot;) = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtils.split(&quot; abc &quot;)    = [&quot;abc&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be null
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    public static String[] split(String string) {
        return org.apache.commons.lang.StringUtils.split(string);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, separator specified. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
     * more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split(&quot;&quot;, *)           = []
     * StringUtils.split(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
     * StringUtils.split(&quot;a\tb\nc&quot;, null) = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be null
     * @param separatorChar the character used as the delimiter, <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String input
     * @since 2.0
     */
    public static String[] split(String string, char separatorChar) {
        return org.apache.commons.lang.StringUtils.split(string, separatorChar);
    }
    
    /**
     * <p>
     * 根据指定的分割符separator把字符串分割为字符串数组. 也可以选择使用 StringTokenizer.
     * </p>
     * <p>
     * 分割符separator不作为数组元素中的数据返回. 邻近的多个分割符separators 被认为是一个分割符separator.
     * </p>
     * <p>
     * 输入的字符串为<code>null</code>返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split(&quot;&quot;, *)           = []
     * StringUtils.split(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
     * StringUtils.split(&quot;a\tb\nc&quot;, null) = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * </pre>
     * 
     * @param string 需要处理的字符串, 可能为null
     * @param separatorChars 作为分隔符的字符, 如果为<code>null</code>就以whitespace作为分隔符
     * @return 处理后的数组, <code>null</code>如果输入的字符串为null
     * @since 2.0
     */
    public static String[] split(String string, String separatorChars) {
        return org.apache.commons.lang.StringUtils.split(string, separatorChars);
    }
    
    /**
     * <p>
     * 根据指定的分割符separator把字符串分割为字符串集合. 也可以选择使用 StringTokenizer.
     * </p>
     * <p>
     * 分割符separator不作为数组元素中的数据返回. 邻近的多个分割符separators 被认为是一个分割符separator.
     * </p>
     * <p>
     * 输入的字符串为<code>null</code>返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split(&quot;&quot;, *)           = []
     * StringUtils.split(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
     * StringUtils.split(&quot;a\tb\nc&quot;, null) = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * </pre>
     * 
     * @param string 需要处理的字符串, 可能为null
     * @param separatorChars 作为分隔符的字符, 如果为<code>null</code>就以whitespace作为分隔符
     * @return 处理后的字符串集合, <code>null</code>如果输入的字符串为null
     * @since 2.0
     */
    public static List<String> splitString(String string, String separatorChars) {
        return Arrays.asList(org.apache.commons.lang.StringUtils.split(string, separatorChars));
    }
    
    /**
     * <p>
     * Splits the provided text into an array with a maximum length, separators specified.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separatorChars splits on
     * whitespace.
     * </p>
     * <p>
     * If more than <code>max</code> delimited substrings are found, the last returned string includes all characters
     * after the first <code>max - 1</code> returned strings (including separator characters).
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *, *)            = null
     * StringUtils.split(&quot;&quot;, *, *)              = []
     * StringUtils.split(&quot;ab de fg&quot;, null, 0)   = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.split(&quot;ab   de fg&quot;, null, 0) = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.split(&quot;ab:cd:ef&quot;, &quot;:&quot;, 0)    = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.split(&quot;ab:cd:ef&quot;, &quot;:&quot;, 2)    = [&quot;ab&quot;, &quot;cd:ef&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be null
     * @param separatorChars the characters used as the delimiters, <code>null</code> splits on whitespace
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    public static String[] split(String string, String separatorChars, int max) {
        return org.apache.commons.lang.StringUtils.split(string, separatorChars, max);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, separator specified. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
     * more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split(&quot;&quot;, *)           = []
     * StringUtils.split(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
     * StringUtils.split(&quot;a\tb\nc&quot;, null) = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.split(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separatorChar the character used as the delimiter, <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String input
     * @since 2.0
     */
    public static String[] splitByChar(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }
    
    /**
     * Performs the logic for the <code>split</code> and <code>splitPreserveAllTokens</code> methods that do not return
     * a maximum array length.
     * 
     * @param str the String to parse, may be <code>null</code>
     * @param separatorChar the separate character
     * @param preserveAllTokens if <code>true</code>, adjacent separators are treated as empty token separators; if
     *            <code>false</code>, adjacent separators are treated as one separator.
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        // Performance tuned for 2.0 (1.0)
        
        if (str == null) {
            return new String[0];
        }
        int iLen = str.length();
        if (iLen == 0) {
            return EMPTY_STRING_ARRAY;
        }
        List<String> lstAr = new ArrayList<String>();
        int i = 0;
        int iStart = 0;
        boolean bMatch = false;
        boolean bLastMatch = false;
        while (i < iLen) {
            if (str.charAt(i) == separatorChar) {
                if (bMatch || preserveAllTokens) {
                    lstAr.add(str.substring(iStart, i));
                    bMatch = false;
                    bLastMatch = true;
                }
                iStart = ++i;
                continue;
            }
            bLastMatch = false;
            bMatch = true;
            i++;
        }
        if (bMatch || (preserveAllTokens && bLastMatch)) {
            lstAr.add(str.substring(iStart, i));
        }
        return lstAr.toArray(new String[lstAr.size()]);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, separator string specified.
     * </p>
     * <p>
     * The separator(s) will not be included in the returned String array. Adjacent separators are treated as one
     * separator.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separator splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitByWholeSeparator(null, *)               = null
     * StringUtils.splitByWholeSeparator(&quot;&quot;, *)                 = []
     * StringUtils.splitByWholeSeparator(&quot;ab de fg&quot;, null)      = [&quot;ab&quot;, &quot;de&quot;, &quot;fg&quot;]
     * StringUtils.splitByWholeSeparator(&quot;ab   de fg&quot;, null)    = [&quot;ab&quot;, &quot;de&quot;, &quot;fg&quot;]
     * StringUtils.splitByWholeSeparator(&quot;ab:cd:ef&quot;, &quot;:&quot;)       = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.splitByWholeSeparator(&quot;ab-!-cd-!-ef&quot;, &quot;-!-&quot;) = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String was input
     */
    public static String[] splitByWholeSeparator(String string, String separator) {
        return org.apache.commons.lang.StringUtils.splitByWholeSeparator(string, separator);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, separator string specified. Returns a maximum of <code>max</code>
     * substrings.
     * </p>
     * <p>
     * The separator(s) will not be included in the returned String array. Adjacent separators are treated as one
     * separator.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separator splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitByWholeSeparator(null, *, *)               = null
     * StringUtils.splitByWholeSeparator(&quot;&quot;, *, *)                 = []
     * StringUtils.splitByWholeSeparator(&quot;ab de fg&quot;, null, 0)      = [&quot;ab&quot;, &quot;de&quot;, &quot;fg&quot;]
     * StringUtils.splitByWholeSeparator(&quot;ab   de fg&quot;, null, 0)    = [&quot;ab&quot;, &quot;de&quot;, &quot;fg&quot;]
     * StringUtils.splitByWholeSeparator(&quot;ab:cd:ef&quot;, &quot;:&quot;, 2)       = [&quot;ab&quot;, &quot;cd:ef&quot;]
     * StringUtils.splitByWholeSeparator(&quot;ab-!-cd-!-ef&quot;, &quot;-!-&quot;, 5) = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.splitByWholeSeparator(&quot;ab-!-cd-!-ef&quot;, &quot;-!-&quot;, 2) = [&quot;ab&quot;, &quot;cd-!-ef&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, <code>null</code> splits on whitespace
     * @param max the maximum number of elements to include in the returned array. A zero or negative value implies no
     *            limit.
     * @return an array of parsed Strings, <code>null</code> if null String was input
     */
    public static String[] splitByWholeSeparator(String string, String separator, int max) {
        return org.apache.commons.lang.StringUtils.splitByWholeSeparator(string, separator, max);
    }
    
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Splits the provided text into an array, using whitespace as the separator, preserving all tokens, including empty
     * tokens created by adjacent separators. This is an alternative to using StringTokenizer. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null)       = null
     * StringUtils.splitPreserveAllTokens(&quot;&quot;)         = []
     * StringUtils.splitPreserveAllTokens(&quot;abc def&quot;)  = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;abc  def&quot;) = [&quot;abc&quot;, &quot;&quot;, &quot;def&quot;]
     * StringUtils.splitPreserveAllTokens(&quot; abc &quot;)    = [&quot;&quot;, &quot;abc&quot;, &quot;&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be <code>null</code>
     * @return an array of parsed Strings, <code>null</code> if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(String string) {
        return org.apache.commons.lang.StringUtils.splitPreserveAllTokens(string);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, separator specified, preserving all tokens, including empty tokens
     * created by adjacent separators. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *)         = null
     * StringUtils.splitPreserveAllTokens(&quot;&quot;, *)           = []
     * StringUtils.splitPreserveAllTokens(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;a\tb\nc&quot;, null) = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;a b c &quot;, ' ')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;a b c  &quot;, ' ')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;&quot;, &quot;&quot;]
     * StringUtils.splitPreserveAllTokens(&quot; a b c&quot;, ' ')   = [&quot;&quot;, a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;  a b c&quot;, ' ')  = [&quot;&quot;, &quot;&quot;, a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtils.splitPreserveAllTokens(&quot; a b c &quot;, ' ')  = [&quot;&quot;, a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be <code>null</code>
     * @param separatorChar the character used as the delimiter, <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(String string, char separatorChar) {
        return org.apache.commons.lang.StringUtils.splitPreserveAllTokens(string, separatorChar);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, separators specified, preserving all tokens, including empty tokens
     * created by adjacent separators. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separatorChars splits on
     * whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *)           = null
     * StringUtils.splitPreserveAllTokens(&quot;&quot;, *)             = []
     * StringUtils.splitPreserveAllTokens(&quot;abc def&quot;, null)   = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;abc def&quot;, &quot; &quot;)    = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;abc  def&quot;, &quot; &quot;)   = [&quot;abc&quot;, &quot;&quot;, def&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab:cd:ef&quot;, &quot;:&quot;)   = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab:cd:ef:&quot;, &quot;:&quot;)  = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;, &quot;&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab:cd:ef::&quot;, &quot;:&quot;) = 
     * [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;, &quot;&quot;, &quot;&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab::cd:ef&quot;, &quot;:&quot;)  = [&quot;ab&quot;, &quot;&quot;, cd&quot;, &quot;ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;:cd:ef&quot;, &quot;:&quot;)     = [&quot;&quot;, cd&quot;, &quot;ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;::cd:ef&quot;, &quot;:&quot;)    = [&quot;&quot;, &quot;&quot;, cd&quot;, &quot;ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;:cd:ef:&quot;, &quot;:&quot;)    = [&quot;&quot;, cd&quot;, &quot;ef&quot;, &quot;&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be <code>null</code>
     * @param separatorChars the characters used as the delimiters, <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(String string, String separatorChars) {
        return org.apache.commons.lang.StringUtils.splitPreserveAllTokens(string, separatorChars);
    }
    
    /**
     * <p>
     * Splits the provided text into an array with a maximum length, separators specified, preserving all tokens,
     * including empty tokens created by adjacent separators.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separatorChars splits on
     * whitespace.
     * </p>
     * <p>
     * If more than <code>max</code> delimited substrings are found, the last returned string includes all characters
     * after the first <code>max - 1</code> returned strings (including separator characters).
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *, *)            = null
     * StringUtils.splitPreserveAllTokens(&quot;&quot;, *, *)              = []
     * StringUtils.splitPreserveAllTokens(&quot;ab de fg&quot;, null, 0)   = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab   de fg&quot;, null, 0) = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab:cd:ef&quot;, &quot;:&quot;, 0)    = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab:cd:ef&quot;, &quot;:&quot;, 2)    = [&quot;ab&quot;, &quot;cd:ef&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab   de fg&quot;, null, 2) = [&quot;ab&quot;, &quot;  de fg&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab   de fg&quot;, null, 3) = [&quot;ab&quot;, &quot;&quot;, &quot; de fg&quot;]
     * StringUtils.splitPreserveAllTokens(&quot;ab   de fg&quot;, null, 4) = [&quot;ab&quot;, &quot;&quot;, &quot;&quot;, &quot;de fg&quot;]
     * </pre>
     * 
     * @param string the String to parse, may be <code>null</code>
     * @param separatorChars the characters used as the delimiters, <code>null</code> splits on whitespace
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
     * @return an array of parsed Strings, <code>null</code> if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(String string, String separatorChars, int max) {
        return org.apache.commons.lang.StringUtils.splitPreserveAllTokens(string, separatorChars, max);
    }
    
    /**
     * 在指定的位置上，获取利用分割符分割的字符串。
     * 
     * <pre>
     * ProjectUtils.getSplitIndexString(&quot;000;111;222&quot;,&quot;;&quot;,0)=&quot;000&quot;
     * ProjectUtils.getSplitIndexString(&quot;&quot;,&quot;;&quot;,0)=&quot;&quot;
     * ProjectUtils.getSplitIndexString(&quot;000&quot;,&quot;;&quot;,0)=&quot;000&quot;
     * ProjectUtils.getSplitIndexString(&quot;&quot;,&quot;&quot;,0) = &quot;&quot;
     * </pre>
     * 
     * @param source 需要被分割的字符串。
     * @param splitString 被指定的分割符号。
     * @param position 分割符所在的索引
     * @return 返回指定位置上面被分割符号分割的字符串。
     */
    public static String getSplitIndexString(String source, String splitString, int position) {
        String[] strSplitStrings = org.apache.commons.lang.StringUtils.split(source, splitString);
        if (strSplitStrings.length > position) {
            return strSplitStrings[position];
        }
        return EMPTY;
    }
    
    /**
     * <p>
     * 连接数组array为一个字符串. 在数组中的null对象或者空字符串将被认为是一个空的字符串处理 .
     * </p>
     * 
     * <pre>
     * StringUtils.concatenate(null)            = null
     * StringUtils.concatenate([])              = &quot;&quot;
     * StringUtils.concatenate([null])          = &quot;&quot;
     * StringUtils.concatenate([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]) = &quot;abc&quot;
     * StringUtils.concatenate([null, &quot;&quot;, &quot;a&quot;]) = &quot;a&quot;
     * </pre>
     * 
     * @param array 需要连接的数组, 可能为null
     * @return 连接后的字符串, <code>null</code> 如果输入的为null
     * @deprecated 应使用更好的方法{@link #join(Object[])} 来代替. 方法将在 Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String concatenate(Object[] array) {
        return join(array, null);
    }
    
    /**
     * <p>
     * 连接数组array为一个字符串 .
     * </p>
     * <p>
     * 在连接后的字符串中不包括分割符separator. 在数组中的null对象或者空字符串将被认为是一个空的字符串处理 .
     * </p>
     * 
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = &quot;&quot;
     * StringUtils.join([null])          = &quot;&quot;
     * StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]) = &quot;abc&quot;
     * StringUtils.join([null, &quot;&quot;, &quot;a&quot;]) = &quot;a&quot;
     * </pre>
     * 
     * @param array 需要连接的数组, 可能为null
     * @return 连接后的字符串, <code>null</code> 如果输入的数组为null
     * @since 2.0
     */
    public static String join(Object[] array) {
        return org.apache.commons.lang.StringUtils.join(array);
    }
    
    /**
     * <p>
     * 把一个数组array和给定的字符分割符separator连接为一个字符串 .
     * </p>
     * <p>
     * 在连接的数组array前后不添加分隔符. 在数组中的null对象或者空字符串将被认为是一个空的字符串处理 .
     * </p>
     * 
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = &quot;&quot;
     * StringUtils.join([null], *)             = &quot;&quot;
     * StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], ';')  = &quot;a;b;c&quot;
     * StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null) = &quot;abc&quot;
     * StringUtils.join([null, &quot;&quot;, &quot;a&quot;], ';')  = &quot;;;a&quot;
     * </pre>
     * 
     * @param array 需连接的数组, 可能为null
     * @param separator 字符分割符separator
     * @return 连接后的字符串, <code>null</code> 如果输入的数组为null
     * @since 2.0
     */
    public static String join(Object[] array, char separator) {
        return org.apache.commons.lang.StringUtils.join(array, separator);
    }
    
    /**
     * <p>
     * 把一个数组array和给定的字符串分割符separator连接为一个字符串 .
     * </p>
     * <p>
     * 在连接的数组array前后不添加分隔符. <code>null</code> 分隔符被当成空字符串 (""). 在数组中的null对象或者空字符串将被认为是一个空的字符串处理 .
     * </p>
     * 
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = &quot;&quot;
     * StringUtils.join([null], *)              = &quot;&quot;
     * StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
     * StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
     * StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
     * StringUtils.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
     * </pre>
     * 
     * @param array 需连接的数组, 可能为null
     * @param separator 字符串分割符separator, null 当作 ""
     * @return 连接后的字符串, <code>null</code> 如果输入的数组为null
     */
    public static String join(Object[] array, String separator) {
        return org.apache.commons.lang.StringUtils.join(array, separator);
    }
    
    /**
     * <p>
     * 把<code>Iterator</code>中的所有元素和给定的字符分割符separator连接为一个字符串 .
     * </p>
     * <p>
     * 在连接的链表前后不添加分隔符. 在iteration中的null对象或者空字符串将被认为是一个空的字符串处理 .
     * </p>
     * <p>
     * 例如: {@link #join(Object[],char)}.
     * </p>
     * 
     * @param iterator 需要进行连接的<code>Iterator</code>, 可能为null
     * @param separator 用于间隔的字符
     * @return 连接后的字符串, <code>null</code> 如果输入的iterator为null
     * @since 2.0
     */
    public static String join(Iterator<?> iterator, char separator) {
        return org.apache.commons.lang.StringUtils.join(iterator, separator);
    }
    
    /**
     * <p>
     * 把<code>Iterator</code>中的所有元素和给定的字符串分割符separator连接为一个字符串 .
     * </p>
     * <p>
     * 在连接的链表前后不添加分隔符. <code>null</code>的分隔符separator 被当作空字符串("").
     * </p>
     * <p>
     * 例如: {@link #join(Object[],String)}.
     * </p>
     * 
     * @param iterator 需要进行连接的 <code>Iterator</code>, 可能为null
     * @param separator 用于间隔的字符, null当作""
     * @return 连接后的字符串, <code>null</code> 如果输入的iterator为null
     */
    public static String join(Iterator<?> iterator, String separator) {
        return org.apache.commons.lang.StringUtils.join(iterator, separator);
    }
    
    /**
     * <p>
     * 删除在{@link Character#isWhitespace(char)}定义的空白字符 .
     * </p>
     * 
     * <pre>
     * StringUtils.deleteWhitespace(null)         = null
     * StringUtils.deleteWhitespace(&quot;&quot;)           = &quot;&quot;
     * StringUtils.deleteWhitespace(&quot;abc&quot;)        = &quot;abc&quot;
     * StringUtils.deleteWhitespace(&quot;   ab  c  &quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param string 需要移除空白的字符串, 可能为null
     * @return 移除空白的字符串, <code>null</code> 输入的字符串为null
     */
    public static String deleteWhitespace(String string) {
        return org.apache.commons.lang.StringUtils.deleteWhitespace(string);
    }
    
    /**
     * <p>
     * 替换一个字符串text中的部分字符串repl为一新的字符串with,只替换开始的第一个.
     * </p>
     * <p>
     * 传入的参数中为<code>null</code>时这个方法不进行操作.
     * </p>
     * 
     * <pre>
     * StringUtils.replaceOnce(null, *, *)        = null
     * StringUtils.replaceOnce(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtils.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtils.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtils.replaceOnce(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
     * StringUtils.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;aba&quot;
     * StringUtils.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zba&quot;
     * </pre>
     * 
     * @see #replace(String text, String repl, String with, int max)
     * @param text 要进行替换的字符串, 可能为null
     * @param repl 要被替换的字符串, 可能为null
     * @param with 替换后的字符串, 可能为null
     * @return 进行替换操作后的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String replaceOnce(String text, String repl, String with) {
        return org.apache.commons.lang.StringUtils.replaceOnce(text, repl, with);
    }
    
    /**
     * <p>
     * 替换字符串text中所有出现的字符串repl替换为新的字符串with.
     * </p>
     * <p>
     * <code>null</code> 传递给这个方法将不会被处理操作.
     * </p>
     * 
     * <pre>
     * StringUtils.replace(null, *, *)        = null
     * StringUtils.replace(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtils.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtils.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtils.replace(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
     * StringUtils.replace(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;aba&quot;
     * StringUtils.replace(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zbz&quot;
     * </pre>
     * 
     * @see #replace(String text, String repl, String with, int max)
     * @param text 要进行替换的字符串, 可能为null
     * @param repl 要被替换的字符串, 可能为null
     * @param with 替换后的字符串, 可能为null
     * @return 进行替换操作后的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String replace(String text, String repl, String with) {
        return org.apache.commons.lang.StringUtils.replace(text, repl, with);
    }
    
    /**
     * <p>
     * 将字符串text中出现指定个数<code>max</code>的字符串repl为替换新的字符串with, .
     * </p>
     * <p>
     * <code>null</code> 传递给这个方法将不会被处理操作.
     * </p>
     * 
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace(&quot;&quot;, *, *, *)           = &quot;&quot;
     * StringUtils.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
     * StringUtils.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
     * StringUtils.replace(&quot;abaa&quot;, &quot;a&quot;, null, 1)  = &quot;abaa&quot;
     * StringUtils.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;&quot;, 1)    = &quot;abaa&quot;
     * StringUtils.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 0)   = &quot;abaa&quot;
     * StringUtils.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 1)   = &quot;zbaa&quot;
     * StringUtils.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 2)   = &quot;zbza&quot;
     * StringUtils.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, -1)  = &quot;zbzz&quot;
     * </pre>
     * 
     * @param text 要进行替换的字符串, 可能为null
     * @param repl 要被替换的字符串, 可能为null
     * @param with 替换后的字符串, 可能为null
     * @param max 要替换的字符串的个数, 或者 <code>-1</code> 标识没有个数限制
     * @return 进行替换操作后的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String replace(String text, String repl, String with, int max) {
        return org.apache.commons.lang.StringUtils.replace(text, repl, with, max);
    }
    
    // Replace, character based
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 替换字符串str中所有出现的字符searchChar为一个新的字符replaceChar. 一个 null-safe 的版本 {@link String#replace(char, char)}.
     * </p>
     * <p>
     * <code>null</code> 字符串的输入返回 <code>null</code>. 空的字符串 ("") string 返回一个空字符串.
     * </p>
     * 
     * <pre>
     * StringUtils.replaceChars(null, *, *)        = null
     * StringUtils.replaceChars(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtils.replaceChars(&quot;abcba&quot;, 'b', 'y') = &quot;aycya&quot;
     * StringUtils.replaceChars(&quot;abcba&quot;, 'z', 'y') = &quot;abcba&quot;
     * </pre>
     * 
     * @param string 要进行替换的字符串, 可能为null
     * @param searchChar 需要替换的字符, 可能为null
     * @param replaceChar 替换后的字符, 可能为null
     * @return 替换后的字符串, <code>null</code> 如果输入的字符串为null
     * @since 2.0
     */
    public static String replaceChars(String string, char searchChar, char replaceChar) {
        return org.apache.commons.lang.StringUtils.replaceChars(string, searchChar, replaceChar);
    }
    
    /**
     * <p>
     * 替换字符串str中的多个字符searchChars替换为新的字符replaceChars. 这个方法可以用来删除字符.
     * </p>
     * <p>
     * 例如:<br />
     * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>.
     * </p>
     * <p>
     * <code>null</code> 字符串输入返回 <code>null</code>. 空字符串 ("") 将返回空字符串. 输入null或者空的需要替换的字符searchChars将仿佛哈字符串str本身.
     * </p>
     * <p>
     * 要被替换的字符searchChars的长度通常应该等于替换的字符replaceChars的长度. 如果被替换的字符searchChars的长度更长,被替换的字符searchChars中额外的字符将被删除
     * 如果替换的字符replaceChars的长度更长,替换的字符replaceChars中额外的字符将被忽略 .
     * </p>
     * 
     * <pre>
     * StringUtils.replaceChars(null, *, *)           = null
     * StringUtils.replaceChars(&quot;&quot;, *, *)             = &quot;&quot;
     * StringUtils.replaceChars(&quot;abc&quot;, null, *)       = &quot;abc&quot;
     * StringUtils.replaceChars(&quot;abc&quot;, &quot;&quot;, *)         = &quot;abc&quot;
     * StringUtils.replaceChars(&quot;abc&quot;, &quot;b&quot;, null)     = &quot;ac&quot;
     * StringUtils.replaceChars(&quot;abc&quot;, &quot;b&quot;, &quot;&quot;)       = &quot;ac&quot;
     * StringUtils.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yz&quot;)  = &quot;ayzya&quot;
     * StringUtils.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;y&quot;)   = &quot;ayya&quot;
     * StringUtils.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yzx&quot;) = &quot;ayzya&quot;
     * </pre>
     * 
     * @param string 需要被替换的字符串, 可能为null
     * @param searchChars 字符串中要被替换的字符, 可能为null
     * @param replaceChars 替换的字符, 可能为null
     * @return 替换后的字符串, <code>null</code> 如果输入的字符串为null
     * @since 2.0
     */
    public static String replaceChars(String string, String searchChars, String replaceChars) {
        return org.apache.commons.lang.StringUtils.replaceChars(string, searchChars, replaceChars);
    }
    
    /**
     * <p>
     * 用字符串overlay覆盖字符串text中的部分字符. 当start或者end参数有不合法的数字时会抛出IndexOutOfBoundsException异常
     * </p>
     * 
     * <pre>
     * StringUtils.overlayString(null, *, *, *)           = NullPointerException
     * StringUtils.overlayString(*, null, *, *)           = NullPointerException
     * StringUtils.overlayString(&quot;&quot;, &quot;abc&quot;, 0, 0)         = &quot;abc&quot;
     * StringUtils.overlayString(&quot;abcdef&quot;, null, 2, 4)    = &quot;abef&quot;
     * StringUtils.overlayString(&quot;abcdef&quot;, &quot;&quot;, 2, 4)      = &quot;abef&quot;
     * StringUtils.overlayString(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 4)  = &quot;abzzzzef&quot;
     * StringUtils.overlayString(&quot;abcdef&quot;, &quot;zzzz&quot;, 4, 2)  = &quot;abcdzzzzcdef&quot;
     * StringUtils.overlayString(&quot;abcdef&quot;, &quot;zzzz&quot;, -1, 4) = IndexOutOfBoundsException
     * StringUtils.overlayString(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 8)  = IndexOutOfBoundsException
     * </pre>
     * 
     * @param text 将要被覆盖字符的字符串, 可能为null
     * @param overlay 覆盖的字符, 可能为null
     * @param start 覆盖字符串的起始位置,必须是有效的数字
     * @param end 覆盖字符串的结束位置,必须是有效的数字
     * @return 覆盖后的字符串, <code>null</code> 如果输入的字符串为null
     * @deprecated 使用 {@link #overlay(String, String, int, int)} 来代替. 方法将在 Commons Lang 3.0. 被取消
     */
    @Deprecated
    public static String overlayString(String text, String overlay, int start, int end) {
        return new StringBuffer(start + overlay.length() + text.length() - end + 1).append(text.substring(0, start))
            .append(overlay).append(text.substring(end)).toString();
    }
    
    /**
     * <p>
     * 用字符串overlay覆盖字符串text中的部分字符.
     * </p>
     * <p>
     * <code>null</code>的字符串输入返回<code>null</code>. start和end参数中有为负数时当作0处理. start和end参数大于字符串str的长度时以str的长度来处理.
     * start参数中是选择两个位置参数中小的那一个.
     * </p>
     * 
     * <pre>
     * StringUtils.overlay(null, *, *, *)            = null
     * StringUtils.overlay(&quot;&quot;, &quot;abc&quot;, 0, 0)          = &quot;abc&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, null, 2, 4)     = &quot;abef&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;&quot;, 2, 4)       = &quot;abef&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;&quot;, 4, 2)       = &quot;abef&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 4)   = &quot;abzzzzef&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 4, 2)   = &quot;abzzzzef&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -1, 4)  = &quot;zzzzef&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 8)   = &quot;abzzzz&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -2, -3) = &quot;zzzzabcdef&quot;
     * StringUtils.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 8, 10)  = &quot;abcdefzzzz&quot;
     * </pre>
     * 
     * @param string 将要被覆盖字符的字符串, 可能为null
     * @param overlay 覆盖的字符, 可能为null
     * @param start 覆盖字符串的起始位置
     * @param end 覆盖字符串的结束位置
     * @return 覆盖后的字符串, <code>null</code> 如果输入的字符串为null
     * @since 2.0
     */
    public static String overlay(String string, String overlay, int start, int end) {
        return org.apache.commons.lang.StringUtils.overlay(string, overlay, start, end);
    }
    
    /**
     * <p>
     * 如果字符串str的末尾有换行就去掉一个换行,多个换行的保留其他的. 换行的定义为 &quot;<code>\n</code>&quot;, &quot;<code>\r</code>&quot;, 或者 &quot;
     * <code>\r\n</code>&quot;.
     * </p>
     * <p>
     * 注意: 这个方法在 2.0. 有所改变 现在和 Perl chomp 相匹配了.
     * </p>
     * 
     * <pre>
     * StringUtils.chomp(null)          = null
     * StringUtils.chomp(&quot;&quot;)            = &quot;&quot;
     * StringUtils.chomp(&quot;abc \r&quot;)      = &quot;abc &quot;
     * StringUtils.chomp(&quot;abc\n&quot;)       = &quot;abc&quot;
     * StringUtils.chomp(&quot;abc\r\n&quot;)     = &quot;abc&quot;
     * StringUtils.chomp(&quot;abc\r\n\r\n&quot;) = &quot;abc\r\n&quot;
     * StringUtils.chomp(&quot;abc\n\r&quot;)     = &quot;abc\n&quot;
     * StringUtils.chomp(&quot;abc\n\rabc&quot;)  = &quot;abc\n\rabc&quot;
     * StringUtils.chomp(&quot;\r&quot;)          = &quot;&quot;
     * StringUtils.chomp(&quot;\n&quot;)          = &quot;&quot;
     * StringUtils.chomp(&quot;\r\n&quot;)        = &quot;&quot;
     * </pre>
     * 
     * @param string 需要去掉换行的字符串, 可能为null
     * @return 去掉换行的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String chomp(String string) {
        return org.apache.commons.lang.StringUtils.chomp(string);
    }
    
    /**
     * <p>
     * 如果字符串<code>str</code>的末端有指定的分隔符<code>separator</code> 就去掉一个 , 其他剩余的保留.
     * </p>
     * <p>
     * 注意: 这个方法在 2.0. 有所改变 现在和 Perl chomp 相匹配了. 先前的版本使用了 {@link #substringBeforeLast(String, String)}. 这个方法使用了
     * {@link String#endsWith(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.chomp(null, *)         = null
     * StringUtils.chomp(&quot;&quot;, *)           = &quot;&quot;
     * StringUtils.chomp(&quot;foobar&quot;, &quot;bar&quot;) = &quot;foo&quot;
     * StringUtils.chomp(&quot;foobar&quot;, &quot;baz&quot;) = &quot;foobar&quot;
     * StringUtils.chomp(&quot;foo&quot;, &quot;foo&quot;)    = &quot;&quot;
     * StringUtils.chomp(&quot;foo &quot;, &quot;foo&quot;)   = &quot;foo&quot;
     * StringUtils.chomp(&quot; foo&quot;, &quot;foo&quot;)   = &quot; &quot;
     * StringUtils.chomp(&quot;foo&quot;, &quot;foooo&quot;)  = &quot;foo&quot;
     * StringUtils.chomp(&quot;foo&quot;, &quot;&quot;)       = &quot;foo&quot;
     * StringUtils.chomp(&quot;foo&quot;, null)     = &quot;foo&quot;
     * </pre>
     * 
     * @param string 需要去掉分隔符的字符串, 可能为null
     * @param separator 指定的分隔符, 可能为null
     * @return 去掉分隔符的字符串, <code>null</code>如果输入的字符串为null
     */
    public static String chomp(String string, String separator) {
        return org.apache.commons.lang.StringUtils.chomp(string, separator);
    }
    
    /**
     * <p>
     * 如果字符串str的末端是以指定的字符串sep结束的 那么就删去str的末端的sep字符串.
     * </p>
     * 
     * @param str 需要处理的字符串,不能为null
     * @param sep 要去掉的字符串,不能为null
     * @return 去掉字符串后的字符串
     * @deprecated 使用 {@link #chomp(String,String)} 来代替. 方法将在Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String chompLast(String str, String sep) {
        if (str.length() == 0) {
            return str;
        }
        String strSub = str.substring(str.length() - sep.length());
        if (sep.equals(strSub)) {
            return str.substring(0, str.length() - sep.length());
        }
        return str;
    }
    
    /**
     * <p>
     * 去掉字符串str中最后出现指定字符sep前的所有的字符, 返回指定字符sep和它后面的字符串.
     * </p>
     * 
     * @param str 需要处理的字符串, 不能为null
     * @param sep 要去掉的字符串, 不能为null
     * @return 去掉字符串后的字符串
     * @deprecated 使用 {@link #substringAfterLast(String, String)} 来代替. (虽然不包括分隔符 separator) 方法将在Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String getChomp(String str, String sep) {
        int iIdx = str.lastIndexOf(sep);
        if (iIdx == str.length() - sep.length()) {
            return sep;
        } else if (iIdx != -1) {
            return str.substring(iIdx);
        } else {
            return EMPTY;
        }
    }
    
    /**
     * <p>
     * 去掉字符串str中第一个出现指定字符sep前的所有的字符, 返回字符串str中第一个字符串sep后面的字符串.
     * </p>
     * 
     * @param str 需要处理的字符串, 不能为null
     * @param sep 要去掉的字符串, 不能为null
     * @return 去掉字符串后的字符串
     * @deprecated 使用 {@link #substringAfter(String,String)} 来代替. 方法将在Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String prechomp(String str, String sep) {
        int iIdx = str.indexOf(sep);
        if (iIdx != -1) {
            return str.substring(iIdx + sep.length());
        }
        return str;
    }
    
    /**
     * <p>
     * 去掉字符串str中第一个出现指定字符sep后面的所有的字符 返回字符串str中第一个字符串sep前面的字符串,包括seq字符串.
     * </p>
     * 
     * <pre>
     * StringUtils.getPrechomp(&quot;accbcabc&quot;, &quot;bc&quot;) = &quot;accbc&quot;
     * </pre>
     * 
     * @param str 需要处理的字符串, 不能为null
     * @param sep 进行比较的字符串, 不能为null
     * @return 去掉字符串后的字符串
     * @deprecated 使用 {@link #substringBefore(String,String)} 来代替 (不包括分隔符separator). 方法将在Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String getPrechomp(String str, String sep) {
        int iIdx = str.indexOf(sep);
        if (iIdx != -1) {
            return str.substring(0, iIdx + sep.length());
        }
        return EMPTY;
    }
    
    /**
     * <p>
     * 去掉字符串str末端的字符.
     * </p>
     * <p>
     * 如果字符串str是以这<code>\r\n</code>其中任意形式的换行, 去掉换行.
     * </p>
     * 
     * <pre>
     * StringUtils.chop(null)          = null
     * StringUtils.chop(&quot;&quot;)            = &quot;&quot;
     * StringUtils.chop(&quot;abc \r&quot;)      = &quot;abc &quot;
     * StringUtils.chop(&quot;abc\n&quot;)       = &quot;abc&quot;
     * StringUtils.chop(&quot;abc\r\n&quot;)     = &quot;abc&quot;
     * StringUtils.chop(&quot;abc&quot;)         = &quot;ab&quot;
     * StringUtils.chop(&quot;abc\nabc&quot;)    = &quot;abc\nab&quot;
     * StringUtils.chop(&quot;a&quot;)           = &quot;&quot;
     * StringUtils.chop(&quot;\r&quot;)          = &quot;&quot;
     * StringUtils.chop(&quot;\n&quot;)          = &quot;&quot;
     * StringUtils.chop(&quot;\r\n&quot;)        = &quot;&quot;
     * </pre>
     * 
     * @param string 需要去掉字符的字符串, 可能为null
     * @return 去掉末尾字符的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String chop(String string) {
        return org.apache.commons.lang.StringUtils.chop(string);
    }
    
    /**
     * <p>
     * 如果字符串str的结尾是<code>\n</code>就去掉<code>\n</code>. 如果<code>\n</code>前面是<code>\r</code>,同样去掉<code>\r</code>.
     * </p>
     * 
     * @param str the String to chop a newline from, 不能为null
     * @return 去掉换行的字符串
     * @deprecated 使用 {@link #chomp(String)} 来代替. 方法将在Commons Lang 3.0.被取消
     */
    @Deprecated
    public static String chopNewline(String str) {
        int iLastIdx = str.length() - 1;
        if (iLastIdx <= 0) {
            return EMPTY;
        }
        char chLast = str.charAt(iLastIdx);
        if (chLast == '\n') {
            if (str.charAt(iLastIdx - 1) == '\r') {
                iLastIdx--;
            }
        } else {
            iLastIdx++;
        }
        return str.substring(0, iLastIdx);
    }
    
    /**
     * <p>
     * 复制一个字符串指定的次数<code>repeat</code> .
     * </p>
     * 
     * <pre>
     * StringUtils.repeat(null, 2) = null
     * StringUtils.repeat(&quot;&quot;, 0)   = &quot;&quot;
     * StringUtils.repeat(&quot;&quot;, 2)   = &quot;&quot;
     * StringUtils.repeat(&quot;a&quot;, 3)  = &quot;aaa&quot;
     * StringUtils.repeat(&quot;ab&quot;, 2) = &quot;abab&quot;
     * StringUtils.repeat(&quot;a&quot;, -2) = &quot;&quot;
     * </pre>
     * 
     * @param string 要复制的字符串, 可能为null
     * @param repeat 字符串str要重复的参数,负数为0
     * @return 由原来字符串进行repeat次重写后的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String repeat(String string, int repeat) {
        return org.apache.commons.lang.StringUtils.repeat(string, repeat);
    }
    
    /**
     * <p>
     * 重复一个字符padChar指定的次数repeat .
     * </p>
     * 
     * <pre>
     * StringUtils.padding(0, 'e')  = &quot;&quot;
     * StringUtils.padding(3, 'e')  = &quot;eee&quot;
     * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     * 
     * @param repeat 要重复的次数
     * @param padChar 要重复的字符
     * @return 重复后的字符串
     */
    public static String padding(int repeat, char padChar) {
        // be careful of synchronization in this method
        // we are assuming that get and set from an array index is atomic
        String strPad = CONSTANTS_PADDING[padChar];
        if (strPad == null) {
            strPad = String.valueOf(padChar);
        }
        while (strPad.length() < repeat) {
            strPad = strPad.concat(strPad);
        }
        CONSTANTS_PADDING[padChar] = strPad;
        return strPad.substring(0, repeat);
    }
    
    /**
     * <p>
     * 为字符串str的右边添加(' ').
     * </p>
     * <p>
     * 添加(' ')后的字符串的长度为参数<code>size</code>指定的长度.
     * </p>
     * 
     * <pre>
     * StringUtils.rightPad(null, *)   = null
     * StringUtils.rightPad(&quot;&quot;, 3)     = &quot;   &quot;
     * StringUtils.rightPad(&quot;bat&quot;, 3)  = &quot;bat&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 5)  = &quot;bat  &quot;
     * StringUtils.rightPad(&quot;bat&quot;, 1)  = &quot;bat&quot;
     * StringUtils.rightPad(&quot;bat&quot;, -1) = &quot;bat&quot;
     * </pre>
     * 
     * @param string 要添加(' ')的字符串, 可能为null
     * @param size 要添加的长度
     * @return 要添加(' ')的后字符串,如果没添加就返回原字符串. <code>null</code>如果输入的字符串为null
     */
    public static String rightPad(String string, int size) {
        return org.apache.commons.lang.StringUtils.rightPad(string, size);
    }
    
    /**
     * <p>
     * 为字符串str的右边添加指定的字符padChar.
     * </p>
     * <p>
     * 添加指定的字符后的字符串的长度为参数<code>size</code>指定的长度.
     * </p>
     * 
     * <pre>
     * StringUtils.rightPad(null, *, *)     = null
     * StringUtils.rightPad(&quot;&quot;, 3, 'z')     = &quot;zzz&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 3, 'z')  = &quot;bat&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 5, 'z')  = &quot;batzz&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 1, 'z')  = &quot;bat&quot;
     * StringUtils.rightPad(&quot;bat&quot;, -1, 'z') = &quot;bat&quot;
     * </pre>
     * 
     * @param string 要添加字符的字符串, 可能为null
     * @param size 添加字符后字符串的长度
     * @param padChar 添加的字符
     * @return 添加字符后的字符串如果没添加就返回原字符串, <code>null</code>如果输入的字符串为null
     * @since 2.0
     */
    public static String rightPad(String string, int size, char padChar) {
        return org.apache.commons.lang.StringUtils.rightPad(string, size, padChar);
    }
    
    /**
     * <p>
     * 为字符串str的右边添加指定的字符串padStr.
     * </p>
     * <p>
     * 添加指定的字符串padStr后的字符串的长度为参数<code>size</code>指定的长度.
     * </p>
     * 
     * <pre>
     * StringUtils.rightPad(null, *, *)      = null
     * StringUtils.rightPad(&quot;&quot;, 3, &quot;z&quot;)      = &quot;zzz&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 3, &quot;yz&quot;)  = &quot;bat&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 5, &quot;yz&quot;)  = &quot;batyz&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 8, &quot;yz&quot;)  = &quot;batyzyzy&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 1, &quot;yz&quot;)  = &quot;bat&quot;
     * StringUtils.rightPad(&quot;bat&quot;, -1, &quot;yz&quot;) = &quot;bat&quot;
     * StringUtils.rightPad(&quot;bat&quot;, 5, null)  = &quot;bat  &quot;
     * StringUtils.rightPad(&quot;bat&quot;, 5, &quot;&quot;)    = &quot;bat  &quot;
     * </pre>
     * 
     * @param string 要添加字符串的字符串, 可能为null
     * @param size 添加字符串后字符串的长度
     * @param padStr 要添加的字符串, null或空字符串当作(" ")
     * @return 添加字符串后的字符串,如果没添加就返回原字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String rightPad(String string, int size, String padStr) {
        return org.apache.commons.lang.StringUtils.rightPad(string, size, padStr);
    }
    
    /**
     * <p>
     * 为字符串str的左边添加(' ').
     * </p>
     * <p>
     * 添加(' ')后的字符串的长度为参数<code>size</code>指定的长度.
     * </p>
     * 
     * <pre>
     * StringUtils.leftPad(null, *)   = null
     * StringUtils.leftPad(&quot;&quot;, 3)     = &quot;   &quot;
     * StringUtils.leftPad(&quot;bat&quot;, 3)  = &quot;bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 5)  = &quot;  bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 1)  = &quot;bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, -1) = &quot;bat&quot;
     * </pre>
     * 
     * @param string 要添加(' ')的字符串, 可能为null
     * @param size 要添加的长度
     * @return 要添加(' ')的后字符串,如果没添加就返回原字符串, <code>null</code> i如果输入的字符串为null
     */
    public static String leftPad(String string, int size) {
        return org.apache.commons.lang.StringUtils.leftPad(string, size);
    }
    
    /**
     * <p>
     * 为字符串str的左边添加指定的字符padChar.
     * </p>
     * <p>
     * 添加指定的字符后的字符串的长度为参数<code>size</code>指定的长度.
     * </p>
     * 
     * <pre>
     * StringUtils.leftPad(null, *, *)     = null
     * StringUtils.leftPad(&quot;&quot;, 3, 'z')     = &quot;zzz&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 3, 'z')  = &quot;bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 5, 'z')  = &quot;zzbat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 1, 'z')  = &quot;bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, -1, 'z') = &quot;bat&quot;
     * </pre>
     * 
     * @param string 要添加字符的字符串, 可能为null
     * @param size 添加字符后字符串的长度
     * @param padChar 添加的字符
     * @return 添加字符后的字符串,如果没添加就返回原字符串, <code>null</code>如果输入的字符串为null
     * @since 2.0
     */
    public static String leftPad(String string, int size, char padChar) {
        return org.apache.commons.lang.StringUtils.leftPad(string, size, padChar);
    }
    
    /**
     * <p>
     * 为字符串str的左边添加指定的字符串padStr.
     * </p>
     * <p>
     * 添加指定的字符串padStr后的字符串的长度为参数<code>size</code>指定的长度.
     * </p>
     * 
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad(&quot;&quot;, 3, &quot;z&quot;)      = &quot;zzz&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 3, &quot;yz&quot;)  = &quot;bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 5, &quot;yz&quot;)  = &quot;yzbat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 8, &quot;yz&quot;)  = &quot;yzyzybat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 1, &quot;yz&quot;)  = &quot;bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, -1, &quot;yz&quot;) = &quot;bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 5, null)  = &quot;  bat&quot;
     * StringUtils.leftPad(&quot;bat&quot;, 5, &quot;&quot;)    = &quot;  bat&quot;
     * </pre>
     * 
     * @param string 要添加字符串的字符串, 可能为null
     * @param size 添加字符串后字符串的长度
     * @param padStr 要添加的字符串, null或空字符串当作(" ")
     * @return 添加字符串后的字符串,如果没添加就返回原字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String leftPad(String string, int size, String padStr) {
        return org.apache.commons.lang.StringUtils.leftPad(string, size, padStr);
    }
    
    /**
     * <p>
     * 把字符串str放到指定长度<code>size</code>的中间, 原字符串的前后用(' ')进行填充 .
     * <p>
     * <p>
     * 如果size参数的小于字符串str的长度就直接返回字符串str. 字符串参数str为<code>null</code>返回<code>null</code>. size参数为负数当作0.
     * </p>
     * <p>
     * 等同于函数<code>center(str, size, " ")</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.center(null, *)   = null
     * StringUtils.center(&quot;&quot;, 4)     = &quot;    &quot;
     * StringUtils.center(&quot;ab&quot;, -1)  = &quot;ab&quot;
     * StringUtils.center(&quot;ab&quot;, 4)   = &quot; ab &quot;
     * StringUtils.center(&quot;abcd&quot;, 2) = &quot;abcd&quot;
     * StringUtils.center(&quot;a&quot;, 4)    = &quot; a  &quot;
     * </pre>
     * 
     * @param string 需要放在中间的字符串, 可能为null
     * @param size 新的字符串的长度, 负数当作0
     * @return 放在中间后的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String center(String string, int size) {
        return org.apache.commons.lang.StringUtils.center(string, size);
    }
    
    /**
     * <p>
     * 把字符串str放到指定长度<code>size</code>的中间. 原字符串的前后用指定的字符padChar进行填充.
     * </p>
     * <p>
     * 如果size参数的小于字符串str的长度就直接返回字符串str. 字符串参数str为<code>null</code>返回<code>null</code>. size参数为负数当作0.
     * </p>
     * 
     * <pre>
     * StringUtils.center(null, *, *)     = null
     * StringUtils.center(&quot;&quot;, 4, ' ')     = &quot;    &quot;
     * StringUtils.center(&quot;ab&quot;, -1, ' ')  = &quot;ab&quot;
     * StringUtils.center(&quot;ab&quot;, 4, ' ')   = &quot; ab&quot;
     * StringUtils.center(&quot;abcd&quot;, 2, ' ') = &quot;abcd&quot;
     * StringUtils.center(&quot;a&quot;, 4, ' ')    = &quot; a  &quot;
     * StringUtils.center(&quot;a&quot;, 4, 'y')    = &quot;yayy&quot;
     * </pre>
     * 
     * @param string 需要放在中间的字符串, 可能为null
     * @param size 新的字符串的长度, 负数当作0
     * @param padChar 字符串str两边填充的字符
     * @return 放在中间后的字符串, <code>null</code> 如果输入的字符串为null
     * @since 2.0
     */
    public static String center(String string, int size, char padChar) {
        return org.apache.commons.lang.StringUtils.center(string, size, padChar);
    }
    
    /**
     * <p>
     * 把字符串str放到指定长度<code>size</code>的中间. 原字符串的前后用指定的字符串padStr进行填充.
     * </p>
     * <p>
     * 如果size参数的小于字符串str的长度就直接返回字符串str. 字符串参数str为<code>null</code>返回<code>null</code>. size参数为负数当作0.
     * </p>
     * 
     * <pre>
     * StringUtils.center(null, *, *)     = null
     * StringUtils.center(&quot;&quot;, 4, &quot; &quot;)     = &quot;    &quot;
     * StringUtils.center(&quot;ab&quot;, -1, &quot; &quot;)  = &quot;ab&quot;
     * StringUtils.center(&quot;ab&quot;, 4, &quot; &quot;)   = &quot; ab&quot;
     * StringUtils.center(&quot;abcd&quot;, 2, &quot; &quot;) = &quot;abcd&quot;
     * StringUtils.center(&quot;a&quot;, 4, &quot; &quot;)    = &quot; a  &quot;
     * StringUtils.center(&quot;a&quot;, 4, &quot;yz&quot;)   = &quot;yayz&quot;
     * StringUtils.center(&quot;abc&quot;, 7, null) = &quot;  abc  &quot;
     * StringUtils.center(&quot;abc&quot;, 7, &quot;&quot;)   = &quot;  abc  &quot;
     * </pre>
     * 
     * @param string 需要放在中间的字符串, 可能为null
     * @param size 新的字符串的长度, 负数当作0
     * @param padStr 字符串str两边填充的字符串,必须不为null或者是空白
     * @return 放在中间后的字符串, <code>null</code>如果输入的字符串为null
     */
    public static String center(String string, int size, String padStr) {
        return org.apache.commons.lang.StringUtils.center(string, size, padStr);
    }
    
    /**
     * <p>
     * 把字符串中的每个字符转换为大写{@link String#toUpperCase()}.
     * </p>
     * <p>
     * <code>null</code>字符串输入返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.upperCase(null)  = null
     * StringUtils.upperCase(&quot;&quot;)    = &quot;&quot;
     * StringUtils.upperCase(&quot;aBc&quot;) = &quot;ABC&quot;
     * </pre>
     * 
     * @param string 需要转换的字符串, 可能为null
     * @return 大写后的字符串,<code>null</code> 如果输入的字符串为null
     */
    public static String upperCase(String string) {
        return org.apache.commons.lang.StringUtils.upperCase(string);
    }
    
    /**
     * <p>
     * 把字符串中的每个字符转换为小写{@link String#toLowerCase()}.
     * </p>
     * <p>
     * <code>null</code>字符串输入返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase(&quot;&quot;)    = &quot;&quot;
     * StringUtils.lowerCase(&quot;aBc&quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param string 需要转换的字符串, 可能为null
     * @return 小写后的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String lowerCase(String string) {
        return org.apache.commons.lang.StringUtils.lowerCase(string);
    }
    
    /**
     * <p>
     * 把字符串str的第一个字符转换为大写的 {@link Character#toTitleCase(char)}. 其他的字符不会改变.
     * </p>
     * <p>
     * For a word based alorithm, 参考{@link WordUtils#capitalize(String)}. <code>null</code>字符串输入返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize(&quot;&quot;)    = &quot;&quot;
     * StringUtils.capitalize(&quot;cat&quot;) = &quot;Cat&quot;
     * StringUtils.capitalize(&quot;cAt&quot;) = &quot;CAt&quot;
     * </pre>
     * 
     * @param string 需要大写首字符的字符串, 可能为null
     * @return 大写首字符的字符串, <code>null</code> 如果输入的字符串为null
     * @see WordUtils#capitalize(String)
     * @see #uncapitalize(String)
     * @since 2.0
     */
    public static String capitalize(String string) {
        return org.apache.commons.lang.StringUtils.capitalize(string);
    }
    
    /**
     * <p>
     * 把字符串str的第一个字符转换为大写的 {@link Character#toTitleCase(char)}. 其他的字符不会改变.
     * </p>
     * 
     * @param str 需要改变的string, 可能为null
     * @return the 大写后的string, 输入null返回<code>null</code>
     * @deprecated 使用标准的命名 {@link #capitalize(String)}. 方法将在 Commons Lang 3.0. 被移除
     */
    @Deprecated
    public static String capitalise(String str) {
        return capitalize(str);
    }
    
    /**
     * <p>
     * 把字符串str的第一个字符转换为小写的 {@link Character#toLowerCase(char)}. 其他的字符不会改变.
     * </p>
     * <p>
     * For a word based alorithm, 参考{@link WordUtils#uncapitalize(String)}. <code>null</code>字符串输入返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.uncapitalize(null)  = null
     * StringUtils.uncapitalize(&quot;&quot;)    = &quot;&quot;
     * StringUtils.uncapitalize(&quot;Cat&quot;) = &quot;cat&quot;
     * StringUtils.uncapitalize(&quot;CAT&quot;) = &quot;cAT&quot;
     * </pre>
     * 
     * @param string 要小写首字符的字符串, 可能为null
     * @return 小写首字符的字符串, <code>null</code> 如果输入的字符串为null
     * @see WordUtils#uncapitalize(String)
     * @see #capitalize(String)
     * @since 2.0
     */
    public static String uncapitalize(String string) {
        return org.apache.commons.lang.StringUtils.uncapitalize(string);
    }
    
    /**
     * <p>
     * 把字符串str的第一个字符转换为小写的 {@link Character#toLowerCase(char)}. 其他的字符不会改变.
     * </p>
     * 
     * @param str 要小写首字符的字符串, 可能为null
     * @return 小写首字符的字符串, <code>null</code> 如果输入的字符串为null
     * @deprecated 使用标准的命名 {@link #uncapitalize(String)}. 方法将在 Commons Lang 3.0. 被取消
     */
    @Deprecated
    public static String uncapitalise(String str) {
        return uncapitalize(str);
    }
    
    /**
     * <p>
     * 把字符串str中的字符进行大小写的交换, 字符串中大写的转换为小写,小写的转换为大写.
     * </p>
     * <ul>
     * <li>大写的字符转换为小写</li>
     * <li>标题字符转换为小写</li>
     * <li>小写的字符转换为大写</li>
     * </ul>
     * <p>
     * For a word based alorithm, 参考{@link WordUtils#swapCase(String)}. <code>null</code>字符串输入返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.swapCase(null)                 = null
     * StringUtils.swapCase(&quot;&quot;)                   = &quot;&quot;
     * StringUtils.swapCase(&quot;The dog has a BONE&quot;) = &quot;tHE DOG HAS A bone&quot;
     * </pre>
     * <p>
     * 注意: 这个方法在 Lang version 2.0. 有所改变 It no longer performs a word based alorithm. 如果使用的是ASCII, 就没有改变.
     * 在WordUtils中是有效的.
     * </p>
     * 
     * @param string 要进行大小写转换的字符串, 可能为null
     * @return 转换的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String swapCase(String string) {
        return org.apache.commons.lang.StringUtils.swapCase(string);
    }
    
    /**
     * <p>
     * 找出字符串str中匹配字符串sub的总数.
     * </p>
     * <p>
     * <code>null</code>字符串或空白("")的字符串返回<code>0</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.countMatches(null, *)       = 0
     * StringUtils.countMatches(&quot;&quot;, *)         = 0
     * StringUtils.countMatches(&quot;abba&quot;, null)  = 0
     * StringUtils.countMatches(&quot;abba&quot;, &quot;&quot;)    = 0
     * StringUtils.countMatches(&quot;abba&quot;, &quot;a&quot;)   = 2
     * StringUtils.countMatches(&quot;abba&quot;, &quot;ab&quot;)  = 1
     * StringUtils.countMatches(&quot;abba&quot;, &quot;xxx&quot;) = 0
     * </pre>
     * 
     * @param string 需要检查的字符串, 可能为null
     * @param sub 匹配的子字符串, 可能为null
     * @return 字符串str中出现字符串sub的次数, 0 如果输入了<code>null</code>
     */
    public static int countMatches(String string, String sub) {
        return org.apache.commons.lang.StringUtils.countMatches(string, sub);
    }
    
    /**
     * <p>
     * 检查字符串str中是否只包含了unicode的字母.
     * </p>
     * <p>
     * <code>null</code> 返回<code>false</code>. 空白字符串("") 返回<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlpha(null)   = false
     * StringUtils.isAlpha(&quot;&quot;)     = true
     * StringUtils.isAlpha(&quot;  &quot;)   = false
     * StringUtils.isAlpha(&quot;abc&quot;)  = true
     * StringUtils.isAlpha(&quot;ab2c&quot;) = false
     * StringUtils.isAlpha(&quot;ab-c&quot;) = false
     * </pre>
     * 
     * @param string 要检查的字符串, 可能为null
     * @return <code>true</code> 如果只由字符,并且输入不为null
     */
    public static boolean isAlpha(String string) {
        return org.apache.commons.lang.StringUtils.isAlpha(string);
    }
    
    /**
     * <p>
     * 查看字符串中是否只有unicode的字母或者是空白(" ").
     * </p>
     * <p>
     * <code>null</code>返回<code>false</code> 空白字符("")返回<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlphaSpace(null)   = false
     * StringUtils.isAlphaSpace(&quot;&quot;)     = true
     * StringUtils.isAlphaSpace(&quot;  &quot;)   = true
     * StringUtils.isAlphaSpace(&quot;abc&quot;)  = true
     * StringUtils.isAlphaSpace(&quot;ab c&quot;) = true
     * StringUtils.isAlphaSpace(&quot;ab2c&quot;) = false
     * StringUtils.isAlphaSpace(&quot;ab-c&quot;) = false
     * </pre>
     * 
     * @param string 要检查的字符串, 可能为null
     * @return <code>true</code> 如果只由字符或者(" "),而且输入不为null
     */
    public static boolean isAlphaSpace(String string) {
        return org.apache.commons.lang.StringUtils.isAlphaSpace(string);
    }
    
    /**
     * <p>
     * 查看字符串中是否只有unicode的字母或者数字.
     * </p>
     * <p>
     * <code>null</code>返回<code>false</code>. 空的字符串("")返回<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric(&quot;&quot;)     = true
     * StringUtils.isAlphanumeric(&quot;  &quot;)   = false
     * StringUtils.isAlphanumeric(&quot;abc&quot;)  = true
     * StringUtils.isAlphanumeric(&quot;ab c&quot;) = false
     * StringUtils.isAlphanumeric(&quot;ab2c&quot;) = true
     * StringUtils.isAlphanumeric(&quot;ab-c&quot;) = false
     * </pre>
     * 
     * @param string 要检查的字符串, 可能为null
     * @return <code>true</code>只有unicode的字母或者数字,而且输入不为null
     */
    public static boolean isAlphanumeric(String string) {
        return org.apache.commons.lang.StringUtils.isAlphanumeric(string);
    }
    
    /**
     * <p>
     * 查看字符串中是否只有unicode的字母,数字 或者空白(<code>' '</code>).
     * </p>
     * <p>
     * <code>null</code> 返回<code>false</code>. 空的字符串 ("") 返回<code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric(&quot;&quot;)     = true
     * StringUtils.isAlphanumeric(&quot;  &quot;)   = true
     * StringUtils.isAlphanumeric(&quot;abc&quot;)  = true
     * StringUtils.isAlphanumeric(&quot;ab c&quot;) = true
     * StringUtils.isAlphanumeric(&quot;ab2c&quot;) = true
     * StringUtils.isAlphanumeric(&quot;ab-c&quot;) = false
     * </pre>
     * 
     * @param string 要检查的字符串, 可能为null
     * @return <code>true</code> 只有unicode的字母或者数字或者空白 而且输入不为null
     */
    public static boolean isAlphanumericSpace(String string) {
        return org.apache.commons.lang.StringUtils.isAlphanumericSpace(string);
    }
    
    /**
     * <p>
     * 查看字符串中是否只有unicode的数字. 小数点不认为是unicode的数字.
     * </p>
     * <p>
     * <code>null</code> 返回 <code>false</code>. 空的字符串("") 返回 <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric(&quot;&quot;)     = true
     * StringUtils.isNumeric(&quot;  &quot;)   = false
     * StringUtils.isNumeric(&quot;123&quot;)  = true
     * StringUtils.isNumeric(&quot;12 3&quot;) = false
     * StringUtils.isNumeric(&quot;ab2c&quot;) = false
     * StringUtils.isNumeric(&quot;12-3&quot;) = false
     * StringUtils.isNumeric(&quot;12.3&quot;) = false
     * </pre>
     * 
     * @param string 要检查的字符串, 可能为null
     * @return <code>true</code> 只有unicode的数字,而且输入不为null
     */
    public static boolean isNumeric(String string) {
        return org.apache.commons.lang.StringUtils.isNumeric(string);
    }
    
    /**
     * <p>
     * 查看字符串中是否只有unicode的数字 和空白(<code>' '</code>). 小数点不认为是unicode的数字.
     * </p>
     * <p>
     * <code>null</code> 返回 <code>false</code>. 空的字符串("") 返回 <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric(&quot;&quot;)     = true
     * StringUtils.isNumeric(&quot;  &quot;)   = true
     * StringUtils.isNumeric(&quot;123&quot;)  = true
     * StringUtils.isNumeric(&quot;12 3&quot;) = true
     * StringUtils.isNumeric(&quot;ab2c&quot;) = false
     * StringUtils.isNumeric(&quot;12-3&quot;) = false
     * StringUtils.isNumeric(&quot;12.3&quot;) = false
     * </pre>
     * 
     * @param string 要检查的字符串, 可能为null
     * @return <code>true</code> 只有unicode的数字或者空白,而且输入不为null
     */
    public static boolean isNumericSpace(String string) {
        return org.apache.commons.lang.StringUtils.isNumericSpace(string);
    }
    
    /**
     * <p>
     * 查看字符串中是否只有空白字符.
     * </p>
     * <p>
     * <code>null</code> 返回 <code>false</code>. 空的字符串("") 返回 <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isWhitespace(null)   = false
     * StringUtils.isWhitespace(&quot;&quot;)     = true
     * StringUtils.isWhitespace(&quot;  &quot;)   = true
     * StringUtils.isWhitespace(&quot;abc&quot;)  = false
     * StringUtils.isWhitespace(&quot;ab2c&quot;) = false
     * StringUtils.isWhitespace(&quot;ab-c&quot;) = false
     * </pre>
     * 
     * @param string 要检查的字符串, 可能为null
     * @return <code>true</code> 只有空白字符,并且输入不为null
     * @since 2.0
     */
    public static boolean isWhitespace(String string) {
        return org.apache.commons.lang.StringUtils.isWhitespace(string);
    }
    
    /**
     * <p>
     * 但输入的字符串为null时返回(""),其他情况返回字符串本身 .
     * </p>
     * 
     * <pre>
     * StringUtils.defaultString(null)  = &quot;&quot;
     * StringUtils.defaultString(&quot;&quot;)    = &quot;&quot;
     * StringUtils.defaultString(&quot;bat&quot;) = &quot;bat&quot;
     * </pre>
     * 
     * @see String#valueOf(Object)
     * @param string 要检查的字符串, 可能为null
     * @return 返回输入的字符串, 如果输入为<code>null</code>返回""
     */
    public static String defaultString(String string) {
        return org.apache.commons.lang.StringUtils.defaultString(string);
    }
    
    /**
     * <p>
     * 检查输入的字符串是否为<code>null</code>是就返回指定的默认值defaultStr, 不是就返回字符串自己本身
     * </p>
     * 
     * <pre>
     * StringUtils.defaultString(null, &quot;null&quot;)  = &quot;null&quot;
     * StringUtils.defaultString(&quot;&quot;, &quot;null&quot;)    = &quot;&quot;
     * StringUtils.defaultString(&quot;bat&quot;, &quot;null&quot;) = &quot;bat&quot;
     * </pre>
     * 
     * @see String#valueOf(Object)
     * @param string 要检查的字符串, 可能为null
     * @param defaultStr 默认返回的字符串 如果输入为<code>null</code>, 可能为null
     * @return 不为<code>null</code>是返回字符串str本身
     */
    public static String defaultString(String string, String defaultStr) {
        return org.apache.commons.lang.StringUtils.defaultString(string, defaultStr);
    }
    
    /**
     * <p>
     * 字符串中的每个字符进行反序{@link StringBuffer#reverse()}.
     * </p>
     * <p>
     * <A code>null</code>返回<code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.reverse(null)  = null
     * StringUtils.reverse(&quot;&quot;)    = &quot;&quot;
     * StringUtils.reverse(&quot;bat&quot;) = &quot;tab&quot;
     * </pre>
     * 
     * @param string 要进行反序的字符串, 可能为null
     * @return 反序的字符串, <code>null</code> 如果输入的字符串为null
     */
    public static String reverse(String string) {
        return org.apache.commons.lang.StringUtils.reverse(string);
    }
    
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 对一个字符串进行省略缩写. 例如转换 "Now is the time for all good men" 为 "Now is the time for..."
     * </p>
     * <p>
     * 特殊的情况:
     * <ul>
     * <li>如果参数字符串<code>str</code>的长度小于参数<code>maxWidth</code> 返回字符串自己本身.</li>
     * <li>缩写的形式为<code>(substring(str, 0, max-3) + "...")</code>.</li>
     * <li>如果参数<code>maxWidth</code>小于<code>4</code>, 抛出异常 <code>IllegalArgumentException</code>.</li>
     * <li>其他情况会返回一个 <code>maxWidth</code>指定的长度的缩写字符串.</li>
     * </ul>
     * </p>
     * 
     * <pre>
     * StringUtils.abbreviate(null, *)      = null
     * StringUtils.abbreviate(&quot;&quot;, 4)        = &quot;&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 6) = &quot;abc...&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 7) = &quot;abcdefg&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 8) = &quot;abcdefg&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 4) = &quot;a...&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 3) = IllegalArgumentException
     * </pre>
     * 
     * @param string 需要转换的字符串, 可能为null
     * @param maxWidth 返回的字符串的长度, 最少必须为4
     * @return 简略的字符串, <code>null</code> 如果输入的字符串为null
     * @since 2.0
     */
    public static String abbreviate(String string, int maxWidth) {
        return org.apache.commons.lang.StringUtils.abbreviate(string, maxWidth);
    }
    
    /**
     * <p>
     * 对一个字符串进行省略缩写. 例如转换"Now is the time for all good men"为"...is the time for..."
     * </p>
     * <p>
     * 类似<code>abbreviate(String, int)</code>, 但是你可以指定左边字符串的起始位置. 注意 以左边开始的offset的字符在结果中不一定是最左边,
     * 第一个字符可能是(...)但是offset的位置的字符一定会在结果中.
     * <p>
     * 其他情况会返回一个<code>maxWidth</code>指定的长度的缩写字符串.
     * </p>
     * 
     * <pre>
     * StringUtils.abbreviate(null, *, *)                = null
     * StringUtils.abbreviate(&quot;&quot;, 0, 4)                  = &quot;&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, -1, 10) = &quot;abcdefg...&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 0, 10)  = &quot;abcdefg...&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 1, 10)  = &quot;abcdefg...&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 4, 10)  = &quot;abcdefg...&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 5, 10)  = &quot;...fghi...&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 6, 10)  = &quot;...ghij...&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 8, 10)  = &quot;...ijklmno&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 10, 10) = &quot;...ijklmno&quot;
     * StringUtils.abbreviate(&quot;abcdefghijklmno&quot;, 12, 10) = &quot;...ijklmno&quot;
     * StringUtils.abbreviate(&quot;abcdefghij&quot;, 0, 3)        = IllegalArgumentException
     * StringUtils.abbreviate(&quot;abcdefghij&quot;, 5, 6)        = IllegalArgumentException
     * </pre>
     * 
     * @param string 需要转换的字符串, 可能为null
     * @param offset 左边开始的字符的偏移位置
     * @param maxWidth 返回的结果的最大长度, 最少必须为 4
     * @return 缩写的字符串, <code>null</code> 如果输入的字符串为null
     * @since 2.0
     */
    public static String abbreviate(String string, int offset, int maxWidth) {
        return org.apache.commons.lang.StringUtils.abbreviate(string, offset, maxWidth);
    }
    
    /**
     * <p>
     * 比较两个字符串str1,str2 返回str2不同于str1的位置. (返回第二个字符串str2的从开始和 第一个字符串str1不同的位置到末尾的剩余部分.)
     * </p>
     * <p>
     * 例如, <code>difference("i am a machine", "i am a robot") -> "robot"</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.difference(null, null) = null
     * StringUtils.difference(&quot;&quot;, &quot;&quot;) = &quot;&quot;
     * StringUtils.difference(&quot;&quot;, &quot;abc&quot;) = &quot;abc&quot;
     * StringUtils.difference(&quot;abc&quot;, &quot;&quot;) = &quot;&quot;
     * StringUtils.difference(&quot;abc&quot;, &quot;abc&quot;) = &quot;&quot;
     * StringUtils.difference(&quot;ab&quot;, &quot;abxyz&quot;) = &quot;xyz&quot;
     * StringUtils.difference(&quot;abcde&quot;, &quot;abxyz&quot;) = &quot;xyz&quot;
     * StringUtils.difference(&quot;abcde&quot;, &quot;xyz&quot;) = &quot;xyz&quot;
     * </pre>
     * 
     * @param str1 第一个字符串, 可能为null
     * @param str2 第二个字符串, 可能为null
     * @return 返回第二个字符串str2不同于第一个字符串str1的字符串; 两个字符串相等就返回("")
     * @since 2.0
     */
    public static String difference(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.difference(str1, str2);
    }
    
    /**
     * <p>
     * 比较两个字符串str1,str2 返回他们开始不相同时的位置 .
     * </p>
     * <p>
     * 例如, <code>indexOfDifference("i am a machine", "i am a robot") -> 7</code>
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfDifference(null, null) = -1
     * StringUtils.indexOfDifference(&quot;&quot;, &quot;&quot;) = -1
     * StringUtils.indexOfDifference(&quot;&quot;, &quot;abc&quot;) = 0
     * StringUtils.indexOfDifference(&quot;abc&quot;, &quot;&quot;) = 0
     * StringUtils.indexOfDifference(&quot;abc&quot;, &quot;abc&quot;) = -1
     * StringUtils.indexOfDifference(&quot;ab&quot;, &quot;abxyz&quot;) = 2
     * StringUtils.indexOfDifference(&quot;abcde&quot;, &quot;abxyz&quot;) = 2
     * StringUtils.indexOfDifference(&quot;abcde&quot;, &quot;xyz&quot;) = 0
     * </pre>
     * 
     * @param str1 第一个字符串,可能为null
     * @param str2 第二个字符串,可能为null
     * @return 字符串 str2 和字符串 str1 开始不同的位置; -1 表示他们相等
     * @since 2.0
     */
    public static int indexOfDifference(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.indexOfDifference(str1, str2);
    }
    
    /**
     * <p>
     * 计算两字符串之间的Levenshtein-Distance.
     * </p>
     * <p>
     * 是字符串s要变成字符串t需要改动的字符的个,一次改动一个字符 (删除,插入或者置换).
     * </p>
     * <p>
     * Levenshtein distance 的算法规则可以参考 <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a>
     * </p>
     * 
     * <pre>
     * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance(&quot;&quot;,&quot;&quot;)               = 0
     * StringUtils.getLevenshteinDistance(&quot;&quot;,&quot;a&quot;)              = 1
     * StringUtils.getLevenshteinDistance(&quot;aaapppp&quot;, &quot;&quot;)       = 7
     * StringUtils.getLevenshteinDistance(&quot;frog&quot;, &quot;fog&quot;)       = 1
     * StringUtils.getLevenshteinDistance(&quot;fly&quot;, &quot;ant&quot;)        = 3
     * StringUtils.getLevenshteinDistance(&quot;elephant&quot;, &quot;hippo&quot;) = 7
     * StringUtils.getLevenshteinDistance(&quot;hippo&quot;, &quot;elephant&quot;) = 7
     * StringUtils.getLevenshteinDistance(&quot;hippo&quot;, &quot;zzzzzzzz&quot;) = 8
     * StringUtils.getLevenshteinDistance(&quot;hello&quot;, &quot;hallo&quot;)    = 1
     * </pre>
     * 
     * @param s 第一个字符串, 不能为null
     * @param t 第二个字符串, 不能为null
     * @return 返回要改动的字符的个数
     */
    public static int getLevenshteinDistance(String s, String t) {
        return org.apache.commons.lang.StringUtils.getLevenshteinDistance(s, t);
    }
    
    /**
     * <p>
     * 根据正则表达式过滤字符
     * </p>
     * 
     * @param string 字符串
     * @param regex 正则表达式
     * @return 过滤后的字符
     */
    public static String filterSpecialChar(String string, String regex) {
        if (string == null) {
            return null;
        }
        
        Pattern objPattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher objMatcher = objPattern.matcher(string);
        return objMatcher.replaceAll("").trim();
    }
    
    /**
     * 根据正则表达式,判断是否匹配字符
     * 
     * @param string 字符串
     * @param regex 正则表达式
     * @return boolean
     */
    public static boolean isMatch(String string, String regex) {
        if (string == null || regex == null || regex.trim().length() == 0) {
            return false;
        }
        Pattern objPattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher objMatcher = objPattern.matcher(string);
        return objMatcher.find();
    }
    
    /**
     * 从源字符串(source)中取前len个字符，如果len小于源字符串的长度，则在返回值后加上字符串concat。
     * 
     * <pre>
     * String strSource = &quot;a中华人民共和国&quot;;
     * ProjectUtils.getFixedLengthString(strSource,10,&quot;...&quot;)= &quot;a中华人民共...&quot;
     * ProjectUtils.getFixedLengthString(strSource,10,null)= &quot;a中华人民共&quot;
     * ProjectUtils.getFixedLengthString(strSource,10,&quot;&quot;)=&quot;a中华人民共&quot;
     * ProjectUtils.getFixedLengthString(strSource,10,&quot; &quot;)=&quot;a中华人民共&quot;
     * ProjectUtils.getFixedLengthString(strSource,-1,&quot;&quot;)=&quot;&quot;
     * ProjectUtils.getFixedLengthString(strSource,0,&quot;&quot;)=&quot;&quot;
     * ProjectUtils.getFixedLengthString(strSource,0,null)=&quot;&quot;
     * ProjectUtils.getFixedLengthString(strSource,-1,null)=&quot;&quot;
     * </pre>
     * 
     * @param source 源字符串信息
     * @param len 需要截取的字符个数
     * @param concat 需要填补的字符串
     * @return 返回获取的字符串信息。
     * @throws UnsupportedEncodingException 不支持GBK编码时抛出
     */
    public static String getFixedLengthString(String source, int len, String concat)
        throws UnsupportedEncodingException {
        String strRet = "";
        if (source == null || concat == null) {
            return "";
        }
        if (len <= 0) {
            return strRet;
        }
        for (int i = 1; i <= source.length(); i++) {
            strRet = source.substring(0, i);
            if (strRet.getBytes("GBK").length >= len) {
                break;
            }
        }
        if (strRet.getBytes("GBK").length < source.getBytes("GBK").length) {
            strRet += concat;
        }
        return strRet;
    }
    
    /**
     * 取一个字符串的长度（一个汉字算两个字节）
     * 
     * <pre>
     * ProjectUtils.getStringCharsCount(&quot;abc&quot;) = 3；
     * ProjectUtils.getStringCharsCount(&quot;abc好&quot;) = 5；
     * ProjectUtils.getStringCharsCount(&quot;好很好极好&quot;) = 10；
     * </pre>
     * 
     * @param pstrSrc 需要计算长度的字符串
     * @return 字符串长度
     */
    public static int getStringCharsCount(String pstrSrc) {
        int iSize = pstrSrc.length();
        int iHZCnt = 0;
        for (int i = 0; i < iSize; i++) {
            if (pstrSrc.charAt(i) < 0 || pstrSrc.charAt(i) > 255) {
                iHZCnt++;
            }
        }
        iSize += iHZCnt;
        return iSize;
    }
    
    /**
     * 长整型类型数组转化为数组的方法
     * 
     * @param lArray 长整型类型数组
     * @return 字符串
     */
    public static String toString(long[] lArray) {
        if (lArray == null) {
            return "null";
        }
        if (lArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBufer = new StringBuffer();
        sbBufer.append('[');
        sbBufer.append(lArray[0]);
        
        for (int i = 1; i < lArray.length; i++) {
            sbBufer.append(", ");
            sbBufer.append(lArray[i]);
        }
        
        sbBufer.append("]");
        return sbBufer.toString();
    }
    
    /**
     * 整型类型数组转化为数组的方法
     * 
     * @param iArray 整型类型数组
     * @return String 字符串
     */
    public static String toString(int[] iArray) {
        if (iArray == null) {
            return "null";
        }
        if (iArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append('[');
        sbBuffer.append(iArray[0]);
        
        for (int i = 1; i < iArray.length; i++) {
            sbBuffer.append(", ");
            sbBuffer.append(iArray[i]);
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * 短整型类型数组转化为数组的方法
     * 
     * @param sArray 短整型类型数组
     * @return 字符串
     */
    public static String toString(short[] sArray) {
        if (sArray == null) {
            return "null";
        }
        if (sArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append('[');
        sbBuffer.append(sArray[0]);
        
        for (int i = 1; i < sArray.length; i++) {
            sbBuffer.append(", ");
            sbBuffer.append(sArray[i]);
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * 字符类型数组转化为数组的方法
     * 
     * @param cArray 字符类型数组
     * @return 字符串
     */
    public static String toString(char[] cArray) {
        if (cArray == null) {
            return "null";
        }
        if (cArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append('[');
        sbBuffer.append(cArray[0]);
        
        for (int i = 1; i < cArray.length; i++) {
            sbBuffer.append(", ");
            sbBuffer.append(cArray[i]);
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * byte类型数组转化为数组的方法
     * 
     * @param bArray byte类型数组
     * @return 字符串
     */
    public static String toString(byte[] bArray) {
        if (bArray == null) {
            return "null";
        }
        if (bArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append('[');
        sbBuffer.append(bArray[0]);
        
        for (int i = 1; i < bArray.length; i++) {
            sbBuffer.append(", ");
            sbBuffer.append(bArray[i]);
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * boolean类型数组转化为数组的方法
     * 
     * @param bArray boolean类型数组
     * @return 字符串
     */
    public static String toString(boolean[] bArray) {
        if (bArray == null) {
            return "null";
        }
        if (bArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append('[');
        sbBuffer.append(bArray[0]);
        
        for (int i = 1; i < bArray.length; i++) {
            sbBuffer.append(", ");
            sbBuffer.append(bArray[i]);
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * 浮点类型数组转化为数组的方法
     * 
     * @param fArray 浮点类型数组
     * @return 字符串
     */
    public static String toString(float[] fArray) {
        if (fArray == null) {
            return "null";
        }
        if (fArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append('[');
        sbBuffer.append(fArray[0]);
        
        for (int i = 1; i < fArray.length; i++) {
            sbBuffer.append(", ");
            sbBuffer.append(fArray[i]);
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * 双精度类型数组转化为数组的方法
     * 
     * @param dArray 双精度类型数组
     * @return 字符串
     */
    public static String toString(double[] dArray) {
        if (dArray == null) {
            return "null";
        }
        if (dArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append('[');
        sbBuffer.append(dArray[0]);
        
        for (int i = 1; i < dArray.length; i++) {
            sbBuffer.append(", ");
            sbBuffer.append(dArray[i]);
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * Object类型数组转化为数组的方法
     * 
     * @param objArray Object类型数组
     * @return 字符串
     */
    public static String toString(Object[] objArray) {
        if (objArray == null) {
            return "null";
        }
        if (objArray.length == 0) {
            return "[]";
        }
        
        StringBuffer sbBuffer = new StringBuffer();
        
        for (int i = 0; i < objArray.length; i++) {
            if (i == 0) {
                sbBuffer.append('[');
            } else {
                sbBuffer.append(", ");
            }
            
            sbBuffer.append(String.valueOf(objArray[i]));
        }
        
        sbBuffer.append("]");
        return sbBuffer.toString();
    }
    
    /**
     * 是否包含汉字字符
     * 
     * @param cnStr 指定字符串
     * @return true:是 false:否
     */
    public static boolean isContainChineseCharacter(String cnStr) {
        if (StringUtil.isEmpty(cnStr)) {
            return false;
        }
        char[] chArray = cnStr.toCharArray();
        boolean bIsContainChineseCharacter = false;
        for (int i = 0; i < chArray.length; i++) {
            byte[] bBytes = ("" + chArray[i]).getBytes();
            if (bBytes.length == 2) {
                int[] iInts = new int[2];
                iInts[0] = bBytes[0] & 0xff;
                iInts[1] = bBytes[1] & 0xff;
                if (iInts[0] >= 0x81 && iInts[0] <= 0xFE && iInts[1] >= 0x40 && iInts[1] <= 0xFE) {
                    bIsContainChineseCharacter = true;
                    break;
                }
            }
        }
        return bIsContainChineseCharacter;
    }
    
    /**
     * 得到字符串的长度，一个汉字长度为2
     * 
     * @param str 指定字符串
     * @return int 字符串的长度
     */
    public static int getStringLength(String str) {
        if (StringUtil.isEmpty(str)) {
            return 0;
        }
        int iLen = 0;
        char[] chArs = str.toCharArray();
        for (int i = 0; i < chArs.length; i++) {
            char chTemp = chArs[i];
            String strTemp = String.valueOf(chTemp);
            if (StringUtil.isContainChineseCharacter(strTemp)) {
                iLen += 2;
            } else {
                iLen++;
            }
        }
        return iLen;
    }
    
    /**
     * 替换特殊字符的方法,替换<,>和"号
     * 
     * @param strInput 要替换的字符串
     * @return strOutPut
     */
    public static String replaceIllegalString(String strInput) {
        // 替换<>和"号
        String strOutPut =
            strInput.replaceAll("<", "&lt ").replaceAll(">", "&gt ").replaceAll("\"", "&#34 ").replaceAll("'", "&#39 ");
        return strOutPut;
    }
}
