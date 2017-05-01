package com.kris.kuaisuyuedu.helper;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class PopupHelper {

	private PopupWindow pw;

	public PopupHelper(View contentView) {
		pw = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		pw.setBackgroundDrawable(new ColorDrawable(0));
		pw.setFocusable(true);
		pw.setOutsideTouchable(true);
		pw.update();
	}

	public void show(View view) {
		pw.showAsDropDown(view);
	}

	public void dismiss() {
		pw.dismiss();
	}
}
