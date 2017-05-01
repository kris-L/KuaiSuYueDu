package com.kris.kuaisuyuedu.core;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptinterface {

    private Context mContext;

    /** Instantiate the interface and set the context */
    public JavaScriptinterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface  
    public void startBrowser(String url) {
        Uri content_url = Uri.parse(url); 
		Intent intent = new Intent(Intent.ACTION_VIEW,content_url);
		intent.setClassName("com.android.browser","com.android.browser.BrowserActivity"); 
		mContext.startActivity(intent);
    }
}
