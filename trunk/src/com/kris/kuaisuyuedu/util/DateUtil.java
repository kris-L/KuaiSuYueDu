package com.kris.kuaisuyuedu.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String HH_mm = "HH:mm";
	public static final String MM_dd_HH_mm = "MM-dd HH:mm";

	/** 锁对象 */
	private static final Object lockObj = new Object();

	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (tl == null) {
			synchronized (lockObj) {
				
				tl = sdfMap.get(pattern);
				if (tl == null) {
					// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
//					System.out.println("put new sdf of pattern " + pattern + " to map");
					// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
					// SimpleDateFormat
					tl = new ThreadLocal<SimpleDateFormat>() {

						@Override
						protected SimpleDateFormat initialValue() {
//							System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
			}
		}

		return tl.get();
	}

	/**
	 * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return getSdf(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern) throws ParseException {
		return getSdf(pattern).parse(dateStr);
	}

	public static long getCurTime() {

		return System.currentTimeMillis();
	}

	/**
	 * 根据时间获取本周第一天
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getWeekStartDate(Date date, String format) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		if (day_of_week == 1) {
			c.add(Calendar.DATE, -6);
		} else {
			c.add(Calendar.DATE, 2 - day_of_week);
		}
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return new SimpleDateFormat(format).format(c.getTime());
	}

	/**
	 * 根据时间获取该月第一天
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getMothFirstDay(Date date, String format) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号,当前日期既为本月第一天
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return new SimpleDateFormat(format).format(c.getTime());
	}

	/**
	 * 根据时间获取该年第一天
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getYearFirstDay(Date date, String format) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, 1);
		return new SimpleDateFormat(format).format(c.getTime());
	}

	/**
	 * 根据时间取前后X天
	 * 
	 * @param date
	 * @param day
	 * @param format
	 * @return
	 */
	public static String beforNumberDay(Date date, int day, String format) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return new SimpleDateFormat(format).format(c.getTime());
	}

	/**
	 * 获取某天开始
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDayBegin(Date date, String format) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return new SimpleDateFormat(format).format(c.getTime());
	}

	/**
	 * 获取某天结束
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDayEnd(Date date, String format) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return new SimpleDateFormat(format).format(c.getTime());
	}

	/**
	 * date转成中午格式(二零一六年八月十八日)
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2c(Date time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String str = sdf.format(time);
		String[] chinese = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String newStr = "";
		int length = str.length();
		for (int i = 0; i < length; i++) {

			boolean flag = false;

			if (checkNumber(str.charAt(i) + "")) {

				if (i == 5 || i == 8) {

					if (!(str.charAt(i) + "").equals("0"))
						flag = true;
					else
						continue;
				}

				if (flag) {

					if (i == 8 && (str.charAt(i) + "").compareTo("1") > 0)
						newStr += chinese[Integer.parseInt(str.charAt(i) + "")];
					newStr += "十";
				} else {

					newStr += chinese[Integer.parseInt(str.charAt(i) + "")];
				}
			} else {

				newStr += str.charAt(i);
			}
		}

		return newStr;
	}

	public static boolean checkNumber(String value) {

		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return value.matches(regex);
	}

	public static String getWeekOfDate(Date date) {

		String[] weekDayName = { "星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int intWeek = c.get(Calendar.DAY_OF_WEEK) - 1;

		return weekDayName[intWeek];
	}

	public static String getZWeekOfDate(Date date) {

		String[] weekDayName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int intWeek = c.get(Calendar.DAY_OF_WEEK) - 1;

		return weekDayName[intWeek];
	}

	public static class TestSimpleDateFormatThreadSafe extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					this.join(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					System.out.println(this.getName() + ":" + DateUtil.parse("2016-11-25 23:51:25", yyyy_MM_dd_HH_mm_ss));
					System.out.println(this.getName() + ":" + DateUtil.parse("2016-11-25 23:51:25", yyyy_MM_dd_HH_mm));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new TestSimpleDateFormatThreadSafe().start();
		}
	}
}
