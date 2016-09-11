package com.microsoft.xbox.toolkit;

import android.app.Dialog;

public abstract interface IProjectSpecificDialogManager
{
  public abstract void addManagedDialog(IXLEManagedDialog paramIXLEManagedDialog);
  
  public abstract void dismissBlocking();
  
  public abstract void dismissManagedDialog(IXLEManagedDialog paramIXLEManagedDialog);
  
  public abstract void dismissToast();
  
  public abstract void dismissTopNonFatalAlert();
  
  public abstract void forceDismissAlerts();
  
  public abstract void forceDismissAll();
  
  public abstract boolean getIsBlocking();
  
  public abstract Dialog getVisibleDialog();
  
  public abstract void onApplicationPause();
  
  public abstract void onApplicationResume();
  
  public abstract void onDialogStopped(IXLEManagedDialog paramIXLEManagedDialog);
  
  public abstract void setBlocking(boolean paramBoolean, String paramString);
  
  public abstract void setCancelableBlocking(boolean paramBoolean, String paramString, Runnable paramRunnable);
  
  public abstract void setEnabled(boolean paramBoolean);
  
  public abstract void showFatalAlertDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable);
  
  public abstract void showManagedDialog(IXLEManagedDialog paramIXLEManagedDialog);
  
  public abstract void showNonFatalAlertDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable);
  
  public abstract void showOkCancelDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable1, String paramString4, Runnable paramRunnable2);
  
  public abstract void showToast(int paramInt);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\IProjectSpecificDialogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */