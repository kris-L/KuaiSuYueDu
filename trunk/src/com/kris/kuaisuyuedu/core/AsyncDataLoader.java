package com.kris.kuaisuyuedu.core;

import java.text.SimpleDateFormat;      
import java.util.Date;

import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.entity.other.HttpInfo;
import com.kris.kuaisuyuedu.util.HttpUtil;
import com.kris.kuaisuyuedu.util.Logger;
import com.kris.kuaisuyuedu.ui.view.DialogImage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.widget.Toast;

/**
 * 异步加载HTTP数据 
 * 
 * @author {XiaOt}
 * 
 */
public class AsyncDataLoader extends AsyncTask<HttpInfo, Integer, String> {
	private static Logger log = new Logger(AsyncDataLoader.class);

	private Context mContext; //
	private ICallBackData callBack = null;// 回调对象
	private int action; // 操作标记

	private boolean isShow = false; // 是否显示Loading
	private boolean isCanceledOnTouchOutside = false;
	private boolean isCanceled = true;
	private String showMessage = "加载中..."; // loading显示的文本

	private Dialog loadingDialog; //
	private Message msg = new Message(); // 消息

	public final static int MESSAGECODE = 0; // 信息编码
	public final static int EXCEPTION = -1; // 异常编码

	private boolean isShowTip = true;


	public boolean isShowTip() {
		return isShowTip;
	}

	public void setShowTip(boolean isShowTip) {
		this.isShowTip = isShowTip;
	}

	/**
	 * 日志日期时间格式(JSON字符串) "1999-12-31 23:59:59"
	 */
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat DF_DEBUG_INFO = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public AsyncDataLoader(Context mContext, ICallBackData callBack) {
		this.mContext = mContext;
		this.callBack = callBack;
	}

	public AsyncDataLoader(Context mContext, ICallBackData callBack, int action) {
		this.mContext = mContext;
		this.callBack = callBack;
		this.action = action;
	}

	public AsyncDataLoader(Context context, ICallBackData callBack, int action, boolean isShow) {
		this.mContext = context;
		this.callBack = callBack;
		this.action = action;
		this.isShow = isShow;
	}

	@Override
	protected String doInBackground(HttpInfo... params) {
		HttpInfo param = params[0];

		publishProgress();

		String json = null;
		try {
			 printHttpRequestDebugInfo(param);
			json = HttpUtil.sendRequest(param.getMethod(), param.getUrl(), param.getType(), param.getRequest());
			 printHttpResponseDebugInfo(json);
		} catch (Exception e) {
			msg.what = EXCEPTION;
			msg.obj = e.getMessage();
			e.printStackTrace();
		}

		return json;
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			if (isShow && (loadingDialog != null) && loadingDialog.isShowing()) {
				loadingDialog.dismiss();
			}
			if (callBack != null) {
				if (result != null) {
					//
					//					Type jsonType = new com.google.gson.reflect.TypeToken<Result<String>>() {
					//					}.getType();
					//					Result<String> body = GsonHelper.fromJson(result, jsonType);
				}else{
					if (isShowTip) {
						Toast.makeText(mContext, "本地或服务器端网络异常,连接超时,请稍后再试!",
								Toast.LENGTH_SHORT).show();
					}
				}
				callBack.handleData(msg, action, result);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (isShow) {
			if (mContext != null) {
				loadingDialog = getDialog((Activity)mContext, this.showMessage);
				loadingDialog.show();
			}
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	/**
	 * 设置回调对象
	 */
	public void setCallBack(ICallBackData callBack) {
		this.callBack = callBack;
	}

	/**
	 * 获取数据回调接口
	 */
	public interface ICallBackData {
		void handleData(Message msg, int action, String result);
	}

	/**
	 * 创建loading对话框
	 * 
	 * @param activity
	 * @param message
	 * @return
	 */
	private ProgressDialog getDialog(Activity activity, String message) {
		ProgressDialog dialog = new DialogImage(activity,R.style.CustomProgressDialog);
		dialog.setCancelable(isCanceled);
		dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
		// ProgressDialog dialog = new ProgressDialog(activity);
		// ProgressDialog dialog = new
		// ProgressDialog(activity,ProgressDialog.THEME_TRADITIONAL);
		// dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// dialog.setMessage(message);

		return dialog;
	}

	public void setContext(Context context) {
		this.mContext = context;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}

	public boolean isCanceledOnTouchOutside() {
		return isCanceledOnTouchOutside;
	}

	public boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public void setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
		this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
	}

	/**
	 * 打印HTTP请求参数
	 * 
	 * @param method
	 * @param type
	 * @param url
	 * @param request
	 */
	public static void printHttpRequestDebugInfo(HttpInfo param) {
		log.i("------------------------------------------");
		log.i("time", DF_DEBUG_INFO.format(new Date()));
		log.i("method", param.getMethod());
		log.i("type", param.getType());
		log.i("url", param.getUrl());
		log.i("request", param.getRequest());
	}

	/**
	 * 打印HTTP返回结果
	 * 
	 * @param result
	 */
	public static void printHttpResponseDebugInfo(String result) {
		if (result != null) {
			log.i("result = ", result);
		} else {
			log.i("网络获取数据为空");
		}
	}


}
