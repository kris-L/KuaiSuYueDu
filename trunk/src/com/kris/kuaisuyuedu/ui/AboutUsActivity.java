package com.kris.kuaisuyuedu.ui;

import java.io.UnsupportedEncodingException; 
import java.net.URLDecoder;

import com.kris.kuaisuyuedu.R;
import com.kris.kuaisuyuedu.core.JavaScriptinterface;
import com.kris.kuaisuyuedu.ui.base.BaseActivity;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class AboutUsActivity extends BaseActivity implements
OnClickListener{
	private WebView service_wv;
	private TextView wb_title_tv;
	private Button wb_left_btn;
	private Button wb_close_btn;
	
	private Context mContext;
	private String urlStr = "file:///android_asset/" + "aboutus.htm";
	private String titleText = "";
	private int type = 0;
	private ValueCallback<Uri> mUploadMessage;
	public ValueCallback<Uri[]> uploadMessage;
	public static final int REQUEST_SELECT_FILE = 100;
	private final static int FILECHOOSER_RESULTCODE = 2;
	private JavaScriptinterface javaScriptinterface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseSetBodyView(R.layout.activity_webview, false);
//		baseSetBodyView(R.layout.agreement, true);
//		setBtnLeftBackground(R.drawable.back);
//		setBtnLeftVisiable(true);
//		setBtnLeftOnClickListener(this);
		
		mContext = this;
		service_wv = (WebView) findViewById(R.id.service_wv);
		service_wv.setVisibility(View.VISIBLE);
		wb_title_tv = (TextView) findViewById(R.id.wb_title_tv);
		wb_left_btn = (Button) findViewById(R.id.wb_left_btn);
		wb_close_btn = (Button) findViewById(R.id.wb_close_btn);
		wb_left_btn.setOnClickListener(this);
		wb_close_btn.setOnClickListener(this);
		
//		titleText = getIntent().getStringExtra("titleText");
//		urlStr = getIntent().getStringExtra("urlStr");
//		type = getIntent().getIntExtra("type", 0);
		
		wb_title_tv.setText(titleText);
		
		service_wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		service_wv.getSettings().setJavaScriptEnabled(true);
		javaScriptinterface = new JavaScriptinterface(this);
		service_wv.addJavascriptInterface(javaScriptinterface,"jsObj");
		
		service_wv.loadUrl(urlStr);

		service_wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				
//                String tag = "tada:tel";
//                if (url.contains(tag) || url.contains("tel"))
//                {
//                    String mobile = url.substring(url.lastIndexOf("/")+1);
//                    DialogManage.showCallPhoneDialog(mContext,mobile);
//                    //这个超连接,java已经处理了，webview不要处理了
//                    return true;
//                }else if ("webShare".equals(getValueByName(url, "queryType"))) {
//                	javaScriptinterface.shareWebpage(getValueByName(url, "title"), 
//                			getValueByName(url, "detail"), getValueByName(url, "pushUrl"));
//				}
                view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		
		service_wv.setWebChromeClient(webChromeClient);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			
		case R.id.wb_left_btn:
			if (service_wv.canGoBack()) {
				service_wv.goBack();
			}else{
				finish();
			}
			break;
			
		case R.id.wb_close_btn:
			finish();
			break;	
		
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && service_wv.canGoBack()) {
			service_wv.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();
//		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
//		MobclickAgent.onPause(this);
	}
	
	WebChromeClient webChromeClient = new WebChromeClient(){
        // For 3.0+ Devices (Start)
        // onActivityResult attached before constructor
        protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
        {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
        }


        // For Lollipop 5.0+ Devices
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
        {
            if (uploadMessage != null) {
                uploadMessage.onReceiveValue(null);
                uploadMessage = null;
            }

            uploadMessage = filePathCallback;

            Intent intent = fileChooserParams.createIntent();
            try
            {
                startActivityForResult(intent, REQUEST_SELECT_FILE);
            } catch (ActivityNotFoundException e)
            {
                uploadMessage = null;
                Toast.makeText(getBaseContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        //For Android 4.1 only
        protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
        {
            mUploadMessage = uploadMsg;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg)
        {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }
	};
	
	
	@SuppressLint("NewApi")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{

	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
	    {
	        if (requestCode == REQUEST_SELECT_FILE)
	        {
	            if (uploadMessage == null)
	                return;
	            uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
	            uploadMessage = null;
	        }
	    }
	    else if (requestCode == FILECHOOSER_RESULTCODE)
	    {
	        if (null == mUploadMessage)
	            return;
	        // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
	        // Use RESULT_OK only if you're implementing WebView inside an Activity
	        Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
	        mUploadMessage.onReceiveValue(result);
	        mUploadMessage = null;
	    }
	    else
	        Toast.makeText(getBaseContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
	}
	
	/*** 
     * 获取url 指定name的value; 
     * @param url 
     * @param name 
     * @return 
	 * @throws UnsupportedEncodingException 
     */  
    public static String getValueByName(String url, String name){  
        String result = "";  
        int index = url.indexOf("?");  
        String temp = url.substring(index + 1);  
        String[] keyValue = temp.split("&");  
        for (String str : keyValue) {  
            if (str.contains(name)) {  
                result = str.replace(name + "=", "");  
                break;  
            }  
        }
        
		try {
			result = URLDecoder.decode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   //.decode(result, Base64.DEFAULT);
        return result;  
    }
}



