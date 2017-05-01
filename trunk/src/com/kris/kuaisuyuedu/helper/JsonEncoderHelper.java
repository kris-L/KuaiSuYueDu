package com.kris.kuaisuyuedu.helper;

import java.lang.reflect.Array;  
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kris.kuaisuyuedu.util.MD5Tool;
import com.kris.kuaisuyuedu.util.StringUtil;

import android.R.integer;
import android.text.TextUtils;


/**
 * @author {XiaOt} 封装json数据
 */
public class JsonEncoderHelper {
	// private volatile static JsonEncoderHelper instance;
	private static JsonEncoderHelper instance = new JsonEncoderHelper();
	private JSONObject jsonData;

	private JsonEncoderHelper() {
	}

	public static JsonEncoderHelper getInstance() {
		return instance;
	}

	// public static JsonEncoderHelper getInstance() {
	// if (instance == null) {
	// synchronized (JsonEncoderHelper.class) {
	// if (instance == null) {
	// instance = new JsonEncoderHelper();
	// }
	// }
	// }
	// return instance;
	// }

	public String autocompleDatas(String content) {
		jsonData = new JSONObject();

		try {
			jsonData.put("keyword", content);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	public String searchDatas(String content, int page, int pageSize, int cabid, int casid, int ttpid, int order) {
		jsonData = new JSONObject();

		try {
			jsonData.put("keyword", content);
			if (page != -1) {
				jsonData.put("page", page);
			}
			if (pageSize != -1) {
				jsonData.put("pagesize", pageSize);
			}
			if (cabid != -1) {
				jsonData.put("cabid", cabid);
			}
			if (casid != -1) {
				jsonData.put("casid", casid);
			}
			if (ttpid != -1) {
				jsonData.put("ttpid", ttpid);
			}
			if (order != -1) {
				jsonData.put("order", order);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	public String symptomDatas(int cabid, int casid) {
		jsonData = new JSONObject();

		try {
			if (cabid != -1) {
				jsonData.put("cabid", cabid);
			}
			if (casid != -1) {
				jsonData.put("casid", casid);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	public String troubleDatas(long id, String keyWord) {
		jsonData = new JSONObject();

		try {
			jsonData.put("autoid", id);
			jsonData.put("keyword", keyWord);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	public String feedbacDatas(String content) {
		jsonData = new JSONObject();

		try {
			jsonData.put("content", content);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	/**
	 * 获取所有城市列表
	 */
	public String getCitys(String token, int provinceId) {
		jsonData = new JSONObject();
		try {
			
			jsonData.put("token", token);
			if (provinceId != 0) {
				jsonData.put("provinceId", provinceId);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}
	
	/**
	 * 获取打车平台、类型
	 */
	public String takeCarType(String token) {
		jsonData = new JSONObject();
		try {
			
			jsonData.put("token", token);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}
	
	/**
	 *  发布订单
	 */
	public String order(String token,int typeId,int seat,String budget,int departureTimeType,
			String departureTime,String fromLat,String fromLong,String fromName,String fromAddr,
			String toLat,String toLong,String toName,String toAddr) {
		jsonData = new JSONObject();
		try {
			
			jsonData.put("token", token);
			jsonData.put("typeId", typeId);
			jsonData.put("seat", seat);
			jsonData.put("budget", budget);
			jsonData.put("departureTimeType", departureTimeType);
			jsonData.put("departureTime", departureTime);
			jsonData.put("fromLat", fromLat);
			jsonData.put("fromLong", fromLong);
			jsonData.put("fromName", fromName);
			jsonData.put("fromAddr", fromAddr);
			jsonData.put("toLat", toLat);
			jsonData.put("toLong", toLong);
			jsonData.put("toName", toName);
			jsonData.put("toAddr", toAddr);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}
	
	/** 版本升级 */
	public String UpdateVersion(String token, int systype, String version) {
		jsonData = new JSONObject();
		try {
			jsonData.put("token", token);
			jsonData.put("systype", systype);
			jsonData.put("version", version);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	
	
	



	public String upgradeDatas(String version) {
		jsonData = new JSONObject();

		try {
			jsonData.put("systype", 0);
			jsonData.put("version", version);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	/** 检测店铺代号、验证码是否可用 */
	public String checkStoreNo(String storeNo, String phone, String activationCode) {
		jsonData = new JSONObject();

		try {
			jsonData.put("storeNo", storeNo);
			jsonData.put("phone", phone);
			jsonData.put("activationCode", activationCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}


	/** 判断用户是否存在，发送验证码 */
	public String activationCodeDatas(String phone) {
		String ImportantStr= "IEALSGJBEWI654654";
		MD5Tool myMd5 =new MD5Tool(); 
		String md5Str = myMd5.MD5Encrypt(phone+ImportantStr); 
		jsonData = new JSONObject();
		try {
			jsonData.put("phone", phone);
			jsonData.put("key", md5Str);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	/** 登录 */
	public String loginDatas(String phone, String code,String sysType,
			String sysVer,String softVer,String registrId,String alias,String accessIp) {
		jsonData = new JSONObject();

		try {
			jsonData.put("phone", phone);
			jsonData.put("code", code);
			jsonData.put("sysType", sysType);
			jsonData.put("sysVer", sysVer);
			jsonData.put("softVer", softVer);
			jsonData.put("registrId", registrId);
			jsonData.put("alias", alias);
			jsonData.put("accessIp", accessIp);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 验证Token是否过期 */
	public String checkToken(String token, String phone,String registrId,String alias) {
		jsonData = new JSONObject();
		try {
			jsonData.put("token", token);
			jsonData.put("phone", phone);
			jsonData.put("registrId", registrId);
			jsonData.put("alias", alias);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 获取个人资料  */
	public String getPersonalData(String token) {
		jsonData = new JSONObject();
		try {
			jsonData.put("token", token);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	

	/** 用户信息 */
	public String userDatas(String userId, String licenseKey) {
		jsonData = new JSONObject();

		try {
			jsonData.put("userId", userId);
			jsonData.put("licenseKey", licenseKey);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}


	/** 修改密码 */
	public String modifyPasswordDatas(int userId, int loginId, int groupId, String oldPassword, String newPassword) {
		jsonData = new JSONObject();
		try {
			jsonData.put("userId", userId);
			jsonData.put("loginId", loginId);
			if (groupId != 0) {
				jsonData.put("groupId", groupId);
			}
			jsonData.put("oldPassword", oldPassword);
			jsonData.put("newPassword", newPassword);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	

	/** 会员验证激活码（用于找回密码） */
	public String userVerifyCodeDatas(String phone, String activationCode) {
		jsonData = new JSONObject();

		try {
			jsonData.put("phone", phone);
			jsonData.put("activationCode", activationCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	/** 会员手机端重设密码(用于找回密码) */
	public String userResetPasswordDatas(String storeNo, String userName, String phone, String password,
			String activationCode) {
		jsonData = new JSONObject();

		try {
			jsonData.put("storeNo", storeNo);
			jsonData.put("userName", userName);
			jsonData.put("phone", phone);
			jsonData.put("password", password);
			jsonData.put("activationCode", activationCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}


	/** 获取权限码 */
	public String selectPermissionList(int userId, int loginId, int groupId) {
		jsonData = new JSONObject();

		try {
			jsonData.put("userId", userId);
			jsonData.put("loginId", loginId);
			if (groupId != 0) {
				jsonData.put("groupId", groupId);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}




	/** 举报 */
	public String reportDatas(String userId, String licenseKey, String qId, String remark) {
		jsonData = new JSONObject();

		try {
			jsonData.put("cqtId", qId);
			jsonData.put("remark", remark);
			jsonData.put("userId", userId);
			jsonData.put("licenseKey", licenseKey);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}


	/** 意见反馈 */
	public String UserFeedBack(int uid, int loginId, int groupId, String content) {
		jsonData = new JSONObject();

		try {
			jsonData.put("userId", uid);
			jsonData.put("loginId", loginId);
			if (groupId != 0) {
				jsonData.put("groupId", groupId);
			}
			jsonData.put("content", content);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	/** 获取短息模板 */
	public String GetMessageModel(int uid, int loginId, int groupId, int type) {
		jsonData = new JSONObject();

		try {
			jsonData.put("userId", uid);
			jsonData.put("loginId", loginId);
			if (groupId != 0) {
				jsonData.put("groupId", groupId);
			}
			jsonData.put("type", type);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}

	public String checkActivationCode(String phone, String activationCode) {
		jsonData = new JSONObject();

		try {
			jsonData.put("phone", phone);
			jsonData.put("activationCode", activationCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData.toString();

	}

	/** 查询违章省份列表 */
	public String selectProvincesList(int userId, int loginId, int groupId) {
		jsonData = new JSONObject();

		try {
			jsonData.put("userId", userId);
			jsonData.put("loginId", loginId);
			if (groupId != 0) {
				jsonData.put("groupId", groupId);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 查询违章城市列表 */
	public String selectCitysList(int userId, int loginId, int groupId,int provinceId) {
		jsonData = new JSONObject();

		try {
			jsonData.put("userId", userId);
			jsonData.put("loginId", loginId);
			if (groupId != 0) {
				jsonData.put("groupId", groupId);
			}
			jsonData.put("provinceId", provinceId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 获取我的订单列表  */
	public String orderList(String token, int page, int count) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("page", page);
			jsonData.put("count", count);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 操作订单（直接叫车、抢单、取消订单）  */
	public String operateOrder(String token, int orderId, int type,String remark) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("orderId", orderId);
			jsonData.put("type", type);
			jsonData.put("remark", remark);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	
	/** 获取抢单方支付信息）  */
	public String getRobOrderPayInfo(String token, int orderId) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("orderId", orderId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 结算   */
	public String settlement(String token, int orderId,String totalPrice) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("orderId", orderId);
			jsonData.put("totalPrice", totalPrice);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 手动推送消息   */
	public String notifyMessage(String token, int orderId,int type,String reapplyPrice) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("orderId", orderId);
			jsonData.put("type", type);
			if (!TextUtils.isEmpty(reapplyPrice)) {
				jsonData.put("reapplyPrice", reapplyPrice);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	
	/** 获取抢单方支付信息）  */
	public String pay(String token, int orderId,int totalPrices,int payType,
			int usePocket,int payMethod) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("orderId", orderId);
			jsonData.put("totalPrices", totalPrices);
			jsonData.put("payType", payType);
			jsonData.put("usePocket", usePocket);
			jsonData.put("payMethod", payMethod);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/**  App微信支付结果回调 */
	public String AppWechatPayCallBack(String token,
			String recordNumber,int resultCode) {
		jsonData = new JSONObject();
		try {
			jsonData.put("token", token);
			jsonData.put("recordNumber", recordNumber);
			jsonData.put("resultCode", resultCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/**  检查拼成订单是否超过时间  */
	public String checkOrderTime(String token,
			int orderId,int type) {
		jsonData = new JSONObject();
		try {
			jsonData.put("token", token);
			jsonData.put("orderId", orderId);
			jsonData.put("type", type);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/**  获取地址历史  */
	public String addressHistory(String token, int page, int count) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("page", page);
			jsonData.put("count", count);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	
	/** 查询所有银行卡信息   */
	public String getBankInfo(String token) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 获取可以解绑的银行卡   */
	public String getBankInfoByExit(String token) {
		jsonData = new JSONObject();

		try {
			
			jsonData.put("token", token);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 解绑银行卡   */
	public String delectBankInfo(String token,int bankId) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("bankId", bankId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 提现   */
	public String withdrawCash(String token,int bankId,String withdrawPrice,String rmk) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("bankId", bankId);
			jsonData.put("withdrawPrice", withdrawPrice);
			jsonData.put("rmk", rmk);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 获取银行卡的类型   */
	public String getBankInfoExist(String token,String bankCode) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("bankCode", bankCode);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 发送信息验证银行卡   */
	public String validateBankCardByBank(String token,String phone,String ip) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("phone", phone);
			jsonData.put("ip", ip);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}

	/** 新增绑定银行卡  */
	public String addBankCardInfo(String token,String cardholder,String bankCode,
			int bankTypeId,String phone,String validateCode) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("cardholder", cardholder);
			jsonData.put("bankCode", bankCode);
			jsonData.put("bankTypeId", bankTypeId);
			jsonData.put("phone", phone);
			jsonData.put("validateCode", validateCode);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	
	/** 获取提现记录  */
	public String getOperationMessage(String token,int page,int count) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			jsonData.put("page", page);
			jsonData.put("count", count);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 获取客服信息   */
	public String customService(String token) {
		jsonData = new JSONObject();

		try {
			jsonData.put("token", token);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData.toString();
	}
	
	/** 获取协议 */
	public String getAgreementURL(String token,int type) {
		
		jsonData = new JSONObject();
		try {
			jsonData.put("token", token);
			jsonData.put("type", type);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonData.toString();
	}
	
	
}
