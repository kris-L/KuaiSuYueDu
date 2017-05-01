package com.kris.kuaisuyuedu.helper;

import android.content.Context;

public class LocalDataHelper {
	
	private Context context;
	private boolean reminderConfirmOrder;
	
	private boolean isFirstLogin;
	private boolean isRegister;
	private String  registerCode;
	private String  shoujiInfo;
	private String  userName;
	
	
	private volatile static LocalDataHelper instance = null;
	
	public LocalDataHelper(Context context) {
		this.context = context;
		getLocalData();
	}

	public static LocalDataHelper getInstance(Context context) {
		if (instance == null) {
			synchronized (LocalDataHelper.class) {
				if (instance == null) {
					instance = new LocalDataHelper(context);
				}
			}
		}
		return instance;
	}
	
	public void getLocalData() {
		reminderConfirmOrder = SharePreferencesHelper.getInstance(context).getBoolean("reminderConfirmOrder", true);
		isFirstLogin = SharePreferencesHelper.getInstance(context).getBoolean("isFirstLogin", true);
		isRegister = SharePreferencesHelper.getInstance(context).getBoolean("isRegister", false);
		registerCode = SharePreferencesHelper.getInstance(context).getString("registerCode", "");
		shoujiInfo = SharePreferencesHelper.getInstance(context).getString("shoujiInfo", "");
		userName = SharePreferencesHelper.getInstance(context).getString("userName", "");
	}

	
	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
		SharePreferencesHelper.getInstance(context).saveBoolean("isFirstLogin", isFirstLogin);
	}

	public boolean isReminderConfirmOrder() {
		return reminderConfirmOrder;
	}

	public void setReminderConfirmOrder(boolean reminderConfirmOrder) {
		this.reminderConfirmOrder = reminderConfirmOrder;
		SharePreferencesHelper.getInstance(context).saveBoolean("reminderConfirmOrder", reminderConfirmOrder);
	}

	public boolean isRegister() {
		return isRegister;
	}

	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
		SharePreferencesHelper.getInstance(context).saveBoolean("isRegister", isRegister);
	}
	
	
	

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
		SharePreferencesHelper.getInstance(context).saveString("registerCode", registerCode);
	}

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		SharePreferencesHelper.getInstance(context).saveString("userName", userName);
		this.userName = userName;
	}

	public String getShoujiInfo() {
		return shoujiInfo;
	}

	public void setShoujiInfo(String shoujiInfo) {
		this.shoujiInfo = shoujiInfo;
		SharePreferencesHelper.getInstance(context).saveString("shoujiInfo", shoujiInfo);
	}

	public void remove() {
		SharePreferencesHelper.getInstance(context).remove("reminderConfirmOrder");
		SharePreferencesHelper.getInstance(context).remove("isRegister");
		SharePreferencesHelper.getInstance(context).remove("registerCode");
		SharePreferencesHelper.getInstance(context).remove("shoujiInfo");
		SharePreferencesHelper.getInstance(context).remove("userName");
		getLocalData();
		
	}
	
	

}
