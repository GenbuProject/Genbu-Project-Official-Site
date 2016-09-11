package com.microsoft.xbox.xle.app.module;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public abstract class ScreenModuleLayout
  extends FrameLayout
{
  public ScreenModuleLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public abstract ViewModelBase getViewModel();
  
  public void invalidateView() {}
  
  public void onApplicationPause() {}
  
  public void onApplicationResume() {}
  
  public void onDestroy() {}
  
  public void onPause() {}
  
  public void onResume() {}
  
  public void onStart() {}
  
  public void onStop() {}
  
  protected void setContentView(int paramInt)
  {
    ((LayoutInflater)XboxTcuiSdk.getSystemService("layout_inflater")).inflate(paramInt, this, true);
  }
  
  public abstract void setViewModel(ViewModelBase paramViewModelBase);
  
  public abstract void updateView();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\module\ScreenModuleLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */