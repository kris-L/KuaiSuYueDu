package com.kris.kuaisuyuedu.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class InputWatcherUtil {
	
	public static void setPricePointWatcher(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int start, int before,
                    int count) {
                if (cs.toString().contains(".")) {
                    if (cs.length() - 1 - cs.toString().indexOf(".") > 2) {
                        cs = cs.toString().subSequence(0,
                                cs.toString().indexOf(".") + 3);
                        editText.setText(cs);
                        editText.setSelection(cs.length());
                    }
                }
                if (cs.toString().trim().substring(0).equals(".")) {
                    cs = "0" + cs;
                    editText.setText(cs);
                    editText.setSelection(2);
                }
 
                if (cs.toString().startsWith("0")
                        && cs.toString().trim().length() > 1) {
                    if (!cs.toString().substring(1, 2).equals(".")) {
                        editText.setText(cs.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }
 
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                 
            }
        });
    }
	
	
	public static void setPhoneNumWatcher(final EditText editText,final ImageButton imageButton) {
        editText.addTextChangedListener(new TextWatcher() {
            
        	private CharSequence temp;
        	@Override  
            public void onTextChanged(CharSequence cs, int start, int before, int count) {  
                // TODO Auto-generated method stub  
                 temp = cs;  
            }  
              
            @Override  
            public void beforeTextChanged(CharSequence s, int start, int count,  
                    int after) {  
                // TODO Auto-generated method stub  
            }  
              
            @Override  
            public void afterTextChanged(Editable s) {  
                // TODO Auto-generated method stub  
                if(PhoneUtil.isMobileNO(temp.toString()))
                {
                	imageButton.setVisibility(View.VISIBLE);
                }else{
                	imageButton.setVisibility(View.GONE);
                }
            }
         });
    }
	
	public static void setPhoneNumWatcher(final EditText editText,final ImageView imageView) {
        editText.addTextChangedListener(new TextWatcher() {
            
        	private CharSequence temp;
        	@Override  
            public void onTextChanged(CharSequence cs, int start, int before, int count) {  
                // TODO Auto-generated method stub  
                 temp = cs;  
            }  
              
            @Override  
            public void beforeTextChanged(CharSequence s, int start, int count,  
                    int after) {  
                // TODO Auto-generated method stub  
            }  
              
            @Override  
            public void afterTextChanged(Editable s) {  
                // TODO Auto-generated method stub  
                if(PhoneUtil.isMobileNO(temp.toString()))
                {
                	imageView.setVisibility(View.VISIBLE);
                }else{
                	imageView.setVisibility(View.GONE);
                }
            }
         });
    }
	
	public static void setPlateNumWatcher(final EditText editText,final ImageButton imageButton) {
        editText.addTextChangedListener(new TextWatcher() {
            
        	private CharSequence temp;
        	@Override  
            public void onTextChanged(CharSequence cs, int start, int before, int count) {  
                // TODO Auto-generated method stub  
                 temp = cs;  
            }  
              
            @Override  
            public void beforeTextChanged(CharSequence s, int start, int count,  
                    int after) {  
                // TODO Auto-generated method stub  
            }  
              
            @Override  
            public void afterTextChanged(Editable s) {  
                // TODO Auto-generated method stub  
                if(temp.toString().length() == 5)
                {
                	imageButton.setVisibility(View.VISIBLE);
                }else{
                	imageButton.setVisibility(View.GONE);
                }
            }
         });
    }
	

}
