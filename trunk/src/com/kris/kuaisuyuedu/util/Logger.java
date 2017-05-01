package com.kris.kuaisuyuedu.util;


import com.kris.kuaisuyuedu.Switch;

import android.app.Activity; 
import android.util.Log;


/**
 * 自定义Log管理器
 * 
 * @author Administrator
 * 
 */
public class Logger implements Switch {

	private String tag;
	/**
	 * 用于设置某些输出频繁的log是否能够输出
	 */
	private boolean childTag;

	public boolean isChildTag() {
		return childTag;
	}

	public void setChildTag(boolean childTag) {
		this.childTag = childTag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * default tag
	 * <p>
	 * <b> tag = Logger
	 */
	public Logger() {
		tag = "Logger";
	}

	/**
	 * set tag from Constructor
	 * <p>
	 * <b> tag = you set "tag"
	 * 
	 * @param tag
	 */
	public Logger(String tag) {
		this.tag = tag;
	}

	/**
	 * set tag from Constructor
	 * <p>
	 * <b> tag = clz.getSimpleName();
	 * 
	 * @param clz
	 */
	public Logger(Class<?> clz) {
		this(clz.getSimpleName());
	}

	/**
	 * set tag from Constructor
	 * <p>
	 * <b> tag = activity.getClass().getSimpleName();
	 * 
	 * @param activity
	 */
	public Logger(Activity activity) {
		this(activity.getClass().getSimpleName());
	}

	public void i(String msg) {
		if (LOG) {
			Log.i(tag, msg);
		}
	}

	public void i(double msg) {
		i(String.valueOf(msg));
	}

	public void i(int msg) {
		i(String.valueOf(msg));
	}

	public void i(String title, int msg) {
		i(title + ">>>" + msg);
	}

	public void i(String title, double msg) {
		i(title + ">>>" + msg);
	}

	public void i(String title, String msg) {
		i(title + ">>>" + msg);
	}

	public void i(String title, boolean msg) {
		i(title + ">>>" + msg);
	}



	public void ic(String msg) {
		if (LOG && childTag) {
			Log.i(tag, msg);
		}
	}



}
