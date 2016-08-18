package com.microsoft.xbox.toolkit;

import android.app.Dialog;

public class DialogManager
  implements IProjectSpecificDialogManager
{
  private static DialogManager instance = new DialogManager();
  private IProjectSpecificDialogManager manager;
  
  private void checkProvider() {}
  
  public static DialogManager getInstance()
  {
    return instance;
  }
  
  public void addManagedDialog(IXLEManagedDialog paramIXLEManagedDialog)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.addManagedDialog(paramIXLEManagedDialog);
    }
  }
  
  public void dismissBlocking()
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.dismissBlocking();
    }
  }
  
  public void dismissManagedDialog(IXLEManagedDialog paramIXLEManagedDialog)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.dismissManagedDialog(paramIXLEManagedDialog);
    }
  }
  
  public void dismissToast()
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.dismissToast();
    }
  }
  
  public void dismissTopNonFatalAlert()
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.dismissTopNonFatalAlert();
    }
  }
  
  public void forceDismissAlerts()
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.forceDismissAlerts();
    }
  }
  
  public void forceDismissAll()
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.forceDismissAll();
    }
  }
  
  public boolean getIsBlocking()
  {
    checkProvider();
    if (this.manager != null) {
      return this.manager.getIsBlocking();
    }
    return false;
  }
  
  public IProjectSpecificDialogManager getManager()
  {
    return this.manager;
  }
  
  public Dialog getVisibleDialog()
  {
    checkProvider();
    if (this.manager != null) {
      return this.manager.getVisibleDialog();
    }
    return null;
  }
  
  public void onApplicationPause()
  {
    if (this.manager != null) {
      this.manager.onApplicationPause();
    }
  }
  
  public void onApplicationResume()
  {
    if (this.manager != null) {
      this.manager.onApplicationResume();
    }
  }
  
  public void onDialogStopped(IXLEManagedDialog paramIXLEManagedDialog)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.onDialogStopped(paramIXLEManagedDialog);
    }
  }
  
  public void setBlocking(boolean paramBoolean, String paramString)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.setBlocking(paramBoolean, paramString);
    }
  }
  
  public void setCancelableBlocking(boolean paramBoolean, String paramString, Runnable paramRunnable)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.setCancelableBlocking(paramBoolean, paramString, paramRunnable);
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.setEnabled(paramBoolean);
    }
  }
  
  public void setManager(IProjectSpecificDialogManager paramIProjectSpecificDialogManager)
  {
    this.manager = paramIProjectSpecificDialogManager;
  }
  
  public void showFatalAlertDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.showFatalAlertDialog(paramString1, paramString2, paramString3, paramRunnable);
    }
  }
  
  public void showManagedDialog(IXLEManagedDialog paramIXLEManagedDialog)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.showManagedDialog(paramIXLEManagedDialog);
    }
  }
  
  public void showNonFatalAlertDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.showNonFatalAlertDialog(paramString1, paramString2, paramString3, paramRunnable);
    }
  }
  
  public void showOkCancelDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable1, String paramString4, Runnable paramRunnable2)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.showOkCancelDialog(paramString1, paramString2, paramString3, paramRunnable1, paramString4, paramRunnable2);
    }
  }
  
  public void showToast(int paramInt)
  {
    checkProvider();
    if (this.manager != null) {
      this.manager.showToast(paramInt);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\DialogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */