package com.kris.kuaisuyuedu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.SpannableString;

/**
 * @author {XiaOt} 类型转换工具类
 */
@SuppressLint("SimpleDateFormat")
public class DataTypeUtil {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat shortDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");

	public static Double getDouble(Object obj) {
		Double res = 0.0;
		if (obj != null) {
			String doubleString = obj.toString();
			try {
				if (doubleString.indexOf(",") != -1) {
					doubleString = doubleString.replaceAll(",", "");
				}
				res = Double.valueOf(doubleString);
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static String getDoubleString(Object obj) {
		String res = "";
		if (obj != null) {
			String doubleString = obj.toString();
			try {
				if (doubleString.indexOf("E") != -1) {

					java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
					nf.setGroupingUsed(false);
					doubleString = nf.format(obj);

				}
				res = doubleString;
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static Float getFloat(Object obj) {
		Float res = 0.0f;
		if (obj != null) {
			String floatString = obj.toString();
			if ("".equals(floatString)) {
				return res;
			}
			try {
				res = Float.parseFloat(floatString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static Integer getInteger(Object obj) {
		Integer res = 0;
		if (obj != null) {
			try {
				String objStr = obj + "";
				if (objStr.indexOf(".") != -1) {
					objStr = objStr.subSequence(0, objStr.indexOf(".")) + "";
				}
				res = Integer.valueOf(objStr);
			} catch (Exception e) {
			}
		}

		return res;
	}

//	public static Long getLong(Object obj) {
//		Long res = 0L;
//		if (obj != null) {
//			try {
//				res = Long.valueOf(obj + "");
//			} catch (Exception e) {
//			}
//		}
//		return res;
//	}
	public static long getLong(Object obj) {
		long res = 0L;
		if (obj != null) {
			try {
				res = Long.valueOf(obj + "");
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static String getString(Object obj) {
		String res = "";
		if (obj != null) {
			try {
				res = obj + "";
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static SpannableString getSpannableString(Object obj) {
		SpannableString res = null;
		if (obj != null) {
			try {
				res = (SpannableString) obj;
			} catch (Exception e) {
			}
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	public static String getTimeString(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = timeFormat.format(obj);
				} else {

					Date d = new Date(Date.parse(date));
					date = timeFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	public static String getDateStringByTime(long time) {
		String date = "";
		try {
			date = getDateString(new Date(time));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	public static String getShortDateString(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = shortDateFormat.format(obj);
				} else {
					Date d = new Date(Date.parse(date));
					date = shortDateFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	public static String getShortDateString2(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = shortDateFormat2.format(obj);
				} else {
					Date d = new Date(Date.parse(date));
					date = shortDateFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	public static String getDateString(Object obj) {
		String date = getString(obj);
		if (date != null && !date.equals("")) {
			try {
				if (obj instanceof Date) {
					date = dateFormat.format(obj);
				} else {
					date = date.replace('T', ' ');
					Date d = new Date(Date.parse(date));
					date = dateFormat.format(d);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	public static Date getDate(Object obj) {
		Date res = new Date();
		if (obj != null) {
			try {
				if (obj instanceof Date)
					return (Date) obj;
				String dateString = getString(obj);
				dateString = dateString.replace('T', ' ');
				res = dateFormat.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static Date getShortDate(Object obj) {
		Date res = new Date();
		if (obj != null) {
			try {
				if (obj instanceof Date)
					return (Date) obj;
				String dateString = getString(obj);
				dateString = dateString.replace('T', ' ');
				res = shortDateFormat.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static Boolean getBoolean(Object obj) {
		Boolean res = false;
		if (obj != null) {
			try {
				res = Boolean.parseBoolean(obj.toString());
			} catch (Exception e) {

			}
		}
		return res;
	}

	public static Boolean IsEmpty(String v) {
		boolean flag = true;
		if (v != null && !"".equals(v)) {
			flag = false;
		}
		return flag;
	}
}
