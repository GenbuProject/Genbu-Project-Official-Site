package com.microsoft.xbox.xle.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.microsoft.xbox.toolkit.BackgroundThreadWaitor;
import com.microsoft.xbox.toolkit.anim.MAAS;
import com.microsoft.xbox.toolkit.anim.MAAS.MAASAnimationType;
import com.microsoft.xbox.toolkit.anim.XLEAnimation;
import com.microsoft.xbox.toolkit.anim.XLEAnimationPackage;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.anim.XLEMAASAnimationPackageNavigationManager;
import com.microsoft.xbox.xle.ui.XLERootView;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.lang.ref.WeakReference;
import java.util.List;

public abstract class ActivityBase
  extends ScreenLayout
{
  private boolean showRightPane = true;
  private boolean showUtilityBar = true;
  protected ViewModelBase viewModel;
  
  public ActivityBase()
  {
    this(0);
  }
  
  public ActivityBase(int paramInt)
  {
    super(XboxTcuiSdk.getApplicationContext(), paramInt);
  }
  
  public ActivityBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private XLERootView getXLERootView()
  {
    if ((getChildAt(0) instanceof XLERootView)) {
      return (XLERootView)getChildAt(0);
    }
    return null;
  }
  
  public void adjustBottomMargin(int paramInt)
  {
    if (getXLERootView() != null) {
      getXLERootView().setBottomMargin(paramInt);
    }
  }
  
  protected int computeBottomMargin()
  {
    return 0;
  }
  
  protected boolean delayAppbarAnimation()
  {
    return false;
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if ((paramAccessibilityEvent.getEventType() == 8) && (getXLERootView() != null) && (getXLERootView().getContentDescription() != null))
    {
      paramAccessibilityEvent.getText().clear();
      paramAccessibilityEvent.getText().add(getXLERootView().getContentDescription());
      return true;
    }
    return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
  }
  
  public void forceRefresh()
  {
    if (this.viewModel != null) {
      this.viewModel.forceRefresh();
    }
  }
  
  public void forceUpdateViewImmediately()
  {
    if (this.viewModel != null) {
      this.viewModel.forceUpdateViewImmediately();
    }
  }
  
  protected abstract String getActivityName();
  
  public XLEAnimationPackage getAnimateIn(boolean paramBoolean)
  {
    Object localObject1 = getChildAt(0);
    if (localObject1 != null)
    {
      Object localObject2 = MAAS.getInstance().getAnimation("Screen");
      if (localObject2 != null)
      {
        localObject1 = ((XLEMAASAnimationPackageNavigationManager)localObject2).compile(MAAS.MAASAnimationType.ANIMATE_IN, paramBoolean, (View)localObject1);
        if (localObject1 != null)
        {
          localObject2 = new XLEAnimationPackage();
          ((XLEAnimationPackage)localObject2).add((XLEAnimation)localObject1);
          return (XLEAnimationPackage)localObject2;
        }
      }
    }
    return null;
  }
  
  public XLEAnimationPackage getAnimateOut(boolean paramBoolean)
  {
    Object localObject1 = getChildAt(0);
    if (localObject1 != null)
    {
      Object localObject2 = MAAS.getInstance().getAnimation("Screen");
      if (localObject2 != null)
      {
        localObject1 = ((XLEMAASAnimationPackageNavigationManager)localObject2).compile(MAAS.MAASAnimationType.ANIMATE_OUT, paramBoolean, (View)localObject1);
        if (localObject1 != null)
        {
          localObject2 = new XLEAnimationPackage();
          ((XLEAnimationPackage)localObject2).add((XLEAnimation)localObject1);
          return (XLEAnimationPackage)localObject2;
        }
      }
    }
    return null;
  }
  
  public String getName()
  {
    return getActivityName();
  }
  
  public String getRelativeId()
  {
    return null;
  }
  
  public boolean getShouldShowAppbar()
  {
    return false;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (this.viewModel != null) {
      this.viewModel.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onAnimateInCompleted()
  {
    if (this.viewModel != null)
    {
      final WeakReference localWeakReference = new WeakReference(this.viewModel);
      BackgroundThreadWaitor.getInstance().postRunnableAfterReady(new Runnable()
      {
        public void run()
        {
          ViewModelBase localViewModelBase = (ViewModelBase)localWeakReference.get();
          if (localViewModelBase != null) {
            localViewModelBase.forceUpdateViewImmediately();
          }
        }
      });
    }
    if (this.viewModel != null) {
      this.viewModel.onAnimateInCompleted();
    }
  }
  
  public void onAnimateInStarted()
  {
    if (this.viewModel != null) {
      this.viewModel.forceUpdateViewImmediately();
    }
  }
  
  public void onApplicationPause()
  {
    super.onApplicationPause();
    if (this.viewModel != null) {
      this.viewModel.onApplicationPause();
    }
  }
  
  public void onApplicationResume()
  {
    super.onApplicationResume();
    if (this.viewModel != null) {
      this.viewModel.onApplicationResume();
    }
  }
  
  public boolean onBackButtonPressed()
  {
    if (this.viewModel != null) {
      return this.viewModel.onBackButtonPressed();
    }
    return false;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.viewModel != null) {
      this.viewModel.onConfigurationChanged(paramConfiguration);
    }
  }
  
  public abstract void onCreateContentView();
  
  public void onDestroy()
  {
    if (this.viewModel != null) {
      this.viewModel.onDestroy();
    }
    this.viewModel = null;
    super.onDestroy();
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    clearDisappearingChildren();
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.viewModel != null) {
      this.viewModel.onPause();
    }
  }
  
  public void onRehydrate()
  {
    super.onRehydrate();
    if (this.viewModel != null) {
      this.viewModel.onRehydrate();
    }
  }
  
  public void onRehydrateOverride()
  {
    onCreateContentView();
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    if (this.viewModel != null) {
      this.viewModel.onRestoreInstanceState(paramBundle);
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.viewModel != null) {
      this.viewModel.onResume();
    }
  }
  
  public void onSetActive()
  {
    super.onSetActive();
    if (this.viewModel != null) {
      this.viewModel.onSetActive();
    }
  }
  
  public void onSetInactive()
  {
    super.onSetInactive();
    if (this.viewModel != null) {
      this.viewModel.onSetInactive();
    }
  }
  
  public void onStart()
  {
    if (!getIsStarted())
    {
      super.onStart();
      if (this.viewModel != null) {
        this.viewModel.onStart();
      }
      if (this.viewModel != null) {
        this.viewModel.load();
      }
    }
    if (!delayAppbarAnimation()) {
      adjustBottomMargin(computeBottomMargin());
    }
  }
  
  public void onStop()
  {
    if (getIsStarted())
    {
      super.onStop();
      if (this.viewModel != null) {
        this.viewModel.onSetInactive();
      }
      if (this.viewModel != null) {
        this.viewModel.onStop();
      }
    }
  }
  
  public void onTombstone()
  {
    if (this.viewModel != null) {
      this.viewModel.onTombstone();
    }
    super.onTombstone();
  }
  
  public void removeBottomMargin()
  {
    if (getXLERootView() != null) {
      getXLERootView().setBottomMargin(0);
    }
  }
  
  public void resetBottomMargin()
  {
    if (getXLERootView() != null) {
      adjustBottomMargin(computeBottomMargin());
    }
  }
  
  public void setHeaderName(String paramString) {}
  
  public void setScreenState(int paramInt)
  {
    if (this.viewModel != null) {
      this.viewModel.setScreenState(paramInt);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\ActivityBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */