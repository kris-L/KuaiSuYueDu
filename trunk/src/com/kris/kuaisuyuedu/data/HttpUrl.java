package com.kris.kuaisuyuedu.data;

import java.io.File;

/**
 * 与后台数据交互接口类 用于存放接口URL
 * 
 */
public interface HttpUrl {

	/**
	 * 后台服务端器
	 */
//	 String IP = "192.168.1.75"; // 刘杰电脑
//	 String PORT = "8080";
	
//	 String IP = "192.168.1.92"; // 志堋电脑
//	 String PORT = "80";

//     String IP = "192.168.1.205"; // 内网
//     String PORT = "9999";
 
	String HTTP_AGREEMENT = "http:"; 
	String IP = "test.chepaipai.com.cn";  // 外网测试环境
	String PORT = "9999";
	 
	
//	String HTTP_AGREEMENT = "https:"; 
//	String IP = "api.kspinche.com"; // 新版外网正式环境
//	String PORT = "9998";

	
	String SERVER_IP = HTTP_AGREEMENT + File.separatorChar + File.separatorChar + IP + ":" + PORT + File.separatorChar;

	/**
	 * http://127.0.0.1:80/iecarpool/app/
	 */

	/**
	 * 
	 */
	String CARPP_SERVER = SERVER_IP + "iecarpool" + File.separatorChar + "app" 
	 + File.separatorChar;


	/**
	 * 车主登记
	 */
	String URL_SERVICEREGISTER = CARPP_SERVER + "serviceRegister";

	/**
	 * 结束服务项目
	 */
	String URL_STOP_SERVICE = CARPP_SERVER + "endService";

	/**
	 * 蓝牙打印信息
	 */
	String URL_SERVICE_LISTING = CARPP_SERVER + "serviceListing";

	/**
	 * 客户订单详情
	 */
	String URL_ORDER_DETAIL = CARPP_SERVER + "selectServiceCar";

	/**
	 * 查询已结账客户列表详情
	 */
	String URL_SETTLE_CARS_RECORDS = CARPP_SERVER + "selectSettleCarsRecords";

	/**
	 * 切换热门症状关键字
	 */
	String URL_SYMPTOM_CHANGE = CARPP_SERVER + "changeSymptom";


	/**
	 * 意见反馈
	 */
	String URL_FEEDBACK = CARPP_SERVER + "userFeedBack";


	/**
	 * 检测店铺代号、验证码是否可用
	 */
	String URL_CHECKSTORENO = CARPP_SERVER + "checkStoreNo";

	/**
	 * 会员注册
	 */
	String URL_REGISTER = CARPP_SERVER + "userRegister";

	/**
	 * 短信验证码
	 */
	String URL_USERGETACTIVATIONCODE = CARPP_SERVER + "userGetActivationCode";

	/** 找回密码时，验证手机并获取短信激活码 */
	String URL_VERIFYPHONEBEFORECODE = CARPP_SERVER + "verifyPhoneBeforeCode";

	/**
	 * 登陆
	 */
	String URL_LOGIN = CARPP_SERVER + "login";
	
	/**
	 * 验证Token是否过期
	 */
	int INDEX_CHECKTOKEN = 1150;
	String URL_CHECKTOKEN = CARPP_SERVER + "checkToken";
	
	/**
	 * 验证Token是否过期
	 */
	int INDEX_GETPERSONALDATA = 1190;
	String URL_GETPERSONALDATA = CARPP_SERVER + "getPersonalData";
	
	/**
	 * 软件升级
	 */
	int INDEX_SOFTAPPUP = 11610;
	String URL_UPDATEVERSION = CARPP_SERVER + "softAppUp";
	

	
	
	
	
	
	
	
	/**
	 * 个人信息
	 */
	String URL_USERDATA = CARPP_SERVER + "userGetUserInfo";

	/**
	 * 修改密码
	 */
	String URL_MODIFYPASSWORD = CARPP_SERVER + "modifyPassword";

	/**
	 * 查询车主信息
	 */
	String URL_OWNERDATA = CARPP_SERVER + "takePicture";

	/**
	 * 会员修改资料（上传头像，修改昵称）
	 */
	String URL_USERMODIFYUSERINFO = CARPP_SERVER + "userModifyUserInfo";

	/**
	 * 会员验证激活码（用于找回密码）
	 */
	String URL_USERVERIFYCODE = CARPP_SERVER + "userVerifyCode";

	/**
	 * 会员手机端重设密码（用于找回密码，需先验证激活码）
	 */
	String URL_USERRESETPASSWORD = CARPP_SERVER + "userFindBackPassword";

	/**
	 * 会员退出
	 */
	String URL_USERLOGOFF = CARPP_SERVER + "userLogoff";

