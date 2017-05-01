package com.kris.kuaisuyuedu.helper;


import com.kris.kuaisuyuedu.core.AsyncDataLoader;

import com.kris.kuaisuyuedu.core.AsyncDataLoader.ICallBackData;
import com.kris.kuaisuyuedu.entity.other.HttpInfo;

import android.content.Context;

public class AsyccDataLoaderHelper {

	public static void request(Context context, ICallBackData callBack, int action, String url, String json,
			boolean isShow) {
		AsyncDataLoader asy = new AsyncDataLoader(context, callBack, action, isShow);
		HttpInfo httpInfo = new HttpInfo(url, json);
		asy.execute(httpInfo);
	}

	public static void request(Context context, ICallBackData callBack, int action, String url, String json) {
		request(context, callBack, action, url, json, true);
	}

}
