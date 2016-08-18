package com.microsoft.xbox.toolkit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

public class XLEManagedAlertDialog
  extends AlertDialog
  implements IXLEManagedDialog
{
  private IXLEManagedDialog.DialogType dialogType = IXLEManagedDialog.DialogType.NORMAL;
  
  protected XLEManagedAlertDialog(Context paramContext)
  {
    super(paramContext);
  }
  
  public Dialog getDialog()
  {
    return this;
  }
  
  public IXLEManagedDialog.DialogType getDialogType()
  {
    return this.dialogType;
  }
  
  public void onStop()
  {
    super.onStop();
    DialogManager.getInstance().onDialogStopped(this);
  }
  
  public void quickDismiss()
  {
    super.dismiss();
  }
  
  public void safeDismiss()
  {
    DialogManager.getInstance().dismissManagedDialog(this);
  }
  
  public void setDialogType(IXLEManagedDialog.DialogType paramDialogType)
  {
    this.dialogType = paramDialogType;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\XLEManagedAlertDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */