package com.kris.kuaisuyuedu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
	/** 判断是否为手机号码
	 * return true：是  false：否 */
	public static boolean isMobileNO(String mobiles) {
		if (mobiles == null || !(mobiles.length()>0)) {
			return false;
		}

		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static int checkPwdSecurity(String pwd)  
	{
		int passwordStrength = 0;
	    String regex_special_character="[`~!@#$%^&*()+=|{}':;',//[//]-_.<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	    Pattern pattern_digit = Pattern.compile("[0-9]+");
	    Matcher matcher_digit = pattern_digit.matcher(pwd);
	    if (matcher_digit.find()) {
	    	passwordStrength++;
	    }
	    Pattern pattern_lowercase = Pattern.compile("[a-z]+");
	    Matcher matcher_lowercase = pattern_lowercase.matcher(pwd);
	    if (matcher_lowercase.find()) {
	    	passwordStrength++;
	    }
	  
	    Pattern pattern_capital = Pattern.compile("[A-Z]+");
	    Matcher matcher_capital = pattern_capital.matcher(pwd);
	    if (matcher_capital.find()) {
	    	passwordStrength++;
	    }
	    
	    Pattern pattern_special_character = Pattern.compile(regex_special_character);
	    Matcher matcher_special_character = pattern_special_character.matcher(pwd);
	    if (matcher_special_character.find()) {
	    	passwordStrength++;
	    }
	    return passwordStrength;  
	}
	
}
