package com.kris.kuaisuyuedu.data;

public interface Flag {
	String KEY_MESSAGE = "message";
	String KEY_EXTRAS = "extras";
	String SELECT_PLACE = "placeStr";
	/** 所选点的纬度 */
	String SELECT_LATITUDE = "latitude";
	/** 所选点的经度 */
	String SELECT_LONGITUDE = "longitude";
	/** 所选点的AOI点 */
	String SELECT_NAME = "aoi_name";
	/** 所选点的地址 */
	String SELECT_ADDRESS = "address";
	
	/* 直接叫车 */
	String ORDER_TYPE = "ORDER_TYPE";
	
	/* webview 头 */
	String WEBTITLETEXT = "webTitleText";
	
	/* webview 头 */
	String WEB_URLSTR = "urlStr";
	
    //钱包	
	String WITHDRAWCASHACTOIN = "withdrawCashActoin";
	String BALANCEPRICE = "balancePrice";
	String BANKID = "bankId";
	String BANKNAME = "bankName";
	String BANKCARDNAME = "bankCardName";
	String BANKCARDNUM = "bankCardNum";
	String ADDBANKTYPE = "AddBankType";
	
	//协议
	String AGREEMENTTYPE = "agreementType";
	String AGREEMENTURL = "agreementUrl";
	
}
