package com.zhzx.uip.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

	/**
	 * 判断一个字符串Str是否为空 return true if it is supplied with an empty, zero length,
	 * or whitespace-only string. documented
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean isEmptyNull(String str) {
		return "null".equals(str);
	}

	/**
	 * 判断一个数组元素是否为空 return true if it is supplied with an empty, zero length, or
	 * whitespace-only string. documented
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmptyArray(List list) {
		return (list == null) || (list.size() == 0);
	}

	/**
	 * 将String数组转换成Integer数组
	 * 
	 * @param s
	 * @return
	 */
	public static Integer[] convertToIntegerArray(String[] s) {
		Integer[] num = new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
			num[i] = new Integer(s[i]);
		}
		return num;
	}

	/**
	 * 将字符串数组转换成字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String arrayToString(String[] str) {
		if (str == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			sb.append(str[i]);
			sb.append(", ");
		}
		return sb.toString();
	}

	// 判断字符串是否存在于指定的字符串数组中
	public static boolean isExist(String str, String[] array) {
		boolean result = false;
		if (array == null)
			return result;

		for (int i = 0; i < array.length; i++) {
			if (str.equals(array[i]))
				result = true;
		}
		return result;
	}

	/**
	 * 右对齐填充字符
	 * 
	 * @param data
	 * @param length
	 * @param fill
	 * @return
	 */
	public static String rightAlign(String data, int length, String fill) {
		for (int i = data.length(); i < length; i++) {
			data = fill + data;
		}
		return data;
	}

	/**
	 * 左对齐填充字符
	 * 
	 * @param data
	 * @param length
	 * @param fill
	 * @return
	 */
	public static String leftAlign(String data, int length, String fill) {
		for (int i = data.length(); i < length; i++) {
			data = data + fill;
		}
		return data;
	}

	/**
	 * MD5转换
	 * 
	 * @param plainText
	 * 
	 * @return MD5字符串
	 */
	public static String toMD5(String plainText) throws NoSuchAlgorithmException {

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainText.getBytes());
		byte by[] = messageDigest.digest();

		StringBuffer buf = new StringBuffer();
		int val;
		for (int i = 0; i < by.length; i++) {
			val = by[i];
			if (val < 0) {
				val += 256;
			} else if (val < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(val));
		}
		return buf.toString();
	}

	public static boolean isEqualString(String arg0, String arg1) {
		return arg0.trim().equals(arg1.trim());
	}

	/**
	 * String转换成String数组 并对第一个元素赋值
	 * 
	 * @param arg
	 * @return
	 */
	public static String[] stringToStringArray(String arg) {
		String[] strArr = new String[1];
		strArr[0] = arg;
		return strArr;
	}

	/**
	 * 格式化字符串
	 * 
	 * @param arg
	 * @param objects
	 * @return
	 */
	public static String formatterString(String arg, Object... objects) {
		return MessageFormat.format(arg, objects);
	}


	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}
}
