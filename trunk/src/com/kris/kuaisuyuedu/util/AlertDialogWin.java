package com.kris.kuaisuyuedu.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class AlertDialogWin
{
  private static boolean youWant = true;
  
  public static boolean showAlertDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    new AlertDialog.Builder(paramActivity).setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AlertDialogWin.youWant = true;
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
    return youWant;
  }
  
  public static boolean showAlertDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    new AlertDialog.Builder(paramActivity).setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AlertDialogWin.youWant = true;
        paramAnonymousDialogInterface.cancel();
      }
    }).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AlertDialogWin.youWant = false;
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
    return youWant;
  }
}
