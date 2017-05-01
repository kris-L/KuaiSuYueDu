package com.kris.kuaisuyuedu.util;

import java.io.File;     
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.entity.NewVersionInfo;
import com.kris.kuaisuyuedu.helper.SharePreferencesHelper;
import com.kris.kuaisuyuedu.ui.view.SelfDialog;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author kris lau 
 * @Time 201603024
 * @blog http://blog.sina.com.cn/u/1964256004
 */

public class UpdateManager
{
	/* 下载中 */
	private static final int DOWNLOAD = 1;
	/* 下载结束 */
	private static final int DOWNLOAD_FINISH = 2;
	/* 下载保存路径 */
	private String mSavePath;
	/* 记录进度条数量 */
	private int progress;
	/* 是否取消更新 */
	private boolean cancelUpdate = false;

	private Context mContext;
	/* 更新进度条 */
	private ProgressBar mProgress;
	private Dialog mDownloadDialog;
	
	private NewVersionInfo newVersionInfo = new NewVersionInfo();

	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			// 正在下载
			case DOWNLOAD:
				// 设置进度条位置
				mProgress.setProgress(progress);
				break;
			case DOWNLOAD_FINISH:
				// 安装文件
				installApk();
				break;
			default:
				break;
			}
		};
	};

	public UpdateManager(Context context)
	{
		this.mContext = context;
	}
	
	
	public NewVersionInfo getNewVersionInfo() {
		return newVersionInfo;
	}

	public void setNewVersionInfo(NewVersionInfo newVersionInfo) {
		this.newVersionInfo = newVersionInfo;
	}

	/**
	 * 检测软件更新
	 */
	public void checkUpdate(NewVersionInfo newVersionInfo)
	{
		this.newVersionInfo = newVersionInfo;
		// 获取当前软件版本
		String versionStr = getVersionName(mContext);
		
		String ingoreVersion = SharePreferencesHelper.getInstance(mContext)
				.getString("IngoreVersion", "0");
		
		if (versionCompare(newVersionInfo.getAppVersion(),versionStr)) {
			if(versionCompare(newVersionInfo.getAppVersion(),ingoreVersion))
			{
				whoShowDialog();
			}
		}
	}
	
	
	/**
	 * 判断显示升级对话框的时机
	 */
	private void whoShowDialog() {
		final SelfDialog dialog = new SelfDialog(mContext, R.style.SelfDialog);
        //设置它的ContentView
        Window window = dialog.getWindow();
        window.setContentView(R.layout.up_dialog);
        WindowManager.LayoutParams params = window.getAttributes();       
        params.y = -50;//设置y坐标
        window.setAttributes(params);
        TextView mTitle = (TextView) window.findViewById(R.id.alertTitle);
        TextView phone_p_txt=(TextView)window.findViewById(R.id.message);
        Button cancelButton=(Button)window.findViewById(R.id.button1);
        Button sumbitButton=(Button)window.findViewById(R.id.button2);
        if (newVersionInfo.getForce() == 1) {
			dialog.setOnKeyListener(keylistener);
			dialog.setCancelable(false);
			cancelButton.setText("退出");
		}else {
			cancelButton.setText("下次再说");
		}
        sumbitButton.setText("现在升级");
        mTitle.setText("发现新版本V"+newVersionInfo.getAppVersion());
        phone_p_txt.setText(newVersionInfo.getRemark());
        
        cancelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (newVersionInfo.getForce() == 1) {
					Toast.makeText(mContext, "重要修改,请升级后,再使用", Toast.LENGTH_SHORT).show();
					//关闭应用
					ActivityUtil.getInstance().popAllActivityExceptOne(null);
					android.os.Process.killProcess(android.os.Process.myPid());
					ActivityManager manager = (ActivityManager) mContext.getSystemService(Activity.ACTIVITY_SERVICE);
					manager.killBackgroundProcesses(mContext.getPackageName());
					System.exit(0);// 退出程序
				}else {
					SharePreferencesHelper.getInstance(
							mContext).saveString("IngoreVersion",
									newVersionInfo.getAppVersion());
				}
				dialog.dismiss();	
			}
		});
        sumbitButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showDownloadDialog();
				dialog.dismiss();
			}
		});
        dialog.show();
	}
	
	OnKeyListener keylistener = new DialogInterface.OnKeyListener(){
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
            {
               return true;
            }
            else
            {
               return false;
            }
        }
    };
	

	/**
	 * 
	 * @param server
	 *            服务器版本
	 * @param local
	 *            本地版本
	 * @return
	 */
	private boolean versionCompare(String server, String local) {
		List<String> serverL = new ArrayList<String>();
		List<String> localL = new ArrayList<String>();
		serverL.addAll(Arrays.asList(server.split("\\.")));
		localL.addAll(Arrays.asList(local.split("\\.")));

		if ((serverL.size() == 0) || (localL.size() == 0)) {
			return server.compareTo(local) > 0 ? true : false;
		}

		for (int i = 0; i < Math.abs(serverL.size() - localL.size()); i++) {
			if (serverL.size() > localL.size()) {
				localL.add("0");
			} else {
				serverL.add("0");
			}
		}
		
		try {
			for (int i = 0; i < serverL.size(); i++) {
				int serverPara = Integer.parseInt(serverL.get(i));
				int localPara = Integer.parseInt(localL.get(i));
				if (serverPara > localPara) {
					return true;
				} else if (serverPara < localPara) {
					return false;
				} else {
					continue;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	
	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	private int getVersionCode(Context context)
	{
		int versionCode = 0;
		try
		{
			// 获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = context.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return versionCode;
	}
	
	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	private String getVersionName(Context context)
	{
		String versionName = "1.0";
		try
		{
			//获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionName = context.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return versionName;
	}
	
	/**
	 * 显示软件下载对话框
	 */
	public void showDownloadDialog()
	{
		// 构造软件下载对话框
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(R.string.soft_updating);
		// 给下载对话框增加进度条
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.softupdate_progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
		builder.setView(v);
		// 取消更新
		builder.setNegativeButton(R.string.soft_update_cancel, new  DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				// 设置取消状态
				cancelUpdate = true;
			}
		});
		mDownloadDialog = builder.create();
		mDownloadDialog.show();
		// 现在文件
		downloadApk();
	}

	/**
	 * 下载apk文件
	 */
	public void downloadApk()
	{
		// 启动新线程下载软件
		new downloadApkThread().start();
	}

	/**
	 * 下载文件线程
	 * 
	 * @author coolszy
	 *@date 2012-4-26
	 *@blog http://blog.92coding.com
	 */
	private class downloadApkThread extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
					// 获得存储卡的路径
					String sdpath = Environment.getExternalStorageDirectory() + "/";
					mSavePath = sdpath + "download";
					URL url = new URL(newVersionInfo.getPath());
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn .setRequestProperty("Accept-Encoding", "identity"); 
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists())
					{
						file.mkdir();
					}
					
					File apkFile = new File(mSavePath, newVersionInfo.getAppName());
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					// 缓存
					byte buf[] = new byte[1024];
					// 写入到文件中
					do
					{
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0)
						{
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.close();
					is.close();
				}
			} catch (MalformedURLException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			// 取消下载对话框显示
			mDownloadDialog.dismiss();
		}
	};

	/**
	 * 安装APK文件
	 */
	private void installApk()
	{
		File apkfile = new File(mSavePath, newVersionInfo.getAppName());
		if (!apkfile.exists())
		{
			return;
		}
		// 通过Intent安装APK文件
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		mContext.startActivity(intent);
	}
}
