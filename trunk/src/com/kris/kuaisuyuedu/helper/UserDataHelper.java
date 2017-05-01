package com.kris.kuaisuyuedu.helper;


import android.R.string;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 * 保存获取用户的相关信息
 */
public class UserDataHelper {

	private static final String TAG = UserDataHelper.class.getSimpleName();

	private volatile static UserDataHelper instance = null;

	private Context context;

	private String token;
	private String icon;
	private String phone;
	private int type;
	private String userName;
	private String mapHimgId;
	/** 消息列表 */
	private String webMessageUrl;
	/** 我的订单列表 */
	private String webOrderListUrl;
	/** 账单列表 */
	private String webBillUrl;
	/** 用户中心 */
	private String webUserCenterUrl;
	/** 头像（圆*/
	private String roundIconUrl;
	
	
	private int currentTab;
	private boolean insufficientMemory;
	private String passwordKey = "CarppKey";

	public UserDataHelper(Context context) {
		this.context = context;
		getUserData();
	}

	public static UserDataHelper getInstance(Context context) {
		if (instance == null) {
			synchronized (UserDataHelper.class) {
				if (instance == null) {
					instance = new UserDataHelper(context);
				}
			}
		}
		return instance;
	}

	public void getUserData() {
		token = SharePreferencesHelper.getInstance(context).getString("token",
				"0");
		phone = SharePreferencesHelper.getInstance(context).getString("phone",
				"");
		userName = SharePreferencesHelper.getInstance(context).getString(
				"userName", "");
		roundIconUrl = SharePreferencesHelper.getInstance(context).getString(
				"roundIconUrl", "");
		
		icon = SharePreferencesHelper.getInstance(context).getString("icon", "");
		mapHimgId = SharePreferencesHelper.getInstance(context).getString("mapHimgId", "");
		webMessageUrl = SharePreferencesHelper.getInstance(context).getString("webMessageUrl", "");
		webOrderListUrl = SharePreferencesHelper.getInstance(context).getString("webOrderListUrl", "");
		webBillUrl = SharePreferencesHelper.getInstance(context).getString("webBillUrl", "");
		webUserCenterUrl = SharePreferencesHelper.getInstance(context).getString("webUserCenterUrl", "");
		
		type = SharePreferencesHelper.getInstance(context).getInteger("type", 0);
	}
	
	public void setUserData(String token,String phone,String icon,String roundIconUrl) {

		if (!TextUtils.isEmpty(token)) {
			setToken(token);
		}
		
		if (!TextUtils.isEmpty(phone)) {
			setPhone(phone);
		}
		
		if (!TextUtils.isEmpty(icon)) {
			setIcon(icon);
		}
		
		if (!TextUtils.isEmpty(roundIconUrl)) {
			setRoundIconUrl(roundIconUrl);
		}
		
		
		setType(type);
	}
	
	
	public void setUserData(String userName,String icon,String mapHimgId,
			String webMessageUrl,String webOrderListUrl,String webBillUrl,String webUserCenterUrl) {

		if (!TextUtils.isEmpty(userName)) {
			setUserName(userName);
		}
		
		if (!TextUtils.isEmpty(icon)) {
			setIcon(icon);
		}
		
		if (!TextUtils.isEmpty(mapHimgId)) {
			setMapHimgId(mapHimgId);
		}
		
		if (!TextUtils.isEmpty(webMessageUrl)) {
			setWebMessageUrl(webMessageUrl);
		}
		
		if (!TextUtils.isEmpty(webOrderListUrl)) {
			setWebOrderListUrl(webOrderListUrl);
		}
		
		if (!TextUtils.isEmpty(webBillUrl)) {
			setWebBillUrl(webBillUrl);
		}
		
		if (!TextUtils.isEmpty(webUserCenterUrl)) {
			setWebUserCenterUrl(webUserCenterUrl);
		}
		
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
		SharePreferencesHelper.getInstance(context).saveString("token",
				token);
		Log.i(TAG, userName + "");
	}

	public String getUserName() {
		Log.i(TAG, "userName:" + userName);
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		SharePreferencesHelper.getInstance(context).saveString("userName",
				userName);
		Log.i(TAG, userName + "");
	}
	
	public String getIcon() {
		Log.i(TAG, "getIcon:" + icon);
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		SharePreferencesHelper.getInstance(context).saveString("icon", icon);
		Log.i(TAG, icon + "");
	}
	
	public String getRoundIconUrl() {
		Log.i(TAG, "getRoundIconUrl:" + roundIconUrl);
		return roundIconUrl;
	}

