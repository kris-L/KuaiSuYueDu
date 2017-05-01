package com.kris.kuaisuyuedu.ui;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.renderscript.Int2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.ui.base.BaseActivity;
import com.kris.kuaisuyuedu.ui.view.UnScrollGridView;
import com.kris.kuaisuyuedu.util.RandomNumUtil;

public class NumShulteActivity extends BaseActivity implements OnClickListener, OnItemClickListener{
	
	private Context mContext;
	private UnScrollGridView node_gv;
	
	
	private int selectNode = -1;
	private int selectItem = -1;
	private int totalNode = 9;
	
	private String wordGroup[] = new String[totalNode];
	private List<String> nodeData = new ArrayList<String>();
	private List<String> tempData = new ArrayList<String>();
	private NodeStrAdapter nodeStrAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseSetBodyView(R.layout.activity_num_shulte, true);
		setTitleText("3*3舒尔特");
		setBtnLeftBackground(R.drawable.back_red);
		setBtnLeftVisiable(true);
		setBtnLeftOnClickListener(this);
		setBtnRightBackground(R.drawable.circular_bead_border_white);
		setBtnRightVisiable(true);
		setBtnRightOnClickListener(this);
		setBtnRightText("开始考试");
		
		mContext = this;
		
		for (int i = 0; i < totalNode; i++) {
			wordGroup[i] = i+1+"";
		}
		
		initViews();
		initData();
	}

	private void initViews() {
		
		node_gv = (UnScrollGridView) findViewById(R.id.shulte_gv);
		node_gv.setOnItemClickListener(this);
		
	}
	
	private void initData() {
		selectNode = -1;
		selectItem = -1;
		
		int[] initRandom = RandomNumUtil.GetRandomSequence(totalNode,8);
		tempData.clear();
		for (int i = 0; i < initRandom.length; i++) {
			tempData.add(wordGroup[initRandom[i]]);
		}

		nodeData.clear();
		int[] intRandom = RandomNumUtil.GetRandomSequence(totalNode);
		for (int i = 0; i < intRandom.length; i++) {
			nodeData.add(i,tempData.get(intRandom[i]));
		}

		if (nodeStrAdapter == null) {
			nodeStrAdapter = new NodeStrAdapter(this, nodeData);
			node_gv.setAdapter(nodeStrAdapter);
		} else {
			nodeStrAdapter.notifyDataSetChanged();
		}

		
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public class NodeStrAdapter extends BaseAdapter {
		private Context mContext = null;
		private List<String> data;

		public NodeStrAdapter(Context context, List<String> data) {
			mContext = context;
			this.data = data;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("NewApi")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_select_node, parent, false);
				holder.node_string_tv = (TextView) convertView
						.findViewById(R.id.node_string_tv);
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			holder.node_string_tv.setText(data.get(position));
			if (selectItem == position) {
				holder.node_string_tv.setBackground(
						getResources().getDrawable(R.drawable.border_red));
			}else{
				holder.node_string_tv.setBackground(
						getResources().getDrawable(R.drawable.border_gray));
			}
			
			return convertView;
		}

		class ViewHolder {
			TextView node_string_tv;
		}
	}
	
	
	

}
