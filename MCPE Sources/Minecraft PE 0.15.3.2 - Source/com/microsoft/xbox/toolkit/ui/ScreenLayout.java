package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLERValueHelper;
import com.microsoft.xbox.toolkit.anim.XLEAnimationPackage;
import com.microsoft.xbox.toolkit.system.SystemUtil;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ScreenLayout
  extends FrameLayout
{
  private static ArrayList<View> badList = new ArrayList();
  private boolean allEventsEnabled = true;
  private boolean drawerEnabled = true;
  private boolean isActive;
  private boolean isEditable = false;
  private boolean isReady;
  private boolean isStarted;
  protected boolean isTombstoned;
  private Runnable onLayoutChangedListener = null;
  private int orientation;
  private int screenPercent = 100;
  
  public ScreenLayout()
  {
    this(XboxTcuiSdk.getApplicationContext());
  }
  
  public ScreenLayout(Context paramContext)
  {
    this(paramContext, 0);
  }
  
  public ScreenLayout(Context paramContext, int paramInt)
  {
    super(paramContext);
    Initialize(paramInt);
  }
  
  public ScreenLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("ScreenLayout"));
    if (paramContext.hasValue(XLERValueHelper.getStyleableRValue("ScreenLayout_screenDIPs"))) {}
    for (this.screenPercent = ((int)(paramContext.getDimensionPixelSize(XLERValueHelper.getStyleableRValue("ScreenLayout_screenDIPs"), SystemUtil.getScreenWidth()) / SystemUtil.getScreenWidth() * 100.0F));; this.screenPercent = paramContext.getInt(XLERValueHelper.getStyleableRValue("ScreenLayout_screenPercent"), -2))
    {
      paramContext.recycle();
      Initialize(7);
      return;
    }
  }
  
  public static void addViewThatCausesAndroidLeaks(View paramView)
  {
    badList.add(paramView);
  }
  
  private void removeAllViewsAndWorkaroundAndroidLeaks()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      removeAllViews();
      Iterator localIterator = badList.iterator();
      while (localIterator.hasNext()) {
        removeViewAndWorkaroundAndroidLeaks((View)localIterator.next());
      }
    }
    badList.clear();
  }
  
  public static void removeViewAndWorkaroundAndroidLeaks(View paramView)
  {
    boolean bool2 = true;
    if (paramView != null)
    {
      ViewParent localViewParent = paramView.getParent();
      if ((localViewParent instanceof ViewGroup))
      {
        ((ViewGroup)localViewParent).removeAllViews();
        if (paramView.getParent() != null) {
          break label72;
        }
        bool1 = true;
        XLEAssert.assertTrue(bool1);
      }
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        paramView.removeAllViews();
        paramView.destroyDrawingCache();
        if (paramView.getChildCount() != 0) {
          break label77;
        }
      }
    }
    label72:
    label77:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      XLEAssert.assertTrue(bool1);
      return;
      bool1 = false;
      break;
    }
  }
  
  protected void Initialize(int paramInt)
  {
    this.isReady = false;
    this.isActive = false;
    this.isStarted = false;
    this.orientation = paramInt;
  }
  
  public void adjustBottomMargin(int paramInt) {}
  
  public abstract void forceRefresh();
  
  public abstract void forceUpdateViewImmediately();
  
  public XLEAnimationPackage getAnimateIn(boolean paramBoolean)
  {
    return null;
  }
  
  public XLEAnimationPackage getAnimateOut(boolean paramBoolean)
  {
    return null;
  }
  
  public boolean getCanAutoLaunch()
  {
    return !this.isEditable;
  }
  
  public String getContent()
  {
    return null;
  }
  
  public boolean getIsActive()
  {
    return this.isActive;
  }
  
  public boolean getIsEditable()
  {
    return this.isEditable;
  }
  
  public boolean getIsReady()
  {
    return this.isReady;
  }
  
  public boolean getIsStarted()
  {
    return this.isStarted;
  }
  
  public boolean getIsTombstoned()
  {
    return this.isTombstoned;
  }
  
  public String getLocalClassName()
  {
    return getClass().getName();
  }
  
  public abstract String getName();
  
  public String getRelativeId()
  {
    return null;
  }
  
  public int getScreenPercent()
  {
    return this.screenPercent;
  }
  
  public boolean getShouldShowAppbar()
  {
    return !this.isEditable;
  }
  
  public Boolean getTrackPage()
  {
    return Boolean.valueOf(true);
  }
  
  public boolean isAllEventsEnabled()
  {
    return this.allEventsEnabled;
  }
  
  public boolean isAnimateOnPop()
  {
    return true;
  }
  
  public boolean isAnimateOnPush()
  {
    return true;
  }
  
  public boolean isDrawerEnabled()
  {
    return this.drawerEnabled;
  }
  
  public boolean isKeepPreviousScreen()
  {
    return false;
  }
  
  public void leaveScreen(Runnable paramRunnable)
  {
    paramRunnable.run();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public abstract void onAnimateInCompleted();
  
  public abstract void onAnimateInStarted();
  
  public void onApplicationPause() {}
  
  public void onApplicationResume() {}
  
  public abstract boolean onBackButtonPressed();
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onCreate() {}
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {}
  
  public void onDestroy()
  {
    removeAllViewsAndWorkaroundAndroidLeaks();
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    if (this.allEventsEnabled) {
      return super.onHoverEvent(paramMotionEvent);
    }
    return true;
  }
  
  public boolean onInterceptHoverEvent(MotionEvent paramMotionEvent)
  {
    if (this.allEventsEnabled) {
      return super.onInterceptHoverEvent(paramMotionEvent);
    }
    return true;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.allEventsEnabled) {
      return super.onInterceptTouchEvent(paramMotionEvent);
    }
    return true;
  }
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.onLayoutChangedListener != null) {
      this.onLayoutChangedListener.run();
    }
  }
  
  public void onPause()
  {
    this.isReady = false;
  }
  
  public void onRehydrate()
  {
    this.isTombstoned = false;
    onRehydrateOverride();
  }
  
  public abstract void onRehydrateOverride();
  
  public void onRestart() {}
  
  public void onRestoreInstanceState(Bundle paramBundle) {}
  
  public void onResume()
  {
    this.isReady = true;
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  public void onSetActive()
  {
    this.isActive = true;
  }
  
  public void onSetInactive()
  {
    this.isActive = false;
  }
  
  public void onStart()
  {
    this.isStarted = true;
  }
  
  public void onStop()
  {
    this.isStarted = false;
  }
  
  public void onTombstone()
  {
    this.isTombstoned = true;
    removeAllViewsAndWorkaroundAndroidLeaks();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.allEventsEnabled) {
      return super.onTouchEvent(paramMotionEvent);
    }
    return true;
  }
  
  public void removeBottomMargin() {}
  
  public void resetBottomMargin() {}
  
  public void setAllEventsEnabled(boolean paramBoolean)
  {
    this.allEventsEnabled = paramBoolean;
  }
  
  public void setContentView(int paramInt)
  {
    LayoutInflater.from(getContext()).inflate(paramInt, this, true);
  }
  
  public void setDrawerEnabled(boolean paramBoolean)
  {
    this.drawerEnabled = paramBoolean;
  }
  
  public void setIsEditable(boolean paramBoolean)
  {
    this.isEditable = paramBoolean;
  }
  
  public void setOnLayoutChangedListener(Runnable paramRunnable)
  {
    this.onLayoutChangedListener = paramRunnable;
  }
  
  public ScreenLayout setScreenPercent(int paramInt)
  {
    this.screenPercent = paramInt;
    return this;
  }
  
  public void setScreenState(int paramInt) {}
  
  public View xleFindViewId(int paramInt)
  {
    return findViewById(paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\ScreenLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */