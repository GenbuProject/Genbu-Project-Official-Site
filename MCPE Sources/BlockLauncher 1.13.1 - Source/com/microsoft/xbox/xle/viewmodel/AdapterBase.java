package com.microsoft.xbox.xle.viewmodel;

import android.app.Activity;
import android.view.View;
import com.microsoft.xbox.toolkit.DialogManager;
import com.microsoft.xbox.toolkit.XLEAllocationTracker;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.anim.XLEAnimation;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xbox.xle.app.module.ScreenModuleLayout;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class AdapterBase
{
  public static String ALLOCATION_TAG = "ADAPTERBASE";
  private static HashMap<String, Integer> adapterCounter = new HashMap();
  protected boolean isActive = false;
  private boolean isStarted = false;
  private ArrayList<ScreenModuleLayout> screenModules = new ArrayList();
  private final ViewModelBase viewModel;
  
  public AdapterBase()
  {
    this(null);
  }
  
  public AdapterBase(ViewModelBase paramViewModelBase)
  {
    this.viewModel = paramViewModelBase;
    XLEAllocationTracker.getInstance().debugIncrement(ALLOCATION_TAG, getClass().getSimpleName());
    XLEAllocationTracker.getInstance().debugPrintOverallocated(ALLOCATION_TAG);
  }
  
  public void finalize()
  {
    XLEAllocationTracker.getInstance().debugDecrement(ALLOCATION_TAG, getClass().getSimpleName());
    XLEAllocationTracker.getInstance().debugPrintOverallocated(ALLOCATION_TAG);
  }
  
  protected void findAndInitializeModuleById(int paramInt, ViewModelBase paramViewModelBase)
  {
    Object localObject = findViewById(paramInt);
    if ((localObject != null) && ((localObject instanceof ScreenModuleLayout)))
    {
      localObject = (ScreenModuleLayout)findViewById(paramInt);
      ((ScreenModuleLayout)localObject).setViewModel(paramViewModelBase);
      this.screenModules.add(localObject);
    }
  }
  
  public View findViewById(int paramInt)
  {
    View localView = null;
    if (this.viewModel != null) {
      localView = this.viewModel.findViewById(paramInt);
    }
    if (localView != null) {
      return localView;
    }
    return XboxTcuiSdk.getActivity().findViewById(paramInt);
  }
  
  public void forceUpdateViewImmediately()
  {
    XLEAssert.assertIsUIThread();
    updateViewOverride();
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).updateView();
    }
  }
  
  public ArrayList<XLEAnimation> getAnimateIn(boolean paramBoolean)
  {
    return null;
  }
  
  public ArrayList<XLEAnimation> getAnimateOut(boolean paramBoolean)
  {
    return null;
  }
  
  protected boolean getIsStarted()
  {
    return this.isStarted;
  }
  
  public void invalidateView()
  {
    if (!NavigationManager.getInstance().isAnimating())
    {
      invalidateViewOverride();
      Iterator localIterator = this.screenModules.iterator();
      while (localIterator.hasNext()) {
        ((ScreenModuleLayout)localIterator.next()).invalidateView();
      }
    }
  }
  
  protected void invalidateViewOverride() {}
  
  public void onAnimateInCompleted() {}
  
  protected void onAppBarButtonsAdded() {}
  
  @Deprecated
  protected void onAppBarUpdated() {}
  
  public void onApplicationPause()
  {
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).onApplicationPause();
    }
  }
  
  public void onApplicationResume()
  {
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).onApplicationResume();
    }
  }
  
  public void onDestroy()
  {
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).onDestroy();
    }
    this.screenModules.clear();
  }
  
  public void onPause()
  {
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).onPause();
    }
  }
  
  public void onResume()
  {
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).onResume();
    }
  }
  
  public void onSetActive()
  {
    this.isActive = true;
    if ((XboxTcuiSdk.getActivity() != null) && (this.isStarted)) {
      updateView();
    }
  }
  
  public void onSetInactive()
  {
    this.isActive = false;
  }
  
  public void onStart()
  {
    this.isStarted = true;
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).onStart();
    }
  }
  
  public void onStop()
  {
    this.isStarted = false;
    Iterator localIterator = this.screenModules.iterator();
    while (localIterator.hasNext()) {
      ((ScreenModuleLayout)localIterator.next()).onStop();
    }
  }
  
  protected void setBlocking(boolean paramBoolean, String paramString)
  {
    DialogManager.getInstance().setBlocking(paramBoolean, paramString);
  }
  
  protected void setCancelableBlocking(boolean paramBoolean, String paramString, Runnable paramRunnable)
  {
    DialogManager.getInstance().setCancelableBlocking(paramBoolean, paramString, paramRunnable);
  }
  
  public void setScreenState(int paramInt) {}
  
  protected void showKeyboard(View paramView, int paramInt)
  {
    XLEUtil.showKeyboard(paramView, paramInt);
  }
  
  public void updateView()
  {
    if (!NavigationManager.getInstance().isAnimating())
    {
      updateViewOverride();
      Iterator localIterator = this.screenModules.iterator();
      while (localIterator.hasNext()) {
        ((ScreenModuleLayout)localIterator.next()).updateView();
      }
    }
  }
  
  protected abstract void updateViewOverride();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\viewmodel\AdapterBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */