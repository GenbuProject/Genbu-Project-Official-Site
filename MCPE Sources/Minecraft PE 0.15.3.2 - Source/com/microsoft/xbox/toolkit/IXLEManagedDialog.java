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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\IXLEManagedDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */