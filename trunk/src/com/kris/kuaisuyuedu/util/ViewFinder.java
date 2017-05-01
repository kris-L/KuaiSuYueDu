package com.kris.kuaisuyuedu.util;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Helper for finding and tweaking a view's children
 */
public class ViewFinder {

	private static interface FindWrapper {

		View findViewById(int id);

		Resources getResources();
	}

	private static class WindowWrapper implements FindWrapper {

		private final Window window;

		WindowWrapper(final Window window) {
			this.window = window;
		}

		@Override
		public View findViewById(final int id) {
			return window.findViewById(id);
		}

		@Override
		public Resources getResources() {
			return window.getContext().getResources();
		}
	}

	private static class ViewWrapper implements FindWrapper {

		private final View view;

		ViewWrapper(final View view) {
			this.view = view;
		}

		@Override
		public View findViewById(final int id) {
			return view.findViewById(id);
		}

		@Override
		public Resources getResources() {
			return view.getResources();
		}
	}

	private final FindWrapper wrapper;

	/**
	 * Create finder wrapping given view
	 * 
	 * @param view
	 */
	public ViewFinder(final View view) {
		wrapper = new ViewWrapper(view);
	}

	/**
	 * Create finder wrapping given window
	 * 
	 * @param window
	 */
	public ViewFinder(final Window window) {
		wrapper = new WindowWrapper(window);
	}

	/**
	 * Create finder wrapping given activity
	 * 
	 * @param activity
	 */
	public ViewFinder(final Activity activity) {
		this(activity.getWindow());
	}

	/**
	 * Find view with id
	 * 
	 * @param id
	 * @return found view
	 */
	@SuppressWarnings("unchecked")
	public <V extends View> V find(final int id) {
		return (V) wrapper.findViewById(id);
	}

	/**
	 * Get image view with id
	 * 
	 * @param id
	 * @return image view
	 */
	public ImageView imageView(final int id) {
		return find(id);
	}

	public EditText editText(final int id) {
		return find(id);
	}

	public EditText editText(final int id, TextWatcher watcher) {
		EditText et = find(id);
		et.addTextChangedListener(watcher);
		return find(id);
	}

	public ViewPager viewPager(final int id) {
		return find(id);
	}

	public ViewPager viewPager(int id, OnPageChangeListener listener) {
		return viewPager(id, listener, null);
	}

	public ViewPager viewPager(int id, FragmentPagerAdapter adapter) {
		return viewPager(id, null, adapter);
	}

	public ViewPager viewPager(final int id,
			final OnPageChangeListener listener, FragmentPagerAdapter adapter) {
		ViewPager vp = find(id);
		vp.setOnPageChangeListener(listener);
		vp.setAdapter(adapter);
		return vp;
	}

	/**
	 * Get compound button with id
	 * 
	 * @param id
	 * @return image view
	 */
	public CompoundButton compoundButton(final int id) {
		return find(id);
	}

	/**
	 * Get text view with id
	 * 
	 * @param id
	 * @return text view
	 */
	public TextView textView(final int id) {
		return find(id);
	}

	/**
	 * Set text of child view with given id
	 * 
	 * @param id
	 * @param content
	 * @return text view
	 */
	public TextView setText(final int id, final CharSequence content) {
		final TextView text = find(id);
		text.setText(content);
		return text;
	}

	/**
	 * Set text of child view with given id
	 * 
	 * @param id
	 * @param content
	 * @return text view
	 */
	public TextView setText(final int id, final int content) {
		return setText(id, wrapper.getResources().getString(content));
	}

	/**
	 * Register on click listener to child view with given id
	 * 
	 * @param id
	 * @param listener
	 * @return
	 * @return view registered with listener
	 */
	@SuppressWarnings("unchecked")
	public <V extends View> V onClick(final int id,
			final OnClickListener listener) {
		View clickable = find(id);
		clickable.setOnClickListener(listener);
		return (V) clickable;
	}

	public RadioButton radioButton(int id, OnClickListener listener) {
		RadioButton rb = find(id);
		rb.setOnClickListener(listener);
		return rb;
	}

	/**
	 * Register runnable to be invoked when child view with given id is clicked
	 * 
	 * @param id
	 * @param runnable
	 * @return view registered with runnable
	 */
	public View onClick(final int id, final Runnable runnable) {
		return onClick(id, new OnClickListener() {

			@Override
			public void onClick(View v) {
				runnable.run();
			}
		});
	}

	/**
	 * Register on click listener with all given child view ids
	 * 
	 * @param ids
	 * @param listener
	 */
	public void onClick(final OnClickListener listener, final int... ids) {
		for (int id : ids) {
			find(id).setOnClickListener(listener);
		}
	}

	/**
	 * Register runnable to be invoked when all given child view ids are clicked
	 * 
	 * @param ids
	 * @param runnable
	 */
	public void onClick(final Runnable runnable, final int... ids) {
		onClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runnable.run();
			}
		}, ids);
	}

	/**
	 * Set drawable on child image view
	 * 
	 * @param id
	 * @param drawable
	 * @return image view
	 */
	public ImageView setDrawable(final int id, final int drawable) {
		ImageView image = imageView(id);
		image.setImageDrawable(image.getResources().getDrawable(drawable));
		return image;
	}

	/**
	 * Register on checked change listener to child view with given id
	 * 
	 * @param id
	 * @param listener
	 * @return view registered with listener
	 */
	public CompoundButton onCheck(final int id,
			final OnCheckedChangeListener listener) {
		CompoundButton checkable = find(id);
		checkable.setOnCheckedChangeListener(listener);
		return checkable;
	}

	/**
	 * Register runnable to be invoked when child view with given id is
	 * checked/unchecked
	 * 
	 * @param id
	 * @param runnable
	 * @return view registered with runnable
	 */
	public CompoundButton onCheck(final int id, final Runnable runnable) {
		return onCheck(id, new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				runnable.run();
			}
		});
	}

	/**
	 * Register on checked change listener with all given child view ids
	 * 
	 * @param ids
	 * @param listener
	 */
	public void onCheck(final OnCheckedChangeListener listener,
			final int... ids) {
		for (int id : ids) {
			compoundButton(id).setOnCheckedChangeListener(listener);
		}
	}

	/**
	 * Register runnable to be invoked when all given child view ids are
	 * checked/unchecked
	 * 
	 * @param ids
	 * @param runnable
	 */
	public void onCheck(final Runnable runnable, final int... ids) {
		onCheck(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				runnable.run();
			}
		}, ids);
	}
}