package com.kris.kuaisuyuedu.util;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListUtil {

	public static void setListViewHeight(ListView lv) {
		ListAdapter la = lv.getAdapter();

		if (null == la) {
			return;
		}
		// calculate height of all items.
		int h = 0;
		final int cnt = la.getCount();
		for (int i = 0; i < cnt; i++) {
			View item = la.getView(i, null, lv);
			item.measure(0, 0);
			h += item.getMeasuredHeight();
		}
		// reset ListView height
		ViewGroup.LayoutParams lp = lv.getLayoutParams();
		lp.height = h + (lv.getDividerHeight() * (cnt - 1));
		lv.setLayoutParams(lp);
	}
	
	
	@SuppressLint("NewApi") 
	public static void setListViewHeight(ListView lv,int numItemHeight) {
		ListAdapter la = lv.getAdapter();
		if (null == la) {
			return;
		}
		int[] location = new int[2];
		lv.getLocationOnScreen(location);
		// calculate height of all items.
		int h = 0;
		ViewGroup.LayoutParams lp = lv.getLayoutParams();
		if (numItemHeight == -1) {
			lp.height = ScreenUtil.getScreenHeight() - location[1];
		} else {
			if (la.getCount() > 0) {
				View item = la.getView(0, null, lv);
				item.measure(0, 0);
			    h += item.getMeasuredHeight()*numItemHeight;
			}
			lp.height = h + (lv.getDividerHeight() * (numItemHeight - 1));
		}
		lv.setLayoutParams(lp);
	}
	
}
