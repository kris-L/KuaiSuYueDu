package com.kris.kuaisuyuedu.ui;

import java.lang.reflect.Type;   
import java.util.List;

import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.data.Const;
import com.kris.kuaisuyuedu.helper.LocalDataHelper;
import com.kris.kuaisuyuedu.ui.base.BaseActivity;
import com.kris.kuaisuyuedu.util.TimeUtil;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle; 
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends BaseActivity implements OnClickListener{

	/** 服务协议 */
	private TextView setting_agreement_tv;
	/** 关于我们 */
	private TextView setting_about_us_tv;
	/** 版本号 */
	private TextView versions_tv;
	
	private Button log_out_btn;
	
	private RelativeLayout update_rl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseSetBodyView(R.layout.activity_setting, true);
		setTitleText("设置");
		setBtnLeftBackground(R.drawable.back_red);
		setBtnLeftVisiable(true);
		setBtnLeftOnClickListener(this);

		initViews();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		MobclickAgent.onResume(this);
		if (LocalDataHelper.getInstance(this).isRegister()) {
			log_out_btn.setText("已注册");
		}else{
			log_out_btn.setText("注册");
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		MobclickAgent.onPause(this);
	}



	private void initViews() {
		setting_agreement_tv = (TextView) findViewById(R.id.setting_agreement_tv);
		setting_about_us_tv = (TextView) findViewById(R.id.setting_about_us_tv);
		log_out_btn = (Button) findViewById(R.id.log_out_btn);
		versions_tv = (TextView) findViewById(R.id.versions_tv);
		update_rl = (RelativeLayout) findViewById(R.id.update_rl);
		update_rl.setOnClickListener(this);
		
		setting_agreement_tv.setOnClickListener(this);
		setting_about_us_tv.setOnClickListener(this);
		log_out_btn.setOnClickListener(this);
		
		
		String softVer = "";
		try {
			softVer = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		versions_tv.setText("v"+softVer);
		
		
	}
	
	
//	/**
//	 * query version information form network
//	 */
//	private void asyncUpdateVersion() {
//		String version = "1.0";
//		PackageManager manager = getPackageManager();
//		try {
//			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
//			version = info.versionName;
//		} catch (NameNotFoundException e) {
//			e.printStackTrace();
//		}
//		AsyncDataLoader asy = new AsyncDataLoader(this, this, HttpUrl.INDEX_SOFTAPPUP);
//		HttpInfo httpInfo = new HttpInfo(HttpUrl.URL_UPDATEVERSION, JsonEncoderHelper.
//				getInstance().UpdateVersion(token,Const.ANDROID_SYSTYPE,version));
//		asy.setCallBack(this);
//		asy.setShow(true);
//		asy.execute(httpInfo);
//	}
	

	@Override
	public void onClick(View v) {
		if (TimeUtil.isFastClick()) {
			return;
		}
		switch (v.getId()) {
		case R.id.btn_topbar_left:
			finish();
			break;

		case R.id.log_out_btn:
			Intent msgIntent = new Intent(Const.LOG_OUT_ACTION);
			sendBroadcast(msgIntent);
			
//			LocalDataHelper.getInstance(this).remove();
//			toActivity(RegisterActivity.class);
			break;

		case R.id.setting_about_us_tv:
			toActivity(AboutUsActivity.class);
			break;
			
		case R.id.update_rl:
//			asyncUpdateVersion();
			break;

		default:
			break;
		}
	}

//	@Override
//	public void handleData(Message msg, int action, String result) {
//		// TODO Auto-generated method stub
//		switch (action) {
//		case HttpUrl.INDEX_SOFTAPPUP:
//			if (msg.what == AsyncDataLoader.EXCEPTION)
//				return;
//			Type jsonTypeVersion = new com.google.gson.reflect.TypeToken<Result<List<NewVersionInfo>>>() {
//			}.getType();
//			Result<List<NewVersionInfo>> bodyVersion = GsonHelper.fromJson(result,
//					jsonTypeVersion);
//			showTips(bodyVersion.getDesc());
//			if (bodyVersion.getCode() == Const.CODE_SUCCESS) {
//				if ((bodyVersion != null) && (bodyVersion.getT() != null)) {
//					if (!bodyVersion.getT().isEmpty()) {
//						UpdateManager manager = new UpdateManager(this);
//						manager.checkUpdate(bodyVersion.getT().get(0));
//					}
//				}
//			}
//			break;
//		default:
//			break;
//		}
//	}
		
		
		
		
		
		



}