	public void setRoundIconUrl(String roundIconUrl) {
		this.roundIconUrl = roundIconUrl;
		SharePreferencesHelper.getInstance(context).saveString("roundIconUrl", roundIconUrl);
		Log.i(TAG, roundIconUrl + "");
	}
	
	

	public String getPhone() {
		Log.i(TAG, "phone:" + phone);
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		SharePreferencesHelper.getInstance(context).saveString("phone", phone);
		Log.i(TAG, phone + "");
	}
	
	public int getType() {
		Log.i(TAG, "type:" + type);
		return type;
	}

	public void setType(int type) {
		this.type = type;
		SharePreferencesHelper.getInstance(context).saveInteger("type", type);
	}

	public String getMapHimgId() {
		return mapHimgId;
	}

	public void setMapHimgId(String mapHimgId) {
		this.mapHimgId = mapHimgId;
		SharePreferencesHelper.getInstance(context).saveString("mapHimgId", mapHimgId);
	}

	public String getWebMessageUrl() {
		return webMessageUrl;
	}

	public void setWebMessageUrl(String webMessageUrl) {
		this.webMessageUrl = webMessageUrl;
		SharePreferencesHelper.getInstance(context).saveString("webMessageUrl", webMessageUrl);
	}

	public String getWebOrderListUrl() {
		return webOrderListUrl;
	}

	public void setWebOrderListUrl(String webOrderListUrl) {
		this.webOrderListUrl = webOrderListUrl;
		SharePreferencesHelper.getInstance(context).saveString("webOrderListUrl", webOrderListUrl);
	}

	public String getWebBillUrl() {
		return webBillUrl;
	}

	public void setWebBillUrl(String webBillUrl) {
		this.webBillUrl = webBillUrl;
		SharePreferencesHelper.getInstance(context).saveString("webBillUrl", webBillUrl);
	}

	public String getWebUserCenterUrl() {
		return webUserCenterUrl;
	}

	public void setWebUserCenterUrl(String webUserCenterUrl) {
		this.webUserCenterUrl = webUserCenterUrl;
		SharePreferencesHelper.getInstance(context).saveString("webUserCenterUrl", webUserCenterUrl);
	}

	public int getCurrentTab() {
		currentTab = SharePreferencesHelper.getInstance(context).getInteger("currentTab", currentTab);
		Log.i(TAG, "currentTab:" + currentTab);
		return currentTab;
	}

	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
		SharePreferencesHelper.getInstance(context).saveInteger("currentTab", currentTab);
		Log.i(TAG, "setCurrentTab:" + currentTab);
	}

	public boolean isInsufficientMemory() {
		Log.i(TAG, "insufficientMemory:" + insufficientMemory);
		return insufficientMemory;
	}

	public void setInsufficientMemory(boolean insufficientMemory) {
		this.insufficientMemory = insufficientMemory;
		SharePreferencesHelper.getInstance(context).saveBoolean("insufficientMemory", insufficientMemory);
		Log.i(TAG, insufficientMemory + "");
	}

	@Override
	public String toString() {
		return "UserDataHelper [token=" + token +", icon=" + icon + ", userName=" + userName
				+", phone=" + phone + "]";
	}
	
	public boolean iconIsEmpty() {
		return TextUtils.isEmpty(icon);
	}

	public boolean userNameIsEmpty() {
		return TextUtils.isEmpty(userName);
	}

	public boolean phoneIsEmpty() {
		return TextUtils.isEmpty(phone);
	}

	/**
	 * the user is logged or not
	 * 
	 * @return
	 */
	public boolean isLogin() {
		if (!TextUtils.isEmpty(token) && !phoneIsEmpty()) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * del userdata
	 */
	public void remove() {
		SharePreferencesHelper.getInstance(context).remove("token");
		SharePreferencesHelper.getInstance(context).remove("phone");
		SharePreferencesHelper.getInstance(context).remove("userName");
		SharePreferencesHelper.getInstance(context).remove("icon");
		SharePreferencesHelper.getInstance(context).remove("mapHimgId");
		SharePreferencesHelper.getInstance(context).remove("webMessageUrl");
		SharePreferencesHelper.getInstance(context).remove("webOrderListUrl");
		SharePreferencesHelper.getInstance(context).remove("webBillUrl");
		SharePreferencesHelper.getInstance(context).remove("webUserCenterUrl");
		
		getUserData();
		Log.i(TAG, instance.toString());
	}
}
