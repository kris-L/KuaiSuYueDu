package com.kris.kuaisuyuedu.ui;


import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.helper.LocalDataHelper;
import com.kris.kuaisuyuedu.ui.base.BaseActivity;
import com.kris.kuaisuyuedu.util.AlertDialogWin;
import com.kris.kuaisuyuedu.util.DESUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	private TextView textView1, registerCode_tv, textView3, textView4,
	  registerCopy,registerClear,zhantie_tv;
	private Button  app_register_btn;
	private EditText editText1,userName_et;

	private Context mContext;

	private String yourCodeShow = "";
	private String userNameStr = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseSetBodyView(R.layout.activity_register, true);
		setBtnLeftBackground(R.drawable.back_red);
		setBtnLeftVisiable(true);
		setBtnLeftOnClickListener(this);
		setTitleText("注册");
		
		mContext = this;
		userNameStr = "";
		userName_et = (EditText) findViewById(R.id.userName_et);
		textView1 = (TextView) findViewById(R.id.registerShow);
		registerCode_tv = (TextView) findViewById(R.id.registerCode);
		registerCopy = (TextView) findViewById(R.id.registerCopy);
		textView3 = (TextView) findViewById(R.id.registerText);
		editText1 = (EditText) findViewById(R.id.registerInput);
		zhantie_tv = (TextView) findViewById(R.id.zhantie);
		app_register_btn = (Button) findViewById(R.id.app_register);
		registerClear = (TextView) findViewById(R.id.registerClear);
		textView4 = (TextView) findViewById(R.id.registerTiShi);
		textView4.setTextSize(15f);
		textView4.setTextColor(-16776961);
		textView4.setText("注册提示：\n注册资格请咨询，\nQQ：1832768043（记忆魅力）\n注册方法：将手机码复制后在网站填写申请http://t.cn/RtWKf3e相关老师会在最晚24小时内邮箱回复。\n");
		
		if (!TextUtils.isEmpty(LocalDataHelper.
				getInstance(mContext).getRegisterCode())) {
			editText1.setText(LocalDataHelper.
					getInstance(mContext).getRegisterCode());
		}
		if (!TextUtils.isEmpty(LocalDataHelper.
				getInstance(mContext).getUserName())) {
			userName_et.setText(LocalDataHelper.
				getInstance(mContext).getUserName());
		}
		
		
		try {
			this.yourCodeShow = getShouJiInfo(((Activity) this));
			LocalDataHelper.getInstance(mContext).setShoujiInfo(yourCodeShow);
		} catch (Exception v0) {
			v0.printStackTrace();
			AlertDialogWin.showAlertDialog(
					((Activity) this),
					"获取手机码异常",
					"没有正常获取手机码，请与管理员联系（QQ：31525874）\n" + v0.getMessage()
							+ v0.toString(), "确定");
		}

		registerCode_tv.setText(this.yourCodeShow);
		registerCopy.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				setClipboard(RegisterActivity.this,
						RegisterActivity.this.yourCodeShow);
				AlertDialogWin.showAlertDialog(RegisterActivity.this, "复制手机码",
						"手机码已经复制到剪贴板。", "确定");
			}
		});

		zhantie_tv.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			public void onClick(View arg0) {
				ClipboardManager clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				String clipStr = clip.getText().toString(); // 粘贴
				editText1.setText(clipStr);
				// registerOperation(RegisterActivity.this, editText1.getText()
				// .toString(), RegisterActivity.this.yourCodeShow);
			}
		});

		app_register_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				userNameStr = userName_et.getText().toString().trim();
				if (TextUtils.isEmpty(userNameStr)) {
					showTips("请先输入用户名");
				}else{
					registerOperation(RegisterActivity.this, editText1.getText()
							.toString(), RegisterActivity.this.yourCodeShow);
				}
			}
		});

		registerClear.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				editText1.setText("");
			}
		});

		String v2 = getShouJiInfo(((Activity) this));

		// registerBackBtn.setClickable(false);
		// registerBackBtn.setVisibility(8);

	}

	// public boolean onCreateOptionsMenu(Menu menu) {
	// this.getMenuInflater().inflate(R.menu.register, menu);
	// return true;
	// }
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		MobclickAgent.onPause(this);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 4) {
			this.finish();
		}

		return super.onKeyDown(keyCode, event);
	}

	// public boolean onOptionsItemSelected(MenuItem mi) {
	// switch(mi.getItemId()) {
	// case 2131296908: {
	// // getCurScreen(((Activity)this));
	// break;
	// }
	// }
	//
	// return 1;
	// }

	public String getShouJiInfo(Activity activity) {
		String v2 = "";
		String deviceId = Secure.getString(activity.getWindow().getContext()
				.getContentResolver(), "android_id");
		try {
			v2 = DESUtil.encryptDES(deviceId, "jiyiInte");
		} catch (Exception v0) {
			v0.printStackTrace();
			AlertDialogWin.showAlertDialog(activity, "加密异常",
					String.valueOf(v0.getMessage()) + v0.toString(), "确定");
		}
		return v2;
	}

	@SuppressLint("NewApi")
	public void setClipboard(Activity paramActivity, String paramString) {
		((ClipboardManager) paramActivity.getSystemService("clipboard"))
				.setPrimaryClip(ClipData.newPlainText("手机码", paramString));
	}

	public void registerOperation(Activity activity, String registerCode,
			String shoujiInfo) {
		if (registerCode.equals("")) {
			AlertDialogWin.showAlertDialog(activity, "注册错误提示", "未检测到输入的注册码",
					"确定");
		} else if (MatchedRegister(activity, registerCode, shoujiInfo)
				.booleanValue()) {
			LocalDataHelper.getInstance(mContext).setRegister(true);
			LocalDataHelper.getInstance(mContext).setRegisterCode(registerCode);
			LocalDataHelper.getInstance(mContext).setUserName(userNameStr);
			
			AlertDialogWin.showAlertDialog(activity, "注册完成！",
					"恭喜你注册成功！\n现在可以使用注册版功能。", "确定");
		} else {
			LocalDataHelper.getInstance(mContext).setRegister(false);
			LocalDataHelper.getInstance(mContext).setRegisterCode("");
			AlertDialogWin
					.showAlertDialog(
							activity,
							"注册失败！",
							"您输入的注册码不正确，请联系管理员重新获取。\nQQ: 1832768043（记忆魅力）\nQQ：8716128（李博士）\n",
							"确定");
		}
	}

	public Boolean MatchedRegister(Activity activity, String registerCode,
			String shoujiInfo) {
		Boolean v5;
		String v1 = "";
		try {
			v1 = DESUtil.decryptDES(registerCode, "jiyiJMBC");
		} catch (Exception v0) {
			v0.printStackTrace();
			AlertDialogWin.showAlertDialog(activity, "注册错误提示",
					"注册码分析异常： " + v0.getMessage() + v0.toString(), "确定");
		}

		String v2 = String.valueOf(shoujiInfo) + "jiyi8.cn";
		String v4 = "VIP" + shoujiInfo + "jiyi8.cn";
		String v3 = "SVIP" + shoujiInfo + "jiyi8.cn";
		if ((v1.equals(v2)) || (v1.equals(v4))) {

			v5 = true;
		} else if (v1.equals(v3)) {

			v5 = true;
		} else {
			v5 = false;
		}
		return v5;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_topbar_left:
			finish();
			break;
		}
	}

}
