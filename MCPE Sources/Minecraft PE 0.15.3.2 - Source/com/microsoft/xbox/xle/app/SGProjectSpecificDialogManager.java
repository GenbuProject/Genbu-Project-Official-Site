package com.microsoft.xbox.xle.app;

import android.app.Dialog;
import com.microsoft.xbox.toolkit.DialogManager;
import com.microsoft.xbox.toolkit.DialogManagerBase;
import com.microsoft.xbox.toolkit.IProjectSpecificDialogManager;
import com.microsoft.xbox.xle.app.dialog.ChangeFriendshipDialog;
import com.microsoft.xbox.xle.viewmodel.ChangeFriendshipDialogViewModel;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class SGProjectSpecificDialogManager
  extends DialogManagerBase
{
  private static IProjectSpecificDialogManager instance = new SGProjectSpecificDialogManager();
  private ChangeFriendshipDialog changeFriendshipDialog;
  
  public static IProjectSpecificDialogManager getInstance()
  {
    return instance;
  }
  
  public static SGProjectSpecificDialogManager getProjectSpecificInstance()
  {
    return (SGProjectSpecificDialogManager)DialogManager.getInstance().getManager();
  }
  
  public void dismissChangeFriendshipDialog()
  {
    if (this.changeFriendshipDialog != null)
    {
      dismissManagedDialog(this.changeFriendshipDialog);
      this.changeFriendshipDialog = null;
    }
  }
  
  public void forceDismissAll()
  {
    super.forceDismissAll();
    dismissChangeFriendshipDialog();
  }
  
  public void notifyChangeFriendshipDialogAsyncTaskCompleted()
  {
    if (this.changeFriendshipDialog != null) {
      this.changeFriendshipDialog.reportAsyncTaskCompleted();
    }
  }
  
  public void notifyChangeFriendshipDialogAsyncTaskFailed(String paramString)
  {
    if (this.changeFriendshipDialog != null) {
      this.changeFriendshipDialog.reportAsyncTaskFailed(paramString);
    }
  }
  
  public void notifyChangeFriendshipDialogUpdateView()
  {
    if (this.changeFriendshipDialog != null) {
      this.changeFriendshipDialog.updateView();
    }
  }
  
  public void onApplicationPause()
  {
    forceDismissAll();
  }
  
  public void onApplicationResume() {}
  
  protected boolean shouldDismissAllBeforeOpeningADialog()
  {
    return false;
  }
  
  public void showChangeFriendshipDialog(ChangeFriendshipDialogViewModel paramChangeFriendshipDialogViewModel, ViewModelBase paramViewModelBase)
  {
    if (this.changeFriendshipDialog != null)
    {
      this.changeFriendshipDialog.setVm(paramChangeFriendshipDialogViewModel);
      this.changeFriendshipDialog.getDialog().show();
      return;
    }
    this.changeFriendshipDialog = new ChangeFriendshipDialog(XboxTcuiSdk.getActivity(), paramChangeFriendshipDialogViewModel, paramViewModelBase);
    addManagedDialog(this.changeFriendshipDialog);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\SGProjectSpecificDialogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */