package com.kris.kuaisuyuedu.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class SelfDialog extends Dialog {
    Context context;
	public SelfDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	
	public SelfDialog(Context context, int theme) {
		super(context,theme);
		// TODO Auto-generated constructor stub
		this.context=context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
}
