package com.kris.kuaisuyuedu.data;

import java.io.File;

import android.annotation.SuppressLint;
import android.os.Environment;

public interface Const {

	/** android设备 */
	public static final int ANDROID_SYSTYPE = 0;

	/**
	 * 客户列表自动刷新时间
	 */
	public static final int REFRESH_SLEEPTIME = 5 * 1000;

	/**
	 * 模拟器设备ID
	 */
	public static final String DEVICE_ID_EMULATOR = "000000000000000";

	/**
	 * 拍照保存的图片名称
	 */
	public static final String pictureName = "cpp_picture";

	/**
	 * 拍照保存的图片文件夹名
	 */
	public static final String pictureFolderName = "platePicture";

	/**
	 * 应用存储目录
	 */
	public static final String APP_STORAGE_PATH = "carpool";

	/**
	 * 应用存储数据库名称
	 */
	public static final String APP_SP_NAME = "iexin_carpool";

	/**
	 * 应用存储数据库名称
	 */
	public static final String APP_DATABASE_NAME = "iexin_cpp.db";

	@SuppressLint("SdCardPath")
	public static final String APP_DATABASE_PATH = "/data/data/com.iexin.cpp/databases/";

	public static final String APP_PHOTO_CACHE_PATH = Environment
			.getExternalStorageDirectory().getPath()
			+ File.separatorChar
			+ APP_STORAGE_PATH + File.separatorChar + "photo_cache";

	public static final String APP_CRASH_PATH = Environment
			.getExternalStorageDirectory().getPath()
			+ File.separatorChar
			+ APP_STORAGE_PATH
			+ File.separatorChar
			+ "crash"
			+ File.separatorChar;

	/**
	 * UTF-8编码方式
	 */
	String UTF8 = "UTF-8";

	/**
	 * XML请求头
	 */
	String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

	/**
	 * 换行符
	 */
	String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * 客户端发送请求日期时间格式
	 */
	String FORMAT_CLIENT_REQUEST = "yyyyMMddHHmmss";

	/**
	 * 服务端返回日期时间格式(JSON字符串)
	 */
	String FORMAT_SERVER_RESPONSE = "yyyy-MM-dd HH:mm:ss";

	public final int CODE_FAIL = 0;// 执行失败
	public final int CODE_SUCCESS = 200;// 执行成功
	public final int CODE_NO_DATA = 100;// 没有找到相应的数据
	public final int CODE_NO_MORE_DATA = -1;// 没有更多的数据
	/** 验证token失败 */
	public final int CODE_VERIFY_TOKEN_FAIL = -2;

    /** 文字通关 */
	public final int TEXT_EXAM_TYPE  = 1;
    /** 图片通关 */
	public final int PICTURE_EXAM_TYPE  = 2;

	
	//广播
	/**
	 * 极光消息通知
	 */
	public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
	public static final String ORDER_PAY_OK_ACTION = "ORDER_PAY_OK_ACTION";
	
	public static final String LOG_OUT_ACTION = "log_out_action";
	
}
