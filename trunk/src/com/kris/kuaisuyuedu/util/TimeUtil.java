package com.kris.kuaisuyuedu.util;

//Download by http://www.codefans.net
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	private static long lastClickTime = 0;
	
	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};


	//防止短时重复多次点击按钮
	public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();   
        if ( time - lastClickTime < 500) {   
            return true;   
        }   
        lastClickTime = time;   
        return false;   
    }
	
	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		return getDateYMDHMS(sdate);
	}

	
	//=============================================================================
	
	
	
	public static Date getDateYMDHMS(String time) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}

	public static Date getDateYMD(String time) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}

	public static Date getDateHezhan(String time) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 秒表
	 * 
	 * @return
	 */
	public static final String createdTimeMillis() {
		// java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
		// "yyyy年MM月dd日   HH:mm:ss");
		// java.util.Date date = new java.util.Date(System.currentTimeMillis());
		// String str = sdf.format(date);
		String str = String.valueOf(System.currentTimeMillis() / 1000);
		return str;
	}

	public static final String createdUploadDate() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String str = sdf.format(date);
		return str;
	}

	/**
	 * Date
	 * 
	 * @return
	 */
	public static final String createdTimeDate() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy年MM月dd日   HH:mm:ss");
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String str = sdf.format(date);
		return str;
	}

	public static final String createdSaveTimeDate() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy年MM月dd日HH时mm分ss秒SS毫秒");
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String str = sdf.format(date);
		return str;
	}

	public static final String createdGPSTimeDate() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String str = sdf.format(date);
		return str;
	}

	public static final String createdEventTimeDate() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String str = sdf.format(date);
		return str;
	}

	public static final String chuangTime(String string) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			SimpleDateFormat show = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Date date = sdf.parse(string);

			return show.format(date);

		} catch (ParseException e) {
			return string;
		}

	}

	public static final Date changeDate(String string) {
		// 2012-8-23 9:00:00
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");

			Date date = sdf.parse(string);

			return date;

		} catch (ParseException e) {
			return null;
		}

	}

	public static String converTime(long timestamp) {
		long currentSeconds = System.currentTimeMillis() / 1000;
		long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
		String timeStr = null;
		if (timeGap > 24 * 60 * 60) {// 1天以上
			timeStr = timeGap / (24 * 60 * 60) + "天前";
		} else if (timeGap > 60 * 60) {// 1小时-24小时
			timeStr = timeGap / (60 * 60) + "小时前";
		} else if (timeGap > 60) {// 1分钟-59分钟
			timeStr = timeGap / 60 + "分钟前";
		} else {// 1秒钟-59秒钟
			timeStr = "刚刚";
		}
		return timeStr;
	}

	public static String getStandardTime(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
		Date date = new Date(timestamp * 1000);
		sdf.format(date);
		return sdf.format(date);
	}
}
