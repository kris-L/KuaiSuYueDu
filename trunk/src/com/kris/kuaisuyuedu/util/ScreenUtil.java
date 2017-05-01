package com.kris.kuaisuyuedu.util;

public class ScreenUtil {
	public static int statusBarHeight = 0;// 屏幕状态栏高度
	public static int screenWidth = 0;// 屏幕宽度
	public static int screenHeight = 85;// 屏幕高度
	public static float dipTopx = 1.0f;// 屏幕1dp等于多少px

	public static int getStatusBarHeight() {
		return statusBarHeight;
	}

	public static void setStatusBarHeight(int statusBarHeight) {
		ScreenUtil.statusBarHeight = statusBarHeight;
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		ScreenUtil.screenWidth = screenWidth;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}

	public static void setScreenHeight(int screenHeight) {
		ScreenUtil.screenHeight = screenHeight;
	}

	// 获得可编辑区域高度
	public static int getEditHeight() {
		if(statusBarHeight == 0){
			return (int) (screenHeight - dipTopx * 30);
		}
		return screenHeight - statusBarHeight;
	}

	public static float getDipTopx() {
		return dipTopx;
	}

	public static void setDipTopx(float dipTopx) {
		ScreenUtil.dipTopx = dipTopx;
	}

}
