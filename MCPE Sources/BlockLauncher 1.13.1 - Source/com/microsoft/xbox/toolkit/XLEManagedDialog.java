package com.microsoft.xbox.toolkit;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import com.microsoft.xbox.toolkit.anim.MAAS;
import com.microsoft.xbox.toolkit.anim.MAAS.MAASAnimationType;
import com.microsoft.xbox.toolkit.anim.XLEAnimation;
import com.microsoft.xbox.toolkit.anim.XLEAnimationPackage;
import com.microsoft.xbox.toolkit.system.SystemUtil;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.xle.anim.XLEMAASAnimationPackageNavigationManager;

public class XLEManagedDialog
  extends Dialog
  implements IXLEManagedDialog
{
  protected static final String BODY_ANIMATION_NAME = "Dialog";
  protected String bodyAnimationName = "Dialog";
  final Runnable callAfterAnimationIn = new Runnable()
  {
    public void run()
    {
      XLEManagedDialog.this.OnAnimationInEnd();
    }
  };
  final Runnable callAfterAnimationOut = new Runnable()
  {
    public void run()
    {
      XLEManagedDialog.this.OnAnimationOutEnd();
    }
  };
  protected View dialogBody = null;
  private IXLEManagedDialog.DialogType dialogType = IXLEManagedDialog.DialogType.NORMAL;
  protected Runnable onAnimateOutCompletedRunable = null;
  
  public XLEManagedDialog(Context paramContext)
  {
    super(paramContext);
  }
  
  public XLEManagedDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  protected XLEManagedDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
  }
  
  protected static boolean isKindle()
  {
    return SystemUtil.isKindle();
  }
  
  public void OnAnimationInEnd()
  {
    NavigationManager.getInstance().setAnimationBlocking(false);
  }
  
  public void OnAnimationOutEnd()
  {
    NavigationManager.getInstance().setAnimationBlocking(false);
    super.dismiss();
    if (this.onAnimateOutCompletedRunable != null) {}
    try
    {
      this.onAnimateOutCompletedRunable.run();
      return;
    }
    catch (Exception localException) {}
  }
  
  public void dismiss()
  {
    if (!isShowing())
    {
      super.dismiss();
      return;
    }
    XLEAnimationPackage localXLEAnimationPackage = getAnimateOut();
    if ((getDialogBody() != null) && (localXLEAnimationPackage != null))
    {
      NavigationManager.getInstance().setAnimationBlocking(true);
      localXLEAnimationPackage.setOnAnimationEndRunnable(this.callAfterAnimationOut);
      localXLEAnimationPackage.startAnimation();
      return;
    }
    if (this.onAnimateOutCompletedRunable != null) {
      this.onAnimateOutCompletedRunable.run();
    }
    super.dismiss();
  }
  
  protected void forceKindleRespectDimOptions()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        XLEManagedDialog.this.getWindow().addFlags(2);
      }
    }, 100L);
  }
  
  public XLEAnimationPackage getAnimateIn()
  {
    XLEAnimation localXLEAnimation = getBodyAnimation(MAAS.MAASAnimationType.ANIMATE_IN, false);
    if (localXLEAnimation != null)
    {
      XLEAnimationPackage localXLEAnimationPackage = new XLEAnimationPackage();
      localXLEAnimationPackage.add(localXLEAnimation);
      return localXLEAnimationPackage;
    }
    return null;
  }
  
  public XLEAnimationPackage getAnimateOut()
  {
    XLEAnimation localXLEAnimation = getBodyAnimation(MAAS.MAASAnimationType.ANIMATE_OUT, true);
    if (localXLEAnimation != null)
    {
      XLEAnimationPackage localXLEAnimationPackage = new XLEAnimationPackage();
      localXLEAnimationPackage.add(localXLEAnimation);
      return localXLEAnimationPackage;
    }
    return null;
  }
  
  protected XLEAnimation getBodyAnimation(MAAS.MAASAnimationType paramMAASAnimationType, boolean paramBoolean)
  {
    XLEAnimation localXLEAnimation = null;
    if (getDialogBody() != null) {
      localXLEAnimation = ((XLEMAASAnimationPackageNavigationManager)MAAS.getInstance().getAnimation(this.bodyAnimationName)).compile(paramMAASAnimationType, paramBoolean, getDialogBody());
    }
    return localXLEAnimation;
  }
  
  public String getBodyAnimationName()
  {
    return this.bodyAnimationName;
  }
  
  public Dialog getDialog()
  {
    return this;
  }
  
  public View getDialogBody()
  {
    return this.dialogBody;
  }
  
  public IXLEManagedDialog.DialogType getDialogType()
  {
    return this.dialogType;
  }
  
  public void makeFullScreen()
  {
    getWindow().setLayout(-1, -2);
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    XLEAnimationPackage localXLEAnimationPackage = getAnimateIn();
    if ((getDialogBody() != null) && (localXLEAnimationPackage != null))
    {
      NavigationManager.getInstance().setAnimationBlocking(true);
      localXLEAnimationPackage.setOnAnimationEndRunnable(this.callAfterAnimationIn);
      localXLEAnimationPackage.startAnimation();
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (!paramBoolean) {
      safeDismiss();
    }
  }
  
  public void quickDismiss()
  {
    super.dismiss();
  }
  
  public void safeDismiss()
  {
    DialogManager.getInstance().dismissManagedDialog(this);
  }
  
  public void setAnimateOutRunnable(Runnable paramRunnable)
  {
    this.onAnimateOutCompletedRunable = paramRunnable;
  }
  
  public void setBodyAnimationName(String paramString)
  {
    this.bodyAnimationName = paramString;
  }
  
  public void setDialogType(IXLEManagedDialog.DialogType paramDialogType)
  {
    this.dialogType = paramDialogType;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEManagedDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */