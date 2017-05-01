package com.kris.kuaisuyuedu.util;

import java.io.InputStream; 
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtil {

	public static final String UTF8 = "UTF-8";

	public static final String KEY_CONTENT_TYPE = "Content-Type";
	public static final String KEY_CONTENT_LENGTH = "Content-Length";

	public static final String GET = "GET";
	public static final String POST = "POST";

	public static final String CONTENT_TYPE_HTML = "text/html; charset=UTF-8";
	public static final String CONTENT_TYPE_XML = "text/xml; charset=UTF-8";
	public static final String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";


	/**
	 * 通过HTTP发送请求，返回字符串结果信息
	 * 
	 * @param method
	 *            方法
	 * @param url
	 *            请求地址
	 * @param type
	 *            HTTP请求类型
	 * @param request
	 *            请求参数
	 * @return
	 * @throws Exception
	 */
	public static String sendRequest(String method, String url, String type, String request) throws Exception {
		String result = null;
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setConnectTimeout(10 * 1000);
		conn.setRequestMethod(method);

		if (!StringUtil.isNullOrEmpty(type)) {
			conn.setRequestProperty(KEY_CONTENT_TYPE, type);
		}
		if (!StringUtil.isNullOrEmpty(request)) {
			conn.setDoInput(true);
			conn.setDoOutput(true);
			byte[] entity = request.getBytes(UTF8);
			conn.setRequestProperty(KEY_CONTENT_LENGTH, String.valueOf(entity.length));

			OutputStream out = conn.getOutputStream();
			out.write(entity);
			out.close();
		}

		if (conn.getResponseCode() == 200) {
			InputStream in = conn.getInputStream();
			result = IoUtil.readStreamToString(in);
		}

		return result;
	}


	public static boolean getNetWorkStatus(Context context) {

		boolean netStataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 获取代表联网状态的NetWorkInfo对象 
		NetworkInfo networkInfo = cwjManager.getActiveNetworkInfo();  
		if (networkInfo != null) {
			netStataus = networkInfo.isConnected();
		}
		return netStataus;
	}

}
