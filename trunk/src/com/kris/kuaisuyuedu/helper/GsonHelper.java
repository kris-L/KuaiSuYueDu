package com.kris.kuaisuyuedu.helper;

import java.lang.reflect.Type;   
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kris.kuaisuyuedu.adapter.DateTypeAdapter;

public class GsonHelper {

	/**
	 * 日期时间格式(JSON字符串) "1999-12-31 23:59:59"
	 */
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat df_common = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * GSON对象
	 */
	public static Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateTypeAdapter()).create();

	/**
	 * 组拼JSON字符串
	 * 
	 * @param t
	 *            泛型对象
	 * @return
	 */
	public static <T> String toJson(T obj) {
		Type type = new com.google.gson.reflect.TypeToken<T>() {
		}.getType();
		return gson.toJson(obj, type);
	}

	/**
	 * 解析服务器返回JSON字符串，返回T对象
	 * 
	 * @param <T>
	 *            泛型方法定义
	 * @param json
	 *            返回JSON字符串
	 * @param type
	 *            类型
	 * @return
	 */
	public static <T> T fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}

}
