package com.kris.kuaisuyuedu;

import java.io.File;     

import com.kris.kuaisuyuedu.util.Logger;



import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Environment;

public class ReadApplication extends Application {

	private static final String TAG = "CarppApplication";
	private Logger log = new Logger(getClass());
	public static ReadApplication instance;
	public String plateResultStr;
	
	
	public static ReadApplication getInstance() {
		return (ReadApplication) instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		log.i("CarppApplication onCreate");
        instance = this;
		
//		友盟，true是测试模式。
//		MobclickAgent.setDebugMode(true);
        
		//初始化腾讯Bugly，实时捕获异常,第三个参数为true是测试模式。
//		CrashReport.initCrashReport(getApplicationContext(), "4123272c31", false);
		
        
      //ImageLoader初始化
//      ImageLoader imageLoader=ImageLoader.getInstance();
//      imageLoader.init(ImageLoaderConfiguration.createDefault(this));
	}
	
	
	public String getAppStoreDirectory() {
		// TODO Auto-generated method stub
		return getSdCardAbsolutePath();
	}
	
	public static String getSdCardAbsolutePath() {

		if (!isDirectoryInit)
			initDirectory();

		return getStoreDirectory();
		// return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	public static boolean isDirectoryInit = false;

	public static void initDirectory() {

		File file = new File(getStoreDirectory());// 创建文件夹
		if (!file.exists()) {
			file.mkdir();
		}

		isDirectoryInit = true;
	}
	
	private static String getStoreDirectory() {

		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/" +"carpoolPicture" + "/";

	}
	

	@SuppressLint("NewApi")
	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
		if(level == TRIM_MEMORY_COMPLETE)
		{
			log.i("TRIM_MEMORY_COMPLETE");
		}
		
	}

	public void onDestroy() {
		log.i("CarppApplication onDestroy");
		// 释放车牌识别,注：整个程序只需要释放一次即可）
	}
	
}
