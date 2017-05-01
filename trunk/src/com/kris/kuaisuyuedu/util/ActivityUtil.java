package com.kris.kuaisuyuedu.util;

import java.util.List;
import java.util.Stack;

import android.app.Activity;

/**
 * activity 管理类 [统一管理activity,控制栈]
 * <p>
 * 使用枚举的模式获取单例（线程安全/反序列化）
 */
public enum ActivityUtil {

	INSTANCE;
	private ActivityUtil() {}
	public static ActivityUtil getInstance(){
		return INSTANCE;
	}

	private static Stack<Activity> activityStack;
	//	private static ActivityUtil instance = new ActivityUtil();
	//	public static ActivityUtil getInstance() {
	//		if (instance == null) {
	//			instance = new ActivityUtil();
	//		}
	//		return instance;
	//	}

	/**
	 * - 退出栈顶Activity
	 * 
	 * @param activity
	 *            [Activity类]
	 */
	public void popActivity(Activity activity) {
		activity.finish();
		activityStack.remove(activity);
		activity = null;
	}

	/**
	 * 获得当前栈顶Activity
	 * 
	 * @return Activity
	 */
	public Activity currentActivity() {
		Activity activity = null;
		if ((null != activityStack) && !activityStack.empty()) {
			activity = activityStack.lastElement();
		}
		return activity;
	}

	/**
	 * 将当前Activity推入栈中
	 * 
	 * @param activity
	 *            [Activity类]
	 */
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 退出栈中所有Activity
	 * 
	 * @param cls
	 *            [class类] 全部退出 该参数 则为null
	 */
	@SuppressWarnings("rawtypes")
	public void popAllActivityExceptOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}

	@SuppressWarnings("rawtypes")
	public void popActivityClass(Class cls){
		for(Activity activity:activityStack){
			if (activity.getClass().equals(cls)) {
				popActivity(activity);
			}
		}
	}

	public void popActivityClassList(List<Class<?>> list) {
		Stack<Activity> stack = new Stack<Activity>();
		for (Activity activity : activityStack) {
			for (Class<?> cls : list) {
				if (activity.getClass().equals(cls)) {
					stack.add(activity);
				}
			}
		}
		for (Activity activity : stack) {
			activity.finish();
			activity = null;
		}
		activityStack.removeAll(stack);
	}
}