	/**
	 * 上传图片
	 */

	String URL_UPLOADICON = SERVER_IP + "uploadIcon";
	
	/**
	 * 获取城市
	 */
	int INDEX_GETCITYS = 161;
	String URL_GETCITYS = CARPP_SERVER + "getCitys";
	
	/**
	 * 获取打车平台、类型
	 */
	int INDEX_TAKECARTYPE = 331;
	String URL_TAKECARTYPE = CARPP_SERVER + "takeCarType";
	
	/**
	 * 发布订单
	 */
	int INDEX_ORDER = 341;
	String URL_ORDER = CARPP_SERVER + "order";
	
	/**
	 * 获取我的订单列表
	 */
	int INDEX_ORDERLIST = 3101;
	String URL_ORDERLIST = CARPP_SERVER + "orderList";
	
	/**
	 * 操作订单（直接叫车、抢单、取消订单）
	 */
	int INDEX_OPERATEORDER = 351;
	String URL_OPERATEORDER = CARPP_SERVER + "operateOrder";
	
	/**
	 * 获取抢单方支付信息
	 */
	int INDEX_GETROBORDERPAYINFO = 3111;
	String URL_GETROBORDERPAYINFO = CARPP_SERVER + "getRobOrderPayInfo";
	
	/**
	 * 获取抢单方支付信息
	 */
	int INDEX_SETTLEMENT = 381;
	String URL_SETTLEMENT= CARPP_SERVER + "settlement";
	
	/**
	 * 手动推送消息
	 */
	int INDEX_NOTIFYMESSAGE = 411;
	String URL_NOTIFYMESSAGE= CARPP_SERVER + "notifyMessage";
	
	/**
	 * App微信支付结果回调
	 */
	int INDEX_APPWECHATPAYCALLBACK = 371;
	String URL_APPWECHATPAYCALLBACK= CARPP_SERVER + "AppWechatPayCallBack";
	
	/**
	 * 微信支付下单
	 */
	int INDEX_PAY = 361;
	String URL_PAY = CARPP_SERVER + "pay";
	
	/**
	 * 检查拼成订单是否超过时间
	 */
	int INDEX_CHECKORDERTIME = 391;
	String URL_CHECKORDERTIME = CARPP_SERVER + "checkOrderTime";
	
	/**
	 * 获取地址历史
	 */
	int INDEX_ADDRESSHISTORY = 3121;
	String URL_ADDRESSHISTORY = CARPP_SERVER + "addressHistory";
	
	/**
	 * 查询所有银行卡信息
	 */
	int INDEX_GETBANKINFO = 281;
	String URL_GETBANKINFO = CARPP_SERVER + "getBankInfo";
	
	/**
	 * 获取可以解绑的银行卡
	 */
	int INDEX_GETBANKINFOBYEXIT = 2101;
	String URL_GETBANKINFOBYEXIT = CARPP_SERVER + "getBankInfoByExit";
	
	/**
	 * 解绑银行卡
	 */
	int INDEX_DELECTBANKINFO = 2111;
	String URL_DELECTBANKINFO = CARPP_SERVER + "delectBankInfo";
	
	/**
	 * 提现
	 */
	int INDEX_WITHDRAWCASH = 241;
	String URL_WITHDRAWCASH = CARPP_SERVER + "withdrawCash";
	
	/**
	 * 获取银行卡的类型
	 */
	int INDEX_GETBANKINFOEXIST = 291;
	String URL_GETBANKINFOEXIST = CARPP_SERVER + "getBankInfoExist";
	
	/**
	 * 发送信息验证银行卡
	 */
	int INDEX_VALIDATEBANKCARDBYBANK = 261;
	String URL_VALIDATEBANKCARDBYBANK = CARPP_SERVER + "validateBankCardByBank";
	
	/**
	 * 新增绑定银行卡
	 */
	int INDEX_ADDBANKCARDINFO = 271;
	String URL_ADDBANKCARDINFO = CARPP_SERVER + "addBankCardInfo";
	
	/**
	 * 提现记录
	 */
	int INDEX_GETOPERATIONMESSAGE = 251;
	String URL_GETOPERATIONMESSAGE = CARPP_SERVER + "getOperationMessage";
	
	/**
	 * 查询所有银行卡信息
	 */
	int INDEX_CUSTOMSERVICE = 2121;
	String URL_CUSTOMSERVICE = CARPP_SERVER + "customService";
	
	/**
	 * 服务协议
	 */
	int INDEX_GETAGREEMENTURL = 2131;
	String URL_GETAGREEMENTURL = CARPP_SERVER + "getAgreementURL";
	
}
