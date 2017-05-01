package com.kris.kuaisuyuedu.ui.base;

import java.util.List;   

import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.helper.UserDataHelper;
import com.kris.kuaisuyuedu.util.ActivityUtil;
import com.kris.kuaisuyuedu.util.StringUtil;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Activity包装父类，本项目所有activity都需要继承此类
 * 
 * @author {kris}
 */
public class BaseActivity extends Activity {

	private RelativeLayout title_layout;
	private int layoutId;// 资源文件id
	private Button btnLeft;
	private Button btnRight;
	private TextView title;
	public String token;
	public UserDataHelper userDataHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);

		// setBackground(R.drawable.common_bg);
		setBackground(R.color.white);
		ActivityUtil.getInstance().pushActivity(this);
		userDataHelper = UserDataHelper.getInstance(this);
		token = userDataHelper.getToken();
	}

	/**
	 * 设置界面主体部分的view的id
	 * 
	 * @param layoutResId
	 */
	protected void baseSetBodyView(int layoutResId, boolean isNeedTopbar) {
		this.layoutId = layoutResId;

		setContentView(R.layout.top_bar, R.id.btn_topbar_left, R.id.btn_topbar_right, R.id.txt_topbar_title,
				isNeedTopbar);
	}

	// 设置通用的背景图片
	@SuppressWarnings("deprecation")
	private void setBackground(int resId) {
		View view = this.getWindow().getDecorView(); // 获得window最顶层的View
		Drawable mDrawable = this.getResources().getDrawable(resId);

		if (null != mDrawable) {
			view.setBackgroundDrawable(mDrawable);
		} else {
			view.setBackgroundColor(resId);
		}
	}
	
	// 设置顶部标题的背景
	public void setTopViewBackground(int resId) {
		
		Drawable mDrawable = this.getResources().getDrawable(resId);
		if (null != mDrawable) {
			title_layout.setBackgroundDrawable(mDrawable);
		} else {
			title_layout.setBackgroundColor(resId);
		}
	}
	

	// 设置整个页面的view
	private void setContentView(int topLayoutId, int letfBtnId, int rightBtnId, int titleId, boolean isNeedTopbar) {

		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LayoutInflater inflate = LayoutInflater.from(this);
		linearLayout.setScrollContainer(true);

		if (!isNeedTopbar) {
			setContentView(layoutId);
			return;
		}

		View topView = inflate.inflate(topLayoutId, null);
		title_layout = (RelativeLayout) topView.findViewById(R.id.title_layout);
		btnLeft = (Button) topView.findViewById(letfBtnId);
		btnRight = (Button) topView.findViewById(rightBtnId);
		title = (TextView) topView.findViewById(titleId);
		btnLeft.setFocusable(false);
		btnRight.setFocusable(false);

		linearLayout.addView(topView, new LayoutParams(LayoutParams.MATCH_PARENT,
				getResources().getDimensionPixelSize(R.dimen.topbar_height)));

		View bodyView = inflate.inflate(layoutId, null);
		if (bodyView != null) {
			linearLayout.addView(bodyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

		setContentView(linearLayout);
	}

	
	/**
	 * 获取左边按钮
	 */
	public Button getBtnRight() {
		return btnRight;
	}
	
	/**
	 * 获取中间标题id
	 */
	public TextView getTitleId() {
		return title;
	}

	/**
	 * 设置左边按钮的监听
	 * 
	 * @param onClickListener
	 */
	protected void setBtnLeftOnClickListener(OnClickListener onClickListener) {
		if (btnLeft == null) {
			return;
		}

		btnLeft.setOnClickListener(onClickListener);
	}

	/**
	 * 设置右边按钮的监听
	 * 
	 * @param onClickListener
	 */
	protected void setBtnRightOnClickListener(OnClickListener onClickListener) {
		if (btnRight == null) {
			return;
		}

		btnRight.setOnClickListener(onClickListener);
	}

	protected void setBtnRightTag(Object tag) {
		if (btnRight == null) {
			return;
		}

		btnRight.setTag(tag);
	}

	protected Object getBtnRightTag() {
		return btnRight.getTag();
	}
	
	/**
	 * 设置背景颜色
	 * 
	 * @param titleTex
	 */
	protected void setTitleBackground(int colorId) {
		if (title_layout == null) {
			return;
		}
		title_layout.setBackgroundColor(getResources().getColor(colorId));
	}
	

	/**
	 * 设置标题文本
	 * 
	 * @param titleTex
	 */
	protected void setTitleText(String titleText) {
		if (title == null) {
			return;
		}
		title.getPaint().setFakeBoldText(true);
		title.setText(titleText);
	}

	/**
	 * 设置左边按钮的显示文本
	 * 
	 * @param leftBtnText
	 */
	protected void setBtnLeftText(String leftBtnText) {
		if (btnLeft == null) {
			return;
		}

		// btnLeft.setText("返回".equals(leftBtnText)?"":leftBtnText);
		btnLeft.setText(leftBtnText);
		btnLeft.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置左边按钮的显示文本大小
	 * 
	 * @param textSize
	 */
	protected void setBtnLeftTextSize(int textSize) {
		if (btnLeft == null) {
			return;
		}

		btnLeft.setTextSize(textSize);
		btnLeft.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置右边按钮的显示文本
	 * 
	 * @param rightBtnTex
	 */
	protected void setBtnRightText(String rightBtnText) {
		if (btnRight == null) {
			return;
		}

		btnRight.setText(rightBtnText);
		btnRight.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置右边按钮的显示文本大小
	 * 
	 * @param textSize
	 */
	protected void setBtnRightTextSize(int textSize) {
		if (btnRight == null) {
			return;
		}

		btnRight.setTextSize(textSize);
		btnRight.setVisibility(View.VISIBLE);
	}
	
	/**
	 * 设置右边按钮的显示文本颜色
	 * 
	 * @param rightBtnTextColor
	 */
	protected void setBtnRightTextColor(int rightBtnTextColor) {
		if (rightBtnTextColor == 0) {
			return;
		}

		btnRight.setTextColor(rightBtnTextColor);
	}

	/**
	 * 设置左边按钮是否可见
	 * 
	 * @param isVisiable
	 */
	protected void setBtnLeftVisiable(boolean isVisiable) {
		if (btnLeft == null) {
			return;
		}

		btnLeft.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
	}

	/**
	 * 设置右边按钮是否可见
	 * 
	 * @param isVisiable
	 */
	protected void setBtnRightVisiable(boolean isVisiable) {
		if (btnRight == null) {
			return;
		}

		btnRight.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
	}

	/**
	 * 设置左边按钮的背景图片
	 * 
	 * @param drawableId
	 */
	@SuppressWarnings("deprecation")
	protected void setBtnLeftBackground(int drawableId) {
		if (btnLeft == null) {
			return;
		}

		if (drawableId == -1) {
			btnLeft.setBackgroundDrawable(null);
			return;
		}

		Drawable mDrawable = this.getResources().getDrawable(drawableId);
		if (mDrawable != null) {
			btnLeft.setBackgroundDrawable(mDrawable);
			btnLeft.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 设置右边按钮的背景图片
	 * 
	 * @param drawableId
	 */
	@SuppressWarnings("deprecation")
	protected void setBtnRightBackground(int drawableId) {
		if (btnRight == null) {
			return;
		}

		if (drawableId == -1) {
			btnRight.setBackgroundDrawable(null);
			return;
		}

		Drawable mDrawable = this.getResources().getDrawable(drawableId);
		if (mDrawable != null) {
			btnRight.setBackgroundDrawable(mDrawable);
			btnRight.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 退出程序
	 */
	public void exitAll() {
		// CameraInterface.getInstance(this).doStopCamera();
		ActivityUtil.getInstance().popAllActivityExceptOne(null);
	}

	/**
	 * Toast弹出提示
	 * 
	 * @param text
	 */
	public void showTips(String text) {
		if (StringUtil.isNullOrEmpty(text)) {
			return;
		}
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

	}

	/**
	 * 导向到Activity
	 * 
	 * @param activity
	 */
	protected void toActivity(Class<?> clazz) {
		startActivity(new Intent(this, clazz));
	}

	/**
	 * 导向到Activity
	 * 
	 * @param activity
	 */
	protected void toActivityForResult(Class<?> clazz, int requestCode) {
		startActivityForResult(new Intent(this, clazz), requestCode);
	}

	/**
	 * 导向到Activity
	 * 
	 * @param activity
	 */
	protected void toActivityForResult(Intent intent, int requestCode) {
		startActivityForResult(intent, requestCode);
	}

	protected void popActivity(Class<?> clazz) {
		ActivityUtil.getInstance().popActivityClass(clazz);
	}

	protected void popActivityList(List<Class<?>> list) {
		ActivityUtil.getInstance().popActivityClassList(list);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityUtil.getInstance().popActivity(this);
	}

	protected boolean isListNotNullOrEmpty(List<?> list) {

		if ((list == null) || list.isEmpty()) {
			return false;
		}

		return true;
	}

	protected void openSoftInput(boolean isOpen) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (isOpen) {
			if (imm.isActive()) {
				imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		} else {
			if (getCurrentFocus() != null) {
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}
	
	public void doFinish(Context context) {
		ActivityUtil.getInstance().popAllActivityExceptOne(null);
		finish();

		android.os.Process.killProcess(android.os.Process.myPid());
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Activity.ACTIVITY_SERVICE);
		manager.killBackgroundProcesses(context.getPackageName());
		System.exit(0);// 退出程序
	}

}
