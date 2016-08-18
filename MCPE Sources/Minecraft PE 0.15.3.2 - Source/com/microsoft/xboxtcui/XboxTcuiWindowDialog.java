package com.microsoft.xboxtcui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.NavigationManager.OnNavigatedListener;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.toolkit.ui.XLEButton;

public class XboxTcuiWindowDialog
  extends Dialog
{
  private DetachedCallback detachedCallback;
  private final XboxTcuiWindow xboxTcuiWindow;
  
  public XboxTcuiWindowDialog(Activity paramActivity, Class<? extends ScreenLayout> paramClass, ActivityParameters paramActivityParameters)
  {
    super(paramActivity, R.style.TcuiDialog);
    this.xboxTcuiWindow = new XboxTcuiWindow(paramActivity, paramClass, paramActivityParameters);
  }
  
  private void addCloseButton()
  {
    FrameLayout localFrameLayout = new FrameLayout(getContext());
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 5;
    XLEButton localXLEButton = new XLEButton(getContext());
    localXLEButton.setPadding(60, 0, 0, 0);
    localXLEButton.setBackgroundResource(R.drawable.common_button_background);
    localXLEButton.setText(R.string.ic_Close);
    localXLEButton.setTextColor(-1);
    localXLEButton.setTextSize(2, 14.0F);
    localXLEButton.setTypeFace("fonts/SegXboxSymbol.ttf");
    localXLEButton.setContentDescription(getContext().getResources().getString(R.string.TextInput_Confirm));
    localXLEButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          NavigationManager.getInstance().PopAllScreens();
          return;
        }
        catch (XLEException paramAnonymousView) {}
      }
    });
    localXLEButton.setOnKeyListener(NavigationManager.getInstance());
    localFrameLayout.addView(localXLEButton);
    addContentView(localFrameLayout, localLayoutParams);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setLayout(-1, -1);
    getWindow().setFlags(1024, 1024);
    this.xboxTcuiWindow.onCreate(paramBundle);
    setContentView(this.xboxTcuiWindow);
    addCloseButton();
    NavigationManager.getInstance().setOnNavigatedListener(new NavigationManager.OnNavigatedListener()
    {
      public void onPageNavigated(ScreenLayout paramAnonymousScreenLayout1, ScreenLayout paramAnonymousScreenLayout2)
      {
        if (paramAnonymousScreenLayout2 == null) {
          XboxTcuiWindowDialog.this.dismiss();
        }
      }
      
      public void onPageRestarted(ScreenLayout paramAnonymousScreenLayout) {}
    });
  }
  
  public void onDetachedFromWindow()
  {
    if (this.detachedCallback != null) {
      this.detachedCallback.onDetachedFromWindow();
    }
    super.onDetachedFromWindow();
  }
  
  public void onStart()
  {
    this.xboxTcuiWindow.onStart();
  }
  
  protected void onStop()
  {
    this.xboxTcuiWindow.onStop();
  }
  
  public void setDetachedCallback(DetachedCallback paramDetachedCallback)
  {
    this.detachedCallback = paramDetachedCallback;
  }
  
  public static abstract interface DetachedCallback
  {
    public abstract void onDetachedFromWindow();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xboxtcui\XboxTcuiWindowDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */