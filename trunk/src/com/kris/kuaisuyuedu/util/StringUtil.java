package com.kris.kuaisuyuedu.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;
import android.util.Log;

public class StringUtil {

	private static final String TAG = "StringUtil";

	/**
	 * 换行符
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * 判断字符串是否为空
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isNullOrEmpty(String text) {
		return (text == null) || (text.trim().length() < 1);
	}

	/**
	 * @param param
	 * @return
	 */
	public static String print(Object obj, Object id, Object param) {
		if (obj == null) {
			return "";
		}
		if (param == null) {
			return id == null ? "" : id.toString();
		} else {
			return param.toString();
		}
	}

	/**
	 * @param list
	 * @return
	 */
	public static int size(List<?> list) {
		if (list == null) {
			return 0;
		} else {
			return list.size();
		}
	}

	/**
	 * 过滤HTML标签，取出文本内容
	 * 
	 * @param inputString
	 * @return
	 */
	public static String filterHtmlTag(String inputString) {
		String htmlStr = inputString; // 含HTML标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{�?script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{�?style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤HTML标签

			textStr = htmlStr;

		} catch (Exception e) {
		}

		return textStr;// 返回文本字符串
	}

	/**
	 * 特殊字符串过滤
	 * 
	 * @return
	 */
	public static String StringFilter(String str) {
		String regEx = "[`~!！@#$%^&*()+=|{}':;',\"\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：_”“’。，、？-]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		Log.i(TAG, "StringFilter:" + m.replaceAll("").trim());
		return m.replaceAll("").trim();
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		Log.i(TAG, "replaceBlank:" + dest);
		return dest;
	}

	public static boolean isSpecialString(String str) {
		return TextUtils.isEmpty(replaceBlank(StringFilter(str)));
	}

	public static String stringFilter(String str) {
		String regEx = "[a-zA-Z0-9`~!！@#$%^&*()+=|{}':;',\"\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：_”“’。，、？-]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static boolean isSpecialstring(String str) {
		return TextUtils.isEmpty(replaceBlank(stringFilter(str)));
	}
	
	/**把小写字母换成大写*/
	public static String exChangeCapital(String str){  
	    StringBuffer sb = new StringBuffer();  
	    if(str!=null){  
	        for(int i=0;i<str.length();i++){  
	            char c = str.charAt(i);  
	            if(Character.isLowerCase(c)){  
	                sb.append(Character.toUpperCase(c));   
	            } else{
	            	sb.append(c);
	            }
	        }  
	    }  
	    return sb.toString();  
	} 
	
	/**计算字符串中中文的数量*/
	public static int countChineseNum(String str){
		int num = 0;
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
			   //Java判断一个字符串是否有中文是利用Unicode编码来判断，
			   // 因为中文的编码区间为：0x4e00--0x9fbb
				num++;
			}
		}
		return num;
	}
	
	
}
