package com.microsoft.xbox.toolkit;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.microsoft.xbox.toolkit.ui.BlockingScreen;
import com.microsoft.xbox.toolkit.ui.CancellableBlockingScreen;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.Stack;

public abstract class DialogManagerBase
  implements IProjectSpecificDialogManager
{
  private BlockingScreen blockingSpinner;
  private CancellableBlockingScreen cancelableBlockingDialog;
  private Stack<IXLEManagedDialog> dialogStack = new Stack();
  private boolean isEnabled;
  private Toast visibleToast;
  
  protected DialogManagerBase()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      return;
    }
  }
  
  private XLEManagedAlertDialog buildDialog(final String paramString1, String paramString2, String paramString3, final Runnable paramRunnable1, String paramString4, final Runnable paramRunnable2)
  {
    final XLEManagedAlertDialog localXLEManagedAlertDialog = new XLEManagedAlertDialog(XboxTcuiSdk.getActivity());
    localXLEManagedAlertDialog.setTitle(paramString1);
    localXLEManagedAlertDialog.setMessage(paramString2);
    localXLEManagedAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ThreadManager.UIThreadPost(paramRunnable1);
      }
    });
    paramString1 = new Runnable()
    {
      public void run()
      {
        DialogManagerBase.this.dismissManagedDialog(localXLEManagedAlertDialog);
        if (paramRunnable2 != null) {
          paramRunnable2.run();
        }
      }
    };
    localXLEManagedAlertDialog.setButton(-2, paramString4, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ThreadManager.UIThreadPost(paramString1);
      }
    });
    if ((paramString4 == null) || (paramString4.length() == 0))
    {
      localXLEManagedAlertDialog.setCancelable(false);
      return localXLEManagedAlertDialog;
    }
    localXLEManagedAlertDialog.setCancelable(true);
    localXLEManagedAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        ThreadManager.UIThreadPost(paramString1);
      }
    });
    return localXLEManagedAlertDialog;
  }
  
  public void addManagedDialog(IXLEManagedDialog paramIXLEManagedDialog)
  {
    if (this.isEnabled)
    {
      this.dialogStack.push(paramIXLEManagedDialog);
      paramIXLEManagedDialog.getDialog().show();
    }
  }
  
  public void dismissBlocking()
  {
    if (this.blockingSpinner != null)
    {
      this.blockingSpinner.dismiss();
      this.blockingSpinner = null;
    }
    if (this.cancelableBlockingDialog != null)
    {
      this.cancelableBlockingDialog.dismiss();
      this.cancelableBlockingDialog = null;
    }
  }
  
  public void dismissManagedDialog(IXLEManagedDialog paramIXLEManagedDialog)
  {
    if (this.isEnabled)
    {
      this.dialogStack.remove(paramIXLEManagedDialog);
      paramIXLEManagedDialog.getDialog().dismiss();
    }
  }
  
  public void dismissToast()
  {
    if (this.visibleToast != null)
    {
      this.visibleToast.cancel();
      this.visibleToast = null;
    }
  }
  
  public void dismissTopNonFatalAlert()
  {
    if ((this.dialogStack.size() > 0) && (((IXLEManagedDialog)this.dialogStack.peek()).getDialogType() != IXLEManagedDialog.DialogType.FATAL)) {
      ((IXLEManagedDialog)this.dialogStack.pop()).getDialog().dismiss();
    }
  }
  
  public void forceDismissAlerts()
  {
    while (this.dialogStack.size() > 0) {
      ((IXLEManagedDialog)this.dialogStack.pop()).quickDismiss();
    }
  }
  
  public void forceDismissAll()
  {
    dismissToast();
    forceDismissAlerts();
    dismissBlocking();
  }
  
  public boolean getIsBlocking()
  {
    return ((this.blockingSpinner != null) && (this.blockingSpinner.isShowing())) || ((this.cancelableBlockingDialog != null) && (this.cancelableBlockingDialog.isShowing()));
  }
  
  public Dialog getVisibleDialog()
  {
    if (!this.dialogStack.isEmpty()) {
      return ((IXLEManagedDialog)this.dialogStack.peek()).getDialog();
    }
    return null;
  }
  
  public void onDialogStopped(IXLEManagedDialog paramIXLEManagedDialog)
  {
    this.dialogStack.remove(paramIXLEManagedDialog);
  }
  
  public void setBlocking(boolean paramBoolean, String paramString)
  {
    boolean bool;
    if (Thread.currentThread() == ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (this.isEnabled)
      {
        if (!paramBoolean) {
          break label64;
        }
        if (this.blockingSpinner == null) {
          this.blockingSpinner = new BlockingScreen(XboxTcuiSdk.getActivity());
        }
        this.blockingSpinner.show(XboxTcuiSdk.getActivity(), paramString);
      }
    }
    label64:
    while (this.blockingSpinner == null)
    {
      return;
      bool = false;
      break;
    }
    this.blockingSpinner.dismiss();
    this.blockingSpinner = null;
  }
  
  public void setCancelableBlocking(boolean paramBoolean, String paramString, final Runnable paramRunnable)
  {
    boolean bool;
    if (Thread.currentThread() == ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (this.isEnabled)
      {
        if (!paramBoolean) {
          break label83;
        }
        if (this.cancelableBlockingDialog == null)
        {
          this.cancelableBlockingDialog = new CancellableBlockingScreen(XboxTcuiSdk.getActivity());
          this.cancelableBlockingDialog.setCancelButtonAction(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              DialogManagerBase.this.cancelableBlockingDialog.dismiss();
              DialogManagerBase.access$002(DialogManagerBase.this, null);
              paramRunnable.run();
            }
          });
        }
        this.cancelableBlockingDialog.show(XboxTcuiSdk.getActivity(), paramString);
      }
    }
    label83:
    while (this.cancelableBlockingDialog == null)
    {
      return;
      bool = false;
      break;
    }
    this.cancelableBlockingDialog.dismiss();
    this.cancelableBlockingDialog = null;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (this.isEnabled != paramBoolean) {
      this.isEnabled = paramBoolean;
    }
  }
  
  protected boolean shouldDismissAllBeforeOpeningADialog()
  {
    return true;
  }
  
  public void showFatalAlertDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable)
  {
    forceDismissAll();
    if (this.isEnabled)
    {
      paramString1 = buildDialog(paramString1, paramString2, paramString3, paramRunnable, null, null);
      paramString1.setDialogType(IXLEManagedDialog.DialogType.FATAL);
      this.dialogStack.push(paramString1);
      paramString1.show();
    }
  }
  
  public void showManagedDialog(IXLEManagedDialog paramIXLEManagedDialog)
  {
    if (shouldDismissAllBeforeOpeningADialog()) {
      forceDismissAll();
    }
    if ((this.isEnabled) && (XboxTcuiSdk.getActivity() != null) && (!XboxTcuiSdk.getActivity().isFinishing())) {
      this.dialogStack.push(paramIXLEManagedDialog);
    }
    try
    {
      paramIXLEManagedDialog.getDialog().show();
      return;
    }
    catch (RuntimeException paramIXLEManagedDialog)
    {
      String str;
      do
      {
        str = paramIXLEManagedDialog.getMessage();
      } while ((str != null) && (str.contains("Adding window failed")));
      throw paramIXLEManagedDialog;
    }
  }
  
  public void showNonFatalAlertDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable)
  {
    if (this.isEnabled)
    {
      paramString1 = buildDialog(paramString1, paramString2, paramString3, paramRunnable, null, null);
      paramString1.setDialogType(IXLEManagedDialog.DialogType.NON_FATAL);
      this.dialogStack.push(paramString1);
      paramString1.show();
    }
  }
  
  public void showOkCancelDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable1, String paramString4, Runnable paramRunnable2)
  {
    XLEAssert.assertNotNull("You must supply cancel text if this is not a must-act dialog.", paramString4);
    if (this.dialogStack.size() > 0) {}
    while ((!this.isEnabled) || (XboxTcuiSdk.getActivity() == null) || (XboxTcuiSdk.getActivity().isFinishing())) {
      return;
    }
    paramString1 = buildDialog(paramString1, paramString2, paramString3, paramRunnable1, paramString4, paramRunnable2);
    paramString1.setDialogType(IXLEManagedDialog.DialogType.NORMAL);
    this.dialogStack.push(paramString1);
    paramString1.show();
  }
  
  public void showToast(int paramInt)
  {
    dismissToast();
    if (this.isEnabled)
    {
      this.visibleToast = Toast.makeText(XboxTcuiSdk.getActivity(), paramInt, 1);
      this.visibleToast.show();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\DialogManagerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */