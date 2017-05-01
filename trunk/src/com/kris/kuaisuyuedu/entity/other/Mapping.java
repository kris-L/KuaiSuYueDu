package com.kris.kuaisuyuedu.entity.other;

import android.text.method.MovementMethod;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @author {XiaOt} 字段与控件资源的对应表
 */
public class Mapping {
	/**
	 * 字段名
	 */
	public String FieldName;
	/**
	 * 资源id
	 */
	public int ResId;
	/**
	 * 点击事件
	 */
	public OnClickListener Click;
	/**
	 * 运转的方法
	 */
	public MovementMethod movement;
	/**
	 * 是否是粗体
	 */
	public boolean isBold;
	/**
	 * 控件是否可见
	 */
	public boolean isVisible = true;
	/**
	 * CheckBox选中改变事件
	 */
	public OnCheckedChangeListener Check;
	/**
	 * 是否需要SpannableString的触摸事件
	 */
	public boolean isNeedTouch = true;
}
