package com.kris.kuaisuyuedu.helper;

import java.util.Map; 
import java.util.Set;

import com.kris.kuaisuyuedu.data.Const;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;


public class SharePreferencesHelper {

	/**
	 * SharedPreferences文件名
	 */
	private final String FILE_NAME = Const.APP_SP_NAME;

	/**
	 * mSharedPreferences
	 */
	private static SharedPreferences mSharedPreferences;

	private volatile static SharePreferencesHelper instance = null;

	private SharePreferencesHelper(Context context) {
		mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	}

	public static SharePreferencesHelper getInstance(Context context) {
		if (instance == null) {
			synchronized (SharePreferencesHelper.class) {
				if (instance == null) {
					instance = new SharePreferencesHelper(context);
				}
			}
		}
		return instance;
	}

	/**
	 * 清除SharedPreferences指定文件所有内容
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldWriteableFiles")
	public void clear(Context context, String filename) {
		SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_WORLD_WRITEABLE);
		sp.edit().clear().commit();
	}

	/**
	 * 清除SharedPreferences指定文件所有内容
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldWriteableFiles")
	public void clear(Context context) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_WORLD_WRITEABLE);
		sp.edit().clear().commit();
	}

	/**
	 * 断定指定key的内容是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return mSharedPreferences.contains(key);
	}

	/**
	 * 获取mSharedPreferences所有属性
	 * 
	 * @return
	 */
	public Map<String, ?> getAll() {
		return mSharedPreferences.getAll();
	}

	/**
	 * 清除指定key的内容
	 */
	public void remove(String key) {
		mSharedPreferences.edit().remove(key).commit();
	}

	/**
	 * 保存字符串
	 * 
	 * @param key
	 * @param value
	 */
	public void saveString(String key, String value) {
		mSharedPreferences.edit().putString(key, value).commit();
	}

	/**
	 * 获取字符串
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public String getString(String key, String... defValue) {
		if (defValue.length > 0) {
			return mSharedPreferences.getString(key, defValue[0]);
		} else {
			return mSharedPreferences.getString(key, "");
		}
	}

	/**
	 * 保存布尔值
	 * 
	 * @param key
	 * @param value
	 */
	public void saveBoolean(String key, Boolean value) {
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}

	/**
	 * 获取布尔值
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public Boolean getBoolean(String key, Boolean... defValue) {
		if (defValue.length > 0)
			return mSharedPreferences.getBoolean(key, defValue[0]);
		else
			return mSharedPreferences.getBoolean(key, false);

	}

	/**
	 * 保存整形
	 * 
	 * @param key
	 * @param value
	 */
	public void saveInteger(String key, Integer value) {
		mSharedPreferences.edit().putInt(key, value).commit();
	}

	/**
	 * 保存长整形
	 * 
	 * @param key
	 * @param value
	 */
	public void saveLong(String key, Long value) {
		mSharedPreferences.edit().putLong(key, value).commit();
	}

	/**
	 * 获取整形
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public Integer getInteger(String key, Integer... defValue) {
		if (defValue.length > 0)
			return mSharedPreferences.getInt(key, defValue[0]);
		else
			return mSharedPreferences.getInt(key, 0);

	}

	/**
	 * 获取长整形
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public long getLong(String key, Long... defValue) {
		if (defValue.length > 0)
			return mSharedPreferences.getLong(key, defValue[0]);
		else
			return mSharedPreferences.getLong(key, 0);

	}
	
	/**
	 * 存储String数组
	 * @param key
	 * @param values
	 */
	@SuppressLint("NewApi")
	public void saveStringSet(String key, Set<String> values) {
		mSharedPreferences.edit().putStringSet(key, values).commit();
	}
	
	/**
	 * 获取String数组
	 * @param key
	 * @param defValue
	 * @return
	 */
	@SuppressLint("NewApi")
	public Set<String> getStringSet(String key, Set<String>... defValue){
		return mSharedPreferences.getStringSet(key, defValue[0]);
	}

}
