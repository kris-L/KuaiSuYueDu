package com.kris.kuaisuyuedu.util;

import java.math.BigDecimal;   

import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.data.Const;
import com.kris.kuaisuyuedu.ui.NumShulteActivity;
import com.kris.kuaisuyuedu.ui.view.SelfDialog;


import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;


public class DialogManage {
	
	public static void selectNodeDialog(final Context mContext,final int type) {

		final SelfDialog dialogHint = new SelfDialog(mContext, R.style.SelfDialog);
		// 设置它的ContentView
		Window window = dialogHint.getWindow();
		window.setContentView(R.layout.dialog_select_node);
		WindowManager.LayoutParams params = window.getAttributes();
//		params.width = LayoutParams.FILL_PARENT;
		params.y = 0; // 设置y坐标
		window.setAttributes(params);
		/** 设置不能点击取消 */
//		dialogHint.setCancelable(false);

		RelativeLayout four_node_rl = (RelativeLayout) window
				.findViewById(R.id.four_node_rl);
		RelativeLayout eight_node_rl = (RelativeLayout) window
				.findViewById(R.id.eight_node_rl);
		RelativeLayout twelve_node_rl = (RelativeLayout) window
				.findViewById(R.id.twelve_node_rl);
		
	
		four_node_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (type == Const.TEXT_EXAM_TYPE) {
					Intent intent_four = new Intent(mContext, NumShulteActivity.class);
					mContext.startActivity(intent_four);
				}else if (type == Const.PICTURE_EXAM_TYPE) {
//					Intent intent_four = new Intent(mContext, PicFourNodeActivity.class);
//					mContext.startActivity(intent_four);
				}
				dialogHint.dismiss();
			}
		});
		
		eight_node_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (type == Const.TEXT_EXAM_TYPE) {
//					Intent intent_four = new Intent(mContext, TextEightNodeActivity.class);
//					mContext.startActivity(intent_four);
				}else if (type == Const.PICTURE_EXAM_TYPE) {
//					Intent intent_four = new Intent(mContext, PicEightNodeActivity.class);
//					mContext.startActivity(intent_four);
				}
				dialogHint.dismiss();
			}
		});
		
		twelve_node_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (type == Const.TEXT_EXAM_TYPE) {
//					Intent intent_four = new Intent(mContext, TextTwelveNodeActivity.class);
//					mContext.startActivity(intent_four);
				}else if (type == Const.PICTURE_EXAM_TYPE) {
//					Intent intent_four = new Intent(mContext, PicTwelveNodeActivity.class);
//					mContext.startActivity(intent_four);
				}
				dialogHint.dismiss();
			}
		});
		
		dialogHint.show();
	}
	

}
