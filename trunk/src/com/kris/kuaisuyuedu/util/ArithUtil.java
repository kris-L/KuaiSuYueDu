package com.kris.kuaisuyuedu.util; 
import java.math.BigDecimal;

import android.text.TextUtils;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 */
public class ArithUtil {
	
	private static final int DEF_DIV_SCALE = 10; // 这个类不能实例化

	private ArithUtil() {
		
	}

	/**
	 * 提供精确的加法运算。
	 * @param v1被加数
	 * @param v2加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的加法运算。
	 * @param v1被加数
	 * @param v2加数
	 * @return 两个参数的和
	 */
	public static double add(float v1, double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的加法运算。
	 * @param v1被加数
	 * @param v2加数
	 * @return 两个参数的和
	 */
	public static double add(String v1, String v2) {
		BigDecimal b1,b2;
		if (!TextUtils.isEmpty(v1)) {
			b1 = new BigDecimal(v1);
		}else{
			b1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(v2)) {
			b2 = new BigDecimal(v2);
		}else{
			b2 = new BigDecimal("0");
		}
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的加法运算。
	 * @param v1被加数
	 * @param v2加数
	 * @return 两个参数的和
	 */
	public static String addDouble(String v1, String v2) {
		BigDecimal b1,b2;
		if (!TextUtils.isEmpty(v1)) {
			b1 = new BigDecimal(v1);
		}else{
			b1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(v2)) {
			b2 = new BigDecimal(v2);
		}else{
			b2 = new BigDecimal("0");
		}
		
		return b1.add(b2).toString();
	}
	
	
	/**
	 * 提供精确的减法运算。
	 * @param v1被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算。
	 * @param v1被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(String v1, String v2) {
		BigDecimal b1,b2;
		if (!TextUtils.isEmpty(v1)) {
			b1 = new BigDecimal(v1);
		}else{
			b1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(v2)) {
			b2 = new BigDecimal(v2);
		}else{
			b2 = new BigDecimal("0");
		}
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * @param v1被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(float v1, double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算。
	 * @param v1被减数
	 * @param v2减数
	 * @return 两个参数的差
	 */
	public static String subDouble(String v1, String v2) {
		BigDecimal b1,b2;
		if (!TextUtils.isEmpty(v1)) {
			b1 = new BigDecimal(v1);
		}else{
			b1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(v2)) {
			b2 = new BigDecimal(v2);
		}else{
			b2 = new BigDecimal("0");
		}
		return b1.subtract(b2).toString();
	}
	
	
	/**
	 * 提供精确的乘法运算。
	 * @param v1被乘数
	 * @param v2乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算。
	 * @param v1被乘数
	 * @param v2乘数  
	 * @return 两个参数的积
	 */
	public static double mul(double v1, int v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算。
	 * @param v1被乘数
	 * @param v2乘数  
	 * @return 两个参数的积
	 */
	public static double mul(float v1, int v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算。
	 * @param v1被乘数
	 * @param v2乘数  
	 * @return 两个参数的积
	 */
	public static String mul(String v1, String v2) {
		BigDecimal b1,b2;
		if (!TextUtils.isEmpty(v1)) {
			b1 = new BigDecimal(v1);
		}else{
			b1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(v2)) {
			b2 = new BigDecimal(v2);
		}else{
			b2 = new BigDecimal("0");
		}
		return b1.multiply(b2).toString();
	}


	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * @param v1被除数
	 * @param v2 除数
	 * @param scale表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 判断第一个参数跟后面的参数和是否相等
	 * @param minuend 被减数
	 * @param meiosis1 减数
	 * @param meiosis2减数
	 * @param meiosis3 减数
	 * @return 相等true,反之false
	 */
	public static boolean judgeEqual(String minuend,String meiosis1,String meiosis2,String meiosis3) {
		BigDecimal m1,b1,b2,b3,b4;
		if (!TextUtils.isEmpty(minuend)) {
			m1 = new BigDecimal(minuend);
		}else{
			m1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(meiosis1)) {
			b1 = new BigDecimal(meiosis1);
		}else{
			b1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(meiosis2)) {
			b2 = new BigDecimal(meiosis2);
		}else{
			b2 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(meiosis3)) {
			b3 = new BigDecimal(meiosis3);
		}else{
			b3 = new BigDecimal("0");
		}
		b4 = m1.subtract(b1.add(b2.add(b3)));
		if (b4.toString().equals("0") || b4.toString().equals("0.0")) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断第一个参数跟后面的参数和是否相等
	 * @param minuend 被减数
	 * @param meiosis1 减数
	 * @param meiosis2减数
	 * @param meiosis3 减数
	 * @param meiosis4 减数
	 * @return 相等true,反之false
	 */
	public static boolean judgeEqual(String minuend,String meiosis1,String meiosis2,String meiosis3,
			String meiosis4) {
		BigDecimal m1,b1,b2,b3,b4,r1;
		if (!TextUtils.isEmpty(minuend)) {
			m1 = new BigDecimal(minuend);
		}else{
			m1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(meiosis1)) {
			b1 = new BigDecimal(meiosis1);
		}else{
			b1 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(meiosis2)) {
			b2 = new BigDecimal(meiosis2);
		}else{
			b2 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(meiosis3)) {
			b3 = new BigDecimal(meiosis3);
		}else{
			b3 = new BigDecimal("0");
		}
		if (!TextUtils.isEmpty(meiosis4)) {
			b4 = new BigDecimal(meiosis4);
		}else{
			b4 = new BigDecimal("0");
		}
		
		r1 = m1.subtract(b1.add(b2.add(b3.add(b4))));
		if (r1.toString().equals("0") || r1.toString().equals("0.0")) {
			return true;
		}else{
			return false;
		}
	}
	
	
};