package com.microsoft.xbox.toolkit;

import android.app.Dialog;

public abstract interface IXLEManagedDialog
{
  public abstract Dialog getDialog();
  
  public abstract DialogType getDialogType();
  
  public abstract void quickDismiss();
  
  public abstract void safeDismiss();
  
  public abstract void setDialogType(DialogType paramDialogType);
  
  public static enum DialogType
  {
    FATAL,  NON_FATAL,  NORMAL;
    
    private DialogType() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\IXLEManagedDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */