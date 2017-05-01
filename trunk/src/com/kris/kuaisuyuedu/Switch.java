package com.kris.kuaisuyuedu;

import com.kris.kuaisuyuedu.BuildConfig;

public interface Switch {

	/**
	 * 调试信息,发布时设置为false
	 */
	public static final boolean LOG = BuildConfig.DEBUG;

	/**
	 * 捕获全局异常，发布时设置为true
	 */
	public static final boolean CRASH = !BuildConfig.DEBUG;
	
}
