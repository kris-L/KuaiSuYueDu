package com.kris.kuaisuyuedu.ui.view;



import com.kris.kuaisuyuedu.R;

import android.app.ProgressDialog; 
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;


public class DialogImage extends ProgressDialog {

	private ImageView image;

	public DialogImage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public DialogImage(Context context,int theme) {
		super(context,theme);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_view);
		image = (ImageView) findViewById(R.id.dialog_image);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		AnimationDrawable ob = (AnimationDrawable) image.getBackground();
		ob.stop();
		ob.start();
	}

}
