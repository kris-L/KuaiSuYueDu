package com.kris.kuaisuyuedu;

import com.kris.kuaisuyuedu.data.Const;
import com.kris.kuaisuyuedu.helper.LocalDataHelper;
import com.kris.kuaisuyuedu.ui.SettingActivity;
import com.kris.kuaisuyuedu.ui.base.BaseActivity; 
import com.kris.kuaisuyuedu.util.DESUtil;
import com.kris.kuaisuyuedu.util.DialogManage;
import com.kris.kuaisuyuedu.util.TimeUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnClickListener{

	
	private TextView text_exam_tv;
	private TextView picture_exam_tv;
	private TextView setting_tv;
	private boolean isRegister; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	    initData();
	}
	

    private void initViews(){
    	text_exam_tv = (TextView) findViewById(R.id.text_exam_tv);
    	picture_exam_tv = (TextView) findViewById(R.id.picture_exam_tv);   	
    	setting_tv = (TextView) findViewById(R.id.setting_tv);
    	
    	text_exam_tv.setOnClickListener(this);
    	picture_exam_tv.setOnClickListener(this);
    	setting_tv.setOnClickListener(this);
    }
    
    private void initData() {
    	
    }
    
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		MobclickAgent.onResume(this);
		isRegister = LocalDataHelper.getInstance(this).isRegister();
    	if (isRegister) {
    		String registerCode = LocalDataHelper.getInstance(this).getRegisterCode();
    		String shoujiInfo = LocalDataHelper.getInstance(this).getShoujiInfo();
    		isRegister = MatchedRegister(registerCode,shoujiInfo);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		MobclickAgent.onPause(this);
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (TimeUtil.isFastClick()) {
			return;
		}
		switch (v.getId()) {
		case R.id.text_exam_tv:
//			Intent intent_four = new Intent(this, TextFourNodeActivity.class);
//			startActivity(intent_four);
			
			DialogManage.selectNodeDialog(this, Const.TEXT_EXAM_TYPE);
			
//			if (isRegister) {
//				DialogManage.selectNodeDialog(this, Const.TEXT_EXAM_TYPE);
//			}else{
//				showTips("请先注册软件");
//				toActivity(RegisterActivity.class);
//			}
			
			
			break;
			
		case R.id.picture_exam_tv:
			DialogManage.selectNodeDialog(this, Const.PICTURE_EXAM_TYPE);
			
//			if (isRegister) {
//				DialogManage.selectNodeDialog(this, Const.PICTURE_EXAM_TYPE);
//			}else{
//				showTips("请先注册软件");
//				toActivity(RegisterActivity.class);
//			}
			break;
			
		case R.id.setting_tv:
			Intent intent_setting = new Intent(this, SettingActivity.class);
			startActivity(intent_setting);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("MainActivity  onDestroy");
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public Boolean MatchedRegister(String registerCode,
			String shoujiInfo) {
		Boolean v5 = false;
		String v1 = "";
		try {
			v1 = DESUtil.decryptDES(registerCode, "jiyiJMBC");
		} catch (Exception v0) {
			v0.printStackTrace();
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

}
