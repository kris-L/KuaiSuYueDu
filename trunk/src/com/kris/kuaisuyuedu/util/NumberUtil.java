package com.kris.kuaisuyuedu.util;

import java.math.BigDecimal;

public class NumberUtil {
	
	/** 进行加法运算 */
	public static String sAdd(String s1, String s2) {
		
		if (StringUtil.isNullOrEmpty(s1)) s1 = "0";
		
		if (StringUtil.isNullOrEmpty(s2)) s2 = "0";
		
		return new BigDecimal(s1).add(new BigDecimal(s2)).toString();
	}

	/** 进行减法运算 */
	public static String sSub(String s1, String s2) {

		if (StringUtil.isNullOrEmpty(s1)) s1 = "0";
		
		if (StringUtil.isNullOrEmpty(s2)) s2 = "0";
		
		return new BigDecimal(s1).subtract((new BigDecimal(s2))).toString();
	}
	
	/** 进行乘法运算 */
	public static String sMul(String s1, String s2) {
		
		if (StringUtil.isNullOrEmpty(s1)) s1 = "0";
		
		if (StringUtil.isNullOrEmpty(s2)) s2 = "0";
		
		return new BigDecimal(s1).multiply((new BigDecimal(s2))).toString();
	}
	
	public static int sMulint(String s1, String s2) {
		
		if (StringUtil.isNullOrEmpty(s1)) s1 = "0";
		
		if (StringUtil.isNullOrEmpty(s2)) s2 = "0";
		
		return new BigDecimal(s1).multiply((new BigDecimal(s2))).intValue();
	}

	/** 进行除法运算 */
	public static String sDiv(String s1, String s2, int len) {
		
		if (StringUtil.isNullOrEmpty(s1)) s1 = "0";
		
		if (StringUtil.isNullOrEmpty(s2)) s2 = "0";
		
		return new BigDecimal(s1).divide(new BigDecimal(s2), len, BigDecimal.ROUND_HALF_UP).toString();
	}

	/** 进行加法运算 */
	public static double dAdd(double d1, double d2) {
		
		return new BigDecimal(d1).add(new BigDecimal(d2)).doubleValue();
	}

	/** 进行减法运算 */
	public static double dSub(double d1, double d2) {
		
		return new BigDecimal(d1).subtract(new BigDecimal(d2)).doubleValue();
	}

	/** 进行乘法运算 */
	public static double dMul(double d1, double d2) {
		
		return new BigDecimal(d1).multiply(new BigDecimal(d2)).doubleValue();
	}

	/** 进行除法运算 */
	public static double dDiv(double d1, double d2, int len) {
		
		return new BigDecimal(d1).divide(new BigDecimal(d2), len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 *  比较大小
	 *  return 1:s1>s2 0:s1=s2 -1:s1<s2
	 *  */
	public static int compare(String s1, String s2) {
		
		return new BigDecimal(s1).compareTo(new BigDecimal(s2));
	}
	
	public static void main(String[] args) {
		
		System.out.println(NumberUtil.sDiv("25.53", "2", 2));
	}
}
