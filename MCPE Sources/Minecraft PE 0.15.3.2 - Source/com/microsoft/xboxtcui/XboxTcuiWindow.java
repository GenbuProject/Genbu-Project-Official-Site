package com.microsoft.xboxtcui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.toolkit.BackgroundThreadWaitor;
import com.microsoft.xbox.toolkit.BackgroundThreadWaitor.WaitType;
import com.microsoft.xbox.toolkit.DialogManager;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.ProjectSpecificDataProvider;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.XLEUnhandledExceptionHandler;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.NavigationManager.NavigationCallbacks;
import com.microsoft.xbox.toolkit.ui.NavigationManager.OnNavigatedListener;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.app.SGProjectSpecificDialogManager;
import com.microsoft.xbox.xle.app.XleProjectSpecificDataProvider;
import java.util.Stack;

public class XboxTcuiWindow
  extends FrameLayout
  implements NavigationManager.NavigationCallbacks, NavigationManager.OnNavigatedListener
{
  private static final int NAVIGATION_BLOCK_TIMEOUT_MS = 5000;
  private static final String TAG = XboxTcuiWindow.class.getSimpleName();
  private Activity activity;
  private boolean animationBlocking;
  private final ActivityParameters launchParams;
  private final Class<? extends ScreenLayout> launchScreenClass;
  private final Stack<ScreenLayout> screens = new Stack();
  private boolean wasRestarted;
  
  public XboxTcuiWindow(Activity paramActivity, Class<? extends ScreenLayout> paramClass, ActivityParameters paramActivityParameters)
  {
    super(paramActivity);
    XLEAssert.assertNotNull(paramActivityParameters.getMeXuid());
    this.activity = paramActivity;
    this.launchScreenClass = paramClass;
    this.launchParams = paramActivityParameters;
    setBackgroundResource(R.color.backgroundColor);
  }
  
  private void setupNavigationManager()
  {
    NavigationManager.getInstance().setNavigationCallbacks(this);
    NavigationManager.getInstance().setOnNavigatedListener(this);
    try
    {
      NavigationManager.getInstance().PopAllScreens();
      return;
    }
    catch (XLEException localXLEException)
    {
      Log.e(TAG, "setupNavigationManager: " + Log.getStackTraceString(localXLEException));
    }
  }
  
  private void setupThreadManager()
  {
    ThreadManager.UIThread = Thread.currentThread();
    ThreadManager.Handler = new Handler();
    Thread localThread = ThreadManager.UIThread;
    Thread.setDefaultUncaughtExceptionHandler(XLEUnhandledExceptionHandler.Instance);
  }
  
  public void addContentViewXLE(ScreenLayout paramScreenLayout)
  {
    if (!this.screens.isEmpty())
    {
      if (paramScreenLayout == this.screens.peek())
      {
        paramScreenLayout.setAllEventsEnabled(true);
        return;
      }
      if (!paramScreenLayout.isKeepPreviousScreen()) {
        break label86;
      }
      ((ScreenLayout)this.screens.peek()).setAllEventsEnabled(false);
    }
    for (;;)
    {
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
      localLayoutParams.addRule(10);
      localLayoutParams.addRule(12);
      addView(paramScreenLayout, localLayoutParams);
      this.screens.push(paramScreenLayout);
      return;
      label86:
      removeView((View)this.screens.pop());
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (!NavigationManager.getInstance().onKey(this, paramKeyEvent.getKeyCode(), paramKeyEvent)) {
      return super.dispatchKeyEvent(paramKeyEvent);
    }
    return true;
  }
  
  public boolean dispatchUnhandledMove(View paramView, int paramInt)
  {
    if (paramView != this) {
      return false;
    }
    switch (paramInt)
    {
    }
    for (;;)
    {
      return true;
      paramView = FocusFinder.getInstance().findNextFocus(this, getFocusedChild(), 130);
      if (paramView != null)
      {
        paramView.requestFocus();
        continue;
        paramView = FocusFinder.getInstance().findNextFocus(this, getFocusedChild(), 33);
        if (paramView != null) {
          paramView.requestFocus();
        }
      }
    }
  }
  
  public void onBeforeNavigatingIn() {}
  
  public void onCreate(Bundle paramBundle)
  {
    if (paramBundle != null) {}
    for (boolean bool = true;; bool = false)
    {
      this.wasRestarted = bool;
      setupThreadManager();
      ProjectSpecificDataProvider.getInstance().setProvider(XleProjectSpecificDataProvider.getInstance());
      paramBundle = ProjectSpecificDataProvider.getInstance().getXuidString();
      if ((!JavaUtil.isNullOrEmpty(paramBundle)) && (!paramBundle.equalsIgnoreCase(this.launchParams.getMeXuid())))
      {
        ProfileModel.getMeProfileModel();
        ProfileModel.reset();
      }
      ProjectSpecificDataProvider.getInstance().setXuidString(this.launchParams.getMeXuid());
      ProjectSpecificDataProvider.getInstance().setPrivileges(this.launchParams.getPrivileges());
      DialogManager.getInstance().setManager(SGProjectSpecificDialogManager.getInstance());
      setFocusableInTouchMode(true);
      requestFocus();
      setupNavigationManager();
      return;
    }
  }
  
  public void onPageNavigated(ScreenLayout paramScreenLayout1, ScreenLayout paramScreenLayout2) {}
  
  public void onPageRestarted(ScreenLayout paramScreenLayout) {}
  
  /* Error */
  public void onStart()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 61	com/microsoft/xboxtcui/XboxTcuiWindow:activity	Landroid/app/Activity;
    //   4: invokestatic 308	com/microsoft/xboxtcui/XboxTcuiSdk:sdkInitialize	(Landroid/app/Activity;)V
    //   7: invokestatic 283	com/microsoft/xbox/toolkit/DialogManager:getInstance	()Lcom/microsoft/xbox/toolkit/DialogManager;
    //   10: iconst_1
    //   11: invokevirtual 311	com/microsoft/xbox/toolkit/DialogManager:setEnabled	(Z)V
    //   14: aload_0
    //   15: getfield 229	com/microsoft/xboxtcui/XboxTcuiWindow:wasRestarted	Z
    //   18: ifeq +50 -> 68
    //   21: invokestatic 85	com/microsoft/xbox/toolkit/ui/NavigationManager:getInstance	()Lcom/microsoft/xbox/toolkit/ui/NavigationManager;
    //   24: invokevirtual 315	com/microsoft/xbox/toolkit/ui/NavigationManager:getCurrentActivity	()Lcom/microsoft/xbox/toolkit/ui/ScreenLayout;
    //   27: astore_1
    //   28: aload_1
    //   29: ifnull +33 -> 62
    //   32: new 317	android/os/Bundle
    //   35: dup
    //   36: invokespecial 318	android/os/Bundle:<init>	()V
    //   39: astore_2
    //   40: invokestatic 85	com/microsoft/xbox/toolkit/ui/NavigationManager:getInstance	()Lcom/microsoft/xbox/toolkit/ui/NavigationManager;
    //   43: invokevirtual 315	com/microsoft/xbox/toolkit/ui/NavigationManager:getCurrentActivity	()Lcom/microsoft/xbox/toolkit/ui/ScreenLayout;
    //   46: aload_2
    //   47: invokevirtual 321	com/microsoft/xbox/toolkit/ui/ScreenLayout:onSaveInstanceState	(Landroid/os/Bundle;)V
    //   50: invokestatic 85	com/microsoft/xbox/toolkit/ui/NavigationManager:getInstance	()Lcom/microsoft/xbox/toolkit/ui/NavigationManager;
    //   53: iconst_0
    //   54: invokevirtual 324	com/microsoft/xbox/toolkit/ui/NavigationManager:RestartCurrentScreen	(Z)V
    //   57: aload_1
    //   58: aload_2
    //   59: invokevirtual 327	com/microsoft/xbox/toolkit/ui/ScreenLayout:onRestoreInstanceState	(Landroid/os/Bundle;)V
    //   62: aload_0
    //   63: iconst_0
    //   64: putfield 229	com/microsoft/xboxtcui/XboxTcuiWindow:wasRestarted	Z
    //   67: return
    //   68: invokestatic 85	com/microsoft/xbox/toolkit/ui/NavigationManager:getInstance	()Lcom/microsoft/xbox/toolkit/ui/NavigationManager;
    //   71: aload_0
    //   72: getfield 63	com/microsoft/xboxtcui/XboxTcuiWindow:launchScreenClass	Ljava/lang/Class;
    //   75: aload_0
    //   76: getfield 65	com/microsoft/xboxtcui/XboxTcuiWindow:launchParams	Lcom/microsoft/xbox/toolkit/ui/ActivityParameters;
    //   79: invokevirtual 331	com/microsoft/xbox/toolkit/ui/NavigationManager:PushScreen	(Ljava/lang/Class;Lcom/microsoft/xbox/toolkit/ui/ActivityParameters;)V
    //   82: goto -20 -> 62
    //   85: astore_1
    //   86: getstatic 36	com/microsoft/xboxtcui/XboxTcuiWindow:TAG	Ljava/lang/String;
    //   89: new 98	java/lang/StringBuilder
    //   92: dup
    //   93: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   96: ldc_w 333
    //   99: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: aload_1
    //   103: invokestatic 111	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   106: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokestatic 118	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   115: pop
    //   116: aload_0
    //   117: iconst_0
    //   118: putfield 229	com/microsoft/xboxtcui/XboxTcuiWindow:wasRestarted	Z
    //   121: return
    //   122: astore_1
    //   123: aload_0
    //   124: iconst_0
    //   125: putfield 229	com/microsoft/xboxtcui/XboxTcuiWindow:wasRestarted	Z
    //   128: aload_1
    //   129: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	this	XboxTcuiWindow
    //   27	31	1	localScreenLayout	ScreenLayout
    //   85	18	1	localXLEException	XLEException
    //   122	7	1	localObject	Object
    //   39	20	2	localBundle	Bundle
    // Exception table:
    //   from	to	target	type
    //   14	28	85	com/microsoft/xbox/toolkit/XLEException
    //   32	62	85	com/microsoft/xbox/toolkit/XLEException
    //   68	82	85	com/microsoft/xbox/toolkit/XLEException
    //   14	28	122	finally
    //   32	62	122	finally
    //   68	82	122	finally
    //   86	116	122	finally
  }
  
  public void onStop()
  {
    DialogManager.getInstance().setEnabled(false);
    try
    {
      NavigationManager.getInstance().PopAllScreens();
      return;
    }
    catch (XLEException localXLEException)
    {
      Log.e(TAG, "onStop: " + Log.getStackTraceString(localXLEException));
    }
  }
  
  public void removeContentViewXLE(ScreenLayout paramScreenLayout)
  {
    int i = this.screens.indexOf(paramScreenLayout);
    if (i >= 0) {
      while (this.screens.size() > i) {
        removeView((View)this.screens.pop());
      }
    }
  }
  
  public void setAnimationBlocking(boolean paramBoolean)
  {
    if (this.animationBlocking != paramBoolean)
    {
      this.animationBlocking = paramBoolean;
      if (this.animationBlocking) {
        BackgroundThreadWaitor.getInstance().setBlocking(BackgroundThreadWaitor.WaitType.Navigation, 5000);
      }
    }
    else
    {
      return;
    }
    BackgroundThreadWaitor.getInstance().clearBlocking(BackgroundThreadWaitor.WaitType.Navigation);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xboxtcui\XboxTcuiWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */