package com.acjava.mytool.core.util;

import com.acjava.mytool.core.util.collection.ArrayUtil;
import com.acjava.mytool.core.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: String工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class StringUtil {

    /**
     * 字符串常量：空格符 {@code " "}
     */
    public static final String SPACE = " ";

    /**
     * 字符串常量：空串 {@code ""}
     */
    public static final String EMPTY = "";

    /**
     * 字符串常量：回车符 {@code "\r"} <br>
     * 解释：该字符常用于表示 Linux 系统和 MacOS 系统下的文本换行
     */
    public static final String CR = "\r";

    /**
     * 字符串常量：换行符 {@code "\n"}
     */
    public static final String LF = "\n";

    /**
     * 字符串常量：Windows 换行 {@code "\r\n"} <br>
     * 解释：该字符串常用于表示 Windows 系统下的文本换行
     */
    public static final String CRLF = "\r\n";

    /**
     * 字符串常量：制表符 {@code "\t"}
     */
    public static final String TAB = "	";

    /**
     * <p>字符串是否为空，空的定义如下：</p>
     * <ol>
     *     <li>{@code null}</li>
     *     <li>空字符串：{@code ""}</li>
     * </ol>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtil.isEmpty(null)     // true}</li>
     *     <li>{@code StringUtil.isEmpty("")       // true}</li>
     *     <li>{@code StringUtil.isEmpty(" \t\n")  // false}</li>
     *     <li>{@code StringUtil.isEmpty("abc")    // false}</li>
     * </ul>
     *
     * <p>注意：该方法与 {@link #isBlank(String)} 的区别是：该方法不校验空白字符。</p>
     * <p>建议：</p>
     * <ul>
     *     <li>该方法建议用于工具类或任何可以预期的方法参数的校验中。</li>
     * </ul>
     *
     * @param str 被检测的字符串
     * @return 是否为空
     * @see #isBlank(String)
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * <p>字符串是否为空白，空白的定义如下：</p>
     * <ol>
     *     <li>{@code null}</li>
     *     <li>空字符串：{@code ""}</li>
     *     <li>空格、全角空格、制表符、换行符，等不可见字符</li>
     * </ol>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtil.isBlank(null)     // true}</li>
     *     <li>{@code StringUtil.isBlank("")       // true}</li>
     *     <li>{@code StringUtil.isBlank(" \t\n")  // true}</li>
     *     <li>{@code StringUtil.isBlank("abc")    // false}</li>
     * </ul>
     *
     * <p>注意：该方法与 {@link #isEmpty(String)} 的区别是：
     * 该方法会校验空白字符，且性能相对于 {@link #isEmpty(String)} 略慢。</p>
     * <br>
     *
     * <p>建议：</p>
     * <ul>
     *     <li>该方法建议仅对于客户端（或第三方接口）传入的参数使用该方法。</li>
     * </ul>
     *
     * @param str 被检测的字符串
     * @return 若为空白，则返回 true
     * @see #isEmpty(String)
     */
    public static boolean isBlank(String str) {
        if (isEmpty(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            // 只要有一个非空字符即可认为是非空字符串
            if (!isBlankChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * <p>根据 {@link String#toUpperCase()} 将字符串转换为大写</p>
     *
     * <pre>
     * StringUtil.toUpperCase(null)  = null
     * StringUtil.toUpperCase("")    = ""
     * StringUtil.toUpperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 小写的字符串，可能为null
     * @return 大写的String, 如果原字串为{@code null}，则返回{@code null}
     */
    public static String toUpperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    /**
     * <p>根据 {@link String#toLowerCase()} 将字符串转换为小写</p>
     *
     * <pre>
     * StringUtil.toLowerCase(null)  = null
     * StringUtil.toLowerCase("")    = ""
     * StringUtil.toLowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 大写的字符串，可能为null
     * @return 小写的String, 如果原字串为{@code null}，则返回{@code null}
     */
    public static String toLowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是{@code null}，依然返回{@code null}。
     *
     * <p>
     * 注意，和{@link String#trim()}不同，此方法使用{@link StringUtil#isBlankChar(int)} 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim(&quot;&quot;)            = &quot;&quot;
     * StringUtil.trim(&quot;     &quot;)       = &quot;&quot;
     * StringUtil.trim(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtil.trim(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 除去头尾空白的字符串，如果原字串为{@code null}，则返回{@code null}
     */
    public static String trim(String str) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        int start = 0;
        int end = len;
        // 扫描字符串头部
        while ((start < end) && isBlankChar(str.charAt(start))) {
            start ++;
        }
        // 扫描字符串尾部
        while ((start < end) && isBlankChar(str.charAt(end - 1))) {
            end --;
        }
        if ((start > 0) || (end < len)) {
            return str.substring(start, end);
        }
        return str;
    }

    /**
     * 以 joinStr 为分隔符将字符串数组拼接成一个字符串<br>
     *
     * <pre>
     * String[] arr1 = {"abc", "de", "fghij"};
     * StringUtil.join(arr1, null)        = abcdefghij
     * StringUtil.join(arr1, "")          = abcdefghij
     * </pre>
     * @param strArr 字符串数组
     * @param joinStr 分隔符
     * @return 连接后的字符串
     */
    public static String join(String[] strArr, String joinStr) {
        if (ArrayUtil.isEmpty(strArr)) {
            return null;
        }
        if (joinStr == null) {
            joinStr = EMPTY;
        }
        return Arrays.stream(strArr).collect(Collectors.joining(joinStr));
//        StringBuilder sb = new StringBuilder();
//        boolean isFirst = true;
//        for (int i = 0; i < strArr.length; i++) {
//            if (isFirst) {
//                isFirst = false;
//            } else {
//                sb.append(joinStr);
//            }
//
//            sb.append(strArr[i]);
//        }
//        return sb.toString();
    }

    public static String join(String[] strArr) {
       return join(strArr,EMPTY);
    }

    /**
     * 以 joinStr 为分隔符将字符串List拼接成一个字符串<br>
     *
     * <pre>
     * List<String> arr1 = {"abc", "de", "fghij"};
     * StringUtil.join(arr1, null)        = abcdefghij
     * StringUtil.join(arr1, "")          = abcdefghij
     * </pre>
     *
     * @param strList 字符串List
     * @param joinStr 分隔符
     * @return 连接后的字符串
     */
    public static String join(List<String> strList, String joinStr) {
        if (CollectionUtil.isEmpty(strList)) {
            return null;
        }
        if (joinStr == null) {
            joinStr = EMPTY;
        }
        return strList.stream().collect(Collectors.joining(joinStr));
//        StringBuilder sb = new StringBuilder();
//        boolean isFirst = true;
//        for (int i = 0; i < strList.size(); i++) {
//            if (isFirst) {
//                isFirst = false;
//            } else {
//                sb.append(joinStr);
//            }
//
//            sb.append(strList.get(i));
//        }
//        return sb.toString();
    }

    /**
     * 分割字符串<br>
     *
     * 当regex为
     *
     * @param str 字符串
     * @param regex 如果为null或“”，则分割效果是：步长为1的分片
     * @return 分割后的字符串List
     * @see String#split(String)
     */
    public static List<String> split(String str, String regex) {
        if (isEmpty(str)) {
            return new ArrayList<>();
        }
        if (regex == null) {
            regex = EMPTY;
        }
        String[] splitStr = str.split(regex);
        return Arrays.asList(splitStr);
    }

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     */
    private static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a';
    }
}
